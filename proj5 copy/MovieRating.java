package project5;

public class MovieRating {
	private Movie movie;
	private float rating;

	//constructor for MovieRating
	public MovieRating(Movie m, float r) {
		movie = m;
		rating = r;
	}
	//getmovie
	public Movie getMovie() {
		return movie;
	}
	//getrating
	public float getRating() {
		return rating;
	}
	//setrating
	public void setRating(float r) {
		rating = r;
	}
	//makes all objects strings
	public String toString() {
		String rate = Float.toString(rating);
		return rate;
	}
}
