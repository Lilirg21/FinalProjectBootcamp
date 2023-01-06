package sophos.infraestructure;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class MySQL {
    private Connection conn;
    final String namedb = "reto";
    final String usrdb = "usr_reto";
    final String hostdb = "localhost";
    final String port = "3306";
    final String password = "dwCHsjNcyHGycC1a";

    private void init() throws ClassNotFoundException {
        try {
            String strConn = "jdbc:mysql://" + this.hostdb + ":" +this.port + "/" + this.namedb;

            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(strConn, this.usrdb, this.password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection conn() throws ClassNotFoundException {
        this.init();
        return this.conn;
    }

    public void CloseConnection() throws SQLException{
        if (this.conn != null) {
            this.conn.close();
        }
    }
}
