package pe.edu.pucp.killabeauty.killarest.services.usuarios;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pe.edu.pucp.killaBeauty.bl.Impl.Promocionales.CuponBLImpl;
import pe.edu.pucp.killaBeauty.bl.Impl.UsuarioBLImpl;
import pe.edu.pucp.killaBeauty.bl.Promocionales.CuponBL;
import pe.edu.pucp.killaBeauty.bl.UsuarioBL;
import pe.edu.pucp.killaBeauty.killaModelo.Usuario;

import java.util.List;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioRS {
    private final UsuarioBL usuarioBL = new UsuarioBLImpl();
    @GET
    public Response listarPorTipoUsuario(@QueryParam("tipo") int idTipoUsuario) {
        try {
            List<Usuario> usuarios = usuarioBL.listByTipoUsuario(idTipoUsuario);
            return Response.ok(usuarios).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
    @GET
    @Path("email/{correo}")
    public Response obtenerUsuarioPorEmail(@PathParam("correo") String email) {
        try {
            Usuario usuario = usuarioBL.loadByEmail(email);
            if (usuario != null) {
                return Response.ok(usuario).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
    @POST
    public Response insertarUsuario(Usuario usuario) {
        try {
            Usuario usuarioCreado = usuarioBL.create(usuario);
            return Response.status(Response.Status.CREATED).entity(usuarioCreado).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
    @PUT
    @Path("{id}")
    public Response actualizarUsuario(@PathParam("id") int id, Usuario usuario) {
        try {
            usuario.setId(id);
            Usuario usuarioActualizado = usuarioBL.update(usuario);
            return Response.ok(usuarioActualizado).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
    @DELETE
    @Path("{id}")
    public Response eliminarUsuario(@PathParam("id") int id) {
        try {
            Usuario usuarioAEliminar = new Usuario();
            usuarioAEliminar.setId(id);

            usuarioBL.remove(usuarioAEliminar);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
