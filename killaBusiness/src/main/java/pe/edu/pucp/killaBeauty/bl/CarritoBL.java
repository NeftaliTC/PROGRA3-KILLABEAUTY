package pe.edu.pucp.killaBeauty.bl;

import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;

import java.util.List;

public interface CarritoBL {
    Carrito create(Carrito c) throws BusinessLogicException;
    Carrito update(Carrito c) throws BusinessLogicException;
    void remove(Carrito c) throws BusinessLogicException;
    Carrito load(Integer id) throws BusinessLogicException;
    List<Carrito> listAll() throws BusinessLogicException;
}
