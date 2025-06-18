
package FinalSprint;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;
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
        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy 'at' HH:mm:ss");

            String formattedDateTime = now.format(formatter);
            System.out.println("Interview logged on: " + formattedDateTime);

        } catch (DateTimeException e) {
            System.out.println("Error while formatting/logging date: " + e.getMessage());
        }}
}
