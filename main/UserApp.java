package internship_project;

import internship_project.enums.RepStatus;
import java.util.List;
import java.util.ArrayList;

public class UserApp {

    // ALWAYS initialize here so it's never null
    private List<User> allUsers = new ArrayList<>();

    private User curUser;

    public UserApp(List<User> allUsers) {
        // if someone passes null, fall back to empty list
        this.allUsers = (allUsers != null) ? allUsers : new ArrayList<>();
        this.curUser = null;
    }

    public UserApp() {
        // keep defaults; allUsers already initialized above
        this.curUser = null;
    }

	public User getCurUser() {		
		return this.curUser;}		// returns user logged in
	
	public User getUserInfo(String userID) {
		if (userID == null) return null;
		for(User user : allUsers) {
			if(user.getUserID().equalsIgnoreCase(userID)) {
				return user;}}
		return null;}				//return all user info using userID
	
	public boolean login(String userID, String password) {		//get input userID and password
	    if (userID == null || password == null) {
	        System.out.println("Login Failed! Incorrect UserID or Password.");
	        return false; //default false (not logged in)
	    }
	    for (User user : allUsers) {	//for each user in masterlist
	        if (user.getUserID().equalsIgnoreCase(userID) && user.checkPassword(password)) { //correct userID and password
	            // extra rule for reps: must be APPROVED
	            if (user instanceof CompanyRep rep && rep.getStatus() != RepStatus.APPROVED) {
	                System.out.println("Login Failed! Your account has not been approved.");
	                return false;
	            }
	            this.curUser = user;
	            System.out.println("Login Successful! Welcome " + user.getName() + ".");
	            return true;
	        }
	    }
	    System.out.println("Login Failed! Incorrect UserID or Password.");
	    return false;
	}

	public void logout() {
		if(this.curUser != null) {
			this.curUser = null; 	//reset back to default
			System.out.println("Logout Successful!");}
		else {
			System.out.println("Logout Unsuccessful.");}}
	
	public boolean checkifLoggedIn() {
		return this.curUser != null;}
	
	public boolean createCompanyRepAccount(String userID, String name, String password, String companyName, String department, String position){			//for new accounts not already under ComppanyRep
		if(getUserInfo(userID) != null) {		// userID already exists in masterlist, means not a new account
			System.out.println("Account with the same User ID already exist. Account creation failed.");
			return false;}
		CompanyRep rep = new CompanyRep(userID, name, password, companyName, department, position);
		allUsers.add(rep);
		System.out.println("Account successfully created. Awaiting approval.");
		return true;}
		
	public void approveCompanyRep(CompanyRep rep, CareerCenterStaff staff) {
		if (!(rep.getStatus() == RepStatus.APPROVED)){
			rep.setStatus(RepStatus.APPROVED);
			System.out.println("Company representative account for " + rep.getName() + " approved by Career Center Staff: " + staff.getName());}
		else {
			System.out.println("Company representative account for " + rep.getName() + " is already approved");}}
	
	public void rejectCompanyRep(CompanyRep rep, CareerCenterStaff staff) {
		if (rep.getStatus() == RepStatus.PENDING) {
			rep.setStatus(RepStatus.REJECTED);
			System.out.println("Company representative account for " + rep.getName() + " rejected by Career Center Staff: " + staff.getName());}
		else {
			System.out.println("Company representative account for " + rep.getName() + " is not pending approval");}}
	
	public List <CompanyRep> viewRepsPending(){
		List <CompanyRep> pendingList = new ArrayList <>();
		for(User user : allUsers) {
			if(user instanceof CompanyRep) {
				CompanyRep rep =  (CompanyRep) user;
				if (rep.getStatus() == RepStatus.PENDING) {
					pendingList.add(rep);}}}						//keeps track of companyrep accounts under pending state)
	
		return pendingList;}
	
	public List <CareerCenterStaff> viewStaffList() {	
		List <CareerCenterStaff> staffList = new ArrayList <>();
		for(User user : allUsers) {
			if(user instanceof CareerCenterStaff) {
				CareerCenterStaff staff = (CareerCenterStaff) user;
				staffList.add(staff);}}
		return staffList;}											// see all staff?
	
	// add users into the list
    public void addUser(User u) {
        if (u == null) throw new IllegalArgumentException("User is null");
        String id = u.getUserID();
        if (id == null || id.isBlank()) throw new IllegalArgumentException("UserID is blank");
        if (getUserInfo(id) != null) throw new IllegalArgumentException("Duplicate userID: " + id);
        allUsers.add(u);  // List add (not Map put)
    }

    // optional overloads
    public void addUser(Student s)           { addUser((User) s); }
    public void addUser(CompanyRep r)        { addUser((User) r); }
    public void addUser(CareerCenterStaff c) { addUser((User) c); }

    // optional helpers
    public List<User> getAllUsers() {
        return allUsers;
    }
    
}
