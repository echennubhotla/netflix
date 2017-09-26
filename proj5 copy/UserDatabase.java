package project5;

import java.util.HashMap;

public class UserDatabase {
	
	public static int minPwdLength = 6;

	private HashMap<String, User> userDB;
	
	public UserDatabase(){
		userDB = new HashMap<String, User> ();
	}
	
	//creates user with first name, last name, username, password
	public User createAccount(String f, String l, String u, String p) {
		int count = 0;
		//loops to count length of password
		for (int i = 0; i < p.length(); i++) {
			count = count + 1;
		}
		if (count < minPwdLength) {
			System.out.println("Error: Your password has to be at least 6 characters");
			return null;
		} else if (p.contains(u)) {
			System.out.println("Error: Your password cannot contain your username");
			return null;
		} else if (!isAvailable(u)) {
			System.out.println("Error: That username is taken");
			return null;
		//if passes all checks returns user
		} else {
			User r = new User(f, l, u, p);
			userDB.put(u, r);
			User.userList.add(r);
			return r;
		}
	}
	
	//checks if the username matches username in userDB
	//if so sends to login in User.java
	public User login(String u, String p) {
		for (String key : userDB.keySet()) {
			if (key.toString().equals(u)){
				User value = userDB.get(u);
				boolean found = value.login(p);
				//if found returns user
				if (found == true){
					User r = userDB.get(u);
					return r;
				}
			}
		}
		//else returns null
		return null;
	}
	
	//checks to see if there is a user with that username already
	public boolean isAvailable(String u) {
		try {
			for (String key : userDB.keySet()) {
				if (key.contains(u)) {
					return false;
				}
			}
		} catch (NullPointerException e) {
			return true;

		}
		return true;
	}
}
