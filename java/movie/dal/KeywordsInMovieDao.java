package movie.dal;

import movie.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class KeywordsInMovieDao {
	protected ConnectionManager connectionManager;
	private static KeywordsInMovieDao instance = null;
	protected KeywordsInMovieDao() {
		connectionManager = new ConnectionManager();
	}
	public static KeywordsInMovieDao getInstance() {
		if(instance == null) {
			instance = new KeywordsInMovieDao();
		}
		return instance;
	}
	
	public KeywordsInMovie create(KeywordsInMovie keywordsInMovie) throws SQLException {
		String insertKeywordsInMovie =
			"INSERT INTO KeywordsInMovie(KeywordId,MovieId) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertKeywordsInMovie);
			insertStmt.setInt(1, keywordsInMovie.getKeywordId());
			insertStmt.setInt(2, keywordsInMovie.getMovieId());
			insertStmt.executeUpdate();
			return keywordsInMovie;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	public KeywordsInMovie delete(KeywordsInMovie keywordsInMovie) throws SQLException {
		String deleteKeywordsInMovie = "DELETE FROM KeywordsInMovie WHERE KeywordsInMovieId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteKeywordsInMovie);
			deleteStmt.setInt(1, keywordsInMovie.getKeywordsInMovieId());
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
	
	public KeywordsInMovie getKeywordsInMovieById(int keywordsInMovieId) throws SQLException {
		String selectKeywordsInMovie =
			"SELECT KeywordsInMovieId,KeywordId,MovieId " +
			"FROM KeywordsInMovie " +
			"WHERE KeywordsInMovieId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectKeywordsInMovie);
			selectStmt.setInt(1, keywordsInMovieId);
			results = selectStmt.executeQuery();
			MoviesDao moviesDao = MoviesDao.getInstance();
			KeywordsDao keywordsDao = KeywordsDao.getInstance();
			if(results.next()) {
				int resultKeywordsInMovieId = results.getInt("keywordsInMovieIdId");
				Movies moviesId = moviesDao.getMoviesById(movieId);
				Keywords keywordsId = keywordsDao.getKeywordById(keywordId);
				KeywordsInMovie keywordsInMovie = new KeywordsInMovie(resultKeywordsInMovieId, keywordsId, moviesId);
				return keywordsInMovie;
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