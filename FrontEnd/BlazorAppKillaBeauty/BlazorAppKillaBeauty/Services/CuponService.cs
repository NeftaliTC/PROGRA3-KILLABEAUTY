using System.Net.Http.Json;
using System.Text.Json;
using System.Text.Json.Serialization;

namespace BlazorAppKillaBeauty.Services
{
    public class CuponService
    {
        private readonly HttpClient http;
        private readonly JsonSerializerOptions jsonOptions = new()
        {
            PropertyNameCaseInsensitive = true,
            DefaultIgnoreCondition = JsonIgnoreCondition.WhenWritingNull
        };

        public CuponService(IHttpClientFactory httpClientFactory)
        {
            http = httpClientFactory.CreateClient("KillaApi");
        }

        public async Task<List<Cupon>> ObtenerTodosAsync()
        {
            return await GetAsync<List<Cupon>>("cupones") ?? new List<Cupon>();
        }

        public async Task<Cupon?> ObtenerPorIdAsync(int id)
        {
            return await GetAsync<Cupon>($"cupones/{id}");
        }

        public async Task<Cupon?> BuscarCuponAsync(string codigo)
        {
            if (string.IsNullOrWhiteSpace(codigo))
            {
                return null;
            }

            var cupones = await ObtenerTodosAsync();
            return cupones.FirstOrDefault(c =>
                c.Activo &&
                c.Codigo.Equals(codigo.Trim(), StringComparison.OrdinalIgnoreCase));
        }

        public decimal CalcularDescuento(Cupon cupon, decimal subtotal)
        {
            if (subtotal < (cupon.MontoMinimoCompra ?? 0))
            {
                return 0;
            }

            if (cupon.FechaInicio.HasValue && cupon.FechaInicio.Value.Date > DateTime.Today)
            {
                return 0;
            }

            if (cupon.FechaFin.HasValue && cupon.FechaFin.Value.Date < DateTime.Today)
            {
                return 0;
            }

            var descuento = cupon.EsPorcentaje
                ? subtotal * (cupon.ValorDescuento / 100)
                : cupon.ValorDescuento;

            return Math.Min(descuento, cupon.MontoMaximoDescuento ?? decimal.MaxValue);
        }

        public async Task<Cupon> GuardarAsync(Cupon cupon)
        {
            using var response = cupon.Id == 0
                ? await http.PostAsJsonAsync("cupones", cupon, jsonOptions)
                : await http.PutAsJsonAsync($"cupones/{cupon.Id}", cupon, jsonOptions);

            await EnsureSuccessAsync(response);
            return await response.Content.ReadFromJsonAsync<Cupon>(jsonOptions) ?? cupon;
        }

        public async Task EliminarLogicoAsync(int id)
        {
            using var response = await http.DeleteAsync($"cupones/{id}");
            await EnsureSuccessAsync(response);
        }

        private async Task<T?> GetAsync<T>(string url)
        {
            using var response = await http.GetAsync(url);
            if (response.StatusCode == System.Net.HttpStatusCode.NotFound)
            {
                return default;
            }

            await EnsureSuccessAsync(response);
            return await response.Content.ReadFromJsonAsync<T>(jsonOptions);
        }

        private static async Task EnsureSuccessAsync(HttpResponseMessage response)
        {
            if (response.IsSuccessStatusCode)
            {
                return;
            }

            var body = await response.Content.ReadAsStringAsync();
            throw new InvalidOperationException(
                string.IsNullOrWhiteSpace(body)
                    ? $"Error REST {(int)response.StatusCode} {response.ReasonPhrase}"
                    : body);
        }
    }

    public class Cupon
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }

        [JsonPropertyName("codigo")]
        public string Codigo { get; set; } = "";

        [JsonPropertyName("descripcion")]
        public string Descripcion { get; set; } = "";

        [JsonPropertyName("valorDescuento")]
        public decimal ValorDescuento { get; set; }

        [JsonPropertyName("tipoDescuento")]
        public string TipoDescuento { get; set; } = "PORCENTAJE";

        [JsonPropertyName("fechaInicio")]
        public DateTime? FechaInicio { get; set; } = DateTime.Today;

        [JsonPropertyName("fechaFin")]
        public DateTime? FechaFin { get; set; } = DateTime.Today.AddDays(7);

        [JsonPropertyName("activo")]
        public bool Activo { get; set; } = true;

        [JsonPropertyName("montoMaximoDescuento")]
        public decimal? MontoMaximoDescuento { get; set; }

        [JsonPropertyName("montoMinimoCompra")]
        public decimal? MontoMinimoCompra { get; set; }

        [JsonPropertyName("maxUsosGenerales")]
        public int? MaxUsosGenerales { get; set; }

        [JsonPropertyName("campana")]
        public Campana? Campana { get; set; }

        [JsonIgnore]
        public int UsosActuales { get; set; } = 0;

        [JsonIgnore]
        public int? CampanaId
        {
            get => Campana?.Id;
            set => Campana = value.HasValue && value.Value > 0 ? new Campana { Id = value.Value } : null;
        }

        [JsonIgnore]
        public string CampanaNombre
        {
            get => Campana?.Nombre ?? "";
            set
            {
                Campana ??= new Campana();
                Campana.Nombre = value;
            }
        }

        [JsonIgnore]
        public bool EsPorcentaje => TipoDescuento.Equals("PORCENTAJE", StringComparison.OrdinalIgnoreCase)
            || TipoDescuento.Equals("Porcentaje", StringComparison.OrdinalIgnoreCase);

        [JsonIgnore]
        public string TipoDescuentoTexto => EsPorcentaje ? "Porcentaje" : "Monto Fijo";

        [JsonIgnore]
        public string Titulo => EsPorcentaje
            ? $"{ValorDescuento:0.##}% OFF"
            : $"S/ {ValorDescuento:0.##} OFF";

        [JsonIgnore]
        public string Subtitulo
        {
            get
            {
                var partes = new List<string>();
                if (MontoMinimoCompra.HasValue && MontoMinimoCompra.Value > 0)
                {
                    partes.Add($"Compra mínima S/ {MontoMinimoCompra.Value:0.##}");
                }
                if (MontoMaximoDescuento.HasValue && MontoMaximoDescuento.Value > 0)
                {
                    partes.Add($"Tope S/ {MontoMaximoDescuento.Value:0.##}");
                }
                return partes.Count == 0 ? TipoDescuentoTexto : string.Join("<br />", partes);
            }
        }

        [JsonIgnore]
        public string DescripcionLegal => string.IsNullOrWhiteSpace(Descripcion)
            ? "Cupón sujeto a términos y condiciones de Killa Beauty."
            : Descripcion;
    }
}