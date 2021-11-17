package MovieMaster.model;

public class Favourites {
	protected int favouritesId;
	protected int userId;
	protected int movieId;
	protected boolean isRecommended;
	
	public Favourites(int favouritesId, int userId, int movieId, boolean isRecommended) {

		this.favouritesId = favouritesId;
		this.userId = userId;
		this.movieId = movieId;
		this.isRecommended = isRecommended;
	}

	public Favourites(int favouritesId) {
		super();
		this.favouritesId = favouritesId;
	}

	public Favourites(int userId, int movieId, boolean isRecommended) {
		super();
		this.userId = userId;
		this.movieId = movieId;
		this.isRecommended = isRecommended;
	}

	public int getFavouritesId() {
		return favouritesId;
	}

	public void setFavouritesId(int favouritesId) {
		this.favouritesId = favouritesId;
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

	public boolean isRecommended() {
		return isRecommended;
	}

	public void setRecommended(boolean isRecommended) {
		this.isRecommended = isRecommended;
	}


}
