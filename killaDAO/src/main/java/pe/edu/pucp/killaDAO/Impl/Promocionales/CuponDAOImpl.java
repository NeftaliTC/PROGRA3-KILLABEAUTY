package pe.edu.pucp.killaDAO.Impl.Promocionales;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.killaBeauty.killaModelo.Promocionales.Cupon;
import pe.edu.pucp.killaBeauty.killaModelo.Promocionales.TipoDescuento;
import pe.edu.pucp.killaBeauty.killaModelo.Promocionales.Campana;
import pe.edu.pucp.killaDAO.Promocionales.CampanaDAO;
import pe.edu.pucp.killaDAO.Promocionales.CuponDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuponDAOImpl implements CuponDAO {

    @Override
    public List<Cupon> listAll() throws SQLException {
        List<Cupon> cupones = new ArrayList<>();
        String sql = "SELECT id_cupon, codigo, descripcion, valor_descuento, fecha_inicio, fecha_fin, " +
                "activo, monto_maximo_descuento, monto_minimo_compra, tipo_descuento, " +
                "max_usos_generales, id_campana FROM Cupon";

        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Cupon cupon = new Cupon();
                cupon.setIdCupon(rs.getInt("id_cupon"));
                cupon.setCodigo(rs.getString("codigo"));
                cupon.setDescripcion(rs.getString("descripcion"));
                cupon.setValorDescuento(rs.getDouble("valor_descuento"));
                cupon.setMontoMinimoCompra(rs.getDouble("monto_minimo_compra"));
                cupon.setMontoMaximoDescuento(rs.getDouble("monto_maximo_descuento"));
                cupon.setActivo(rs.getBoolean("activo"));
                // CONVERSIÓN DE SQL Date A LocalDate
                if (rs.getDate("fecha_inicio") != null)
                    cupon.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
                if (rs.getDate("fecha_fin") != null)
                    cupon.setFechaFin(rs.getDate("fecha_fin").toLocalDate());

                String tipoStr = rs.getString("tipo_descuento");
                if (tipoStr != null) {
                    cupon.setTipoDescuento(TipoDescuento.valueOf(tipoStr));
                }
                CampanaDAO campanaDAO = new CampanaDAOImpl();

                // pregunta -> se debe regresar el objeto completo a pesar del problema de rendimiento? o se mapea en consulta con left join ?

                int idCampana = rs.getInt("id_campana");
                if (!rs.wasNull()) {
                    Campana campanaCompleta = campanaDAO.load(idCampana);
                    cupon.setCampana(campanaCompleta);
                }
                cupones.add(cupon);
            }
        }
        return cupones;
    }

    @Override
    public Cupon load(Integer id) throws SQLException {
        String sql = "SELECT id_cupon, codigo, descripcion, valor_descuento, fecha_inicio, " +
                "fecha_fin, activo, monto_maximo_descuento, monto_minimo_compra, " +
                "tipo_descuento, max_usos_generales, id_campana " +
                "FROM Cupon WHERE id_cupon = ?";

        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Cupon cupon = new Cupon();

                    cupon.setIdCupon(rs.getInt("id_cupon"));
                    cupon.setCodigo(rs.getString("codigo"));
                    cupon.setDescripcion(rs.getString("descripcion"));
                    cupon.setValorDescuento(rs.getDouble("valor_descuento"));
                    cupon.setMontoMinimoCompra(rs.getDouble("monto_minimo_compra"));
                    cupon.setMontoMaximoDescuento(rs.getDouble("monto_maximo_descuento"));
                    cupon.setActivo(rs.getBoolean("activo"));

                    // CONVERSIÓN DE SQL Date A LocalDate
                    if (rs.getDate("fecha_inicio") != null)
                        cupon.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
                    if (rs.getDate("fecha_fin") != null)
                        cupon.setFechaFin(rs.getDate("fecha_fin").toLocalDate());

                    String tipoStr = rs.getString("tipo_descuento");
                    if (tipoStr != null) {
                        cupon.setTipoDescuento(TipoDescuento.valueOf(tipoStr));
                    }

                    cupon.setMaxUsosGenerales(rs.getInt("max_usos_generales"));

                    CampanaDAO campanaDAO = new CampanaDAOImpl();
                    int idCampana = rs.getInt("id_campana");
                    if (!rs.wasNull()) {
                        Campana camp = campanaDAO.load(idCampana);
                        cupon.setCampana(camp);
                    }
                    return cupon;
                }
            }
        }
        return null;
    }

    @Override
    public Cupon save(Cupon cupon) throws SQLException {
        String sql = "INSERT INTO Cupon (codigo, descripcion, valor_descuento, fecha_inicio, fecha_fin, " +
                "activo, monto_maximo_descuento, monto_minimo_compra, tipo_descuento, " +
                "max_usos_generales, id_campana) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, cupon.getCodigo());
            pstmt.setString(2, cupon.getDescripcion());
            pstmt.setDouble(3, cupon.getValorDescuento());

            // CONVERSIÓN DE LocalDate A SQL Date
            pstmt.setDate(4, Date.valueOf(cupon.getFechaInicio()));
            pstmt.setDate(5, Date.valueOf(cupon.getFechaFin()));

            pstmt.setBoolean(6, cupon.isActivo());
            pstmt.setDouble(7, cupon.getMontoMaximoDescuento());
            pstmt.setDouble(8, cupon.getMontoMinimoCompra());

            if (cupon.getTipoDescuento() != null) {
                pstmt.setString(9, cupon.getTipoDescuento().name());
            } else {
                pstmt.setNull(9, java.sql.Types.VARCHAR);
            }

            pstmt.setInt(10, cupon.getMaxUsosGenerales());
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
        String sql = "UPDATE Cupon SET codigo = ?, descripcion = ?, valor_descuento = ?, " +
                "fecha_inicio = ?, fecha_fin = ?, activo = ?, monto_maximo_descuento = ?, " +
                "monto_minimo_compra = ?, tipo_descuento = ?, max_usos_generales = ?, " +
                "id_campana = ? WHERE id_cupon = ?";
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, cupon.getCodigo());
            pstmt.setString(2, cupon.getDescripcion());
            pstmt.setDouble(3, cupon.getValorDescuento());

            // CONVERSIÓN DE LocalDate A SQL Date
            pstmt.setDate(4, Date.valueOf(cupon.getFechaInicio()));
            pstmt.setDate(5, Date.valueOf(cupon.getFechaFin()));

            pstmt.setBoolean(6, cupon.isActivo());
            pstmt.setDouble(7, cupon.getMontoMaximoDescuento());
            pstmt.setDouble(8, cupon.getMontoMinimoCompra());
            if (cupon.getTipoDescuento() != null) {
                pstmt.setString(9, cupon.getTipoDescuento().name());
            } else {
                pstmt.setNull(9, java.sql.Types.VARCHAR);
            }
            pstmt.setInt(10, cupon.getMaxUsosGenerales());
            if (cupon.getCampana() != null) {
                pstmt.setInt(11, cupon.getCampana().getIdCampana()); // O getIdCampana(), según cómo lo hayas dejado
            } else {
                pstmt.setNull(11, java.sql.Types.INTEGER);
            }
            pstmt.setInt(12, cupon.getIdCupon());
            pstmt.executeUpdate();
        }
        return cupon;
    }

    @Override
    public void remove(Cupon cupon) throws SQLException {
        // logica
        String sql = "UPDATE Cupon SET activo = 0 WHERE id_cupon = ?";

        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, cupon.getIdCupon());
            pstmt.executeUpdate();
        }
    }
}