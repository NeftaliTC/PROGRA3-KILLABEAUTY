namespace BlazorAppKillaBeauty.Services
   
{
        public class Producto
        {
            public string Nombre { get; set; }
            public decimal PrecioMinimo { get; set; }
            public decimal PrecioMaximo { get; set; }
            public string Imagen { get; set; }
            public string Categoria { get; set; }
            public string Subcategoria { get; set; }
            public string Marca { get; set; }
            public int Calificacion { get; set; }
            public bool EsPopular { get; set; }

            public Producto(
                string nombre,
                decimal precioMinimo,
                decimal precioMaximo,
                string imagen,
                string categoria,
                string subcategoria,
                string marca,
                int calificacion,
                bool esPopular)
            {
                Nombre = nombre;
                PrecioMinimo = precioMinimo;
                PrecioMaximo = precioMaximo;
                Imagen = imagen;
                Categoria = categoria;
                Subcategoria = subcategoria;
                Marca = marca;
                Calificacion = calificacion;
                EsPopular = esPopular;
            }
        
    }
}
