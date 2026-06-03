namespace BlazorAppKillaBeauty.Services
{
    public class CouponService
    {
        public List<Cupon> Cupones { get; } = new()
        {
            new Cupon
            {
                Codigo = "MAMA20",
                Titulo = "20% OFF Día de la Madre",
                Tipo = "PORCENTAJE",
                Valor = 20,
                MontoMinimoCompra = 100,
                TopeMaximoDescuento = 30,
                FechaFin = new DateTime(2026, 5, 12)
            },
            new Cupon
            {
                Codigo = "BIENVENIDO",
                Titulo = "S/. 15 OFF primera compra",
                Tipo = "MONTO",
                Valor = 15,
                MontoMinimoCompra = 75,
                TopeMaximoDescuento = 15,
                FechaFin = new DateTime(2026, 12, 31)
            },
            new Cupon
            {
                Codigo = "CYBERWOW",
                Titulo = "30% OFF CyberWOW",
                Tipo = "PORCENTAJE",
                Valor = 30,
                MontoMinimoCompra = 250,
                TopeMaximoDescuento = 100,
                FechaFin = new DateTime(2026, 10, 14)
            }
        };

        public Cupon? BuscarCupon(string codigo)
        {
            return Cupones.FirstOrDefault(c =>
                c.Codigo.Equals(codigo.Trim(), StringComparison.OrdinalIgnoreCase));
        }

        public decimal CalcularDescuento(Cupon cupon, decimal subtotal)
        {
            if (subtotal < cupon.MontoMinimoCompra)
                return 0;

            decimal descuento = cupon.Tipo == "PORCENTAJE"
                ? subtotal * (cupon.Valor / 100)
                : cupon.Valor;

            return Math.Min(descuento, cupon.TopeMaximoDescuento);
        }
    }

    public class Cupon
    {
        public string Codigo { get; set; } = "";
        public string Titulo { get; set; } = "";
        public string Tipo { get; set; } = "";
        public decimal Valor { get; set; }
        public decimal MontoMinimoCompra { get; set; }
        public decimal TopeMaximoDescuento { get; set; }
        public DateTime FechaFin { get; set; }
    }
}