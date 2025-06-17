package FinalSprint;

import java.time.LocalDateTime;
import java.util.List;

public class App {
    public static void main(String[] args) {
    	Candidate candidate = DBConnector.getCandidateById("C1");  // make sure it's a digit "1", not letter "l"

    	if (candidate != null) {
    	    candidate.display();
    	} else {
    	    System.out.println("Candidate not found.");
    	}

    	
       
       /* Recruiter recruiter = new Recruiter("R1", "Anita", "anita@jobs.com", "IT", 12);
        DBConnector.insertRecruiter(recruiter);

        Candidate candidate = new Candidate("C1", "Lavan", "lavan@demo.com", "C:/Users/myself/eclipse-workspace/WORK/src/resumes/lavan.pdf", 3);
        DBConnector.insertCandidate(candidate);

        Job job = new Job("J1", "Java Developer", "Backend dev", "Java, SQL", 2);
        DBConnector.insertJob(job);
        
        DBConnector.applyCandidateToJob("C1", "J1");

        // Upload resume (preview first line)
        Utils.uploadResume(candidate.getResumePath());
        
     // Get and print all jobs for candidate C1
        List<Job> jobsApplied = DBConnector.getJobsForCandidate("C1");
        System.out.println("\nJobs applied by candidate C1:");
        for (Job j : jobsApplied) {
            j.display();
        }
        
        // Get and print all candidates for job J1
        


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
     // Update Candidate's experience to 5 years
        Candidate candidateToUpdate = DBConnector.getCandidateById("C1");
        if (candidateToUpdate != null) {
            candidateToUpdate.setExperienceYears(5);
            DBConnector.updateCandidateById(candidateToUpdate);
        }
        List<Candidate> candidatesForJob = DBConnector.getCandidatesForJob("J1");
        System.out.println("\nCandidates who applied to job J1:");
        for (Candidate c : candidatesForJob) {
            c.display();
        }


        
 
        
      
        /*
        

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
        */

        
    }
}
