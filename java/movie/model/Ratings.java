package MovieMaster.model;



public class Ratings {
	
	protected int ratingId;
	protected int score;
	protected int userId;
	protected int movieId;
	public Ratings(int ratingId, int score, int userId, int movieId) {
		super();
		this.ratingId = ratingId;
		this.score = score;
		this.userId = userId;
		this.movieId = movieId;
	}
	public Ratings(int ratingId) {
		super();
		this.ratingId = ratingId;
	}
	public Ratings(int score, int userId, int movieId) {
		super();
		this.score = score;
		this.userId = userId;
		this.movieId = movieId;
	}
	public int getRatingId() {
		return ratingId;
	}
	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
}
