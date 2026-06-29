package pe.edu.pucp.killabeauty.killarest.services.usuarios;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pe.edu.pucp.killaBeauty.bl.DireccionBL;
import pe.edu.pucp.killaBeauty.bl.Impl.DireccionBLImpl;
import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.Direccion;
import pe.edu.pucp.killabeauty.killarest.dto.ErrorDTO;

import java.util.List;

@Path("/direcciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DireccionRS {
    private final DireccionBL direccionBL = new DireccionBLImpl();

    @GET
    public Response listar(@QueryParam("usuarioId") Integer usuarioId) {
        try {
            List<Direccion> direcciones = usuarioId == null
                    ? direccionBL.listAll()
                    : direccionBL.listarPorUsuario(usuarioId);
            return Response.ok(direcciones).build();
        } catch (BusinessLogicException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(ex.getMessage())).build();
        }catch (Exception ex) {
            return Response.serverError().entity(new ErrorDTO(ex.getMessage())).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") int id) {
        try {
            Direccion direccion = direccionBL.load(id);
            if (direccion == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorDTO("No existe una direccion con id " + id))
                        .build();
            }
            return Response.ok(direccion).build();
        } catch (BusinessLogicException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(ex.getMessage())).build();
        }catch (Exception ex) {
            return Response.serverError().entity(new ErrorDTO(ex.getMessage())).build();
        }
    }

    @POST
    public Response registrar(Direccion direccion) {
        try {
            if (direccion == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO("Datos de la dirección incompletos.")).build();
            }
            Direccion creada = direccionBL.create(direccion);
            return Response.status(Response.Status.CREATED).entity(creada).build();

        } catch (BusinessLogicException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(ex.getMessage())).build();
        } catch (Exception ex) {
            return Response.serverError().entity(new ErrorDTO(ex.getMessage())).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") int id, Direccion direccion) {
        try {
            if (direccion == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO("Datos incompletos para actualizar.")).build();
            }

            direccion.setId(id);
            Direccion actualizada = direccionBL.update(direccion);
            return Response.ok(actualizada).build();
        }catch (BusinessLogicException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(ex.getMessage())).build();
        } catch (Exception ex) {
            return Response.serverError().entity(new ErrorDTO(ex.getMessage())).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") int id) {
        try {
            Direccion direccion = new Direccion();
            direccion.setId(id);
            direccionBL.remove(direccion);
            return Response.noContent().build();
        } catch (BusinessLogicException ex) {
            if (ex.getMessage().toLowerCase().contains("no existe") || ex.getMessage().toLowerCase().contains("no encontrad")) {
                return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(ex.getMessage())).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(ex.getMessage())).build();
        } catch (Exception ex) {
            return Response.serverError().entity(new ErrorDTO(ex.getMessage())).build();
        }
    }

    private Response badRequest(Exception ex) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorDTO(ex.getMessage()))
                .build();
    }

    private Response serverError(Exception ex) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorDTO(ex.getMessage()))
                .build();
    }
}
