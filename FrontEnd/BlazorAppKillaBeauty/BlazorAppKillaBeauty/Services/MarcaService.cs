namespace BlazorAppKillaBeauty.Services
{
    public class MarcaService
    {
        private readonly HttpClient _httpClient;

        
        public MarcaService(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }


        // GET: Listar todas las marcas de Java
        // URL: http://localhost:8080/KillaREST-1.0-SNAPSHOT/services/marcas

        public async Task<List<Marca>> ListarTodasAsync()
        {
            try
            {
                // Convierte automáticamente el JSON que manda tu Java en una Lista de C#
                var marcas = await _httpClient.GetFromJsonAsync<List<Marca>>("marcas");
                return marcas ?? new List<Marca>();
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error al listar marcas: {ex.Message}");
                return new List<Marca>(); // Retorna lista vacía para que la interfaz no colapse
            }
        }


        // POST: Registrar una nueva marca en Java
        // URL: http://localhost:8080/KillaREST-1.0-SNAPSHOT/services/marcas

        public async Task<Marca?> CrearAsync(Marca nuevaMarca)
        {
            try
            {
                // Envía el objeto C# serializado en JSON hacia el @POST 
                var response = await _httpClient.PostAsJsonAsync("marcas", nuevaMarca);

                if (response.IsSuccessStatusCode)
                {
                    // Retorna la marca con el id generado por la bd 
                    return await response.Content.ReadFromJsonAsync<Marca>();
                }
                return null;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error al crear marca: {ex.Message}");
                return null;
            }
        }

        // POST: Registrar una nueva marca en Java
        // URL: http://localhost:8080/KillaREST-1.0-SNAPSHOT/services/marcas

        public async Task<bool> ActualizarMarcaAsync(Marca marca)
        {
            try
            {
                // Concatenamos el ID en la URL para que coincida con tu @Path("{id}") en Java
                var response = await _httpClient.PutAsJsonAsync($"marcas/{marca.Id}", marca);

                // Devuelve true si se actualizó correctamente (código 200 OK)
                return response.IsSuccessStatusCode;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error al actualizar marca: {ex.Message}");
                return false;
            }
        }
    }
    public class Marca
    {
        public int Id { get; set; }
        public string Descripcion { get; set; } = "";

      
        public int? PaisId { get; set; }
        public string PaisNombre { get; set; } = "";

        public bool Activo { get; set; } = true;
    }
}

