package Internship_project;

import Internship_project.enums.RepStatus;

public class CompanyRep extends User {
		
	private final String companyname;
	private final String department;
	private final String position;
	private RepStatus status;
	
	public CompanyRep(String userID, String name, String password, String companyname, String department, String position ) {
		super(userID, name, password);
		this.companyname = companyname;
		this.department = department;
		this.position = position;
		this.status = RepStatus.PENDING;}	//autoset default pending,  need approval
	
	public String getCompanyName() {
		return companyname;}
	
	public String getDepartment() {
		return department;}
	 
	public String getPosition() {
		return position;}
	
	public RepStatus getStatus() {
		return status;}
	
	public void setStatus(RepStatus status) {
		this.status = status;}
		
	public void showMenu() {
		System.out.println("Company Representative Menu: ");
		System.out.println("-".repeat(50));
		System.out.println("Hello" + super.getName() + "," + getCompanyName() + "!");
		System.out.println("-".repeat(50));
		System.out.println("What would you like to do?");
		System.out.println("1. Create Internship Opportunities");
		System.out.println("2. Manage My Internships");
		System.out.println("3. Manage Applications For My Internships");
		System.out.println("4. Toggle Visibility");
		System.out.println("5. Change Password");
		System.out.println("6. Logout");
	}
}

		
	
	
		
	
		

	

