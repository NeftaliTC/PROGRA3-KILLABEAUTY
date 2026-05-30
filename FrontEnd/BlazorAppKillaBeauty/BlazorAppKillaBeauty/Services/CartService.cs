namespace BlazorAppKillaBeauty.Services
{
    public class CartService
    {
        private List<CartItem> productos = new();

        public IReadOnlyList<CartItem> Productos => productos;

        public int CantidadTotal =>
            productos.Sum(p => p.Cantidad);

        public decimal Total =>
            productos.Sum(p => p.Subtotal);

        public decimal DescuentoTotal =>
            productos.Sum(p => (p.PrecioOriginal - p.PrecioUnitario) * p.Cantidad);

        public void AgregarProducto(string nombre, decimal precio, string imagen)
        {
            var productoExistente =
                productos.FirstOrDefault(p => p.Nombre == nombre);

            if (productoExistente != null)
            {
                productoExistente.Cantidad++;
                productoExistente.ActualizarPrecio();
            }
            else
            {
                productos.Add(new CartItem
                {
                    Nombre = nombre,
                    PrecioOriginal = precio,
                    PrecioUnitario = precio,
                    Imagen = imagen,
                    Cantidad = 1
                });
            }
        }

        public void AumentarCantidad(string nombre)
        {
            var producto =
                productos.FirstOrDefault(p => p.Nombre == nombre);

            if (producto != null)
            {
                producto.Cantidad++;
                producto.ActualizarPrecio();
            }
        }

        public void DisminuirCantidad(string nombre)
        {
            var producto =
                productos.FirstOrDefault(p => p.Nombre == nombre);

            if (producto != null && producto.Cantidad > 1)
            {
                producto.Cantidad--;
                producto.ActualizarPrecio();
            }
        }

        public void EliminarProducto(string nombre)
        {
            var producto =
                productos.FirstOrDefault(p => p.Nombre == nombre);

            if (producto != null)
            {
                productos.Remove(producto);
            }
        }

        public void VaciarCarrito()
        {
            productos.Clear();
        }
    }

    public class CartItem
    {
        public string Nombre { get; set; } = "";
        public string Imagen { get; set; } = "";

        public decimal PrecioOriginal { get; set; }
        public decimal PrecioUnitario { get; set; }

        public int Cantidad { get; set; }

        public decimal Subtotal =>
            PrecioUnitario * Cantidad;

        public string TipoCompra
        {
            get
            {
                if (Cantidad >= 24)
                    return "Mayorista";

                if (Cantidad >= 6)
                    return "Media Docena";

                return "Unidad";
            }
        }

        public void ActualizarPrecio()
        {
            if (Cantidad >= 24)
            {
                PrecioUnitario = PrecioOriginal - 20;
            }
            else if (Cantidad >= 6)
            {
                PrecioUnitario = PrecioOriginal - 10;
            }
            else
            {
                PrecioUnitario = PrecioOriginal;
            }
        }
    }
}
