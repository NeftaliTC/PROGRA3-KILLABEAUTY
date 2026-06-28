package pe.edu.pucp.killabeauty.killarest.services.productos;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pe.edu.pucp.killaBeauty.bl.Impl.ResenaBLImpl;
import pe.edu.pucp.killaBeauty.bl.ResenaBL;
import pe.edu.pucp.killaBeauty.killaModelo.Resena;
import pe.edu.pucp.killabeauty.killarest.dto.ErrorDTO;
import pe.edu.pucp.killabeauty.killarest.dto.ProductoCatalogoDTO;
import pe.edu.pucp.killaBeauty.bl.Impl.ProductoBLImpl;
import pe.edu.pucp.killaBeauty.bl.ProductoBL;
import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.Producto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import pe.edu.pucp.killaBeauty.killaModelo.ImagenProducto;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import java.util.List;

@Path("/productos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductoRS {
    private final ProductoBL productoBL = new ProductoBLImpl();
    private final ResenaBL resenaBL = new ResenaBLImpl();
    private final CloudinaryService cloudinaryService = new CloudinaryService();
    @GET
    @Path("/test")
    public String test() {
        return "FUNCIONA";
    }
    @GET
    public Response listar() {
        try {
            return Response.ok(productoBL.listAll()).build();
        } catch (BusinessLogicException ex) {
            return serverError(ex);
        }
    }

    @GET
    @Path("/catalogo")
    public Response listarCatalogo() {
        try {
            List<ProductoCatalogoDTO> productos = productoBL.listAll().stream()
                    .map(ProductoCatalogoDTO::new)
                    .toList();
            return Response.ok(productos).build();
        } catch (BusinessLogicException ex) {
            return serverError(ex);
        }
    }

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") int id) {
        try {
            Producto producto = productoBL.load(id);
            if (producto == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorDTO("No existe un producto con id " + id))
                        .build();
            }
            return Response.ok(producto).build();
        } catch (BusinessLogicException ex) {
            return serverError(ex);
        }
    }

    @POST
    public Response registrar(Producto producto) {
        try {
            Producto creado = productoBL.create(producto);
            return Response.status(Response.Status.CREATED).entity(creado).build();
        } catch (BusinessLogicException ex) {
            return badRequest(ex);
        }
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") int id, Producto producto) {
        try {
            producto.setId(id);
            return Response.ok(productoBL.update(producto)).build();
        } catch (BusinessLogicException ex) {
            return badRequest(ex);
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") int id) {
        try {
            Producto producto = productoBL.load(id);
            if (producto == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorDTO("No existe un producto con id " + id))
                        .build();
            }
            productoBL.remove(producto);
            return Response.noContent().build();
        } catch (BusinessLogicException ex) {
            return serverError(ex);
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


    // RESEÑAS DEL PRODUCTO
    @GET
    @Path("{id}/resenas")
    public Response listarResenasPorProducto(@PathParam("id") int idProducto) {
        try {
            List<Resena> resenas = resenaBL.listByProductoId(idProducto);
            return Response.ok(resenas).build();
        } catch (BusinessLogicException ex) {
            return serverError(ex);
        }
    }

    // agregar reseña en un producto especifico
    @POST
    @Path("{id}/resenas")
    public Response agregarResena(@PathParam("id") int idProducto, Resena resena) {
        try {
            Producto productoAsociado = new Producto();
            productoAsociado.setId(idProducto);

            resena.setProducto(productoAsociado);
            Resena resenaCreada = resenaBL.create(resena);
            return Response.status(Response.Status.CREATED).entity(resenaCreada).build();
        } catch (BusinessLogicException ex) {
            return badRequest(ex);
        }
    }

    @POST
    @Path("/con-imagenes")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarConImagenes(
            @FormDataParam("producto") String productoJson,
            @FormDataParam("imagenes") List<FormDataBodyPart> imagenesPartes
    ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Producto producto = mapper.readValue(productoJson, Producto.class);

            List<ImagenProducto> imagenes = new ArrayList<>();

            if (imagenesPartes != null) {
                int orden = 1;

                for (FormDataBodyPart parte : imagenesPartes) {
                    InputStream inputStream = parte.getEntityAs(InputStream.class);

                    File archivoTemporal = File.createTempFile("producto_", ".jpg");
                    Files.copy(inputStream, archivoTemporal.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    String url = cloudinaryService.subirImagen(archivoTemporal);

                    ImagenProducto img = new ImagenProducto();
                    img.setUrl(url);
                    img.setTitulo("Imagen producto " + orden);
                    img.setOrden(orden);
                    img.setPrincipal(orden == 1);
                    img.setActivo(true);

                    imagenes.add(img);

                    archivoTemporal.delete();
                    orden++;
                }
            }

            Producto creado = productoBL.createConImagenes(producto, imagenes);

            return Response.status(Response.Status.CREATED).entity(creado).build();

        } catch (BusinessLogicException ex) {
            return badRequest(ex);
        } catch (Exception ex) {
            return serverError(ex);
        }
    }
}
