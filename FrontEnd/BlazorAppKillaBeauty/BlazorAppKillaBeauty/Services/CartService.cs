namespace BlazorAppKillaBeauty.Services
{
    public class CartService
    {
        private List<CartItem> productos = new();

        public IReadOnlyList<CartItem> Productos => productos;

        public int CantidadTotal =>
            productos.Sum(p => p.Cantidad);

        public decimal Total =>
            productos.Sum(p => p.Precio * p.Cantidad);

        public void AgregarProducto(string nombre, decimal precio, string imagen)
        {
            var productoExistente =
                productos.FirstOrDefault(p => p.Nombre == nombre);

            if (productoExistente != null)
            {
                productoExistente.Cantidad++;
            }
            else
            {
                productos.Add(new CartItem
                {
                    Nombre = nombre,
                    Precio = precio,
                    Imagen = imagen,
                    Cantidad = 1
                });
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
        public decimal Precio { get; set; }
        public string Imagen { get; set; } = "";
        public int Cantidad { get; set; }
    }
}
