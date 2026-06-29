namespace BlazorAppKillaBeauty.ClienteREST.Models
{
    public class ProductoCarrito
    {
        public int Id { get; set; }
        public string Nombre { get; set; } = "";
        public decimal PrecioBase { get; set; }
        public string Imagen { get; set; } = "";
        public int Stock { get; set; }
        public Marca? Marca { get; set; }
        public Categoria? Categoria { get; set; }
        public Subcategoria? Subcategoria { get; set; }
    }
}
