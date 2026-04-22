package pe.edu.pucp.killaDAO.Impl;

import pe.edu.pucp.killaBeauty.killaModelo.Carrito;
import pe.edu.pucp.killaDAO.CarritoDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarritoDAOImpl implements CarritoDAO {

    @Override
    public List<Carrito> listAll() throws SQLException {
        return new ArrayList<>();
    }

    @Override
    public Carrito load(Integer id) throws SQLException {
        return null;
    }

    @Override
    public Carrito save(Carrito carrito) throws SQLException {
        return null;
    }

    @Override
    public Carrito update(Carrito carrito) throws SQLException {
        return null;
    }

    @Override
    public void remove(Carrito carrito) throws SQLException {
        // sin implementar aún
    }
}
