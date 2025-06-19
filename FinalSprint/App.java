package FinalSprint;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== JOB PORTAL MENU =====");
            System.out.println("1. Candidate Management");
            System.out.println("2. Job Management");
            System.out.println("3. Recruiter Management");
            System.out.println("4. Interview Management");
            System.out.println("5. Candidate-Job Mapping");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    while (true) {
                        System.out.println("\n--- Candidate Menu ---");
                        System.out.println("1. Add Candidate");
                        System.out.println("2. View All Candidates");
                        System.out.println("3. View Candidates");
                        System.out.println("4. Update Candidate Experience");
                        System.out.println("5. Delete Candidate");
                        System.out.println("6. Back to Main Menu");
                        System.out.print("Enter choice: ");
                        int cchoice = scanner.nextInt();
                        scanner.nextLine();
                        if (cchoice == 6) break;

                        switch (cchoice) {
                            case 1:
                                System.out.print("Enter Candidate ID: ");
                                String cid = scanner.nextLine();
                                System.out.print("Enter Name: ");
                                String cname = scanner.nextLine();
                                System.out.print("Enter Email: ");
                                String cemail = scanner.nextLine();
                                System.out.print("Enter Resume Path: ");
                                String cresume = scanner.nextLine();
                                System.out.print("Enter Experience (years): ");
                                int cexp = scanner.nextInt();
                                scanner.nextLine();
                                Candidate c = new Candidate(cid, cname, cemail, cresume, cexp);
                                DBConnector.insertCandidate(c);
                                Utils.uploadResume(c.getResumePath());
                                break;

                            case 2:
                                List<Candidate> candidates = DBConnector.getAllCandidates();
                                System.out.println("\nAll Candidates:");
                                for (Candidate cand : candidates) {
                                    System.out.println(" - " + cand.getId() + ": " + cand.getName());
                                }
                                break;

                            case 3:
                            	System.out.print("Enter Candidate ID to view: ");
                            	String id=scanner.nextLine();
                            	Candidate candidate = DBConnector.getCandidateById(id);  // make sure it's a digit "1", not letter "l"

                            	if (candidate != null) {
                            	    candidate.display();
                            	} else {
                            	    System.out.println("Candidate not found.");
                            	}
                            	break;
                            case 4:
                                System.out.print("Enter Candidate ID to update: ");
                                String updateId = scanner.nextLine();
                                Candidate toUpdate = DBConnector.getCandidateById(updateId);
                                if (toUpdate != null) {
                                    System.out.print("Enter new Experience (years): ");
                                    int newExp = scanner.nextInt();
                                    scanner.nextLine();
                                    //updateCandidateById
                                    DBConnector.updateCandidateById(toUpdate);
                                } else {
                                    System.out.println("Candidate not found.");
                                }
                                Candidate candidateToUpdate = DBConnector.getCandidateById("C1");
                                if (candidateToUpdate != null) {
                                    candidateToUpdate.setExperienceYears(5);
                                    DBConnector.updateCandidateById(candidateToUpdate);
                                }
                                break;

                            case 5:
                                System.out.print("Enter Candidate ID to delete: ");
                                String delId = scanner.nextLine();
                                DBConnector.deleteCandidateById(delId);
                                break;
                           
                            default:
                                System.out.println("Invalid option.");
                        }
                    }
                    break;

                case 2:
                    while (true) {
                        System.out.println("\n--- Job Menu ---");
                        System.out.println("1. Add Job");
                        System.out.println("2. View All Jobs");
                        System.out.println("3. Back to Main Menu");
                        System.out.print("Enter choice: ");
                        int jchoice = scanner.nextInt();
                        scanner.nextLine();
                        if (jchoice == 3) break;

                        switch (jchoice) {
                            case 1:
                                System.out.print("Enter Job ID: ");
                                String jid = scanner.nextLine();
                                System.out.print("Enter Title: ");
                                String title = scanner.nextLine();
                                System.out.print("Enter Description: ");
                                String desc = scanner.nextLine();
                                System.out.print("Enter Skills Required: ");
                                String skills = scanner.nextLine();
                                System.out.print("Enter Experience Required: ");
                                int jexp = scanner.nextInt();
                                scanner.nextLine();
                                Job job = new Job(jid, title, desc, skills, jexp);
                                DBConnector.insertJob(job);
                                break;
                            case 2:
                                List<Job> jobs = DBConnector.getAllJobs();
                                System.out.println("\nAll Jobs:");
                                for (Job j : jobs) {
                                    System.out.println(" - " + j.getId() + ": " + j.getTitle());
                                }
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                    }
                    break;

                case 3:
                    while (true) {
                        System.out.println("\n--- Recruiter Menu ---");
                        System.out.println("1. Add Recruiter");
                        System.out.println("2. Back to Main Menu");
                        System.out.print("Enter choice: ");
                        int rchoice = scanner.nextInt();
                        scanner.nextLine();
                        if (rchoice == 2) break;

                        switch (rchoice) {
                            case 1:
                                System.out.print("Enter Recruiter ID: ");
                                String rid = scanner.nextLine();
                                System.out.print("Enter Name: ");
                                String rname = scanner.nextLine();
                                System.out.print("Enter Email: ");
                                String remail = scanner.nextLine();
                                System.out.print("Enter Expertise: ");
                                String exp = scanner.nextLine();
                                System.out.print("Enter Hiring Count: ");
                                int hcount = scanner.nextInt();
                                scanner.nextLine();
                                Recruiter r = new Recruiter(rid, rname, remail, exp, hcount);
                                DBConnector.insertRecruiter(r);
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                    }
                    break;

                case 4:
                    while (true) {
                        System.out.println("\n--- Interview Menu ---");
                        System.out.println("1. Schedule Interview");
                        System.out.println("2. View All Interviews");
                        System.out.println("3. Back to Main Menu");
                        System.out.print("Enter choice: ");
                        int ichoice = scanner.nextInt();
                        scanner.nextLine();
                        if (ichoice == 3) break;

                        switch (ichoice) {
                            case 1:
                                System.out.print("Enter Interview ID: ");
                                String iid = scanner.nextLine();
                                System.out.print("Enter Candidate ID: ");
                                String intCid = scanner.nextLine();
                                System.out.print("Enter Job ID: ");
                                String intJid = scanner.nextLine();
                                System.out.print("Enter Recruiter ID: ");
                                String intRid = scanner.nextLine();

                                System.out.print("Enter Interview DateTime (yyyy-MM-dd HH:mm): ");
                                String dateStr = scanner.nextLine();
                                LocalDateTime dt = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                                System.out.print("Enter Status: ");
                                String status = scanner.nextLine();
                                System.out.print("Enter Feedback: ");
                                String feedback = scanner.nextLine();

                                Candidate ic = DBConnector.getCandidateById(intCid);
                                Job ij = DBConnector.getJobById(intJid);
                                Recruiter ir = DBConnector.getRecruiterById(intRid);

                                if (ic != null && ij != null && ir != null) {
                                    Interview interview = new Interview(iid, ic, ij, ir, dt, status, feedback);
                                    DBConnector.insertInterview(interview);
                                    Utils.logInterviewTime();
                                    interview.display();
                                } else {
                                    System.out.println("Invalid candidate, job, or recruiter ID.");
                                }
                                break;

                            case 2:
                                List<Interview> interviews = DBConnector.getAllInterviews();
                                for (Interview i : interviews) {
                                    i.display();
                                }
                                break;

                            default:
                                System.out.println("Invalid option.");
                        }
                    }
                    break;

                case 5:
                    while (true) {
                        System.out.println("\n--- Candidate-Job Mapping Menu ---");
                        System.out.println("1. Apply Candidate to Job");
                        System.out.println("2. View Jobs Applied by Candidate");
                        System.out.println("3. View Candidates Applied to Job");
                        System.out.println("4. Delete Candidate-Job Mapping");
                        System.out.println("5. Back to Main Menu");
                        System.out.print("Enter choice: ");
                        int mchoice = scanner.nextInt();
                        scanner.nextLine();
                        if (mchoice == 5) break;

                        switch (mchoice) {
                            case 1:
                                System.out.print("Enter Candidate ID: ");
                                String mcid = scanner.nextLine();
                                System.out.print("Enter Job ID: ");
                                String mjid = scanner.nextLine();
                                DBConnector.applyCandidateToJob(mcid, mjid);
                                break;
                            case 2:
                                System.out.print("Enter Candidate ID: ");
                                String viewCid = scanner.nextLine();
                                List<Job> jobs = DBConnector.getJobsForCandidate(viewCid);
                                System.out.println("\nJobs applied by candidate:");
                                for (Job j : jobs) {
                                    System.out.println(" - " + j.getId() + ": " + j.getTitle());
                                }
                                break;
                            case 3:
                                System.out.print("Enter Job ID: ");
                                String viewJid = scanner.nextLine();
                                List<Candidate> jobCandidates = DBConnector.getCandidatesForJob(viewJid);
                                System.out.println("\nCandidates who applied:");
                                for (Candidate ca : jobCandidates) {
                                    System.out.println(" - " + ca.getId() + ": " + ca.getName());
                                }
                                break;
                            case 4:
                                System.out.print("Enter Candidate ID: ");
                                String dcid = scanner.nextLine();
                                System.out.print("Enter Job ID: ");
                                String djid = scanner.nextLine();
                                DBConnector.deleteCandidateJobMapping(dcid, djid);
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                    }
                    break;

                case 6:
                    System.out.println("Exiting application.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

    	
