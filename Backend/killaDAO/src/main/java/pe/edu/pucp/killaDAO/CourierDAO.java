package pe.edu.pucp.killaDAO;

import pe.edu.pucp.killaBeauty.killaModelo.Courier;
import pe.edu.pucp.killaDAO.Base.BaseDAO;

import java.sql.SQLException;
import java.util.List;

public interface CourierDAO extends BaseDAO<Courier, Integer> {
    List<Courier> listAll() throws SQLException;
}
