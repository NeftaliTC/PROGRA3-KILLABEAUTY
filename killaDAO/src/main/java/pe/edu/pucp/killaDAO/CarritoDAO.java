package pe.edu.pucp.killaDAO;

import pe.edu.pucp.killaBeauty.killaModelo.Carrito;
import pe.edu.pucp.killaDAO.Base.BaseDAO;

import java.sql.SQLException;
import java.util.List;

public interface CarritoDAO extends BaseDAO<Carrito,Integer> {
    List<Carrito> listAll() throws SQLException;
}
