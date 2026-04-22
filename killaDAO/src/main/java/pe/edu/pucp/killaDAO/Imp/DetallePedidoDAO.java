package pe.edu.pucp.killaDAO.Imp;

import pe.edu.pucp.killaBeauty.killaModelo.DetallePedido;
import pe.edu.pucp.killaDAO.Base.BaseDAO;

import java.sql.SQLException;
import java.util.List;
public interface DetallePedidoDAO extends BaseDAO<DetallePedido,Integer> {
    List<DetallePedido> listAll() throws SQLException;
}