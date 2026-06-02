namespace BlazorAppKillaBeauty.Services
{
    /*CuponService va a actuar como tu pequeña bd mientras la app web esté abierta
        Como Blazor no cierra el servicio al cambiar de página, cualquier pantalla que lo "inyecte" 
        verá exactamente la misma lista*/

    public class CuponService
    {
        private List<Cupon> cupones = new();
        public CuponService()
        {
            cupones.Add(new Cupon
            {
                Id = 1,
                Codigo = "KILLA20",
                ValorDescuento = 20,
                TipoDescuento = "Porcentaje",
                Activo = true,
                FechaFin = new DateTime(2026, 11, 10)
            });
            cupones.Add(new Cupon
            {
                Id = 2,
                Codigo = "MAMA50",
                ValorDescuento = 50,
                TipoDescuento = "Monto Fijo",
                Activo = false,
                FechaFin = new DateTime(2025, 5, 10)
            });
        }


        public IReadOnlyList<Cupon> ObtenerTodos()
        {
            return cupones;
        }

        public Cupon ObtenerPorId(int id)
        {
            // Busca el cupón. Si es 0 o no lo encuentra, devuelve uno nuevo y limpio.
            return cupones.FirstOrDefault(c => c.Id == id) ?? new Cupon();
        }

        public void Guardar(Cupon cupon)
        {
            if (cupon.Id == 0)
            {
                // Simula el "Auto Increment" de la base de datos
                cupon.Id = cupones.Count > 0 ? cupones.Max(c => c.Id) + 1 : 1;
                cupon.Activo = true;
                cupones.Add(cupon);
            }
            else
            {
                // Es un UPDATE: Buscamos el original y lo reemplazamos
                var index = cupones.FindIndex(c => c.Id == cupon.Id);
                if (index != -1)
                {
                    cupones[index] = cupon;
                }
            }
        }
        public void EliminarLogico(int id)
        {
            var cupon = cupones.FirstOrDefault(c => c.Id == id);
            if (cupon != null)
            {
                cupon.Activo = false; // Mismo concepto de eliminación lógica de Java
            }
        }
    }
    public class Cupon
    {
        public int Id { get; set; }
        public string Codigo { get; set; } = "";
        public string Descripcion { get; set; } = "";
        public double ValorDescuento { get; set; }
        public string TipoDescuento { get; set; } = "seleccione";
        public DateTime? FechaInicio { get; set; } = DateTime.Now;
        public DateTime? FechaFin { get; set; } = DateTime.Now.AddDays(7);
        public bool Activo { get; set; } = true;
        public double MontoMaximoDescuento { get; set; }
        public double MontoMinimoCompra { get; set; }
        public int MaxUsosGenerales { get; set; }
    }
}
