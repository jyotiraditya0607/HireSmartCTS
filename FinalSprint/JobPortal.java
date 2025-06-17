package FinalSprint;
import java.util.*;
public class JobPortal {
	private Map<String, List<Candidate>> jobApplicants;

    public JobPortal() {
        this.jobApplicants = new HashMap<>();
    }

    public void addCandidateToJob(String jobId, Candidate candidate) {
        jobApplicants.computeIfAbsent(jobId, k -> new ArrayList<>()).add(candidate);
    }

    public void showApplicants(String jobId) {
        List<Candidate> list = jobApplicants.getOrDefault(jobId, new ArrayList<>());
        for (Candidate c : list) {
            c.display();
        }
    }

}
