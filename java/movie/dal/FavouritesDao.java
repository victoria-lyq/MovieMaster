package MovieMaster.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import MovieMaster.model.*;



public class FavouritesDao {
	protected ConnectionManager connectionManager;
	private static FavouritesDao instance = null;
	protected FavouritesDao() {
		connectionManager = new ConnectionManager();
	}
	public static FavouritesDao getInstance() {
		if(instance == null) {
			instance = new FavouritesDao();
		}
		return instance;
	}
	
	public Favourites create(Favourites favourite) throws SQLException {
		String insertFavourites =
				"INSERT INTO Favourites(UserId, MovieId, RecommendedByMovieMaster) " +
						"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFavourites,
					Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, favourite.getUserId());
			insertStmt.setInt(2, favourite.getMovieId());
			insertStmt.setBoolean(3, favourite.isRecommended());
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int favouriteId = -1;
			if(resultKey.next()) {
				favouriteId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			favourite.setFavouritesId(favouriteId);
			return favourite;
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
	
	public Favourites delete(Favourites favourites) throws SQLException {

		String deleteFavourite= "DELETE FROM Favourites WHERE FavouritesId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFavourite);
			deleteStmt.setInt(1, favourites.getFavouritesId());
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
	
	public Favourites getFavouriteById(int favoriteId) throws SQLException {
		String selectFavourites =
			"SELECT FavouritesId, UserId, MovieId,RecommendedByMovieMaster " +
			"FROM Favourites " +
			"WHERE FavouritesId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFavourites);
			selectStmt.setInt(1, favoriteId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int favouriteId = results.getInt("FavouritesId");
				int userId = results.getInt("UserId");
				int movieId = results.getInt("MovieId");
				boolean recommended = results.getBoolean("RecommendedByMovieMaster");
				Favourites favourite = new Favourites(favouriteId, userId, movieId, recommended);
				return favourite;
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
	public List<Favourites> getBlogPostsForUser(int userId) throws SQLException {
		List<Favourites> favourites = new ArrayList<Favourites>();
		String selectFavourites =
				"SELECT FavouritesId, UserId, MovieId,RecommendedByMovieMaster " +
						"FROM Favourites " +
						"WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFavourites);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int favouriteId = results.getInt("FavouritesId");
				int movieId = results.getInt("MovieId");
				boolean recommended = results.getBoolean("RecommendedByMovieMaster");
				Favourites favourite = new Favourites(favouriteId, userId, movieId, recommended);
				favourites.add(favourite);
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
		return favourites;
	}
	

}
