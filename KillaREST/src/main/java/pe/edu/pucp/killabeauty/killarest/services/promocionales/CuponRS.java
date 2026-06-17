package pe.edu.pucp.killabeauty.killarest.services.promocionales;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pe.edu.pucp.killaBeauty.bl.Promocionales.CuponBL;
import pe.edu.pucp.killaBeauty.bl.Impl.Promocionales.CuponBLImpl;
import pe.edu.pucp.killaBeauty.killaModelo.Promocionales.Cupon;

import java.util.List;

@Path("/cupones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CuponRS {
    private final CuponBL cuponBL = new CuponBLImpl();

    @GET
    public Response listarCupones() {
        try {
            List<Cupon> cupones = cuponBL.listAll();
            return Response.ok(cupones).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{id}")
    public Response obtenerCuponPorId(@PathParam("id") int id) {
        try {
            Cupon cupon = cuponBL.load(id);
            if (cupon != null) return Response.ok(cupon).build();
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    public Response insertarCupon(Cupon cupon) {
        try {
            Cupon creado = cuponBL.create(cupon);
            return Response.status(Response.Status.CREATED).entity(creado).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response actualizarCupon(@PathParam("id") int id, Cupon cupon) {
        try {
            cupon.setId(id);
            Cupon actualizado = cuponBL.update(cupon);
            return Response.ok(actualizado).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response eliminarCupon(@PathParam("id") int id) {
        try {
            Cupon cupon = new Cupon();
            cupon.setId(id);
            cuponBL.remove(cupon);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}