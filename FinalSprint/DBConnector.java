package FinalSprint;
import java.util.*;
import java.sql.*;

public class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/recruitment";
    private static final String USER = "root";
    private static final String PASSWORD = "Lavan@2110";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    //INSERTION
    public static void insertCandidate(Candidate c) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO Candidate (id, name, email, resumePath, experienceYears) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, c.getId());
            stmt.setString(2, c.getName());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getResumePath());
            stmt.setInt(5, c.getExperienceYears());
            stmt.executeUpdate();
            System.out.println("Candidate inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert error (Candidate): " + e.getMessage());
        }
    }


    public static void insertRecruiter(Recruiter r) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO Recruiter (Recruiter_ID, Name, Email, Expertise, Hiring_Count) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, r.getId());
            stmt.setString(2, r.getName());
            stmt.setString(3, r.getEmail());
            stmt.setString(4, r.getExpertise());
            stmt.setInt(5, r.getHiringCount());
            stmt.executeUpdate();
            System.out.println("Recruiter inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert error (Recruiter): " + e.getMessage());
        }
    }

    public static void insertInterview(Interview i) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO Interview (Interview_ID, Candidate_ID, Job_ID, Recruiter_ID, Interview_Date, Status, Feedback) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, i.getId());
            stmt.setString(2, i.getCandidate().getId());
            stmt.setString(3, i.getJob().getId());
            stmt.setString(4, i.getRecruiter().getId());
            stmt.setTimestamp(5, Timestamp.valueOf(i.getInterviewDate()));
            stmt.setString(6, i.getStatus());
            stmt.setString(7, i.getFeedback());
            stmt.executeUpdate();
            System.out.println("Interview inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert error (Interview): " + e.getMessage());
        }
    }

    public static void insertJob(Job j) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO Job (Job_ID, Title, Description, SkillsRequired, ExperienceRequired) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, j.getId());
            stmt.setString(2, j.getTitle());
            stmt.setString(3, j.getDescription());
            stmt.setString(4, j.getSkillsRequired());
            stmt.setInt(5, j.getOpenings());
            stmt.executeUpdate();
            System.out.println("Job inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insert error (Job): " + e.getMessage());
        }
        
    }
    
    
    //Retrival
    public static Recruiter getRecruiterById(String recruiterId) {
        Recruiter recruiter = null;
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM Recruiter WHERE Recruiter_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, recruiterId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                recruiter = new Recruiter(
                    rs.getString("Recruiter_ID"),
                    rs.getString("Name"),
                    rs.getString("Email"),
                    rs.getString("Expertise"),
                    rs.getInt("Hiring_Count")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching recruiter: " + e.getMessage());
        }
        return recruiter;
    }

    public static Job getJobById(String jobId) {
        Job job = null;
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM Job WHERE Job_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, jobId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                job = new Job(
                    rs.getString("Job_ID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getString("SkillsRequired"),
                    rs.getInt("ExperienceRequired")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching job: " + e.getMessage());
        }
        return job;
    }
    public static Candidate getCandidateById(String candidateId) {
        Candidate candidate = null;
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM Candidate WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, candidateId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                candidate = new Candidate(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("resumePath"),
                    rs.getInt("experienceYears")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching candidate: " + e.getMessage());
        }
        return candidate;
    }
    public static Interview getInterviewById(String interviewId) {
        Interview interview = null;
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM Interview WHERE Interview_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, interviewId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String candidateId = rs.getString("Candidate_ID");
                String jobId = rs.getString("Job_ID");
                String recruiterId = rs.getString("Recruiter_ID");

                Candidate candidate = getCandidateById(candidateId);
                Job job = getJobById(jobId);
                Recruiter recruiter = getRecruiterById(recruiterId);

                interview = new Interview(
                    rs.getString("Interview_ID"),
                    candidate,
                    job,
                    recruiter,
                    rs.getTimestamp("Interview_Date").toLocalDateTime(),
                    rs.getString("Status"),
                    rs.getString("Feedback")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching interview: " + e.getMessage());
        }
        return interview;
    }
    //update
    public static void updateCandidateById(Candidate c) {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE Candidate SET name = ?, email = ?, resumePath = ?, experienceYears = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, c.getName());
            stmt.setString(2, c.getEmail());
            stmt.setString(3, c.getResumePath());
            stmt.setInt(4, c.getExperienceYears());
            stmt.setString(5, c.getId());
            stmt.executeUpdate();
            System.out.println("Candidate updated successfully.");
        } catch (SQLException e) {
            System.out.println("Update error (Candidate): " + e.getMessage());
        }
    }

    public static void updateJobById(Job j) {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE Job SET Title = ?, Description = ?, SkillsRequired = ?, ExperienceRequired = ? WHERE Job_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, j.getTitle());
            stmt.setString(2, j.getDescription());
            stmt.setString(3, j.getSkillsRequired());
            stmt.setInt(4, j.getOpenings());
            stmt.setString(5, j.getId());
            stmt.executeUpdate();
            System.out.println("Job updated successfully.");
        } catch (SQLException e) {
            System.out.println("Update error (Job): " + e.getMessage());
        }
    }

    public static void updateRecruiterById(Recruiter r) {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE Recruiter SET Name = ?, Email = ?, Expertise = ?, Hiring_Count = ? WHERE Recruiter_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, r.getName());
            stmt.setString(2, r.getEmail());
            stmt.setString(3, r.getExpertise());
            stmt.setInt(4, r.getHiringCount());
            stmt.setString(5, r.getId());
            stmt.executeUpdate();
            System.out.println("Recruiter updated successfully.");
        } catch (SQLException e) {
            System.out.println("Update error (Recruiter): " + e.getMessage());
        }
    }

    public static void updateInterviewById(Interview i) {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE Interview SET Candidate_ID = ?, Job_ID = ?, Recruiter_ID = ?, Interview_Date = ?, Status = ?, Feedback = ? WHERE Interview_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, i.getCandidate().getId());
            stmt.setString(2, i.getJob().getId());
            stmt.setString(3, i.getRecruiter().getId());
            stmt.setTimestamp(4, Timestamp.valueOf(i.getInterviewDate()));
            stmt.setString(5, i.getStatus());
            stmt.setString(6, i.getFeedback());
            stmt.setString(7, i.getId());
            stmt.executeUpdate();
            System.out.println("Interview updated successfully.");
        } catch (SQLException e) {
            System.out.println("Update error (Interview): " + e.getMessage());
        }
    }
 // Delete Operations
    public static void deleteCandidateById(String candidateId) {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM Candidate WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, candidateId);
            stmt.executeUpdate();
            System.out.println("Candidate deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Delete error (Candidate): " + e.getMessage());
        }
    }

    public static void deleteRecruiterById(String recruiterId) {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM Recruiter WHERE Recruiter_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, recruiterId);
            stmt.executeUpdate();
            System.out.println("Recruiter deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Delete error (Recruiter): " + e.getMessage());
        }
    }

    public static void deleteJobById(String jobId) {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM Job WHERE Job_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, jobId);
            stmt.executeUpdate();
            System.out.println("Job deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Delete error (Job): " + e.getMessage());
        }
    }

    public static void deleteInterviewById(String interviewId) {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM Interview WHERE Interview_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, interviewId);
            stmt.executeUpdate();
            System.out.println("Interview deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Delete error (Interview): " + e.getMessage());
        }
    }

    // Select All
    public static List<Candidate> getAllCandidates() {
        List<Candidate> list = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM Candidate";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Candidate(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("resumePath"),
                    rs.getInt("experienceYears")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching candidates: " + e.getMessage());
        }
        return list;
    }

    public static List<Job> getAllJobs() {
        List<Job> list = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM Job";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Job(
                    rs.getString("Job_ID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getString("SkillsRequired"),
                    rs.getInt("ExperienceRequired")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching jobs: " + e.getMessage());
        }
        return list;
    }

    public static List<Interview> getAllInterviews() {
        List<Interview> list = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM Interview";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Interview i = new Interview(
                    rs.getString("Interview_ID"),
                    getCandidateById(rs.getString("Candidate_ID")),
                    getJobById(rs.getString("Job_ID")),
                    getRecruiterById(rs.getString("Recruiter_ID")),
                    rs.getTimestamp("Interview_Date").toLocalDateTime(),
                    rs.getString("Status"),
                    rs.getString("Feedback")
                );
                list.add(i);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching interviews: " + e.getMessage());
        }
        return list;
    }
    
    //JOB MAPPING
    public static void applyCandidateToJob(String candidateId, String jobId) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO candidate_applied_jobs (Candidate_ID, Job_ID) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, candidateId);
            stmt.setString(2, jobId);
            stmt.executeUpdate();
            System.out.println("Candidate applied to job successfully.");
        } catch (SQLException e) {
            System.out.println("Error applying candidate to job: " + e.getMessage());
        }
    }
    public static List<Job> getJobsForCandidate(String candidateId) {
        List<Job> jobs = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT j.* FROM Job j JOIN candidate_applied_jobs c ON j.Job_ID = c.Job_ID WHERE c.Candidate_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, candidateId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                jobs.add(new Job(
                    rs.getString("Job_ID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getString("SkillsRequired"),
                    rs.getInt("ExperienceRequired")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching jobs for candidate: " + e.getMessage());
        }
        return jobs;
    }
    public static List<Candidate> getCandidatesForJob(String jobId) {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT cnd.* FROM Candidate cnd JOIN candidate_applied_jobs caj ON cnd.id = caj.Candidate_ID WHERE caj.Job_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, jobId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                candidates.add(new Candidate(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("resumePath"),
                    rs.getInt("experienceYears")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching candidates for job: " + e.getMessage());
        }
        return candidates;
    }
    public static void deleteCandidateJobMapping(String candidateId, String jobId) {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM candidate_applied_jobs WHERE Candidate_ID = ? AND Job_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, candidateId);
            stmt.setString(2, jobId);
            stmt.executeUpdate();
            System.out.println("Candidate-job mapping deleted.");
        } catch (SQLException e) {
            System.out.println("Error deleting mapping: " + e.getMessage());
        }
    }




}
