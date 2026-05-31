package pe.edu.pucp.killaDAO.Impl;
import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.killaBeauty.killaModelo.CarritoDeCompras;
import pe.edu.pucp.killaDAO.CarritoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoDAOImpl implements CarritoDAO {

    @Override
    public List<CarritoDeCompras> listAll() throws SQLException {
        return List.of();
    }

    @Override
    public CarritoDeCompras load(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public CarritoDeCompras save(CarritoDeCompras carritoDeCompras) throws SQLException {
        return null;
    }

    @Override
    public CarritoDeCompras update(CarritoDeCompras carritoDeCompras) throws SQLException {
        return null;
    }

    @Override
    public void remove(CarritoDeCompras carritoDeCompras) throws SQLException {

    }

 //   @Override
//    public List<Carrito> listAll() throws SQLException {
//        List<Carrito> carritos = new ArrayList<>();
//        String sql = "SELECT id_carrito, fecha, estado FROM Carrito";
//        try (Connection connection = DBManager.getInstance().getConnection();
//             PreparedStatement pstmt = connection.prepareStatement(sql);
//             ResultSet rs = pstmt.executeQuery()) {
//            while (rs.next()) {
//                Carrito carrito = new Carrito();
//                carrito.setId(rs.getInt(1));
//                carrito.setFechaDeCreacion(rs.getTimestamp(2));
//                carrito.setEstado(EstadoCarro.valueOf(rs.getString(3)));
//                carritos.add(carrito);
//            }
//        }
//        return carritos;
//    }
//
//    @Override
//    public Carrito load(Integer id) throws SQLException {
//        String sql="SELECT id_carrito, fecha, estado FROM Carrito WHERE id_carrito = ?";
//        try(Connection connection=DBManager.getInstance().getConnection();
//             PreparedStatement pstmt=connection.prepareStatement(sql)) {
//            pstmt.setInt(1, id);
//            try (ResultSet rs = pstmt.executeQuery()) {
//                if (rs.next()) {
//                    Carrito carrito = new Carrito();
//                    carrito.setId(rs.getInt(1));
//                    carrito.setFechaDeCreacion(rs.getTimestamp(2));
//                    carrito.setEstado(EstadoCarro.valueOf(rs.getString(3)));
//                    return carrito;
//                }
//            }
//        }
//        return null;
//    }
//
//
//    @Override
//    public Carrito save(Carrito carrito) throws SQLException {
//        String sql="INSERT INTO Carrito (fecha, estado) VALUES (?, ?)";
//        try (Connection connection = DBManager.getInstance().getConnection();
//             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//            pstmt.setTimestamp(1, new Timestamp(carrito.getFechaDeCreacion().getTime()));
//            pstmt.setString(2, carrito.getEstado().name());
//            int affectedRows = pstmt.executeUpdate();
//            if (affectedRows > 0) {
//                try (ResultSet keys = pstmt.getGeneratedKeys()) {
//                    if (keys.next()) {
//                        carrito.setId(keys.getInt(1));
//                    }
//                }
//            }
//        }
//        return carrito;
//    }
//
//    @Override
//    public Carrito update(Carrito carrito) throws SQLException {
//        String sql="UPDATE Carrito SET fecha = ?, estado = ? WHERE id_carrito = ?";
//        try (Connection connection = DBManager.getInstance().getConnection();
//             PreparedStatement pstmt = connection.prepareStatement(sql)) {
//            pstmt.setTimestamp(1, new Timestamp(carrito.getFechaDeCreacion().getTime()));
//            pstmt.setString(2, carrito.getEstado().name());
//            pstmt.setInt(3, carrito.getId());
//            pstmt.executeUpdate();
//            return carrito;
//        } catch (SQLException e){
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void remove(Carrito carrito) throws SQLException {
//        String sql = "DELETE FROM Carrito WHERE id_carrito = ?";
//        try (Connection connection = DBManager.getInstance().getConnection();
//             PreparedStatement pstmt = connection.prepareStatement(sql)) {
//            pstmt.setInt(1, carrito.getId());
//            pstmt.executeUpdate();
//        }
//    }
}
