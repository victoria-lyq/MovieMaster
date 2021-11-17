package review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import review.model.*;

public class ActorsDao {
  protected ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static ActorsDao instance = null;
  protected ActorsDao() {
    connectionManager = new ConnectionManager();
  }
  public static ActorsDao getInstance() {
    if(instance == null) {
      instance = new ActorsDao();
    }
    return instance;
  }


  public Actors create(Actors actor) throws SQLException {
    String insertActor = "INSERT INTO Actors(ActorId,ActorName) VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertUser);
      insertStmt.setString(1, actor.getActorId());
      insertStmt.setString(2, actor.getActorName());
      insertStmt.executeUpdate();
      return actor;
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

  public Actors delete(Actors actor) throws SQLException {
    String deleteActor = "DELETE FROM Actors WHERE ActorId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteActor);
      deleteStmt.setString(1, actor.getActorId());
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

  public Actors getActorByActorId(String actorId) throws SQLException {
    String selectActor = "SELECT ActorId,ActorName FROM Actors WHERE ActorId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectActor);
      selectStmt.setString(1, actorId);
      results = selectStmt.executeQuery();
      if(results.next()) {
        Integer resultActorId = results.getInt("ActorId");
        String actorName = results.getString("ActorName");
        Actors actor = new Actors(resultActorId, actorName);
        return actor;
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
