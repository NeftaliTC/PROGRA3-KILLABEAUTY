package pe.edu.pucp.killaDAO.Imp;

import pe.edu.pucp.killaBeauty.killaModelo.Carrito;
import pe.edu.pucp.killaDAO.CarritoDAO;

import java.sql.SQLException;
import java.util.List;

public class CarritoDAOImp implements CarritoDAO {
    @Override
    public List<Carrito> listAll() throws SQLException {
        return List.of();
    }

    @Override
    public Carrito load(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public Carrito save(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public Carrito update(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public void remove(Integer integer) throws SQLException {

    }
}
