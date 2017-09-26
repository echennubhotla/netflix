package project5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Driver class manages user interaction: input and output between the program
 * and users
 * 
 * @author EJ Jung
 * @version 2.0 March 3, 2013.
 */
public class Driver {

	/**
	 * There will be only one movie database and one user database in the
	 * system, so they are declared as static.
	 */
	private static MovieDatabase movieDB;
	private static UserDatabase userDB;

	/**
	 * The first program argument (args[0]) may contain the movie information
	 * file name, and the second program argument (args[1]) may contain the user
	 * information file name.
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */

	public static void main(String[] args) throws FileNotFoundException {

		File filename = null;
		Scanner scan = new Scanner(System.in);

		userDB = new UserDatabase();
		
		if (args.length > 1) {
			filename = new File(args[0]);
			movieDB = new MovieDatabase(filename);
			try {
				loadUsers(args[1]);
			} catch (FileNotFoundException e) {
				System.out.println("User file not found");
				e.printStackTrace();
			}
		} else if (args.length > 0) {
			filename = new File(args[0]);
			movieDB = new MovieDatabase(filename);
		} else {
			System.out.print("Please enter the movie database file name: ");
			String name = scan.nextLine();
			filename = new File(name);
			movieDB = new MovieDatabase(filename);
		}

		int choice = -1;
		while (choice != 0) {
			try {
				System.out.println("Welcome to USFlix! Select an option from the menu.");
				System.out.println("1 to load users and their ratings from a file");
				System.out.println("2 to login");
				System.out.println("3 to create a new account");
				System.out.println("0 to quit");
				System.out.print("Enter your choice: ");
				choice = Integer.parseInt(scan.nextLine());

				switch (choice) {
				case 0:
					System.out.println("Bye!");
					break;
				case 1:
					System.out.print("Enter the file name: ");
					loadUsers(scan.nextLine());
					break;
				case 2:
					System.out.print("Enter the username: ");
					String username = scan.nextLine();
					System.out.print("Enter the password: ");
					String password = scan.nextLine();
					User u = userDB.login(username, password);
					if (u == null)
						System.out.println("Login error");
					else {
						userMenu(u);
					}
					break;
				case 3:
					System.out.print("Enter your first name: ");
					String firstName = scan.nextLine();
					System.out.print("Enter your last name: ");
					String lastName = scan.nextLine();
					System.out.print("Enter a username: ");
					username = scan.nextLine();
					System.out.print("Enter a password: ");
					password = scan.nextLine();
					userDB.createAccount(firstName, lastName, username, password);
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println("Invalid choice");
			} catch (FileNotFoundException e) {
				System.out.println("User file is not found");
			}
		}
	}

	/**
	 * loadUsers() method loads the user information from a file. If the
	 * username is available, then the new account is created and his or her
	 * rating information gets added to the movie database and also to the user
	 * object. If the username is not available, then this method skips to the
	 * next user information (until it sees "done").
	 * 
	 * @param filename
	 *            the user information file name
	 * @throws FileNotFoundException
	 *             if the user information file is not found, then the main
	 *             method catches the exception and asks user for another file
	 *             name.
	 */
	private static void loadUsers(String filename) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(filename));
		while (scan.hasNextLine()) {
			String f = scan.nextLine();
			String l = scan.nextLine();
			String n = scan.nextLine();
			String p = scan.nextLine();
			User u = userDB.createAccount(f, l, n, p);
			String title = scan.nextLine();
			if (u != null) {
				while (!title.equals("done")) {
					Movie m = movieDB.getMoviebyTitle(title);
					if (m == null) {
						m = new Movie(title, 0, null);
						movieDB.addMovie(m);
					}
					String tmp = scan.nextLine();
					float temp = Float.parseFloat(tmp);
					u.addRating(m, temp);
					title = scan.nextLine();
				}
			}

		}
	}

	/**
	 * userMenu handles menu options that are available after logging in, such
	 * as search by title (and director if you have it) and the list of movies
	 * the user has seen before.
	 * 
	 * @param u
	 */
	private static void userMenu(User u) {
		Scanner scan = new Scanner(System.in);
		int choice = -1;
		while (choice != 0) {
			System.out.println("Welcome, " + u.getFirstName() + "! Select an option from the menu.");
			System.out.println("1 to search movies by title");
			System.out.println("2 to see the list of movies you have seen before");
			System.out.println("3 to see the recommended movies");
			System.out.println("0 to logout");
			System.out.print("Enter your choice: ");
			choice = Integer.parseInt(scan.nextLine());
			switch (choice) {
			case 1:
				System.out.print("Enter keywords: ");
				String search = scan.nextLine();
				String keywords = search.toLowerCase();
				ArrayList<Movie> searchResults = movieDB.searchByTitle(keywords);
				listMenu(u, searchResults);
				break;
			case 2:
				listMenu(u, u.getSeenMovies());
				break;
			case 3:
				float mostSimilarVal = Integer.MAX_VALUE;
				ArrayList<User> mostSimilarUser = new ArrayList<>();
				for (int i = 0; i < User.userList.size(); i++) {
					int seen = 0;
					float difference = 0;
					User user = User.userList.get(i);
					if (u != user) {
						for (int j = 0; j < MovieDatabase.movies.size(); j++) {
							float user1 = u.getRating(MovieDatabase.movies.get(j));
							float user2 = user.getRating(MovieDatabase.movies.get(j));
							if (user1 > 0 && user2 > 0) {
								difference = (float) (difference + (Math.abs(user1 - user2)));
								// movies they have seen
								seen++;
							}
						}

						float mostcommon = difference / seen;
						// update mostsimilarval and add user to the
						// arraylist and clear each time
						if (mostcommon < mostSimilarVal) {
							mostSimilarVal = mostcommon;
							mostSimilarUser.clear();
							mostSimilarUser.add(User.userList.get(i));
							// if they equal only add to arraylist
						} else if (mostcommon == mostSimilarVal) {
							mostSimilarUser.add(User.userList.get(i));
						}

					}
				}
				ArrayList<Movie> recommended = new ArrayList<>();
				ArrayList<Movie> recomMovies = new ArrayList<>();
				for (User us : mostSimilarUser) {
					recomMovies.addAll(us.getSeenMovies());
				}
				for (User us : mostSimilarUser) {
					for (Movie mov : recomMovies) {
						if (!u.getSeenMovies().contains(mov) && us.getRating(mov) >= 3.0
								&& !recommended.contains(mov)) {
							recommended.add(mov);
						}
					}
				}

				listMenu(u, recommended);
			case 0:
				return;
			}
		}
	}

	/**
	 * listMenu handles printing the list of Movies with the appropriate rating
	 * (user's own if available, average otherwise), letting user rate any of
	 * the Movies in the list.
	 * 
	 * @param u
	 * @param list
	 *            search result or the list of movies user has seen
	 */

	private static void listMenu(User u, ArrayList<Movie> list) {
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < list.size(); i++) {
			Movie m = list.get(i);
			if (list.get(i).equals(m)) {
				float rating = u.getRating(m);
				if (rating < 0.5) {
					System.out.println((i + 1) + ". " + m.getTitle() + ": " + m.getAverageRatings() + " (average)");
				} else {
					System.out.println((i + 1) + ". " + m.getTitle() + ": " + rating);
				}
			}

		}
		int choice = -1;
		while (choice != 0) {
			System.out.println("Select the movie number to rate or watch");
			System.out.println("0 to go back to previous menu");
			System.out.print("Enter your choice: ");
			choice = Integer.parseInt(scan.nextLine());
			if (choice == 0) {
				return;
			} else if (choice - 1 >= 0 && choice <= list.size()) {
				Movie m = list.get(choice - 1);
				System.out.println("1. Rate the movie");
				System.out.println("2. Watch the movie");
				System.out.print("Enter your choice: ");
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				case 1:
					System.out.print("How did you like " + m.title + "? (0.5~5 stars)");
					float rating = Float.parseFloat(scan.nextLine());
					while(rating < 0.5 || rating > 5.0){
						System.out.print("That is an invalid rating. How did you like " + m.title + "? (0.5~5 stars)");
						rating = Float.parseFloat(scan.nextLine());
					}
					u.addRating(m, rating);
					break;
				case 2:
					System.out.println("You are watching " + m.title);
					String input = "No";
					while (!input.equals("yes") && !input.equals("no")) {
						System.out.print("Would you like to rate " + m.title + "(yes/no): ");
						input = scan.nextLine().toLowerCase();
					}
					if (input.equals("yes")) {
						System.out.print("How did you like " + m.title + "? (0.5~5 stars)");
						float rate = Float.parseFloat(scan.nextLine());
						while(rate < 0.5 || rate > 5.0){
							System.out.print("That is an invalid rating. How did you like " + m.title + "? (0.5~5 stars)");
							rate = Float.parseFloat(scan.nextLine());
						}
						u.addRating(m, rate);
						break;
					} else {
						break;
					}
				}

			}
		}
	}

}