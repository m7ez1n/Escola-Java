package app.database;

// importações de bibliotecas 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// fazendo a classe de conexão 
public class ConnectionFactory {
  // declarando os logins do banco de dados
  private static final String DRIVER = "com.mysql.jdbc.Driver";
  private static final String URL = "jdbc:mysql://localhost:3308/escola-java";
  private static final String USER = "root";
  private static final String PASS = "";

  // metódo para abrir uma conexão
  public static Connection getConnection() {
    try {
      Class.forName(DRIVER);

      return DriverManager.getConnection(URL, USER, PASS);
    } catch (ClassNotFoundException | SQLException ex) {
      throw new RuntimeException("Connection error: ", ex);
    }
  }

  // primeiro metódo estático para tratar uma exeção no fechamento
  public static void closeConnection(Connection connection) {
    try {
      if (connection != null) {
        connection.close();
      }
    } catch (SQLException ex) {
      Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  // segundo metódo estático para tratar uma exeção no fechamento
  public static void closeConnection(Connection connection, PreparedStatement stmt) {

    closeConnection(connection);

    try {
      if (stmt != null) {
        stmt.close();
      }
    } catch (SQLException ex) {
      Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  // terceiro metódo estático para tratar uma exeção no fechamento
  public static void closeConnection(Connection connection, PreparedStatement stmt, ResultSet rs) {

    closeConnection(connection, stmt);

    try {
      if (rs != null) {
        rs.close();
      }
    } catch (SQLException ex) {
      Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}