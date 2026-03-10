package internship_project;

import java.time.LocalDate;

import internship_project.enums.InternshipLevel;
import internship_project.enums.InternshipStatus;

public class Internship {
    private final int id; // id should not change once allocated
    
    // ID tracker
    private static int nextIdTracker = 1; 
    public static void setNextId(int nextId) {
        nextIdTracker = nextId;
    }

    private String internshipTitle;
    private String description;
    private InternshipLevel internshipLevel;
    private String preferredMajor;
    private LocalDate applicationClosingDate;
    private LocalDate applicationOpeningDate;
    private InternshipStatus status; 
    private String companyName;
    private String companyRepId; 
    private int numberOfSlots; // 1..*
    private int confirmedSlots; // 0..slots
    private boolean visible; // visibility toggle

    // original constructor for new creation
    public Internship(String internshipTitle, String description, InternshipLevel internshipLevel,
                      String preferredMajor, LocalDate applicationClosingDate, LocalDate applicationOpeningDate,
                      String companyName, String companyRepId, int numberOfSlots) {
        
        // use static tracker
        this.id = nextIdTracker++; 

        this.internshipTitle = internshipTitle;
        this.description = description;
        this.internshipLevel = internshipLevel;
        this.preferredMajor = preferredMajor;
        this.applicationClosingDate = applicationClosingDate;
        this.applicationOpeningDate = applicationOpeningDate;
        this.companyName = companyName;
        this.companyRepId = companyRepId; 
        this.numberOfSlots = numberOfSlots;
        
        
        this.status = InternshipStatus.PENDING;
        this.confirmedSlots = 0;
        this.visible = false;
    }
    
    // loading from CSV
    public Internship(int id, String internshipTitle, String description, InternshipLevel internshipLevel,
                      String preferredMajor, LocalDate applicationClosingDate, LocalDate applicationOpeningDate,
                      String companyName, String companyRepId, int numberOfSlots) {
        
        this.id = id; // use the ID from the file
        
        this.internshipTitle = internshipTitle;
        this.description = description;
        this.internshipLevel = internshipLevel;
        this.preferredMajor = preferredMajor;
        this.applicationClosingDate = applicationClosingDate;
        this.applicationOpeningDate = applicationOpeningDate;
        this.companyName = companyName;
        this.companyRepId = companyRepId; 
        this.numberOfSlots = numberOfSlots;
        
        // defaults overwritten by the loader
        this.status = InternshipStatus.PENDING; 
        this.confirmedSlots = 0;
        this.visible = false;
    }
    
    // getter and setters
    public int getId() { return id; }

    public String getInternshipTitle() { return internshipTitle; }
    public void setInternshipTitle(String internshipTitle) { this.internshipTitle = internshipTitle; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public InternshipLevel getInternshipLevel() { return internshipLevel; }
    public void setInternshipLevel(InternshipLevel internshipLevel) { this.internshipLevel = internshipLevel; }

    public String getPreferredMajor() { return preferredMajor; }
    public void setPreferredMajor(String preferredMajor) { this.preferredMajor = preferredMajor; }

    public LocalDate getApplicationOpeningDate() { return applicationOpeningDate; }
    public void setApplicationOpeningDate(LocalDate applicationOpeningDate) { this.applicationOpeningDate = applicationOpeningDate; }

    public LocalDate getApplicationClosingDate() { return applicationClosingDate; }
    public void setApplicationClosingDate(LocalDate applicationClosingDate) { this.applicationClosingDate = applicationClosingDate; }

    public InternshipStatus getStatus() { return status; }
    public void setStatus(InternshipStatus status) { this.status = status; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getCompanyRepId() { return companyRepId; }
    public void setCompanyRepId(String companyRepId) { this.companyRepId = companyRepId; }

    public int getNumberOfSlots() { return numberOfSlots; }
    public void setNumberOfSlots(int numberOfSlots) { this.numberOfSlots = numberOfSlots; }

    public int getConfirmedSlots() { return confirmedSlots; }
    public void setConfirmedSlots(int confirmedSlots) { this.confirmedSlots = confirmedSlots; }

    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; }

    @Override public String toString() {
        return "{\"title\": \"" + internshipTitle + "\", \"ID\": \"" + id + "\", \"Level\": \"" + internshipLevel +
               "\", \"Major\": " + preferredMajor + ", \"Opening date\": \"" + applicationOpeningDate + 
               ", \"Closing date\": \"" + applicationClosingDate + ", \"Company name\": \"" + companyName + 
               ", \"Company Rep ID\": \"" + companyRepId + ", \"Number of Slots\": \"" + numberOfSlots +  "}";
    }
}
