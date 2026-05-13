package pe.edu.pucp.killaBeauty.bl;

import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.EscalaPrecio;

import java.util.List;

public interface EscalaPrecioBL {
    EscalaPrecio create(EscalaPrecio e) throws BusinessLogicException;
    EscalaPrecio update(EscalaPrecio e) throws BusinessLogicException;
    void remove(EscalaPrecio e) throws BusinessLogicException;
    EscalaPrecio load(Integer id) throws BusinessLogicException;
    List<EscalaPrecio> listAll() throws BusinessLogicException;
}
