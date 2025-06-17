package FinalSprint;

import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {
       
        Recruiter recruiter = new Recruiter("R1", "Anita", "anita@jobs.com", "IT", 12);
        DBConnector.insertRecruiter(recruiter);

        Candidate candidate = new Candidate("C1", "Lavan", "lavan@demo.com", "C:/Users/myself/eclipse-workspace/WORK/src/resumes/lavan.pdf", 3);
        DBConnector.insertCandidate(candidate);

        Job job = new Job("J1", "Java Developer", "Backend dev", "Java, SQL", 2);
        DBConnector.insertJob(job);

        //Upload resume (preview first line)
        Utils.uploadResume(candidate.getResumePath());

        
        //Recruiter recruiter=DBConnector.getRecruiterById("R1");
        Interview interview = new Interview(
            "I1",
            candidate,
            job,
            recruiter,
            LocalDateTime.of(2025, 6, 17, 11, 0),
            "Scheduled",
            "Initial Round"
        );
        DBConnector.insertInterview(interview);

        // Log interview time
        Utils.logInterviewTime();

        // Display objects
        recruiter.display();
        interview.display();
    }
}
/*// CREATE Candidate
Candidate candidate = new Candidate("C1", "Lavan", "lavan@demo.com",
"C:/Users/myself/eclipse-workspace/WORK/src/resumes/lavan.pdf", 3);
DBConnector.insertCandidate(candidate);

// READ Candidate by ID
Candidate fetched = DBConnector.getCandidateById("C1");
if (fetched != null) {
System.out.println("Fetched Candidate: " + fetched.getName() + " - " + fetched.getEmail());
}

// UPDATE Candidate (manually update data and re-insert for demo)
Candidate updatedCandidate = new Candidate("C1", "Lavan Koripally", "lavan.k@demo.com",
"C:/Users/myself/eclipse-workspace/WORK/src/resumes/lavan_updated.pdf", 4);
DBConnector.deleteCandidateById("C1"); // delete old
DBConnector.insertCandidate(updatedCandidate); // insert updated

// READ ALL Candidates
List<Candidate> allCandidates = DBConnector.getAllCandidates();
System.out.println("All Candidates:");
for (Candidate c : allCandidates) {
System.out.println(" - " + c.getId() + ": " + c.getName());
}

// DELETE Candidate
DBConnector.deleteCandidateById("C1");
System.out.println("Deleted candidate C1");

// Confirm deletion
Candidate afterDelete = DBConnector.getCandidateById("C1");
if (afterDelete == null) {
System.out.println("Candidate C1 no longer exists.");
}
}*/