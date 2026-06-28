using System.Text.Json.Serialization;

namespace BlazorAppKillaBeauty.ClienteREST.Models
{
    public class Producto
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("nombre")]
        public string Nombre { get; set; } = "";

        [JsonPropertyName("precioBase")]
        public decimal PrecioBase { get; set; }

        [JsonPropertyName("imagenes")]
        public List<ImagenProducto> Imagenes { get; set; } = new();

        [JsonIgnore]
        public string ImagenPrincipal =>
            Imagenes.FirstOrDefault(i => i.Principal)?.Url
            ?? Imagenes.FirstOrDefault()?.Url
            ?? "/Images/product-placeholder.png";

        [JsonPropertyName("categoria")]
        public string Categoria { get; set; } = "";

        [JsonPropertyName("subcategoria")]
        public string Subcategoria { get; set; } = "";

        [JsonPropertyName("marca")]
        public string Marca { get; set; } = "";

        [JsonPropertyName("calificacion")]
        public int Calificacion { get; set; }

        [JsonPropertyName("esPopular")]
        public bool EsPopular { get; set; }

        [JsonPropertyName("stock")]
        public int Stock { get; set; }

    }

    public class ProductoApi
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
        public bool Disponible { get; set; } = true;

        [JsonPropertyName("promocion")]
        public bool Promocion { get; set; }

        [JsonPropertyName("marca")]
        public Marca Marca { get; set; } = new();

        [JsonPropertyName("subcategoria")]
        public Subcategoria Subcategoria { get; set; } = new();

        [JsonPropertyName("activo")]
        public bool Activo { get; set; } = true;
    }

    public class Categoria
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("descripcion")]
        public string Descripcion { get; set; } = "";

        [JsonPropertyName("activo")]
        public bool Activo { get; set; } = true;
    }

    public class Subcategoria
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("descripcion")]
        public string Descripcion { get; set; } = "";

        [JsonPropertyName("activo")]
        public bool Activo { get; set; } = true;

        [JsonPropertyName("categoria")]
        public Categoria Categoria { get; set; } = new();
    }

    public class ResenaProducto
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("calificacion")]
        public int Calificacion { get; set; }

        [JsonPropertyName("titulo")]
        public string Titulo { get; set; } = "";

        [JsonPropertyName("comentario")]
        public string Comentario { get; set; } = "";

        [JsonPropertyName("fecha")]
        public DateTime? Fecha { get; set; }
    }
}
