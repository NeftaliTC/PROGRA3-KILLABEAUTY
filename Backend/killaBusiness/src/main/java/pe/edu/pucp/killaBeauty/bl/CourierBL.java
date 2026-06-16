package pe.edu.pucp.killaBeauty.bl;

import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.Courier;

import java.util.List;

public interface CourierBL {
    Courier create(Courier c) throws BusinessLogicException;
    Courier update(Courier c) throws BusinessLogicException;
    Courier load(Integer id) throws BusinessLogicException;
    List<Courier> listAll() throws BusinessLogicException;
}
