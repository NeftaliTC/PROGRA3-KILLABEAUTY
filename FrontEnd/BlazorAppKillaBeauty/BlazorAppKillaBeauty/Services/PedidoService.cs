using System.Net.Http.Json;
using System.Text.Json;
using System.Text.Json.Serialization;
using BlazorAppKillaBeauty.ClienteREST.Models;

namespace BlazorAppKillaBeauty.Services
{
    public class PedidoService
    {
        private readonly HttpClient http;

        private readonly JsonSerializerOptions jsonOptions = new()
        {
            PropertyNameCaseInsensitive = true,
            DefaultIgnoreCondition = JsonIgnoreCondition.WhenWritingNull
        };

        public PedidoService(IHttpClientFactory httpClientFactory)
        {
            http = httpClientFactory.CreateClient("KillaApi");
        }

        public async Task<List<Pedido>> ListarTodosAsync()
        {
            return await GetAsync<List<Pedido>>("pedido")
                   ?? new List<Pedido>();
        }

        public async Task<Pedido?> ObtenerPorIdAsync(int id)
        {
            return await GetAsync<Pedido>($"pedido/{id}");
        }

        public async Task<Pedido> CrearAsync(Pedido pedido)
        {
            using var response = await http.PostAsJsonAsync("pedido", pedido, jsonOptions);
            await EnsureSuccessAsync(response);

            return await response.Content.ReadFromJsonAsync<Pedido>(jsonOptions)
                   ?? pedido;
        }

        public async Task<Pedido> ActualizarAsync(Pedido pedido)
        {
            using var response = await http.PutAsJsonAsync($"pedido/{pedido.Id}", pedido, jsonOptions);
            await EnsureSuccessAsync(response);

            return await response.Content.ReadFromJsonAsync<Pedido>(jsonOptions)
                   ?? pedido;
        }

        public async Task EliminarAsync(int id)
        {
            using var response = await http.DeleteAsync($"pedido/{id}");
            await EnsureSuccessAsync(response);
        }

        private async Task<T?> GetAsync<T>(string url)
        {
            using var response = await http.GetAsync(url);

            if (response.StatusCode == System.Net.HttpStatusCode.NotFound)
                return default;

            await EnsureSuccessAsync(response);

            return await response.Content.ReadFromJsonAsync<T>(jsonOptions);
        }

        private static async Task EnsureSuccessAsync(HttpResponseMessage response)
        {
            if (response.IsSuccessStatusCode)
                return;

            var body = await response.Content.ReadAsStringAsync();

            throw new InvalidOperationException(
                string.IsNullOrWhiteSpace(body)
                    ? $"Error REST {(int)response.StatusCode} {response.ReasonPhrase}"
                    : body);
        }
    }
}