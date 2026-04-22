package pe.edu.pucp.killaDAO.Imp;

import pe.edu.pucp.killaBeauty.killaModelo.RolPermiso;
import pe.edu.pucp.killaDAO.Base.BaseDAO;

import java.sql.SQLException;
import java.util.List;
public interface RolPermisoDAO extends BaseDAO<RolPermiso,Integer> {
    List<RolPermiso> listAll() throws SQLException;
}