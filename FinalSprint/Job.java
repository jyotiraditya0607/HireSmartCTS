package FinalSprint;

public class Job implements Participate{
	 private String id;
	    private String title;
	    private String description;
	    private String skillsRequired;
	    private Integer openings;

	    public Job(String id, String title, String description, String skillsRequired, Integer openings) {
	        this.id = id;
	        this.title = title;
	        this.description = description;
	        this.skillsRequired = skillsRequired;
	        this.openings = openings;
	    }

	    public String getId() {
	        return id;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public String getSkillsRequired() {
	        return skillsRequired;
	    }

	    public Integer getOpenings() {
	        return openings;
	    }

	    public void setOpenings(Integer openings) {
	        this.openings = openings;
	    }

	    public void display() {
	        System.out.println("Job ID: " + id + ", Title: " + title + ", Skills: " + skillsRequired + ", Openings: " + openings);
	    }

}
