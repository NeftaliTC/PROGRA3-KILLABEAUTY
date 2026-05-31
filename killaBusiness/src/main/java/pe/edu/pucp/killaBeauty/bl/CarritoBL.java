package pe.edu.pucp.killaBeauty.bl;

import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.CarritoDeCompras;

import java.util.List;

public interface CarritoBL {
    CarritoDeCompras create(CarritoDeCompras c) throws BusinessLogicException;
    CarritoDeCompras update(CarritoDeCompras c) throws BusinessLogicException;
    void remove(CarritoDeCompras c) throws BusinessLogicException;
    CarritoDeCompras load(Integer id) throws BusinessLogicException;
    List<CarritoDeCompras> listAll() throws BusinessLogicException;
}
