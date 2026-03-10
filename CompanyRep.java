package internship_project;

import internship_project.enums.RepStatus;

public class CompanyRep extends User {
		
	private final String companyName;
	private final String department;
	private final String position;
	private RepStatus status;
	
	public CompanyRep(String userID, String name, String password, String companyName, String department, String position ) {
		super(userID, name, password);
		this.companyName = companyName;
		this.department = department;
		this.position = position;
		this.status = RepStatus.PENDING;}	//autoset default to pending,  need approval from career staff
	
	public String getCompanyName() {
		return companyName;}
	
	public String getDepartment() {
		return department;}
	 
	public String getPosition() {
		return position;}
	
	public RepStatus getStatus() {
		return status;}
	
	public void setStatus(RepStatus status) {
		this.status = status;}
		
	public void showMenu() {
		System.out.println("\nCompany Representative Menu: ");
		System.out.println("-".repeat(40));
		System.out.println("Hello " + super.getName() + " (" + getCompanyName() + ")!");
		System.out.println("-".repeat(40));
		System.out.println("What would you like to do?");
		System.out.println("1. Create Internship Opportunities");
		System.out.println("2. Manage My Internships");
		System.out.println("3. Manage Applications For My Internships");
        System.out.println("4. Set View Filters");
		System.out.println("5. Change Password");
		System.out.println("6. Logout");
		System.out.println("-".repeat(40));
	}
}
