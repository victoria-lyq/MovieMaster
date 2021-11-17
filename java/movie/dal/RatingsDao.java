package MovieMaster.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import MovieMaster.model.*;





public class RatingsDao {
	
	protected ConnectionManager connectionManager;
	private static RatingsDao instance = null;
	protected RatingsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RatingsDao getInstance() {
		if(instance == null) {
			instance = new RatingsDao();
		}
		return instance;
	}
	
	public Ratings create(Ratings rating) throws SQLException {
		String insertRatings =
				"INSERT INTO Ratings(UserId, MovieId, Score) " +
						"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRatings,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, rating.getUserId());
			insertStmt.setInt(2, rating.getMovieId());
			insertStmt.setInt(3, rating.getScore());
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int ratingId = -1;
			if(resultKey.next()) {
				ratingId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			rating.setRatingId(ratingId);
			return rating;
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
	
	
	public Ratings delete(Ratings rating) throws SQLException {
		String deleteRatings = "DELETE FROM Ratings WHERE RatingId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRatings);
			deleteStmt.setInt(1, rating.getRatingId());
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
	
	public Ratings updateScore(Ratings rating, int newScore) throws SQLException {
		String updateRatings = "UPDATE Ratings SET Score=? WHERE RatingId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRatings);
			updateStmt.setInt(1, newScore);
			updateStmt.setInt(2, rating.getRatingId());
			updateStmt.executeUpdate();
			rating.setScore(newScore);

			return rating;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	public Ratings getRatingById(int ratingId) throws SQLException {
		String selectRating =
			"SELECT * " +
			"FROM Ratings " +
			"WHERE RatingId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRating);
			selectStmt.setInt(1, ratingId);
			results = selectStmt.executeQuery();
//			UsersDao UsersDao = UsersDao.getInstance();
			if(results.next()) {
				int resultRatingId = results.getInt("RatingId");
				int userId = results.getInt("UserId");
				int movieId = results.getInt("MovieId");
				int score = results.getInt("Score");

				
//				Users user = UsersDao.getUserFromUserId(userId);
				Ratings rating = new Ratings(resultRatingId, score, userId, movieId);
				return rating;
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
	
	public List<Ratings> getRatingsForUser(int userId) throws SQLException {
		List<Ratings> ratings = new ArrayList<Ratings>();
		String selectRatings =
				"SELECT * " +
						"FROM Ratings " +
						"WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRatings);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultRatingId = results.getInt("RatingId");
				int movieId = results.getInt("MovieId");
				int score = results.getInt("Score");
				Ratings rating = new Ratings(resultRatingId, score, userId, movieId);
				ratings.add(rating);
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
		return ratings;
	}
	public List<Ratings> getRatingsForMovie(int movieId) throws SQLException {
		List<Ratings> ratings = new ArrayList<Ratings>();
		String selectRatings =
				"SELECT * " +
						"FROM Ratings " +
						"WHERE movieId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRatings);
			selectStmt.setInt(1, movieId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultRatingId = results.getInt("RatingId");
				int userId = results.getInt("UserId");
				int score = results.getInt("Score");
				Ratings rating = new Ratings(resultRatingId, score, userId, movieId);
				ratings.add(rating);
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
		return ratings;
	}
	
}
