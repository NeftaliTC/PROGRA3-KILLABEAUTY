

namespace BlazorAppKillaBeauty.Services
{
    public class ProductoService
    {
            
     private List<Producto> productos = new(){
        new Producto(
        "Serum Skin Garnier Vitamina Express Aclara 15ml",
        65.00m,
        80.00m,
        "Images/Serum.jpg",
        "Skincare",
        "Serums",
        "Garnier",
        4,
        true
    ),

    new Producto(
        "Crema Hidratante Facial CeraVe",
        69.90m,
        69.90m,
        "Images/Cerave.jpg",
        "Skincare",
        "Crema Hidratante",
        "CeraVe",
        4,
        true
    ),

    new Producto(
        "Labial Matte Ink Mood PERIPERA",
        45.90m,
        45.90m,
        "Images/LabialPeripera.jpg",
        "Makeup",
        "Labiales",
        "Peripera",
        5,
        true
    ),

    new Producto(
        "Máscara Essence Prince False Lash Effect",
        52.90m,
        52.90m,
        "Images/Mascara.jpg",
        "Makeup",
        "Máscaras",
        "Essence",
        4,
        true
    ),

    new Producto(
        "L03 Professional Protein Treatment LORINIQUE",
        42.50m,
        42.50m,
        "Images/LoriniqueCabello.jpg",
        "Cuidado del cabello",
        "Tratamientos",
        "Lorinique",
        4,
        true
    ),

    new Producto(
        "Limpiador Facial CeraVe Espumoso",
        58.00m,
        75.00m,
        "Images/Logo.png",
        "Skincare",
        "Limpiadores faciales",
        "CeraVe",
        4,
        false
    ),

    new Producto(
        "The Ordinary Niacinamide 10% + Zinc 1%",
        55.00m,
        70.00m,
        "Images/Logo.png",
        "Skincare",
        "Serums",
        "The Ordinary",
        5,
        false
    ),

    new Producto(
        "La Roche-Posay Effaclar Gel Limpiador",
        85.00m,
        110.00m,
        "Images/Logo.png",
        "Skincare",
        "Limpiadores faciales",
        "La Roche-Posay",
        5,
        false
    ),

    new Producto(
        "Garnier Agua Micelar Todo en 1",
        28.00m,
        39.90m,
        "Images/Logo.png",
        "Skincare",
        "Limpiadores faciales",
        "Garnier",
        4,
        false
    ),

    new Producto(
        "CeraVe Crema Hidratante Tarro",
        95.00m,
        130.00m,
        "Images/Logo.png",
        "Skincare",
        "Crema Hidratante",
        "CeraVe",
        5,
        false
    ),

    new Producto(
        "Maybelline Fit Me Base Líquida",
        39.90m,
        49.90m,
        "Images/Logo.png",
        "Makeup",
        "Bases",
        "Maybelline",
        4,
        false
    ),

    new Producto(
        "Maybelline Sky High Mascara",
        48.00m,
        65.00m,
        "Images/Logo.png",
        "Makeup",
        "Máscaras",
        "Maybelline",
        5,
        false
    ),

    new Producto(
        "Gloss Extreme Shine Essence 09",
        6.00m,
        15.00m,
        "Images/Logo.png",
        "Makeup",
        "Labiales",
        "Essence",
        4,
        false
    ),

    new Producto(
        "Rubor Líquido Rare Beauty Soft Pinch",
        85.00m,
        120.00m,
        "Images/Logo.png",
        "Makeup",
        "Rubores",
        "Rare Beauty",
        5,
        false
    ),

    new Producto(
        "Corrector Instant Age Rewind Maybelline",
        35.00m,
        45.00m,
        "Images/Logo.png",
        "Makeup",
        "Correctores",
        "Maybelline",
        4,
        false
    ),

    new Producto(
        "Shampoo Elvive Reparación Total 5",
        22.00m,
        29.90m,
        "Images/Logo.png",
        "Cuidado del cabello",
        "Shampoo",
        "L'Oréal",
        4,
        false
    ),

    new Producto(
        "Acondicionador Elvive Hidra Hialurónico",
        22.00m,
        29.90m,
        "Images/Logo.png",
        "Cuidado del cabello",
        "Acondicionador",
        "L'Oréal",
        4,
        false
    ),

    new Producto(
        "Crema de Peinado Rizos Argan Oil 300 ml Lan Pro",
        20.50m,
        39.90m,
        "Images/Logo.png",
        "Cuidado del cabello",
        "Cremas de peinar",
        "Lan Pro",
        4,
        false
    ),

    new Producto(
        "Tratamiento Capilar Kativa Keratina",
        45.00m,
        65.00m,
        "Images/Logo.png",
        "Cuidado del cabello",
        "Tratamientos",
        "Kativa",
        5,
        false
    ),

    new Producto(
        "Perfume Ariana Grande Cloud",
        140.00m,
        190.00m,
        "Images/Logo.png",
        "Fragancias",
        "Perfumes",
        "Ariana Grande",
        5,
        false
    )
};
   

            public IReadOnlyList<Producto> ObtenerTodos()
            {
                return productos;
            }

            public IReadOnlyList<Producto> ObtenerPopulares()
            {
                return productos.Where(p => p.EsPopular).ToList();
            }
        }
    }

