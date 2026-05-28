package pe.edu.pucp.killaBeauty.bl;

import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.Permiso;

import java.util.List;

public interface PermisoBL {
    Permiso create(Permiso p) throws BusinessLogicException;
    Permiso update(Permiso p) throws BusinessLogicException;
    void remove(Permiso p) throws BusinessLogicException;
    Permiso load(Integer id) throws BusinessLogicException;
    List<Permiso> listAll() throws BusinessLogicException;
}
