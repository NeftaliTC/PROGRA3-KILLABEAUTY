package pe.edu.pucp.killaDAO.Impl;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.killaBeauty.killaModelo.Cupon;
import pe.edu.pucp.killaDAO.CuponDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuponDAOImpl implements CuponDAO {

    @Override
    public List<Cupon> listAll() throws SQLException {
        List<Cupon> cupones = new ArrayList<>();
        String sql = "SELECT id_cupon, codigo, descripcion, monto_maximo, porcentaje_descuento, fecha_inicio, fecha_fin, monto_minimo_compRA, activo FROM Cupon";
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Cupon cupon = new Cupon();
                cupon.setIdCupon(rs.getInt("id_cupon"));
                cupon.setCodigo(rs.getString("codigo"));
                cupon.setDescripcion(rs.getString("descripcion"));
                cupon.setMontoMaximo(rs.getDouble("monto_maximo"));
                cupon.setPorcentajeDeDescuento(rs.getDouble("porcentaje_descuento"));

                // CONVERSIÓN DE SQL Date A LocalDate
                if (rs.getDate("fecha_inicio") != null)
                    cupon.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
                if (rs.getDate("fecha_fin") != null)
                    cupon.setFechaFin(rs.getDate("fecha_fin").toLocalDate());

                cupon.setMontoMinimoCompra(rs.getDouble("monto_minimo_compRA"));
                cupon.setActivo(rs.getBoolean("activo"));
                cupones.add(cupon);
            }
        }
        return cupones;
    }

    @Override
    public Cupon load(Integer id) throws SQLException {
        String sql = "SELECT id_cupon, codigo, descripcion, monto_maximo, porcentaje_descuento, fecha_inicio, fecha_fin, monto_minimo_compRA, activo FROM Cupon WHERE id_cupon = ?";
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Cupon cupon = new Cupon();
                    cupon.setIdCupon(rs.getInt("id_cupon"));
                    cupon.setCodigo(rs.getString("codigo"));
                    cupon.setDescripcion(rs.getString("descripcion"));
                    cupon.setMontoMaximo(rs.getDouble("monto_maximo"));
                    cupon.setPorcentajeDeDescuento(rs.getDouble("porcentaje_descuento"));

                    // CONVERSIÓN DE SQL Date A LocalDate
                    if (rs.getDate("fecha_inicio") != null)
                        cupon.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
                    if (rs.getDate("fecha_fin") != null)
                        cupon.setFechaFin(rs.getDate("fecha_fin").toLocalDate());

                    cupon.setMontoMinimoCompra(rs.getDouble("monto_minimo_compRA"));
                    cupon.setActivo(rs.getBoolean("activo"));
                    return cupon;
                }
            }
        }
        return null;
    }

    @Override
    public Cupon save(Cupon cupon) throws SQLException {
        String sql = "INSERT INTO Cupon (codigo, descripcion, monto_maximo, porcentaje_descuento, fecha_inicio, fecha_fin, monto_minimo_compRA, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, cupon.getCodigo());
            pstmt.setString(2, cupon.getDescripcion());
            pstmt.setDouble(3, cupon.getMontoMaximo());
            pstmt.setDouble(4, cupon.getPorcentajeDeDescuento());

            // CONVERSIÓN DE LocalDate A SQL Date
            pstmt.setDate(5, Date.valueOf(cupon.getFechaInicio()));
            pstmt.setDate(6, Date.valueOf(cupon.getFechaFin()));

            pstmt.setDouble(7, cupon.getMontoMinimoCompra());
            pstmt.setBoolean(8, cupon.isActivo());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet keys = pstmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        cupon.setIdCupon(keys.getInt(1));
                    }
                }
            }
        }
        return cupon;
    }

    @Override
    public Cupon update(Cupon cupon) throws SQLException {
        String sql = "UPDATE Cupon SET codigo = ?, descripcion = ?, monto_maximo = ?, porcentaje_descuento = ?, fecha_inicio = ?, fecha_fin = ?, monto_minimo_compRA = ?, activo = ? WHERE id_cupon = ?";
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, cupon.getCodigo());
            pstmt.setString(2, cupon.getDescripcion());
            pstmt.setDouble(3, cupon.getMontoMaximo());
            pstmt.setDouble(4, cupon.getPorcentajeDeDescuento());

            // CONVERSIÓN DE LocalDate A SQL Date
            pstmt.setDate(5, Date.valueOf(cupon.getFechaInicio()));
            pstmt.setDate(6, Date.valueOf(cupon.getFechaFin()));

            pstmt.setDouble(7, cupon.getMontoMinimoCompra());
            pstmt.setBoolean(8, cupon.isActivo());
            pstmt.setInt(9, cupon.getIdCupon());

            pstmt.executeUpdate();
        }
        return cupon;
    }

    @Override
    public void remove(Cupon cupon) throws SQLException {
        // Si decidiste hacerlo igual que tu compañera (Borrado Físico):
        String sql = "DELETE FROM Cupon WHERE id_cupon = ?";
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, cupon.getIdCupon());
            pstmt.executeUpdate();
        }
    }
}