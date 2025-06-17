package FinalSprint;
import java.time.*;
public class Interview implements Participate{
	 private String id;
	    private Candidate candidate;
	    private Job job;
	    private Recruiter recruiter;
	    private LocalDateTime interviewDate;
	    private String status;
	    private String feedback;

	    public Interview(String id, Candidate candidate, Job job, Recruiter recruiter, LocalDateTime interviewDate, String status, String feedback) {
	        this.id = id;
	        this.candidate = candidate;
	        this.job = job;
	        this.recruiter = recruiter;
	        this.interviewDate = interviewDate;
	        this.status = status;
	        this.feedback = feedback;
	    }

	    public String getId() { return id; }
	    public Candidate getCandidate() { return candidate; }
	    public Job getJob() { return job; }
	    public Recruiter getRecruiter() { return recruiter; }
	    public LocalDateTime getInterviewDate() { return interviewDate; }
	    public String getStatus() { return status; }
	    public String getFeedback() { return feedback; }

	    public void display() {
	        System.out.println("Interview ID: " + id);
	        System.out.println("Candidate Name: " + candidate.getName());
	        System.out.println("Job Title: " + job.getTitle());
	        System.out.println("Recruiter Name: " + recruiter.getName());
	        System.out.println("Date: " + interviewDate);
	        System.out.println("Status: " + status);
	        System.out.println("Feedback: " + feedback);
	    }

}
