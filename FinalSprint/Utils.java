// Utils.java
package FinalSprint;

import java.io.*;
import java.time.LocalDateTime;

public class Utils {

    public static void uploadResume(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Resume file not found: " + filePath);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            System.out.println("Uploading resume: " + file.getName());
            System.out.println("Preview: " + br.readLine());
        } catch (IOException e) {
            System.out.println("Error uploading resume: " + e.getMessage());
        }
    }

    public static void logInterviewTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Interview logged at: " + now);
    }
}
