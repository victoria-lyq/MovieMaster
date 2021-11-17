package movie.model;

public class Recommendations {
	protected int recommendationId;
	protected int userId;
	protected int movieId;
	public Recommendations(int recommendationId, int userId, int movieId) {
		this.recommendationId = recommendationId;
		this.userId = userId;
		this.movieId = movieId;
	}
	public int getRecommendationId() {
		return recommendationId;
	}
	public void setRecommendationId(int recommendationId) {
		this.recommendationId = recommendationId;
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
