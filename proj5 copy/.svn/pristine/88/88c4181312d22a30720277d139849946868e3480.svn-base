package project5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieDatabase {

	protected static ArrayList<Movie> movies = new ArrayList<Movie>();

	public MovieDatabase(File filename) throws FileNotFoundException {
		// create a scanner to read the filename
		Scanner filescan = new Scanner(filename);
		// create a loop so each attribute from Movie.java
		// is created as a variable
		while (filescan.hasNextLine()) {
			String title = filescan.nextLine();
			String year = filescan.nextLine();
			String director = filescan.nextLine();
			// change string year to int because in Movies.java
			// it is an int
			int intyear = Integer.parseInt(year);
			// create a new movie with Movie.java with each
			// movie from the array
			Movie m = new Movie(title, intyear, director);
			addMovie(m);
		}
		// close filescan
		filescan.close();

	}

	// returns true if added, false if not
	public boolean addMovie(Movie m) {
		return movies.add(m);
	}

	public ArrayList<Movie> searchByTitle(String keywords) {
		// for loop looks at each word from search and compares
		// it to each movie title from the file and ensures all words from
		// the search are in the movie title
		ArrayList<Movie> movFound = new ArrayList<Movie>();
		boolean found = false;
		for (int i = 0; i < movies.size(); i++) {
			Movie mov = movies.get(i);
			String search = mov.getTitle().toLowerCase();
			// when found add to movFound, arraylist of movies found from
			// keyword
			if (search.contains(keywords)) {
				found = true;
				movFound.add(mov);
			}
		}
		if (movFound.size() == 0) {
			System.out.println("No results found");
		}
		// return arraylist
		return movFound;
	}

	// get movie by title
	// loop through movie arraylist and get movie and compare to title
	// if it equals to, return movie
	public Movie getMoviebyTitle(String title) {
		for (int i = 0; i < movies.size(); i++) {
			Movie mov = movies.get(i);
			String t = title.toLowerCase();
			String search = mov.getTitle().toLowerCase();
			if (search.compareTo(t) == 0) {
				return mov;
			}
		}
		return null;
	}
}
