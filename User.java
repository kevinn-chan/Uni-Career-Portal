package Internship_project;

public abstract class User {
	
	private String userID;	// unique identifier of user
	
	private String name;	// full name of user
	
	private String password;	//user's password
	
	public User(String userID, String name, String password) {
		this.userID = userID;
		this.name = name;
		this.password = password;}
	
	public String getUserID() {
		return userID;}		// gets users unique ID
	
	public String getName() {
		return name;}		// gets users name
	
	public String getPassword() {
		return password;}	// gets users password
	
	public boolean changePassword(String oldPassword, String newPassword) {
		if (!(this.password.equals(oldPassword))) {
			System.out.println("Incorrect password entered.");
			return false;}
		else {
			if (newPassword.isEmpty()) {
				System.out.println("New password cannot be empty.")
				return false;}
			
			if (this.password.equals(oldPassword)) {
			this.password = newPassword;
			System.out.println("Password successfully changed.");
			return true;}}		//method to change password
		}


//should we change the password here? then we wld be mixing ui side and code? 
/**
 * public void changePassword(Scanner sc){
 * 		System.out.println("Enter Password: "
 * 		String oldPassword = sc.next();
 * 		if (this.password == oldPassword){
 * 			System.out.print("Enter new password: ");
 * 			String newPassword = sc.next();
 * 			this.password = newPassword;
 * 			System.out.println("Password changed!");}
 * 		else{
 * 			System.out.println("Incorrect Password.");
 */
	


	public boolean checkPassword(String password) {
		return this.password.equals(password);} 	//if password is correct

	public abstract void showMenu();		// to be defined in all subclasses
}
