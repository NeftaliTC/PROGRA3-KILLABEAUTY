package pe.edu.pucp.killaBeauty.bl;

import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.RolPermiso;

import java.util.List;

public interface RolPermisoBL {
    RolPermiso create(RolPermiso rp) throws BusinessLogicException;
    void remove(RolPermiso rp) throws BusinessLogicException;
    RolPermiso load(Integer id) throws BusinessLogicException;
    List<RolPermiso> listAll() throws BusinessLogicException;
}
