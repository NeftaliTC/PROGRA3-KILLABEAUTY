namespace BlazorAppKillaBeauty.Services
{
    public class CampanaService
    {
        private List<Campana> campanas = new();

        public CampanaService()
        {
            // Mock Data inicial
            campanas.Add(new Campana
            {
                Id = 1,
                Nombre = "Cyber Wow 2026",
                Descripcion = "Descuentos masivos de mitad de año",
                Activa = true
            });
            campanas.Add(new Campana
            {
                Id = 2,
                Nombre = "Día de la Madre",
                Descripcion = "Promociones especiales para mamá",
                Activa = false
            });
        }
        public IReadOnlyList<Campana> ObtenerTodas()
        {
            return campanas;
        }

        public IReadOnlyList<Campana> ObtenerActivas()
        {
            // Este método será útil para el dropdown del formulario de cupones
            return campanas.Where(c => c.Activa).ToList();
        }

        public Campana ObtenerPorId(int id)
        {
            return campanas.FirstOrDefault(c => c.Id == id) ?? new Campana();
        }

        public void Guardar(Campana campana)
        {
            if (campana.Id == 0)
            {
                campana.Id = campanas.Count > 0 ? campanas.Max(c => c.Id) + 1 : 1;
                campana.Activa = true;
                campanas.Add(campana);
            }
            else
            {
                var index = campanas.FindIndex(c => c.Id == campana.Id);
                if (index != -1)
                {
                    campanas[index] = campana;
                }
            }
        }
    }
    public class Campana
    {
        public int Id { get; set; }
        public string Nombre { get; set; } = "";
        public string Descripcion { get; set; } = "";
        public bool Activa { get; set; } = true;
    }
}
