package pe.edu.pucp.killaDAO.baseDao;
import java.sql.SQLException;
public interface baseDao<T, ID> {
    T load(ID id) throws SQLException;
    T save(ID id)throws SQLException;
    T update(ID id)throws SQLException;
    void remove(ID id)throws SQLException;


}
