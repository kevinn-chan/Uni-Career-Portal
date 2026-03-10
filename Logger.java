package internship_project;

import java.nio.file.*;
import java.time.LocalDateTime;

public class Logger {
    static Path LOG = Paths.get("audit_log.csv");
    public static void log(String userId, String action, String target) {
        String line = LocalDateTime.now() + "," + userId + "," + action + "," + target;
        try { Files.writeString(LOG, line + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND); }
        catch (Exception e) {}
    }
}
