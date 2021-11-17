package review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import review.model.*;

public class DirectorsDao {
  protected ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static DirectorsDao instance = null;
  protected DirectorsDao() {
    connectionManager = new ConnectionManager();
  }
  public static DirectorsDao getInstance() {
    if(instance == null) {
      instance = new DirectorsDao();
    }
    return instance;
  }


  public Directors create(Directors director) throws SQLException {
    String insertDirector = "INSERT INTO Directors(DirectorId,Name) VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertDirector);
      insertStmt.setString(1, director.getDirectorId());
      insertStmt.setString(2, director.getName());
      insertStmt.executeUpdate();
      return director;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(insertStmt != null) {
        insertStmt.close();
      }
    }
  }

  public Directors delete(Directors director) throws SQLException {
    String deleteDirector = "DELETE FROM Directors WHERE DirectorId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteDirector);
      deleteStmt.setString(1, director.getDirectorId());
      deleteStmt.executeUpdate();
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }

  public Directors getDirectorByDirectorId(String directorId) throws SQLException {
    String selectDirector = "SELECT DirectorId,Name FROM Directors WHERE DirectorId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectDirector);
      selectStmt.setString(1, directorId);
      results = selectStmt.executeQuery();
      if(results.next()) {
        Integer resultDirectorId = results.getInt("DirectorId");
        String name = results.getString("Name");
        Directors director = new Directors(resultDirectorId, name);
        return director;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return null;
  }

}

