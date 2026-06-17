package pe.edu.pucp.killabeauty.killarest.services.productos;


import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pe.edu.pucp.killaBeauty.bl.Impl.MarcaBLImpl;
import pe.edu.pucp.killaBeauty.bl.Impl.ProductoBLImpl;
import pe.edu.pucp.killaBeauty.bl.MarcaBL;
import pe.edu.pucp.killaBeauty.bl.ProductoBL;

@Path("/marcas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MarcaRS {
    private final MarcaBL marcaBL = new MarcaBLImpl();
    @GET
    public Response listarMarcas() {
        return Response.ok().build();
    }
}
