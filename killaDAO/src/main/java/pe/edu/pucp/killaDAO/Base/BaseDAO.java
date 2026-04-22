package pe.edu.pucp.killaDAO.Base;
import java.sql.SQLException;
public interface BaseDAO<T, ID> {
    T load(ID id) throws SQLException;
    T save(ID id)throws SQLException;
    T update(ID id)throws SQLException;
    void remove(ID id)throws SQLException;


}
