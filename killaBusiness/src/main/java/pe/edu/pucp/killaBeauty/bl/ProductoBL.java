package pe.edu.pucp.killaBeauty.bl;

import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.Producto;

import java.util.List;

public interface ProductoBL {
    Producto create(Producto p) throws BusinessLogicException;
    Producto update(Producto p) throws BusinessLogicException;
    void remove(Producto p) throws BusinessLogicException;
    Producto load(Integer id) throws BusinessLogicException;
    List<Producto> listAll() throws BusinessLogicException;
}