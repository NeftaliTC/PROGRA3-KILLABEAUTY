    package pe.edu.pucp.killabeauty.killarest.services.usuarios;

    import jakarta.ws.rs.*;
    import jakarta.ws.rs.core.MediaType;
    import jakarta.ws.rs.core.Response;
    import pe.edu.pucp.killaBeauty.bl.Impl.Promocionales.CuponBLImpl;
    import pe.edu.pucp.killaBeauty.bl.Impl.ResenaBLImpl;
    import pe.edu.pucp.killaBeauty.bl.Impl.UsuarioBLImpl;
    import pe.edu.pucp.killaBeauty.bl.Promocionales.CuponBL;
    import pe.edu.pucp.killaBeauty.bl.ResenaBL;
    import pe.edu.pucp.killaBeauty.bl.UsuarioBL;
    import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
    import pe.edu.pucp.killaBeauty.killaModelo.Resena;
    import pe.edu.pucp.killaBeauty.killaModelo.Usuario;
    import org.mindrot.jbcrypt.BCrypt;

    import java.util.List;

    @Path("/usuarios")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public class UsuarioRS {
        private final UsuarioBL usuarioBL = new UsuarioBLImpl();
        private final ResenaBL resenaBL = new ResenaBLImpl();
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
                // 1. Extraemos la contraseña en texto plano que envió Blazor
                String passwordPlano = usuario.getContrasena();

                // 2. Generamos el hash con BCrypt
                String passwordHasheado = BCrypt.hashpw(passwordPlano, BCrypt.gensalt());

                // 3. Reemplazamos la contraseña plana por el hash en el objeto
                usuario.setContrasena(passwordHasheado);

                // 4. Guardamos en la base de datos de forma segura
                Usuario usuarioCreado = usuarioBL.create(usuario);
                return Response.status(Response.Status.CREATED).entity(usuarioCreado).build();
            } catch (BusinessLogicException e) {
                // Devuelve 400 Bad Request con tu mensaje ("El correo electrónico ya se...")
                return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
            } catch (Exception e) {
                // Errores de servidor (500)
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
        @POST
        @Path("/login")
        public Response login(Usuario credenciales) {
            try {
                // 1. Buscas al usuario en la BD usando su correo o nombre de usuario
                Usuario usuarioBD = usuarioBL.loadByEmail(credenciales.getCorreoElectronico());

                // Si el usuario no existe
                if (usuarioBD == null) {
                    return Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Correo o contraseña incorrectos").build();
                }

                // 2. Verificas la contraseña con BCrypt
                // Parámetro 1: La clave plana que tecleó el usuario ahora
                // Parámetro 2: El hash ilegible que sacaste de la BD
                boolean credencialesValidas = BCrypt.checkpw(credenciales.getContrasena(), usuarioBD.getContrasena());

                if (credencialesValidas) {
                    // ¡Login exitoso!
                    return Response.ok(usuarioBD).build();
                } else {
                    // Contraseña incorrecta
                    return Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Correo o contraseña incorrectos").build();
                }

            } catch (Exception ex) {
                return Response.serverError().entity(ex.getMessage()).build();
            }
        }
        // reseñas emitidas por el usuario, si se muestra en la pagina de inicio
        @GET
        @Path("{id}/resenas")
        public Response buscarResenasPorUsuario(@PathParam("id")int idUsuario){
            try{
                List<Resena> resenas = resenaBL.listByUsuarioId(idUsuario);
                return Response.ok(resenas).build();
            }catch(Exception e){
                return Response.serverError().entity(e.getMessage()).build();
            }
        }
    }
