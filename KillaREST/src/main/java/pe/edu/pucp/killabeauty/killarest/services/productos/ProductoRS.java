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
import pe.edu.pucp.killabeauty.killarest.dto.ErrorDTO;
import pe.edu.pucp.killabeauty.killarest.dto.ProductoCatalogoDTO;
import pe.edu.pucp.killaBeauty.bl.Impl.ProductoBLImpl;
import pe.edu.pucp.killaBeauty.bl.ProductoBL;
import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.Producto;

import java.util.List;

@Path("/productos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductoRS {
    private final ProductoBL productoBL = new ProductoBLImpl();

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
}
