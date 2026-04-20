package pe.edu.pucp.dbManager;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando prueba");

        try {

            dbManager manager = dbManager.getInstance();


            Connection con = manager.getConnection();

            if (con != null) {
                System.out.println("==========================================");
                System.out.println("   CONEXIÓN EXITOSA");
                System.out.println("==========================================");
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR DE CONEXIÓN: " + e.getMessage());
        }
    }
}