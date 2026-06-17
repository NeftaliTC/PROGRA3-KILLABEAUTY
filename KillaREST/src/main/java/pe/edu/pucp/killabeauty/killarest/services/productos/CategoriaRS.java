package pe.edu.pucp.killabeauty.killarest.services.productos;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pe.edu.pucp.killaBeauty.bl.CategoriaBL;
import pe.edu.pucp.killaBeauty.bl.Impl.CategoriaBLImpl;
import pe.edu.pucp.killaBeauty.bl.Impl.MarcaBLImpl;
import pe.edu.pucp.killaBeauty.bl.MarcaBL;
import pe.edu.pucp.killaBeauty.bl.ProductoBL;

@Path("/categorias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoriaRS {
    private final CategoriaBL categoriaBL = new CategoriaBLImpl();
    @GET
    public Response listarCategorias() {
        return Response.ok().build();
    }
}
