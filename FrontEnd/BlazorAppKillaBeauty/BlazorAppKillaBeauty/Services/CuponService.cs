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
            cupones.Add(new Cupon
            {
                Id = 2,
                Codigo = "MAMA50",
                ValorDescuento = 50,
                TipoDescuento = "Monto Fijo",
                FechaInicio = new DateTime(2026, 06, 02),
                FechaFin = new DateTime(2026, 06, 30),
                Activo = true,
                MontoMinimoCompra = 200,
                MaxUsosGenerales = 50,
                UsosActuales = 45,
                Descripcion = "Promoción mes de la madre"
            });

            cupones.Add(new Cupon
            {
                Id = 3,
                Codigo = "ENVIOFREE",
                ValorDescuento = 10,
                TipoDescuento = "Monto Fijo",
                FechaInicio = new DateTime(2026, 05, 01),
                FechaFin = new DateTime(2026, 05, 31),
                Activo = false,
                MontoMinimoCompra = 150,
                MaxUsosGenerales = 200,
                UsosActuales = 200,
                Descripcion = "Cupón vencido de envío"
            });

            cupones.Add(new Cupon
            {
                Id = 4,
                Codigo = "SUMMER26",
                ValorDescuento = 15,
                TipoDescuento = "Porcentaje",
                FechaInicio = new DateTime(2026, 07, 01),
                FechaFin = new DateTime(2026, 09, 20),
                Activo = true,
                MontoMinimoCompra = 80,
                MaxUsosGenerales = 500,
                UsosActuales = 0,
                Descripcion = "Descuento de temporada de verano"
            });

            cupones.Add(new Cupon
            {
                Id = 5,
                Codigo = "FLASH100",
                ValorDescuento = 100,
                TipoDescuento = "Monto Fijo",
                FechaInicio = new DateTime(2026, 06, 01),
                FechaFin = new DateTime(2026, 06, 05),
                Activo = true,
                MontoMinimoCompra = 400,
                MaxUsosGenerales = 20,
                UsosActuales = 5,
                Descripcion = "Oferta relámpago"
            });
            cupones.Add(new Cupon
            {
                Id = 10, Codigo = "BELLEZA2026", ValorDescuento = 15, TipoDescuento = "Porcentaje", FechaInicio = DateTime.Now.AddDays(-15), FechaFin = DateTime.Now.AddDays(90), Activo = true, MontoMinimoCompra = 90, Descripcion = "Aniversario Killa" 

            });
            cupones.Add(new Cupon
            {
                Id = 11, Codigo = "DESCUENTON", ValorDescuento = 100, TipoDescuento = "Monto Fijo", FechaInicio = DateTime.Now.AddDays(-5), FechaFin = DateTime.Now.AddDays(10), Activo = true, MontoMinimoCompra = 500, Descripcion = "Mega ahorro" 
            });
        }

        public Cupon? BuscarCupon(string codigo)
        {
            return cupones.FirstOrDefault(c =>
                c.Codigo.Equals(codigo.Trim(), StringComparison.OrdinalIgnoreCase));
        }

        public decimal CalcularDescuento(Cupon cupon, decimal subtotal)
        {
            if (subtotal < cupon.MontoMinimoCompra)
                return 0;

            decimal descuento = cupon.TipoDescuento == "PORCENTAJE"
                ? subtotal * (cupon.ValorDescuento / 100)
                : cupon.ValorDescuento;

            //return Math.Min(descuento, cupon.MontoMaximoDescuento);
            return Math.Min(descuento, cupon.MontoMaximoDescuento ?? decimal.MaxValue);
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
        public decimal ValorDescuento { get; set; }
        public string TipoDescuento { get; set; } = "seleccione";
        public DateTime? FechaInicio { get; set; } = DateTime.Now;
        public DateTime? FechaFin { get; set; } = DateTime.Now.AddDays(7);
        public bool Activo { get; set; } = true;
        public decimal? MontoMaximoDescuento { get; set; }
        public decimal? MontoMinimoCompra { get; set; }
        public int? MaxUsosGenerales { get; set; }


        public int UsosActuales { get; set; } = 0;

        public int? CampanaId { get; set; }
        public string CampanaNombre { get; set; } = "";
    }
}
