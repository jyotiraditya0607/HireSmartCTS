package FinalSprint;

public class Recruiter implements Participate{
	private String id;
    private String name;
    private String email;
    private String expertise;
    private int hiringCount;

    public Recruiter(String id, String name, String email, String expertise, int hiringCount) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.expertise = expertise;
        this.hiringCount = hiringCount;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getExpertise() { return expertise; }
    public int getHiringCount() { return hiringCount; }

    public void display() {
        System.out.println("Recruiter ID: " + id + ", Name: " + name + ", Email: " + email + ", Expertise: " + expertise + ", Hiring Count: " + hiringCount);
    }

}
