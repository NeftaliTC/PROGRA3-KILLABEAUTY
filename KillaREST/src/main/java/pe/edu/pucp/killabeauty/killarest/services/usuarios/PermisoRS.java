package pe.edu.pucp.killabeauty.killarest.services.usuarios;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pe.edu.pucp.killaBeauty.bl.Impl.PermisoBLImpl;
import pe.edu.pucp.killaBeauty.bl.Impl.Promocionales.CuponBLImpl;
import pe.edu.pucp.killaBeauty.bl.PermisoBL;
import pe.edu.pucp.killaBeauty.bl.Promocionales.CuponBL;

@Path("/permisos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PermisoRS {
    private final PermisoBL permisoBL = new PermisoBLImpl();
    @GET
    public Response listarPermisos() {
        return Response.ok().build();
    }
}
