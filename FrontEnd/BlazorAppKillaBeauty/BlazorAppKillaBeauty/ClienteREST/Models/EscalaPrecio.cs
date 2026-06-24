using System.Text.Json.Serialization;

namespace BlazorAppKillaBeauty.ClienteREST.Models
{
    public class EscalaPrecio
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("cantidadMinima")]
        public int CantidadMinima { get; set; }

        [JsonPropertyName("precioUnitario")]
        public decimal PrecioUnitario { get; set; }

        [JsonIgnore]
        public Producto? Producto { get; set; }
    }
}
