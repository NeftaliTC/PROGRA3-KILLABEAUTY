package pe.edu.pucp.killabeauty.killarest.services.promocionales;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pe.edu.pucp.killaBeauty.bl.CategoriaBL;
import pe.edu.pucp.killaBeauty.bl.Impl.CategoriaBLImpl;
import pe.edu.pucp.killaBeauty.bl.Impl.Promocionales.CampanaBLImpl;
import pe.edu.pucp.killaBeauty.bl.Promocionales.CampanaBL;

@Path("/campanas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CampanaRS {
    private final CampanaBL campanaBL = new CampanaBLImpl();
    @GET
    public Response listarCampanas() {
        return Response.ok().build();
    }
}
