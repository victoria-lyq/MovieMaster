package movie.tools;

import movie.dal.*;
import movie.model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.

		UsersDao usersDao = UsersDao.getInstance();
		CreditCardsDao creditCardsDao = CreditCardsDao.getInstance();
		MoviesDao moviesDao = MoviesDao.getInstance();
		RecommendationsDao recommendationsDao = RecommendationsDao.getInstance();
		
		// INSERT objects from our model.
		Users user = new Users(001,"111","Andy","A","B","ab@email.com",111111);
		user = usersDao.create(user);
		Users user1 = new Users(002,"222","Bruce","B","C","bc@email.com",222222);
		user1 = usersDao.create(user1);
		Users user2 = new Users(003,"333","Candice","C","D","cd@email.com",333333);
		user2 = usersDao.create(user2);
		
		Date date = new Date();
		
		
		CreditCards creditCard = new CreditCards(1111222233334444L,new Date(2022-04-30),"Andy",001);
		creditCard = creditCardsDao.create(creditCard);
		CreditCards creditCard1 = new CreditCards(4444333322221111L,new Date(2021-11-28),"Bruce", 002);
		creditCard1 = creditCardsDao.create(creditCard1);
		CreditCards creditCard2 = new CreditCards(1111222277778888L,new Date(2023-01-01),"Candice", 003);
		creditCard2 = creditCardsDao.create(creditCard2);
		
		Movies movie1 = new Movies(1, "movie1", 2021, 120, "Chinese", "test");
		movie1 = moviesDao.create(movie1);
		Movies movie2 = new Movies(2, "movie2", 2021, 120, "English", "test");
		movie2 = moviesDao.create(movie2);
		Movies movie3 = new Movies(3, "movie3", 2021, 120, "French", "test");
		movie3 = moviesDao.create(movie3);
		Movies movie4 = new Movies(4, "movie4", 2020, 120, "French", "test");
		movie4 = moviesDao.create(movie4);
		Movies dummy = new Movies(5, "dummy", 2020, 120, "dummy", "dummy");
		dummy = moviesDao.create(dummy);

		Recommendations recommendation1 = new Recommendations(0,user,movie1);
		  Recommendations recommendation2 = new Recommendations(0,user,movie2);
		  Recommendations recommendation3 = new Recommendations(0,user,movie3);
		  Recommendations recommendation4 = new Recommendations(0,user2,movie1);
		  Recommendations recommendation5 = new Recommendations(0,user2,movie4);
		  recommendation1  = recommendationsDao.create(recommendation1);
		  recommendation2  = recommendationsDao.create(recommendation2);
		  recommendation3  = recommendationsDao.create(recommendation3);
		  recommendation4  = recommendationsDao.create(recommendation4);
		  recommendation5  = recommendationsDao.create(recommendation5);

		

		// READ.
		
		Users u1 = usersDao.getUserByUserId(001);
		System.out.format("Reading user: ui:%s u:%s f:%s l:%s \n",u1.getUserId(), u1.getUserName(), u1.getFirstName(), u1.getLastName());

		CreditCards cc1 = creditCardsDao.getCreditCardByCardNumber(1111222233334444L);
		System.out.format("Reading CreditCard: cn:%s e:%s u:%s id%s \n",
				cc1.getCardNumber(), cc1.getExpiration() ,cc1.getUserName() ,cc1.getUserId());

		List<CreditCards> ccList1 = creditCardsDao.getCreditCardsByUserId(002);
		for(CreditCards card : ccList1) {
			System.out.format("Looping administrators: u:%s f:%s l:%s id:%s \n",
					card.getCardNumber(), card.getExpiration() ,card.getUserName() ,card.getUserId());
		}
		
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
		  
		  System.out.println("test getMovieByUserId()");
		  List<Recommendations> rec1 = recommendationsDao.getRecommendationByUserId(1);
		  for (Recommendations r: rec1) {
		  System.out.format("Reading movies: recommendationId:%s userId:%s movieId:%s \n",
				 r.getRecommendationId(), r.getUser().getUserId(), r.getMovie().getMovieId());
		  }
		  
		  System.out.println("test getMovieByMovieId()");
		  List<Recommendations> rec2 = recommendationsDao.getRecommendationByMovieId(1);
		  for (Recommendations r: rec2) {
		  System.out.format("Reading movies: recommendationId:%s userId:%s movieId:%s \n",
				 r.getRecommendationId(), r.getUser().getUserId(), r.getMovie().getMovieId());
		  }

		//Update
		
		CreditCards ccu = creditCardsDao.updateExpiration(creditCard2, date);
		System.out.format("Reading CreditCard: cn:%s e:%s u:%s id:%s \n",
				ccu.getCardNumber(), ccu.getExpiration() ,ccu.getUserName(), ccu.getUserId());
		

		//Delete
		
		Users du = usersDao.delete(user);
		System.out.println("Deleted user:"+ du);
		
		CreditCards dcc = creditCardsDao.delete(creditCard);
		System.out.println("Deleted creditCard:"+ dcc);
		
		System.out.println("test delete(Movies object)");
		dummy = moviesDao.delete(dummy);
	

	}
}
