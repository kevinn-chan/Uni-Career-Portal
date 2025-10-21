package Internship_project;

import Internship_project.enums.RepStatus;

import java.util.List;

import java.util.ArrayList;


public class UserApp {
	
	private List<User> allUsers;		//allUsers is masterlist to store all user objects
	
	private User curUser; 		//default null when noone logged in, curuser for user logged in
	
	public UserApp(List<User> allUsers) {
		this.allUsers = allUsers;
		this.curUser = null;}
	
	public User getCurUser() {		
		return this.curUser;}		// returns user logged in
	
	public User getUserInfo(String userID) {
		for(User user : allUsers) {
			if(user.getUserID().equals(userID)) {
				return user;}}
		return null;}				//return all user info using userID
		
	public boolean login(String userID, String password) {		//get input userID and password
		for(User user : allUsers) {								//for each user in masterlist
			if(user.getUserID().equals(userID) && user.checkPassword(password)) {		//correct userID and password
				if(user instanceof CompanyRep) {
					if(!(((CompanyRep) user).getStatus() == RepStatus.APPROVED)) {		//if companyrep not approved
						System.out.println("Login Failed! Your account has not been approved.");
						return false;}
					else {	
						this.curUser = user;
						System.out.println("Login Successful! Welcome" + user.getName() + ".");
						return true;}}
				else { 
					this.curUser = user;
					System.out.println("Login Successful! Welcome" + user.getName() + ".");
					return true;}				
				}
			else { 
				System.out.println("Login Failed! Incorrect UserID or Password.");
				return false;}
			}
		return false;}		//default false (not logged in)
	
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
	
	public List <CareerCenterStaff> viewStaffList() {				//necessary? i dont see it in the requirments
		List <CareerCenterStaff> staffList = new ArrayList <>();
		for(User user : allUsers) {
			if(user instanceof CareerCenterStaff) {
				CareerCenterStaff staff = (CareerCenterStaff) user;
				staffList.add(staff);}}
		return staffList;}											// see all staff?
	
	}

	
		
			
			
			
			
		

		
	


