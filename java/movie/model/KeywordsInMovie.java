package movie.model;
import java.util.Date;

public class KeywordsInMovie {
	protected int keywordsInMovieId;
	protected int keywordId;
	protected int movieId;
	
	public KeywordsInMovie(int keywordsInMovieId, int keywordId, int movieId) {
		this.keywordsInMovieId = keywordsInMovieId;
		this.keywordId = keywordId;
		this.movieId = movieId;
	}
	
	public KeywordsInMovie(int keywordsInMovieId) {
		this.keywordsInMovieId = keywordsInMovieId;
	}
	
	public KeywordsInMovie(int keywordId, int movieId) {
		this.keywordId = keywordId;
		this.movieId = movieId;
	}
	public int getKeywordsInMovieId() {
		return keywordsInMovieId;
	}
	public void setKeywordsInMovieId(int keywordsInMovieId) {
		this.keywordsInMovieId = keywordsInMovieId;
	}
	public int getKeywordId() {
		return keywordId;
	}
	public void setKeywordId(int keywordId) {
		this.keywordId = keywordId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
}
