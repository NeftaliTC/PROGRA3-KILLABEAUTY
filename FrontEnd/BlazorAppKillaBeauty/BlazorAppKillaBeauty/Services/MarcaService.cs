using BlazorAppKillaBeauty.ClienteREST.Models;

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


    //public class MarcaService
    //{
    //    private List<Marca> marcas = new();

    //    public MarcaService()
    //    {

    //        marcas.Add(new Marca { Id = 1, Descripcion = "L'Oréal Paris", PaisId = 2, PaisNombre = "Francia", Activo = true });
    //        marcas.Add(new Marca { Id = 2, Descripcion = "Maybelline", PaisId = 1, PaisNombre = "Estados Unidos", Activo = false });
    //        marcas.Add(new Marca { Id = 3, Descripcion = "Sulwhasoo", PaisId = 3, PaisNombre = "Corea del Sur", Activo = true });
    //        marcas.Add(new Marca { Id = 4, Descripcion = "Natura", PaisId = 4, PaisNombre = "Brasil", Activo = true });
    //        marcas.Add(new Marca { Id = 5, Descripcion = "SKIN1004", PaisId = 3, PaisNombre = "Corea del Sur", Activo = true });
    //        marcas.Add(new Marca { Id = 6, Descripcion = "CELIMAX", PaisId = 3, PaisNombre = "Corea del Sur", Activo = true });
    //        marcas.Add(new Marca { Id = 7, Descripcion = "The Ordinary", PaisId = 5, PaisNombre = "Canadá", Activo = true });
    //        marcas.Add(new Marca { Id = 8, Descripcion = "CeraVe", PaisId = 1, PaisNombre = "Estados Unidos", Activo = true });
    //        marcas.Add(new Marca { Id = 9, Descripcion = "MAC Cosmetics", PaisId = 1, PaisNombre = "Estados Unidos", Activo = true });
    //        marcas.Add(new Marca { Id = 10, Descripcion = "Vichy", PaisId = 2, PaisNombre = "Francia", Activo = false });
    //        marcas.Add(new Marca { Id = 11, Descripcion = "COSRX", PaisId = 3, PaisNombre = "Corea del Sur", Activo = true });
    //        marcas.Add(new Marca { Id = 12, Descripcion = "Isdin", PaisId = 2, PaisNombre = "Francia", Activo = true });
    //    }



    //    public IReadOnlyList<Marca> ObtenerTodas()
    //    {
    //        return marcas;
    //    }

    //    public IReadOnlyList<Marca> ObtenerActivas()
    //    {
    //        return marcas.Where(m => m.Activo).ToList();
    //    }

    //    public Marca ObtenerPorId(int id)
    //    {
    //        return marcas.FirstOrDefault(m => m.Id == id) ?? new Marca();
    //    }

    //    public void Guardar(Marca marca)
    //    {
    //        if (marca.Id == 0)
    //        {
    //            // Simula el Auto-Increment
    //            marca.Id = marcas.Count > 0 ? marcas.Max(m => m.Id) + 1 : 1;
    //            marca.Activo = true;
    //            marcas.Add(marca);
    //        }
    //        else
    //        {
    //            // Es un UPDATE
    //            var index = marcas.FindIndex(m => m.Id == marca.Id);
    //            if (index != -1)
    //            {
    //                marcas[index] = marca;
    //            }
    //        }
    //    }

    //    public void EliminarLogico(int id)
    //    {
    //        var marca = marcas.FirstOrDefault(m => m.Id == id);
    //        if (marca != null)
    //        {
    //            marca.Activo = false;
    //        }
    //    }
    //}
    //public class Marca
    //{
    //    public int Id { get; set; }
    //    public string Descripcion { get; set; } = "";


    //    public int? PaisId { get; set; }
    //    public string PaisNombre { get; set; } = "";

    //    public bool Activo { get; set; } = true;
    //}
}
