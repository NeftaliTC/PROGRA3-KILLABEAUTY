namespace BlazorAppKillaBeauty.ClienteREST.Models
{
    public class Marca
    {
        public int Id { get; set; }
        public string Descripcion { get; set; } = "";

        // Ahora Pais usa el enum que acabamos de crear arriba
        public Pais Pais { get; set; }

        public bool Activo { get; set; }
    }
}
