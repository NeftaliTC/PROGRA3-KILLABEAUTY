namespace BlazorAppKillaBeauty.ClienteREST.Models
{
    public class EscalaPrecio
    {
        public int Id { get; set; }
        public int CantidadMinima { get; set; }
        public decimal PrecioUnitario { get; set; }

        public Producto? Producto { get; set; }
    }
}
