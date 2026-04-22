package pe.edu.pucp.killaDAO.Imp;

import pe.edu.pucp.killaBeauty.killaModelo.Resena;
import pe.edu.pucp.killaDAO.Base.BaseDAO;

import java.sql.SQLException;
import java.util.List;
public interface ResenaDAO extends BaseDAO<Resena,Integer> {
    List<Resena> listAll() throws SQLException;
}