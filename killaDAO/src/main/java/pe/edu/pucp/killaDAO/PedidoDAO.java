package pe.edu.pucp.killaDAO;

import pe.edu.pucp.killaBeauty.killaModelo.Pedido;
import pe.edu.pucp.killaDAO.Base.BaseDAO;

import java.sql.SQLException;
import java.util.List;
public interface PedidoDAO extends BaseDAO<Pedido,Integer> {
    List<Pedido> listAll() throws SQLException;
    void updateEstado(Integer idPedido, Integer idNuevoEstado) throws SQLException;
}