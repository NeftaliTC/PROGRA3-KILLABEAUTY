using System.Text.Json.Serialization;

namespace BlazorAppKillaBeauty.ClienteREST.Models
{
    public class Usuario
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("nombre")]
        public string Nombre { get; set; } = "";

        [JsonPropertyName("apellido")]
        public string Apellido { get; set; } = "";

        [JsonPropertyName("correo")]
        public string Correo { get; set; } = "";

        [JsonPropertyName("telefono")]
        public string Telefono { get; set; } = "";

        [JsonPropertyName("activo")]
        public bool Activo { get; set; }
    }
}
