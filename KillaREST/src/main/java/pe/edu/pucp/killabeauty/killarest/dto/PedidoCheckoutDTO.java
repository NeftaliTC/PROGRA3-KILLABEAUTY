package pe.edu.pucp.killabeauty.killarest.dto;

import java.util.ArrayList;
import java.util.List;

public class PedidoCheckoutDTO {
    private Integer usuarioId;
    private Integer direccionId;
    private Integer cuponId;
    private List<ItemCarritoDTO> items = new ArrayList<>();

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getDireccionId() {
        return direccionId;
    }

    public void setDireccionId(Integer direccionId) {
        this.direccionId = direccionId;
    }

    public Integer getCuponId() {
        return cuponId;
    }

    public void setCuponId(Integer cuponId) {
        this.cuponId = cuponId;
    }

    public List<ItemCarritoDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemCarritoDTO> items) {
        this.items = items;
    }

    public static class ItemCarritoDTO {
        private Integer productoId;
        private Integer cantidad;

        public Integer getProductoId() {
            return productoId;
        }

        public void setProductoId(Integer productoId) {
            this.productoId = productoId;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }
    }
}
