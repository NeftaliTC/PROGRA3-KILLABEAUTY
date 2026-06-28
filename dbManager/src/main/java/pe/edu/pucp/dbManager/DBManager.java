package pe.edu.pucp.dbManager;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class DBManager {
// publica para q los otros modelos puedan usar la clase

    private static DBManager instance;
    private Properties properties;
    private final String url;
    private final String user;
    private final String password;
    private final String DB_CREDENTIALS_FILE = "db.properties";


    private DBManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("No se encontro el driver MySQL en el classpath", ex);
        }

        // clase q se diseÃ±o para leer archivo .properties como el de db.properties

        properties = new Properties();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(DB_CREDENTIALS_FILE);
            properties.load(inputStream);
        } catch (IOException ex) {
            System.out.println("Error when loading properties file: " + ex.getMessage());
        }
        String host = properties.getProperty("host");
        String port = properties.getProperty("port");
        String database = properties.getProperty("database");
        this.url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?sslMode=DISABLED&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        this.user = properties.getProperty("user");
        this.password = properties.getProperty("password");
    }

    public static DBManager getInstance() {
        if (instance == null)
            instance = new DBManager();
        return instance;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
