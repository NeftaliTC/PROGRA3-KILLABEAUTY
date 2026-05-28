package pe.edu.pucp.killaBeauty.bl;

import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.DetalleCarrito;

import java.util.List;

public interface DetalleCarritoBL {
    DetalleCarrito create(DetalleCarrito d) throws BusinessLogicException;
    DetalleCarrito update(DetalleCarrito d) throws BusinessLogicException;
    void remove(DetalleCarrito d) throws BusinessLogicException;
    DetalleCarrito load(Integer id) throws BusinessLogicException;
    List<DetalleCarrito> listAll() throws BusinessLogicException;
}