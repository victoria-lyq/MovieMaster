package movie.tools;

import java.sql.SQLException;
import java.util.List;

import movie.dal.*;
import movie.model.*;

public class Inserter {
	 public static void main(String[] args) throws SQLException {
		  MoviesDao moviesDao = MoviesDao.getInstance();
		  Movies movie1 = new Movies(1, "movie1", 2021, 120, "Chinese", "test");
		  Movies movie2 = new Movies(2, "movie2", 2021, 120, "English", "test");
		  Movies movie3 = new Movies(3, "movie3", 2021, 120, "French", "test");
		  Movies movie4 = new Movies(4, "movie4", 2020, 120, "French", "test");
		  Movies dummy = new Movies(5, "dummy", 2020, 120, "dummy", "dummy");
		  movie1 = moviesDao.create(movie1);
		  movie2 = moviesDao.create(movie2);
		  movie3 = moviesDao.create(movie3);
		  movie4 = moviesDao.create(movie4);
		  dummy = moviesDao.create(dummy);
		  
		  System.out.println("test delete(Movies object)");
		  dummy = moviesDao.delete(dummy);
		  
		  
		  System.out.println("test getMovieByMovieId()");
		  Movies m1 = moviesDao.getMovieByMovieId(1);
		  System.out.format("Reading movies: movieId:%s title:%s year:%s duration:%s languages:%s description:%s \n",
				  m1.getMovieId(), m1.getTitle(),m1.getYear(), m1.getDuration(), 
				  m1.getLanguages(), m1.getDescription());
		  
		  System.out.println("test getMovieByYear()");
		  List<Movies> movies = moviesDao.getMovieByYear(2021);
		  for (Movies m: movies) {
		  System.out.format("Reading movies: movieId:%s title:%s year:%s duration:%s languages:%s description:%s \n",
				  m.getMovieId(), m.getTitle(),m.getYear(), m.getDuration(), 
				  m.getLanguages(), m.getDescription());
		  }
		  
		  System.out.println("test getMovieByLanguage()");
		  List<Movies> movies2 = moviesDao.getMovieByLanguage("French");
		  for (Movies m: movies2) {
		  System.out.format("Reading movies: movieId:%s title:%s year:%s duration:%s languages:%s description:%s \n",
				  m.getMovieId(), m.getTitle(),m.getYear(), m.getDuration(), 
				  m.getLanguages(), m.getDescription());
		  }
		  
		  System.out.println("test getMovieByTitle()");
		  List<Movies> movies3 = moviesDao.getMovieByTitle("movie1");
		  for (Movies m: movies3) {
		  System.out.format("Reading movies: movieId:%s title:%s year:%s duration:%s languages:%s description:%s \n",
				  m.getMovieId(), m.getTitle(),m.getYear(), m.getDuration(), 
				  m.getLanguages(), m.getDescription());
		  }
		  
		  
		  RecommendationsDao recommendationsDao = RecommendationsDao.getInstance();
		  Recommendations recommendation1 = new Recommendations(0,1,1);
		  Recommendations recommendation2 = new Recommendations(0,1,2);
		  Recommendations recommendation3 = new Recommendations(0,1,3);
		  Recommendations recommendation4 = new Recommendations(0,2,1);
		  Recommendations recommendation5 = new Recommendations(0,2,4);
		  recommendation1  = recommendationsDao.create(recommendation1);
		  recommendation2  = recommendationsDao.create(recommendation2);
		  recommendation3  = recommendationsDao.create(recommendation3);
		  recommendation4  = recommendationsDao.create(recommendation4);
		  recommendation5  = recommendationsDao.create(recommendation5);
		  
		  System.out.println("test getMovieByUserId()");
		  List<Recommendations> rec1 = recommendationsDao.getRecommendationByUserId(1);
		  for (Recommendations r: rec1) {
		  System.out.format("Reading movies: recommendationId:%s userId:%s movieId:%s \n",
				 r.getRecommendationId(), r.getUserId(), r.getMovieId());
		  }
		  
		  System.out.println("test getMovieByMovieId()");
		  List<Recommendations> rec2 = recommendationsDao.getRecommendationByMovieId(1);
		  for (Recommendations r: rec2) {
		  System.out.format("Reading movies: recommendationId:%s userId:%s movieId:%s \n",
				 r.getRecommendationId(), r.getUserId(), r.getMovieId());
		  }
	 }
}
