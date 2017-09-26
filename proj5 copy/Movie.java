package project5;

import java.util.ArrayList;

public class Movie {
	protected final String title;
	protected final int year;
	private ArrayList<Float> ratings = new ArrayList<Float>();
	protected final String director;

	// constructor for the title, year, and director
	public Movie(String t, int y, String d) {
		title = t;
		year = y;
		director = d;
	}
	//get title
	public String getTitle() {
		return title;
	}
	//get director
	public String getDirector() {
		return director;
	}
	
	//get year
	public int getYear() {
		return year;
	}
	
	//get average ratings of a movie if no rating exists
	public float getAverageRatings() {
		float sum = 0;
		if (ratings.size() == 0) {
			return 0;
		} else {
			for (int i = 0; i < ratings.size(); i++) {
				sum += ratings.get(i);
			}
			float avg = sum / (ratings.size());
			return avg;
		}
	}
	
	//add rating to arraylist of ratings
	public void addRating(float r) {
		ratings.add(r);
	}
	
	//remove rating from arraylist of ratings
	public void removeRating(float r) {
		ratings.remove(r);
	}

	// when called, makes all of it to a string
	public String toString() {
		return title + " (" + year + ")" + "\n" + "Director: " + director;
	}

}


