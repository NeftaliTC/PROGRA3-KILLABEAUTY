using System.Net.Http.Json;
using System.Text.Json;
using System.Text.Json.Serialization;
using BlazorAppKillaBeauty.ClienteREST.Models;

namespace BlazorAppKillaBeauty.Services
{
    public class CartService
    {
        private readonly HttpClient http;

        private readonly JsonSerializerOptions jsonOptions = new()
        {
            PropertyNameCaseInsensitive = true,
            DefaultIgnoreCondition = JsonIgnoreCondition.WhenWritingNull
        };

        public List<CartItem> Productos { get; private set; } = new();

        public int CantidadTotal => Productos.Sum(p => p.Cantidad);

        public decimal Total =>Productos.Sum(p => p.Cantidad * (decimal)p.Producto.PrecioBase);

        public decimal DescuentoTotal { get; private set; } = 0;

        public CartService(IHttpClientFactory httpClientFactory)
        {
            http = httpClientFactory.CreateClient("KillaApi");
        }

        public async Task CargarCarritoAsync(int idUsuario)
        {
            var carritos = await GetAsync<List<Carrito>>($"carrito/usuario/{idUsuario}");

            var carritoActual = carritos?
                .FirstOrDefault(c => c.Estado == "ACTIVO");

            Productos = carritoActual?.DetalleCarritoList ?? new();
        }

        public async Task<Carrito?> ObtenerPorIdAsync(int id)
        {
            return await GetAsync<Carrito>($"carrito/{id}");
        }

        public async Task<Carrito> CrearAsync(Carrito carrito)
        {
            using var response = await http.PostAsJsonAsync("carrito", carrito, jsonOptions);
            await EnsureSuccessAsync(response);

            return await response.Content.ReadFromJsonAsync<Carrito>(jsonOptions)
                   ?? carrito;
        }

        public async Task<Carrito> ActualizarAsync(Carrito carrito)
        {
            using var response = await http.PutAsJsonAsync($"carrito/{carrito.Id}", carrito, jsonOptions);
            await EnsureSuccessAsync(response);

            return await response.Content.ReadFromJsonAsync<Carrito>(jsonOptions)
                   ?? carrito;
        }

        public async Task EliminarAsync(int id)
        {
            using var response = await http.DeleteAsync($"carrito/{id}");
            await EnsureSuccessAsync(response);
        }

        public async Task VaciarCarritoAsync(int idUsuario)
        {
            var carritos = await GetAsync<List<Carrito>>($"carrito/usuario/{idUsuario}");
            var carritoActual = carritos?.FirstOrDefault();

            if (carritoActual != null)
                await EliminarAsync(carritoActual.Id);

            Productos.Clear();
        }

        private async Task<T?> GetAsync<T>(string url)
        {
            using var response = await http.GetAsync(url);

            if (response.StatusCode == System.Net.HttpStatusCode.NotFound)
                return default;

            await EnsureSuccessAsync(response);
            return await response.Content.ReadFromJsonAsync<T>(jsonOptions);
        }

        private static async Task EnsureSuccessAsync(HttpResponseMessage response)
        {
            if (response.IsSuccessStatusCode)
                return;

            var body = await response.Content.ReadAsStringAsync();

            throw new InvalidOperationException(
                string.IsNullOrWhiteSpace(body)
                    ? $"Error REST {(int)response.StatusCode} {response.ReasonPhrase}"
                    : body);
        }

        private ProductoCarrito ConvertirAProductoCarrito(Producto producto)
        {
            return new ProductoCarrito
            {
                Id = producto.Id,
                Nombre = producto.Nombre,
                Imagen = producto.ImagenPrincipal,
                PrecioBase = producto.PrecioBase
            };
        }

        public async Task AgregarProductoAsync(Producto producto, int idUsuario, int cantidad)
        {
            await AgregarProductoPorIdAsync(producto.Id, idUsuario, cantidad);

            await CargarCarritoAsync(idUsuario);
        }

        public void AgregarProductoLocal(Producto producto, int cantidad)
        {
            var existente = Productos
                .FirstOrDefault(p => p.Producto.Id == producto.Id);

            if (existente != null)
            {
                existente.Cantidad += cantidad;
            }
            else
            {
                Productos.Add(new CartItem
                {
                    Producto = ConvertirAProductoCarrito(producto),
                    Cantidad = cantidad
                });
            }
        }

        private async Task<Carrito> ObtenerOCrearCarritoUsuarioAsync(int idUsuario)
        {
            var carritos = await GetAsync<List<Carrito>>($"carrito/usuario/{idUsuario}");

            var carritoActual = carritos?
                .FirstOrDefault(c => c.Estado == "ACTIVO");

            if (carritoActual != null)
                return carritoActual;

            var request = new
            {
                usuario = new
                {
                    id = idUsuario
                },
                estado = "ACTIVO"
            };

            using var response = await http.PostAsJsonAsync("carrito", request, jsonOptions);
            await EnsureSuccessAsync(response);

            return await response.Content.ReadFromJsonAsync<Carrito>(jsonOptions)
                   ?? throw new Exception("No se pudo crear el carrito.");
        }

        public async Task AgregarDetalleCarritoAsync(int idProducto, int idCarrito, int cantidad)
        {
            var request = new
            {
                cantidad = cantidad,
                producto = new
                {
                    id = idProducto
                },
                carritoDeCompras = new
                {
                    id = idCarrito
                }
            };

            var response = await http.PostAsJsonAsync(
                "detalle-carrito/agregar",
                request,
                jsonOptions);

            if (!response.IsSuccessStatusCode)
            {
                var error = await response.Content.ReadAsStringAsync();
                throw new Exception($"Error detalle carrito: {(int)response.StatusCode} - {error}");
            }
        }

        public async Task AgregarProductoPorIdAsync(int idProducto, int idUsuario, int cantidad)
        {
            var carrito = await ObtenerOCrearCarritoUsuarioAsync(idUsuario);

            await AgregarDetalleCarritoAsync(
                idProducto,
                carrito.Id,
                cantidad);
        }

        public async Task MigrarCarritoLocalAUsuarioAsync(int idUsuario)
        {
            var productosLocales = Productos
                .Where(d => d.Producto != null)
                .Select(d => new
                {
                    IdProducto = d.Producto!.Id,
                    Cantidad = d.Cantidad
                })
                .ToList();

            if (productosLocales.Count == 0)
            {
                await CargarCarritoAsync(idUsuario);
                return;
            }

            foreach (var item in productosLocales)
            {
                await AgregarProductoPorIdAsync(
                    item.IdProducto,
                    idUsuario,
                    item.Cantidad
                );
            }

            await CargarCarritoAsync(idUsuario);
        }

        public async Task AumentarCantidadAsync(int detalleId)
        {
            var detalle = Productos.FirstOrDefault(d => d.Id == detalleId);

            if (detalle == null)
                return;

            var nuevaCantidad = detalle.Cantidad + 1;

            using var response = await http.PutAsync(
                $"detalle-carrito/{detalleId}/cantidad/{nuevaCantidad}",
                null);

            await EnsureSuccessAsync(response);

            detalle.Cantidad = nuevaCantidad;
        }

        public async Task DisminuirCantidadAsync(int detalleId)
        {
            var detalle = Productos.FirstOrDefault(d => d.Id == detalleId);

            if (detalle == null || detalle.Cantidad <= 1)
                return;

            var nuevaCantidad = detalle.Cantidad - 1;

            using var response = await http.PutAsync(
                $"detalle-carrito/{detalleId}/cantidad/{nuevaCantidad}",
                null);

            await EnsureSuccessAsync(response);

            detalle.Cantidad = nuevaCantidad;
        }

        public async Task EliminarDetalleAsync(int detalleId)
        {
            using var response = await http.DeleteAsync($"detalle-carrito/{detalleId}");
            await EnsureSuccessAsync(response);

            var detalle = Productos.FirstOrDefault(d => d.Id == detalleId);

            if (detalle != null)
                Productos.Remove(detalle);
        }
    }
}