package pe.edu.pucp.killaDAO;

import pe.edu.pucp.killaBeauty.killaModelo.Direccion;
import pe.edu.pucp.killaDAO.Impl.DireccionDAOImpl;
import java.sql.SQLException;
import java.util.List;

public class TestDireccionDAO {
    public static void main(String[] args) {
        DireccionDAO dao = new DireccionDAOImpl();

        try {
            // 1) SAVE
            Direccion nueva = new Direccion();
            nueva.setDepartamento("Lima");
            nueva.setProvincia("Lima");
            nueva.setDistrito("San Miguel");
            nueva.setDireccionExacta("Av. Universitaria 1801");
            nueva.setReferencia("Cerca a la PUCP");
            nueva.setIdUsuario(1); // usuario existente

            Direccion guardada = dao.save(nueva);
            System.out.println("SAVE OK -> id: " + guardada.getIdDireccion());

            // 2) LOAD
            Direccion cargada = dao.load(guardada.getIdDireccion());
            System.out.println("LOAD OK -> " + (cargada != null ? cargada.getDireccionExacta() : "null"));

            // 3) UPDATE - Actualización
            if (cargada != null) {
                cargada.setReferencia("Actualizado por Cielo");
                dao.update(cargada);
                Direccion actualizada = dao.load(cargada.getIdDireccion());
                System.out.println("UPDATE OK -> " + actualizada.getReferencia());
            }

            // 4) LIST ALL
            List<Direccion> lista = dao.listAll();
            System.out.println("LIST ALL -> total: " + lista.size());

            // 5) REMOVE
            if (cargada != null) {
                dao.remove(cargada);
                Direccion eliminada = dao.load(cargada.getIdDireccion());
                System.out.println("REMOVE OK -> " + (eliminada == null ? "eliminado" : "aun existe"));
            }

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
