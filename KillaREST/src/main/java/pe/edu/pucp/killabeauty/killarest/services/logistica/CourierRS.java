package pe.edu.pucp.killabeauty.killarest.services.logistica;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pe.edu.pucp.killaBeauty.bl.CourierBL;
import pe.edu.pucp.killaBeauty.bl.Impl.CourierBLImpl;
import pe.edu.pucp.killaBeauty.killaModelo.Courier;

@Path("/courier")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourierRS {
    private CourierBL courierBL = new CourierBLImpl();
    @POST
    public Response registrar(Courier courier) {
        try {
            Courier creado = courierBL.create(courier);
            return Response.status(Response.Status.CREATED).entity(creado).build();
        } catch (Exception ex) {
            return Response.status(500).entity("Error al registrar: " + ex.getMessage()).build();
        }
    }

    @Path("{id}")
    @GET
    public Response obtenerPorId(@PathParam("id") Integer id) throws Exception {
        try {
            Courier courier = courierBL.load(id);
            if (courier == null) return Response.status(404).build();
            return Response.ok(courier).build();
        } catch (Exception ex) {
            return Response.status(500).entity("Error de conexión: " + ex.getMessage()).build();
        }
    }

    // Actualizar o desactivar (UPDATE)
    @PUT
    public Response actualizar(Courier courier) {
        try {
            return Response.ok(courierBL.update(courier)).build();
        } catch (Exception ex) {
            return Response.status(500).entity("Error al actualizar: " + ex.getMessage()).build();
        }
    }

    @GET
    public Response listar() {
        try {
            return Response.ok(courierBL.listAll()).build();
        } catch (Exception ex) {
            return Response.status(500).entity("Error al listar: " + ex.getMessage()).build();
        }
    }
}
