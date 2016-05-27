package au.edu.unsw.soacourse.job.model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JobApplicationAssignment {
	private String id;
    private String jobApplicationId;
    private String reviewer1;
	private String reviewer2;
	private String link;
	

	public JobApplicationAssignment() {
		super();
	}

	public JobApplicationAssignment(String id, String jobApplicationId,
			String reviewer1, String reviewer2) {
		super();
		this.id = id;
		this.jobApplicationId = jobApplicationId;
		this.reviewer1 = reviewer1;
		this.reviewer2 = reviewer2;
		this.link = "http://localhost:8080/RestfulJobService/foundIT/jobappreviewassign/" + this.id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
		this.link = "http://localhost:8080/RestfulJobService/foundIT/jobappreviewassign/" + this.id;
	}
	public String getJobApplicationId() {
		return jobApplicationId;
	}
	public void setJobApplicationId(String jobApplicationId) {
		this.jobApplicationId = jobApplicationId;
	}
	public String getReviewer1() {
		return reviewer1;
	}
	public void setReviewer1(String reviewer1) {
		this.reviewer1 = reviewer1;
	}
	public String getReviewer2() {
		return reviewer2;
	}
	public void setReviewer2(String reviewer2) {
		this.reviewer2 = reviewer2;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	
	
	
}