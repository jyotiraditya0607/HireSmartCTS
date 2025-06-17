package FinalSprint;
import java.util.*;
public class Candidate implements Participate {
	 private String id;
	    private String name;
	    private String email;
	    private String resumePath;
	    private List<Job> appliedJobs;
	    private Integer experienceYears;

	    public Candidate(String id, String name, String email, String resumePath, int experienceYears) {
	        this.id = id;
	        this.name = name;
	        this.email = email;
	        this.resumePath = resumePath;
	        this.experienceYears = experienceYears;
	    }


	    public void addJob(Job job) {
	        appliedJobs.add(job);
	    }

	    public List<Job> getAppliedJobs() {
	        return appliedJobs;
	    }

	    public String getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public String getResumePath() {
	        return resumePath;
	    }

	    public Integer getExperienceYears() {
	        return experienceYears;
	    }

	    public void setExperienceYears(Integer experienceYears) {
	        this.experienceYears = experienceYears;
	    }

	    
	    public void display() {
	        System.out.println("Candidate ID: " + id + ", Name: " + name +
	                ", Email: " + email + ", Resume: " + resumePath +
	                ", Experience: " + experienceYears + " years");
	    }

}
