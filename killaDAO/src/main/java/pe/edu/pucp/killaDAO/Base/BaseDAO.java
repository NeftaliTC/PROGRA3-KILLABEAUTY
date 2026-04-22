package pe.edu.pucp.killaDAO.Base;
import java.sql.SQLException;
public interface BaseDAO<T, ID> {
    //CREATE--> save  READ-->load  update-->update  delete-->remove
    T load(ID id) throws SQLException;
    T save(ID id)throws SQLException;
    T update(ID id)throws SQLException;
    void remove(ID id)throws SQLException;


}
