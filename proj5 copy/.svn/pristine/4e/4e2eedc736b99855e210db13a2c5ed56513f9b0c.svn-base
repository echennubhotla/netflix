package project5;

import java.util.ArrayList;

public class User {

	private String firstName;
	private String lastName;
	private String username;
	private String password;
	protected static ArrayList<User> userList = new ArrayList<User>();
	protected ArrayList<MovieRating> seenMovies;
	private ArrayList<Movie> moviesSeen;

	// constructor for user
	//first name, last name, username, password
	//arraylist seenmovies for each user
	public User(String f, String l, String u, String p) {
		firstName = f;
		lastName = l;
		username = u;
		password = p;
		seenMovies = new ArrayList<MovieRating>();
	}

	// get first name
	public String getFirstName() {
		return firstName;
	}

	// get last name
	public String getLastName() {
		return lastName;
	}

	// get seenmovies adds it to a new arraylist moviesseen
	public ArrayList<Movie> getSeenMovies() {
		//create new array moviesSeen each time, so it gets updated
		ArrayList<Movie>moviesSeen = new ArrayList<>();
		for (int i = 0; i < seenMovies.size(); i++) {
			Movie m = seenMovies.get(i).getMovie();
				moviesSeen.add(m);
		}
		return moviesSeen;

	}

	// checks if passwords match
	public boolean login(String p) {
		if (password.equals(p)) {
			return true;
		} else {
			return false;
		}
	}

	// gets rating of movie if user has entered a rating, if not
	// return 0
	public float getRating(Movie m) {
		for (int i = 0; i < seenMovies.size(); i++) {
			Movie mov = seenMovies.get(i).getMovie();
			if (m.equals(mov)) {
				float r = seenMovies.get(i).getRating();
				return r;
			}
		}
		return 0;
	}

	// add rating of movie to user
	public void addRating(Movie m, float r) {
		// if arraylist seenmovies is 0, creates movierating and adds to
		// seenmovie and movie
		if (seenMovies.size() == 0) {
			m.addRating(r);
			MovieRating n = new MovieRating(m, r);
			seenMovies.add(n);
			// checks if movie already exists in seenmovies and updates it
			// else adds to seenmovies 
		} else {
			for (int i = 0; i < seenMovies.size(); i++) {
				Movie mov = seenMovies.get(i).getMovie();
				if (m.equals(mov)) {
					seenMovies.get(i).setRating(r);
					mov.addRating(r);
				} else if (i == (seenMovies.size() - 1)) {
					MovieRating n = new MovieRating(m, r);
					seenMovies.add(n);
				}

			}
		}

	}

}
