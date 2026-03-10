package internship_project;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.nio.charset.*;

import internship_project.enums.*;

// load or save for Application objects
public class ApplicationCSV {

    private final Path file;
    private final InternshipService intApp;

    // header
    private static final String[] HEADER = {
        "StudentId", "InternshipId", "Status", "WithdrawalStatus", "Accepted"
    };

    public ApplicationCSV(Path file, InternshipService service) {
        this.file = file;
        this.intApp = service;
    }

    // load applications from CSV into InternshipApp 
    public void load() throws IOException {
        if (!Files.exists(file)) return; // no file, nothing to load

        try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line; boolean first = true;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                var cols = CSVParser.parseLine(line);
                if (first && looksHeader(cols)) { first = false; continue; }
                first = false;

                try {
                    String sid = get(cols,0);
                    int iid = parseInt(get(cols,1), 0);
                    if (iid == 0 || sid.isEmpty()) continue;

                    ApplicationStatus status = ApplicationStatus.valueOf(get(cols,2));
                    WithdrawalRequest wStatus = WithdrawalRequest.valueOf(get(cols,3));
                    boolean accepted = Boolean.parseBoolean(get(cols,4));

                    Application app = new Application(sid, iid);
                    app.setStatus(status);
                    app.setWithdrawalStatus(wStatus);
                    app.setAccepted(accepted);
                    
                    intApp.addApplication(app); // add to master list

                } catch (Exception e) {
                    System.out.println("[WARN] Skipping bad application CSV line: " + e.getMessage());
                }
            }
        }
    }

    public void updateAll() throws IOException {
        ensureHeader();
        List<String> out = new ArrayList<>();
        out.add(String.join(",", HEADER)); 

        for (Application a : intApp.getApplications()) {
            out.add(String.join(",",
                CSVParser.q(a.getStudentId()),
                CSVParser.q(String.valueOf(a.getInternshipId())),
                CSVParser.q(a.getStatus().name()),
                CSVParser.q(a.getWithdrawalStatus().name()),
                CSVParser.q(String.valueOf(a.isAccepted()))
            ));
        }
        
        Files.write(file, out, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // CSV helpers
    private void ensureHeader() throws IOException {
        if (!Files.exists(file) || Files.size(file) == 0) {
            Files.createDirectories(file.getParent() == null ? Path.of(".") : file.getParent());
            Files.writeString(file, String.join(",", HEADER) + System.lineSeparator(), StandardCharsets.UTF_8);
        }
    }
    private static boolean looksHeader(List<String> c) { return !c.isEmpty() && c.get(0).toLowerCase().contains("studentid"); }
    private static String get(List<String> c, int i) { return (i < c.size() && c.get(i)!=null) ? c.get(i).trim() : ""; }
    private static int parseInt(String s, int d) { try { return Integer.parseInt(s.trim()); } catch (Exception e) { return d; } }
}
