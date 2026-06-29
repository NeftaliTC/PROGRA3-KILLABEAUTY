using System.Net.Http.Json;
using System.Text.Json;
using System.Text.Json.Serialization;
using BlazorAppKillaBeauty.ClienteREST.Models;

namespace BlazorAppKillaBeauty.Services
{
    public class ProductoService
    {
        private readonly HttpClient http;
        private readonly JsonSerializerOptions jsonOptions = new()
        {
            PropertyNameCaseInsensitive = true,
            DefaultIgnoreCondition = JsonIgnoreCondition.WhenWritingNull
        };

        public ProductoService(IHttpClientFactory httpClientFactory)
        {
            http = httpClientFactory.CreateClient("KillaApi");
        }

        private List<Producto>? catalogoCache;

        public async Task<List<Producto>> ObtenerCatalogoAsync()
        {
            if (catalogoCache != null)
                return catalogoCache;

            catalogoCache = await http.GetFromJsonAsync<List<Producto>>("productos/catalogo")
                           ?? new List<Producto>();

            return catalogoCache;
        }

        public async Task<List<Producto>> ObtenerPopularesAsync()
        {
            var productos = await ObtenerCatalogoAsync();
            return productos.Where(p => p.EsPopular).ToList();
        }

        public async Task<List<ProductoApi>> ObtenerTodosApiAsync()
        {
            return await GetAsync<List<ProductoApi>>("productos") ?? new List<ProductoApi>();
        }

        public async Task<ProductoApi?> ObtenerPorIdAsync(int id)
        {
            return await GetAsync<ProductoApi>($"productos/{id}");
        }

        public async Task<Producto?> ObtenerCatalogoPorIdAsync(int id)
        {
            var productos = await ObtenerCatalogoAsync();
            return productos.FirstOrDefault(p => p.Id == id);
        }

        public async Task<List<ResenaProducto>> ObtenerResenasAsync(int productoId)
        {
            return await GetAsync<List<ResenaProducto>>($"productos/{productoId}/resenas") ?? new List<ResenaProducto>();
        }

        public async Task<List<Categoria>> ObtenerCategoriasAsync()
        {
            return await GetAsync<List<Categoria>>("categorias") ?? new List<Categoria>();
        }

        public async Task<List<Subcategoria>> ObtenerSubcategoriasAsync()
        {
            return await GetAsync<List<Subcategoria>>("categorias/subcategorias") ?? new List<Subcategoria>();
        }

        public async Task<List<Marca>> ObtenerMarcasAsync()
        {
            return await GetAsync<List<Marca>>("marcas") ?? new List<Marca>();
        }

        public async Task<ProductoApi> CrearAsync(ProductoApi producto)
        {
            using var response = await http.PostAsJsonAsync("productos", ProductoRequest.From(producto), jsonOptions);
            await EnsureSuccessAsync(response);
            return await response.Content.ReadFromJsonAsync<ProductoApi>(jsonOptions) ?? producto;
        }

        public async Task<ProductoApi> ActualizarAsync(ProductoApi producto)
        {
            using var response = await http.PutAsJsonAsync($"productos/{producto.Id}", ProductoRequest.From(producto), jsonOptions);
            await EnsureSuccessAsync(response);
            return await response.Content.ReadFromJsonAsync<ProductoApi>(jsonOptions) ?? producto;
        }

        public async Task DarDeBajaAsync(ProductoApi producto)
        {
            producto.Activo = false;
            producto.Disponible = false;
            await ActualizarAsync(producto);
        }

        private async Task<T?> GetAsync<T>(string url)
        {
            using var response = await http.GetAsync(url);

            if (response.StatusCode == System.Net.HttpStatusCode.NotFound)
            {
                return default;
            }

            await EnsureSuccessAsync(response);
            return await response.Content.ReadFromJsonAsync<T>(jsonOptions);
        }

        private static async Task EnsureSuccessAsync(HttpResponseMessage response)
        {
            if (response.IsSuccessStatusCode)
            {
                return;
            }

            var body = await response.Content.ReadAsStringAsync();
            throw new InvalidOperationException(
                string.IsNullOrWhiteSpace(body)
                    ? $"Error REST {(int)response.StatusCode} {response.ReasonPhrase}"
                    : body);
        }

        public async Task<bool> RegistrarResenaAsync(int productoId, ResenaProducto resena)
        {
            using var response = await http.PostAsJsonAsync($"productos/{productoId}/resenas", resena, jsonOptions);
            return response.IsSuccessStatusCode;
        }
        private class ProductoRequest
        {
            [JsonPropertyName("id")]
            public int Id { get; set; }

            [JsonPropertyName("nombre")]
            public string Nombre { get; set; } = "";

            [JsonPropertyName("precioBase")]
            public decimal PrecioBase { get; set; }

            [JsonPropertyName("stock")]
            public int Stock { get; set; }

            [JsonPropertyName("disponible")]
            public bool Disponible { get; set; }

            [JsonPropertyName("promocion")]
            public bool Promocion { get; set; }

            [JsonPropertyName("activo")]
            public bool Activo { get; set; }

            [JsonPropertyName("marca")]
            public IdRef Marca { get; set; } = new();

            [JsonPropertyName("subcategoria")]
            public IdRef Subcategoria { get; set; } = new();

            public static ProductoRequest From(ProductoApi producto)
            {
                return new ProductoRequest
                {
                    Id = producto.Id,
                    Nombre = producto.Nombre?.Trim() ?? "",
                    PrecioBase = producto.PrecioBase,
                    Stock = producto.Stock,
                    Disponible = producto.Disponible,
                    Promocion = producto.Promocion,
                    Activo = producto.Activo,
                    Marca = new IdRef { Id = producto.Marca?.Id ?? 0 },
                    Subcategoria = new IdRef { Id = producto.Subcategoria?.Id ?? 0 }
                };
            }
        }

        private class IdRef
        {
            [JsonPropertyName("id")]
            public int Id { get; set; }
        }
    }
}
