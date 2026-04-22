package pe.edu.pucp.killaDAO;

import pe.edu.pucp.killaBeauty.killaModelo.Cupon;
import pe.edu.pucp.killaDAO.Impl.CuponDAOImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TestCuponDAO {
    public static void main(String[] args) {
        try {
            CuponDAO cuponDAO = new CuponDAOImpl();
            // 1) SAVE - Prueba de guardado
            Cupon nuevo = new Cupon();
            nuevo.setCodigo("KILLATEST2026");
            nuevo.setDescripcion("Descuento de prueba");
            nuevo.setMontoMaximo(50.0);
            nuevo.setPorcentajeDeDescuento(20.0);
            nuevo.setFechaInicio(LocalDate.now());
            nuevo.setFechaFin(LocalDate.now().plusDays(7));
            nuevo.setMontoMinimoCompra(100.0);
            nuevo.setActivo(true);

            Cupon guardado = cuponDAO.save(nuevo);
            System.out.println("SAVE OK -> id: " + guardado.getIdCupon());

            // 2) LOAD
            Cupon cargado = cuponDAO.load(guardado.getIdCupon());
            System.out.println("LOAD OK -> " + (cargado != null ? cargado.getCodigo() : "null"));

            // 3) UPDATE - Actualización
            if (cargado != null) {
                cargado.setPorcentajeDeDescuento(25.0);
                cargado.setDescripcion("Descuento ACTUALIZADO");
                cuponDAO.update(cargado);
                Cupon actualizado = cuponDAO.load(cargado.getIdCupon());
                System.out.println("UPDATE OK -> porcentaje: " + actualizado.getPorcentajeDeDescuento());
            }

            // 4) LIST ALL
            List<Cupon> cupones = cuponDAO.listAll();
            System.out.println("LIST ALL -> total: " + cupones.size());
            for (Cupon c : cupones) {
                System.out.println(" - " + c.getIdCupon() + " | " + c.getCodigo() + " | activo: " + c.isActivo());
            }

            // 5) REMOVE (Borrado lógico)
            if (cargado != null) {
                cuponDAO.remove(cargado);
                Cupon eliminado = cuponDAO.load(cargado.getIdCupon());
                //Cupon aun existe pero ya no funciona (no vigente)
                System.out.println("REMOVE OK -> " + (eliminado != null && !eliminado.isActivo() ? "inactivo" : "aun activo"));
            }

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
