namespace BlazorAppKillaBeauty.Services
{
    public class AddressService
    {
        public List<Address> UserAddresses { get; set; } = new()
        {
            new Address
            {
                Id = 1,
                Alias = "Casa",
                DireccionDetalle = "Av. Javier Prado 123",
                Distrito = "Miraflores",
                Provincia = "Lima Metropolitana",
                Departamento = "Lima",
                Telefono = "987654321",
                Referencia = "A dos cuadras de la Plaza",
                IsDefault = true
            },
            new Address
            {
                Id = 2,
                Alias = "Trabajo",
                DireccionDetalle = "Calle Las Begonias 456",
                Distrito = "San Isidro",
                Provincia = "Lima Metropolitana",
                Departamento = "Lima",
                Telefono = "912345678",
                Referencia = "Frente al parque Las Magnolias",
                IsDefault = false
            }
        };

        public Address? DireccionSeleccionada { get; set; }

        public Address? ObtenerPredeterminada()
        {
            return UserAddresses.FirstOrDefault(a => a.IsDefault);
        }

        public decimal CalcularEnvio(Address direccion)
        {
            return direccion.Distrito switch
            {
                "Miraflores" => 10,
                "San Isidro" => 12,
                "Santiago de Surco" => 15,
                "San Borja" => 12,
                "La Molina" => 15,
                "Ate" => 18,
                "Comas" => 18,
                "Los Olivos" => 16,
                "San Juan de Lurigancho" => 20,
                "Villa El Salvador" => 20,

                _ => 15
            };
        }
    }

    public class Address
    {
        public int Id { get; set; }
        public string Alias { get; set; } = "";
        public string DireccionDetalle { get; set; } = "";
        public string Distrito { get; set; } = "";
        public string Provincia { get; set; } = "";
        public string Departamento { get; set; } = "";
        public string Telefono { get; set; } = "";
        public string Referencia { get; set; } = "";
        public bool IsDefault { get; set; }
    }
}