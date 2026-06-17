package pe.edu.pucp.killabeauty.killarest.services.promocionales;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pe.edu.pucp.killaBeauty.bl.Impl.Promocionales.CampanaBLImpl;
import pe.edu.pucp.killaBeauty.bl.Impl.Promocionales.CuponBLImpl;
import pe.edu.pucp.killaBeauty.bl.Promocionales.CampanaBL;
import pe.edu.pucp.killaBeauty.bl.Promocionales.CuponBL;

@Path("/cupones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CuponRS {
    private final CuponBL cuponBL = new CuponBLImpl();
    @GET
    public Response listarCupones() {
        return Response.ok().build();
    }
}
