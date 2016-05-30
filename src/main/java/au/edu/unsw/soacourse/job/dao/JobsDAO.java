package au.edu.unsw.soacourse.job.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.edu.unsw.soacourse.job.model.CompanyProfile;
import au.edu.unsw.soacourse.job.model.CompanyProfiles;
import au.edu.unsw.soacourse.job.model.HiringTeam;
import au.edu.unsw.soacourse.job.model.HiringTeamStore;
import au.edu.unsw.soacourse.job.model.HiringTeams;
import au.edu.unsw.soacourse.job.model.JobApplication;
import au.edu.unsw.soacourse.job.model.JobApplicationAssignment;
import au.edu.unsw.soacourse.job.model.JobApplicationAssignments;
import au.edu.unsw.soacourse.job.model.JobApplications;
import au.edu.unsw.soacourse.job.model.JobPosting;
import au.edu.unsw.soacourse.job.model.JobPostings;
import au.edu.unsw.soacourse.job.model.Review;
import au.edu.unsw.soacourse.job.model.Reviews;
import au.edu.unsw.soacourse.job.model.TeamMemberProfile;
import au.edu.unsw.soacourse.job.model.TeamMemberProfiles;
import au.edu.unsw.soacourse.job.model.UserProfile;
import au.edu.unsw.soacourse.job.model.UserProfiles;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

//This is suppose to be the DAO layer so we should probably change this
//This class should interact with our data storage
//Probably this may  convert all data to xml or something ??
//Need to grab job alerts from two URLs

// https://www.teach.nsw.edu.au/find-teaching-jobs/jobfeed (is in html will need to convert)
// http://rss.jobsearch.careerone.com.au/rssquery.ashx?q=Java&rad_units=km&cy=AU&pp=25&sort=rv.di.dt&baseurl=jobview.careerone.com.au
// above is in xml so no need to convert

// Expose these two urls
// http://foundit-server/jobalerts?keyword={keyword}
// http://foundit-server/jobalerts?keyword={keyword}&sort_by=jobtitle

//everything above is from the lab

//what do we want to be able to do

//store metadata about the service
//nextAvailableID for all model classes (profiles, applications ...etc.)
//


public enum JobsDAO {
	instance;
	
	String homeDir = System.getProperty("catalina.home")+"/webapps/{root context}/WEB-INF/";
	/*
	private final String COMPANY_PROFILE_FILEDIR = homeDir + "/compprofilestore.xml";
	private final String USER_PROFILE_FILEDIR = homeDir +  "/userprofilestore.xml";
	private final String HIRING_TEAM_FILEDIR = homeDir + "/hiringteamstore.xml";
	private final String JOB_APPLICATION_FILEDIR = homeDir + "/jobappsstore.xml";
	private final String JOB_POSTING_FILEDIR = homeDir + "/jobpostingstore.xml";
	private final String REVIEW_FILEDIR = homeDir +  "/reviewsstore.xml";
	private final String TEAM_MEMBER_PROFILE_FILEDIR = homeDir + "/teammemberprofilestore.xml";
	private final String JOB_APPLICATION_ASSIGNMENT_FILEDIR = homeDir + "/jobappassignstore.xml";
	*/
	//metadata	
	private final String COMPANY_PROFILE_FILEDIR = System.getProperty("user.dir") + "/compprofilestore.xml";
	private final String USER_PROFILE_FILEDIR =System.getProperty("user.dir") +  "/userprofilestore.xml";
	private final String HIRING_TEAM_FILEDIR = System.getProperty("user.dir") + "/hiringteamstore.xml";
	private final String JOB_APPLICATION_FILEDIR = System.getProperty("user.dir") + "/jobappsstore.xml";
	private final String JOB_POSTING_FILEDIR = System.getProperty("user.dir") + "/jobpostingstore.xml";
	private final String REVIEW_FILEDIR =System.getProperty("user.dir") +  "/reviewsstore.xml";
	private final String TEAM_MEMBER_PROFILE_FILEDIR = System.getProperty("user.dir") + "/teammemberprofilestore.xml";
	private final String JOB_APPLICATION_ASSIGNMENT_FILEDIR = System.getProperty("user.dir") + "/jobappassignstore.xml";
	
	
	//these are in place of XML storage for now! 
	//we need to store persistently
	//referred to by their ids
    private Map<String, CompanyProfile> contentStoreCompProfiles = new HashMap<String, CompanyProfile>();
    private Map<String, UserProfile> contentStoreUserProfiles = new HashMap<String, UserProfile>();
    private Map<String, HiringTeamStore> contentStoreHiringTeam = new HashMap<String, HiringTeamStore>();
    private Map<String, JobApplication> contentStoreApplications = new HashMap<String, JobApplication>();
    private Map<String, JobPosting> contentStorePostings = new HashMap<String, JobPosting>();
    private Map<String, Review> contentStoreReviews = new HashMap<String, Review>();
    private Map<String, TeamMemberProfile> contentStoreTeamProfiles = new HashMap<String, TeamMemberProfile>();
    private Map<String, JobApplicationAssignment> contentStoreJobApplicationAssignment= new HashMap<String, JobApplicationAssignment>();

    //archived status
    public static final String ARCHIVED_TRUE = "T";
    public static final String ARCHIVED_FALSE = "F";
    
    
    private JobsDAO() {
  
    	/*
    	JobApplication b = new JobApplication("1", "RESTful Web Services");
        b.setDetail("http://oreilly.com/catalog/9780596529260");
        contentStore.put("1", b);
        b = new JobApplication("2", "RESTful Java with JAX-RS");
        b.setDetail("http://oreilly.com/catalog/9780596158057");
        contentStore.put("2", b);
        */
    	
    	//add test cases files (to avoid having to create manually)
    	//addTestCasesJobPostings();
    	//addTestCasesUserProfiles();
    	//addTestCasesJobApplications();
    	//addTestCasesHiringTeamMembersAndTeam();
    	//addTestCasesJobApplicationAssignment();
    }
    
    private void addTestCasesJobPostings(){
    	String id = "1"; 
    	String title = "title"; 
    	String description = "description"; 
    	String companyProfileId = "companyProfileId"; 
		String positionType = "positionType"; 
    	String desiredSkills = "desiredSkills"; 
    	String salaryLevel= "salaryLevel"; 
		String location= "location"; 
    	
    	JobPosting post1 = new JobPosting(id, title, description, companyProfileId,
    			positionType, desiredSkills, salaryLevel,
    			location);
    	
    	JobPosting post2 = new JobPosting("2");
    	JobPosting post3 = new JobPosting("3");
    	JobPosting post4 = new JobPosting("4");
    	

    	contentStorePostings.put(id, post1);
    	contentStorePostings.put("2", post2);
    	contentStorePostings.put("3", post3);
    	contentStorePostings.put("4", post4);
    }
    
    private void addTestCasesUserProfiles(){
    	/*
    	  	private String id;
		    private String name;
		    private String currentPosition;
			private String education;
		    private String pastExperience;
		    private String professionalSkills;
		    private String link;
			private String rel;
	
    	 */
    	String id1 = "1";
    	String id2 = "2";
    	String name1 = "Bob";
    	String name2 = "John";
    	String currentpos1 = "Tiler";
    	String currentpos2 = "Smoker";
    	String education1 = "LOL";
    	String education2 = "MEME";
    	String pastExp1 = "Clown";
    	String pastExp2 = "Steroids Consultant";
    	String profSk1 = "Teaching";
    	String profSk2 = "Attacking";
    	
    	//Skip links
    	
    	UserProfile user1 = new UserProfile(id1);
    	user1.setEducation(education1);
    	user1.setCurrentPosition(currentpos1);
    	user1.setId(id1);
    	user1.setPastExperience(pastExp1);
    	user1.setProfessionalSkills(profSk1);
    	user1.setName(name1);
    	
    	UserProfile user2 = new UserProfile(id2);
    	user2.setEducation(education2);
    	user2.setCurrentPosition(currentpos2);
    	user2.setId(id2);
    	user2.setPastExperience(pastExp2);
    	user2.setProfessionalSkills(profSk2);
    	user2.setName(name2);
    	
    	storeUserProfile(user1);
    	storeUserProfile(user2);
    }
    
    private void addTestCasesJobApplications(){
    	String id = "1"; 
        String jobApplicationId= "1"; 
        String userProfileId= "1"; 
        String coverLetter= "Hey"; 
        String resume= "resume.pdf"; //Maybe just a text pretending to be an attachment (i.e. �resume.pdf�)

        JobApplication app1 = new JobApplication(id, jobApplicationId, userProfileId, coverLetter, resume);
        JobApplication app2 = new JobApplication("2");
        JobApplication app3 = new JobApplication("3");
        JobApplication app4 = new JobApplication("4");
        JobApplication app5 = new JobApplication("5");

        //test for manager grab
        app2.setJobPostId("1");
        app3.setJobPostId("1");
        app4.setJobPostId("1");
        contentStoreApplications.put(id, app1);
        contentStoreApplications.put("2", app2);
        contentStoreApplications.put("3", app3);
        contentStoreApplications.put("4", app4);
        contentStoreApplications.put("5", app5);
        
    	
    }
    private void  addTestCasesHiringTeamMembersAndTeam(){
    	String id = "1";
        String companyProfileId = "1";
        
        CompanyProfile newCompProf = new CompanyProfile(companyProfileId, "", "", "", "", "" );
        storeCompanyProfile(newCompProf);
        
        String id2 ="1";
        String username2 = "review1";
        String password2 = "review1";
    	String professionalSkills2 = "skill";
    	String id3="2";
        String username3 = "review2";
        String password3 = "review2";
    	String professionalSkills3 = "skill";
    	String id4="3";
        String username4 = "review3";
        String password4 = "review3";
    	String professionalSkills4 = "skill";
    	String id5="4";
        String username5 = "review4";
        String password5 = "review4";
    	String professionalSkills5 = "skill";
    	String id6="5";
        String username6 = "review5";
        String password6 = "review5";
    	String professionalSkills6 = "skill";
        
    	TeamMemberProfile memProf1 = new TeamMemberProfile(id2, username2, password2, professionalSkills2);
    	TeamMemberProfile memProf2 = new TeamMemberProfile(id3, username3, password3, professionalSkills3);
    	TeamMemberProfile memProf3 = new TeamMemberProfile(id4, username4, password4, professionalSkills4);
    	TeamMemberProfile memProf4 = new TeamMemberProfile(id5, username5, password5, professionalSkills5);
    	TeamMemberProfile memProf5 = new TeamMemberProfile(id6, username6, password6, professionalSkills6);
    	
    	storeTeamMemberProfile(memProf1);
    	storeTeamMemberProfile(memProf2);
    	storeTeamMemberProfile(memProf3);
    	storeTeamMemberProfile(memProf4);
    	storeTeamMemberProfile(memProf5);
		
		//create new profile to transfer
		HiringTeamStore newHiringTeam = new HiringTeamStore(id, companyProfileId, id2, id3, id4, id5, id6);
				
    	storeHiringTeam(newHiringTeam);
    }
    
    private void addTestCasesJobApplicationAssignment() {
    	
    	String reviewer2 = "3";
		String reviewer1 = "4";
		//String jobApplicationId1 = "15";
		//String jobApplicationId2 = "16";
		//String id1 = "test1";
		//String id2 = "test2";
		
		String id1 = "6"; 
        String jobApplicationId1= "15"; 
        String userProfileId1= "1"; 
        String coverLetter1= "Trees are life"; 
        String resume1= "resume.pdf"; //Maybe just a text pretending to be an attachment (i.e. �resume.pdf�)

        String id2 = "7"; 
        String jobApplicationId2= "16"; 
        String userProfileId2= "2"; 
        String coverLetter2= "I am not real"; 
        String resume2= "bomb.pdf";
        
        JobApplication app1 = new JobApplication(id1, jobApplicationId1, userProfileId1, coverLetter1, resume1);
        JobApplication app2 = new JobApplication(id2, jobApplicationId2, userProfileId2, coverLetter2, resume2);
		
        storeJobApplication(app1);
        storeJobApplication(app2);
        //contentStoreApplications.put("1", app1);
        //contentStoreApplications.put("2", app2);
        
		JobApplicationAssignment newJobApplicationAssignment1 = new JobApplicationAssignment("test1", id1, reviewer1, reviewer2);
		JobApplicationAssignment newJobApplicationAssignment2 = new JobApplicationAssignment("test2", id2, reviewer1, reviewer2);
		
		storeJobApplicationAssignment(newJobApplicationAssignment1);
		storeJobApplicationAssignment(newJobApplicationAssignment2);
    }
    //CREATE/UPDATE
    
    //what do we want to do?
    //store userProfiles etc..
    
    public void storeUserProfile(UserProfile newUserProfile){
    	loadUserProfilesFromFile();
      	
    	contentStoreUserProfiles.put(newUserProfile.getId(), newUserProfile);
    	System.out.println(":" + newUserProfile.getPastExperience());
    	//create storage class
      	List<UserProfile> userProfiles = new ArrayList<UserProfile>(contentStoreUserProfiles.values());
    	UserProfiles storeThis = new UserProfiles(userProfiles);
    	
    	try {
    		File file = new File(USER_PROFILE_FILEDIR);
    		
    		JAXBContext jaxbContext = JAXBContext.newInstance(UserProfiles.class);
    		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

    		// output pretty printed
    		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    		jaxbMarshaller.marshal(storeThis, file);
    		//jaxbMarshaller.marshal(storeThis, System.out);

    	} catch (JAXBException e) {
    		e.printStackTrace();
    	}

    }
    
    public void storeCompanyProfile(CompanyProfile newCompanyProfile){
    	loadCompanyProfilesFromFile();

    	contentStoreCompProfiles.put(newCompanyProfile.getId(), newCompanyProfile);
    	
    	//create storage class
      	List<CompanyProfile> compProfiles = new ArrayList<CompanyProfile>(contentStoreCompProfiles.values());
      	CompanyProfiles storeThis = new CompanyProfiles(compProfiles);
    	
    	try {
    		File file = new File(COMPANY_PROFILE_FILEDIR);
    		
    		JAXBContext jaxbContext = JAXBContext.newInstance(CompanyProfiles.class);
    		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

    		// output pretty printed
    		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    		jaxbMarshaller.marshal(storeThis, file);
    		//jaxbMarshaller.marshal(storeThis, System.out);

    	} catch (JAXBException e) {
    		e.printStackTrace();
    	}
    }
    
    public void storeHiringTeam(HiringTeamStore newHiringTeam){
    	loadHiringTeamsFromFile();

    	contentStoreHiringTeam.put(newHiringTeam.getId(), newHiringTeam);
    	
     	//create storage class
      	List<HiringTeamStore> hiringTeams = new ArrayList<HiringTeamStore>(contentStoreHiringTeam.values());
      	HiringTeams storeThis = new HiringTeams(hiringTeams);
    	
    	try {
    		File file = new File(HIRING_TEAM_FILEDIR);
    		
    		JAXBContext jaxbContext = JAXBContext.newInstance(HiringTeams.class);
    		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

    		// output pretty printed
    		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    		jaxbMarshaller.marshal(storeThis, file);
    		//jaxbMarshaller.marshal(storeThis, System.out);

    	} catch (JAXBException e) {
    		e.printStackTrace();
    	}
    	
    }
    
    public void storeJobApplication(JobApplication newJobApplication ){
    	loadJobApplicationsFromFile();
    	
    	contentStoreApplications.put(newJobApplication.getId(), newJobApplication);
    	
    	//create storage class
      	List<JobApplication> jobApps = new ArrayList<JobApplication>(contentStoreApplications.values());
      	JobApplications storeThis = new JobApplications(jobApps);
    	
    	try {
    		File file = new File(JOB_APPLICATION_FILEDIR);
    		
    		JAXBContext jaxbContext = JAXBContext.newInstance(JobApplications.class);
    		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

    		// output pretty printed
    		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    		jaxbMarshaller.marshal(storeThis, file);
    		//jaxbMarshaller.marshal(storeThis, System.out);

    	} catch (JAXBException e) {
    		e.printStackTrace();
    	}
    	
    }
    
    public void storeJobPosting(JobPosting newJobPosting){
    	loadJobPostingsFromFile();

    	contentStorePostings.put(newJobPosting.getId(), newJobPosting);
    	
    	//create storage class
      	List<JobPosting> jobPosts = new ArrayList<JobPosting>(contentStorePostings.values());
      	JobPostings storeThis = new JobPostings(jobPosts);
    	
    	try {
    		File file = new File(JOB_POSTING_FILEDIR);
    		
    		JAXBContext jaxbContext = JAXBContext.newInstance(JobPostings.class);
    		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

    		// output pretty printed
    		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    		jaxbMarshaller.marshal(storeThis, file);
    		//jaxbMarshaller.marshal(storeThis, System.out);

    	} catch (JAXBException e) {
    		e.printStackTrace();
    	}
    }
    
    public void storeReview(Review newReview){
    	loadReviewsFromFile();
    	
    	//check whether the relevant job application has all it's reviews in to move to next stage
    	String jobAppId = newReview.getJobApplicationId();
    	JobApplication app = getJobApplication(jobAppId);
    	
    	if(newReview.getDecision() == Review.DECISION_REJECTED){
    		//set to not being shortlisted!
    		app.setStatus(JobApplication.STATUS_NOT_SHORTLISTED);
    		//archive the job
    		app.setArchived(JobApplication.ARCHIVED_TRUE);
    	} else {
    		//compare with other review... if it exists!
    		//check if the review is in and is positive
			for(Review review : contentStoreReviews.values()){
				//we assume there are only 2 reviews per application
				if(review.getJobApplicationId().matches(jobAppId)){
					if(review.getDecision().matches(Review.DECISION_ACCEPTED)){
						app.setStatus(JobApplication.STATUS_SHORTLISTED);
					} else {
						app.setStatus(JobApplication.STATUS_NOT_SHORTLISTED);
			    		app.setArchived(JobApplication.ARCHIVED_TRUE);
					}
				}
				
			}	
    	}
    	//now store the new review
    	contentStoreReviews.put(newReview.getId(), newReview);
    	
    	//create storage class
      	List<Review> reviews = new ArrayList<Review>(contentStoreReviews.values());
      	Reviews storeThis = new Reviews(reviews);
    	
    	try {
    		File file = new File(REVIEW_FILEDIR);
    		
    		JAXBContext jaxbContext = JAXBContext.newInstance(Reviews.class);
    		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

    		// output pretty printed
    		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    		jaxbMarshaller.marshal(storeThis, file);
    		//jaxbMarshaller.marshal(storeThis, System.out);

    	} catch (JAXBException e) {
    		e.printStackTrace();
    	}
    }
    
    public void storeTeamMemberProfile(TeamMemberProfile newTeamMemberProfile){
    	loadTeamMemberProfilesFromFile();
    	
    	contentStoreTeamProfiles.put(newTeamMemberProfile.getId(), newTeamMemberProfile);
    	
    	//create storage class
      	List<TeamMemberProfile> profiles = new ArrayList<TeamMemberProfile>(contentStoreTeamProfiles.values());
      	TeamMemberProfiles storeThis = new TeamMemberProfiles(profiles);
    	
    	try {
    		File file = new File(TEAM_MEMBER_PROFILE_FILEDIR);
    		
    		JAXBContext jaxbContext = JAXBContext.newInstance(TeamMemberProfiles.class);
    		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

    		// output pretty printed
    		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    		jaxbMarshaller.marshal(storeThis, file);
    		//jaxbMarshaller.marshal(storeThis, System.out);

    	} catch (JAXBException e) {
    		e.printStackTrace();
    	}
    }
    
    
    public void storeJobApplicationAssignment(JobApplicationAssignment newJobAppAssignment){
    	loadJobApplicationAssignmentsFromFile();
    	
    	contentStoreJobApplicationAssignment.put(newJobAppAssignment.getId(), newJobAppAssignment);
    	
    	//create storage class
      	List<JobApplicationAssignment> assgns = new ArrayList<JobApplicationAssignment>(contentStoreJobApplicationAssignment.values());
      	JobApplicationAssignments storeThis = new JobApplicationAssignments(assgns);
    	
    	try {
    		File file = new File(JOB_APPLICATION_ASSIGNMENT_FILEDIR);
    		
    		JAXBContext jaxbContext = JAXBContext.newInstance(JobApplicationAssignments.class);
    		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

    		// output pretty printed
    		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    		jaxbMarshaller.marshal(storeThis, file);
    		//jaxbMarshaller.marshal(storeThis, System.out);

    	} catch (JAXBException e) {
    		e.printStackTrace();
    	}
    }
    

    /*
    public Map<String, JobApplication> getStore(){
        return contentStore;
    }
*/
    //GET
    public UserProfile getUserProfile(String id){
    	//if we've reset load from file
		loadUserProfilesFromFile();
    	return contentStoreUserProfiles.get(id);
    }
    
    public CompanyProfile getCompanyProfile(String id){
    	loadCompanyProfilesFromFile();
    	return contentStoreCompProfiles.get(id);
    }
    
    public HiringTeamStore getHiringTeam(String id){
    	loadHiringTeamsFromFile();
    	return contentStoreHiringTeam.get(id);
    }
    
    public JobApplication getJobApplication(String id){
    	loadJobApplicationsFromFile();
    	return contentStoreApplications.get(id);
    }
    
    public JobPosting getJobPosting(String id){
    	loadJobPostingsFromFile();
    	return contentStorePostings.get(id);
    }
    
    public Review getReview(String id){
    	loadReviewsFromFile();
    	return contentStoreReviews.get(id);
    }
    
    public TeamMemberProfile getTeamMemberProfile(String id){
    	loadTeamMemberProfilesFromFile();
    	return contentStoreTeamProfiles.get(id);
    }
    
    public JobApplicationAssignment getJobApplicationAssignment(String id){
    	loadJobApplicationAssignmentsFromFile();
    	return contentStoreJobApplicationAssignment.get(id);
    }

    //DELETE
    
    public UserProfile deleteUserProfile(String id){
    	return contentStoreUserProfiles.remove(id);
    }
    
    public CompanyProfile deleteCompanyProfile(String id){
    	return contentStoreCompProfiles.remove(id);
    }
    
    public HiringTeamStore deleteHiringTeam(String id){
    	return contentStoreHiringTeam.remove(id);
    }
    
    public JobApplication deleteJobApplication(String id){
    	//archive instead of delete
    	JobApplication archived = contentStoreApplications.get(id);
    	if(archived != null)
    		archived.setArchived(ARCHIVED_TRUE);
    	
    	return archived;
    }
    
    public JobPosting deleteJobPosting(String id){
    	//archive instead of delete
    	JobPosting archived = contentStorePostings.get(id);
    	if(archived != null)
    		archived.setArchived(ARCHIVED_TRUE);
    	
    	return archived;
    }
    
    public Review deleteReview(String id){
    	return contentStoreReviews.remove(id);
    }
    
    public TeamMemberProfile deleteTeamMemberProfile(String id){
    	return contentStoreTeamProfiles.remove(id);
    }
    
    public JobApplicationAssignment deleteJobApplicationAssignment(String id){
    	return contentStoreJobApplicationAssignment.remove(id);
    }
    
     
 
    ///ID STUFF
    
    public String getNextUserProfileId(){
    	loadUserProfilesFromFile();
    	int nextId = contentStoreUserProfiles.size() + 1;
    	while(contentStoreUserProfiles.containsKey(Integer.toString(nextId))){
    		nextId++;
    	}
    	return Integer.toString(nextId);
    }
    
    public String getNextCompanyProfileId(){
    	loadCompanyProfilesFromFile();
    	
    	int nextId = contentStoreUserProfiles.size() + 1;
    	while(contentStoreCompProfiles.containsKey(Integer.toString(nextId))){
    		nextId++;
    	}
    	return Integer.toString(nextId);
    }
    
    public String getNextHiringTeamId(){
    	loadHiringTeamsFromFile();
    	int nextId = contentStoreHiringTeam.size() + 1;
    	while(contentStoreHiringTeam.containsKey(Integer.toString(nextId))){
    		nextId++;
    	}
    	return Integer.toString(nextId);
    }
    
    public String getNextJobApplicationId(){
    	loadJobApplicationsFromFile();
    	int nextId = contentStoreApplications.size() + 1;
    	while(contentStoreApplications.containsKey(Integer.toString(nextId))){
    		nextId++;
    	}
    	return Integer.toString(nextId);
    }
    
    public String getNextJobPostingId(){
    	loadJobPostingsFromFile();
    	int nextId = contentStorePostings.size() + 1;
    	while(contentStorePostings.containsKey(Integer.toString(nextId))){
    		nextId++;
    	}
    	return Integer.toString(nextId);
    }
    
    public String getNextReviewId(){
    	loadReviewsFromFile();
    	int nextId = contentStoreReviews.size() + 1;
    	while(contentStoreReviews.containsKey(Integer.toString(nextId))){
    		nextId++;
    	}
    	return Integer.toString(nextId);
    }
    
    public String getNextTeamMemberProfileId(){
    	loadTeamMemberProfilesFromFile();
    	int nextId = contentStoreTeamProfiles.size() + 1;
    	while(contentStoreTeamProfiles.containsKey(Integer.toString(nextId))){
    		nextId++;
    	}
    	return Integer.toString(nextId);
    }
    public String getJobApplicationAssignmentId(){
        loadJobApplicationAssignmentsFromFile();
    	int nextId = contentStoreJobApplicationAssignment.size() + 1;
    	while(contentStoreJobApplicationAssignment.containsKey(Integer.toString(nextId))){
    		nextId++;
    	}
    	return Integer.toString(nextId);
    }
    
    //QUERY JOB POSTINGS
//  private String description;
//  private String companyProfileId;
//	  private String positionType;
//	  private String desiredSkills;
//  private String salaryLevel;
//  private String location;
//	  private String status; //(created, open, in-review, processed, sent invitations)
//	  private String archived;
	
    public JobPostings searchJobPostingKeyword(String keyword){
      	//		
    	//match as a substring
    	//note (?i: PATTERN ) makes match case insensitive

  	  	loadJobPostingsFromFile();
    	  
    	keyword = "(?i:.*" + keyword + ".*)";
    	
      	List<JobPosting> jobPostingsList = new ArrayList<JobPosting>();

      	for(JobPosting posting : contentStorePostings.values()){
      		if(posting.getTitle().matches(keyword) || posting.getSkills().matches(keyword) || posting.getStatus().matches(keyword) || posting.getDescription().matches(keyword)){
      			jobPostingsList.add(posting);
      		}
      	}
      	
      	JobPostings newJobPostings = new JobPostings(jobPostingsList);
      	
      	if(jobPostingsList.size() == 0){
      		newJobPostings = null;
      	} 
      	
      	
      	return newJobPostings;
      }
    
  public JobPostings searchJobPostingAttribute(String query, String attrbute){
  	//		
	//match as a substring
	//note (?i: PATTERN ) makes match case insensitive

	loadJobPostingsFromFile();
	  
	query = "(?i:.*" + query + ".*)";
	
  	List<JobPosting> jobPostingsList = new ArrayList<JobPosting>();

  	for(JobPosting posting : contentStorePostings.values()){
  		
  		if(attrbute == "title"){
  			if(posting.getTitle().matches(query)){
  	  			jobPostingsList.add(posting);
  	  		}
  		} else if(attrbute == "skills"){
  			if(posting.getSkills().matches(query)){
  	  			jobPostingsList.add(posting);
  	  		}
  		} else if(attrbute == "status"){
  			if(posting.getStatus().matches(query)){
  	  			jobPostingsList.add(posting);
  	  		}
  		} else if(attrbute == "description"){
  			if(posting.getDescription().matches(query)){
  	  			jobPostingsList.add(posting);
  	  		}
  		} 
  		
  	}
  	if(jobPostingsList.size() == 0)
  		return null;
	  
  	JobPostings newJobPostings = new JobPostings(jobPostingsList);
  	
  	return newJobPostings;
  }
  
  public JobPostings getAllJobPostings(){
	  //
	  loadJobPostingsFromFile();
	  List<JobPosting> jobPostingsList = new ArrayList<JobPosting>();
	
	  for(JobPosting posting : contentStorePostings.values()){
		
		  jobPostingsList.add(posting);
		
	  }
	  if(jobPostingsList.size() == 0)
		  return null;
	  
	  JobPostings newJobPostings = new JobPostings(jobPostingsList);
	
	  return newJobPostings;
  }
  
  public JobApplications searchJobApplicationsPostId(String query){
	  	//		
		//match as a substring
		//query = ".*" + query + ".*";
	  	loadJobApplicationsFromFile();
	  	
	  	List<JobApplication> JobApplicationsList = new ArrayList<JobApplication>();

	  	for(JobApplication app : contentStoreApplications.values()){
	  		if(app.getJobPostId().matches(query)){
	  			JobApplicationsList.add(app);
	  		}
	  	}
	  	
	  	if(JobApplicationsList.size() == 0)
	  		return null;
	  	
	  	
	  	JobApplications newJobApplications = new JobApplications(JobApplicationsList);
	  	return newJobApplications;
  }
  
  public JobApplications searchApplicationsTeamMembertId(String query){
	  	//		
		//match as a substring
		//query = ".*" + query + ".*";
	  	loadJobApplicationAssignmentsFromFile();
	  	
	  	List<JobApplication> JobApplicationsList = new ArrayList<JobApplication>();

	  	for(JobApplicationAssignment appAss : contentStoreJobApplicationAssignment.values()){
	  		if(appAss.getReviewer1().matches(query) || appAss.getReviewer2().matches(query)){
	  			
	  			//add job...
	  			System.out.println("Found id " + appAss.getJobApplicationId());
	  			JobApplicationsList.add(getJobApplication(appAss.getJobApplicationId()));
	  		}
	  	}
	  	
	  	if(JobApplicationsList.size() == 0)
	  		return null;
	  	
	  	
	  	JobApplications newJobApplications = new JobApplications(JobApplicationsList);
	  	return newJobApplications;
}
  public JobApplications searchJobApplicationsUserId(String query){
	  	//		
		//match as a substring
		//query = ".*" + query + ".*";
	  	loadJobApplicationsFromFile();
	  	
	  	List<JobApplication> JobApplicationsList = new ArrayList<JobApplication>();

	  	for(JobApplication app : contentStoreApplications.values()){
	  		if(app.getUserProfileId().matches(query)){
	  			JobApplicationsList.add(app);
	  		}
	  	}
	  	
	  	if(JobApplicationsList.size() == 0)
	  		return null;
	  	
	  	
	  	JobApplications newJobApplications = new JobApplications(JobApplicationsList);
	  	
	  	return newJobApplications;
}
  
  public JobApplications getAllJobApplications(){
	  	//
	  	loadJobApplicationsFromFile();
	  	List<JobApplication> jobApplicationList = new ArrayList<JobApplication>();

	  	for(JobApplication application : contentStoreApplications.values()){
	  		
	  		jobApplicationList.add(application);
	  		
	  	}
	  	JobApplications newJobApplications = new JobApplications(jobApplicationList);
	  	
	  	return newJobApplications;
}
	  
  
  
/*	
  public JobPosting searchJobPostingPositionType(String query){
  	return contentStorePostings.remove(id);
  }
  public JobPosting searchJobPostingDesiredSkills(String query){
  	return contentStorePostings.remove(id);
  }
  public JobPosting searchJobPostingSalaryLevel(String query){
  	return contentStorePostings.remove(id);
  }
  public JobPosting searchJobPostingLocation(String query){
  	return contentStorePostings.remove(id);
  }
*/
//loads from file if we've reset from last time
	void loadUserProfilesFromFile(){
	  	if(contentStoreUserProfiles.size() == 0){
	    	try {
	    		
	    		File file = new File(USER_PROFILE_FILEDIR);
	    		JAXBContext jaxbContext = JAXBContext.newInstance(UserProfiles.class);
	
	    		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    		UserProfiles profiles = (UserProfiles) jaxbUnmarshaller.unmarshal(file);
	    		
	    		for(UserProfile profile : profiles.getUserProfiles()){
	    			contentStoreUserProfiles.put(profile.getId(), profile);
	          	}
	    		
	    		System.out.println(profiles);
	    		
			} catch (JAXBException e) {
				e.printStackTrace();
			}
	  	}
	}
	
	void loadCompanyProfilesFromFile(){
	  	if(contentStoreCompProfiles.size() == 0){
	    	try {
	    		
	    		File file = new File(COMPANY_PROFILE_FILEDIR);
	    		JAXBContext jaxbContext = JAXBContext.newInstance(CompanyProfiles.class);
	
	    		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    		CompanyProfiles profiles = (CompanyProfiles) jaxbUnmarshaller.unmarshal(file);
	    		
	    		for(CompanyProfile profile : profiles.getCompanyProfiles()){
	    			contentStoreCompProfiles.put(profile.getId(), profile);
	          	}
	    		
	    		System.out.println(contentStoreCompProfiles.size());
	    		
			} catch (JAXBException e) {
				e.printStackTrace();
			}
	  	}
	}
	
	void loadHiringTeamsFromFile(){
	  	if(contentStoreHiringTeam.size() == 0){
	    	try {
	    		
	    		File file = new File(HIRING_TEAM_FILEDIR);
	    		JAXBContext jaxbContext = JAXBContext.newInstance(HiringTeams.class);
	
	    		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    		HiringTeams teams = (HiringTeams) jaxbUnmarshaller.unmarshal(file);
	    		
	    		for(HiringTeamStore team : teams.getHiringTeams()){
	    			contentStoreHiringTeam.put(team.getId(), team);
	          	}
	    		
	    		System.out.println(teams);
	    		
			} catch (JAXBException e) {
				e.printStackTrace();
			}
	  	}
	}
	
	void loadJobApplicationsFromFile(){
	  	if(contentStoreApplications.size() == 0){
	    	try {
	    		
	    		File file = new File(JOB_APPLICATION_FILEDIR);
	    		JAXBContext jaxbContext = JAXBContext.newInstance(JobApplications.class);
	
	    		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    		JobApplications apps = (JobApplications) jaxbUnmarshaller.unmarshal(file);
	    		
	    		for(JobApplication app : apps.getJobApplications()){
	    			contentStoreApplications.put(app.getId(), app);
	          	}
	    		
	    		System.out.println(apps);
	    		
			} catch (JAXBException e) {
				e.printStackTrace();
			}
	  	}
	}
	
	void loadJobPostingsFromFile(){
	  	if(contentStorePostings.size() == 0){
	    	try {
	    		
	    		File file = new File(JOB_POSTING_FILEDIR);
	    		JAXBContext jaxbContext = JAXBContext.newInstance(JobPostings.class);
	
	    		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    		JobPostings posts = (JobPostings) jaxbUnmarshaller.unmarshal(file);
	    		
	    		for(JobPosting post : posts.getJobPostings()){
	    			contentStorePostings.put(post.getId(), post);
	          	}
	    		
	    		System.out.println(posts);
	    		
			} catch (JAXBException e) {
				e.printStackTrace();
			}
	  	}
	}
	
	void loadReviewsFromFile(){
	  	if(contentStoreReviews.size() == 0){
	    	try {
	    		
	    		File file = new File(REVIEW_FILEDIR);
	    		JAXBContext jaxbContext = JAXBContext.newInstance(Reviews.class);
	
	    		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    		Reviews reviews = (Reviews) jaxbUnmarshaller.unmarshal(file);
	    		
	    		for(Review review : reviews.getReviews()){
	    			contentStoreReviews.put(review.getId(), review);
	          	}
	    		
	    		System.out.println(reviews);
	    		
			} catch (JAXBException e) {
				e.printStackTrace();
			}
	  	}
	}
	
	void loadTeamMemberProfilesFromFile(){
	  	if(contentStoreTeamProfiles.size() == 0){
	    	try {
	    		
	    		File file = new File(TEAM_MEMBER_PROFILE_FILEDIR);
	    		JAXBContext jaxbContext = JAXBContext.newInstance(TeamMemberProfiles.class);
	
	    		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    		TeamMemberProfiles profiles = (TeamMemberProfiles) jaxbUnmarshaller.unmarshal(file);
	    		
	    		for(TeamMemberProfile profile : profiles.getTeamMemberProfiles()){
	    			contentStoreTeamProfiles.put(profile.getId(), profile);
	          	}
	    		
	    		System.out.println(profiles);
	    		
			} catch (JAXBException e) {
				e.printStackTrace();
			}
	  	}
	}
	
	void loadJobApplicationAssignmentsFromFile(){
	  	if(contentStoreJobApplicationAssignment.size() == 0){
	    	try {
	    		
	    		File file = new File(JOB_APPLICATION_ASSIGNMENT_FILEDIR);
	    		JAXBContext jaxbContext = JAXBContext.newInstance(JobApplicationAssignments.class);
	
	    		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    		JobApplicationAssignments profiles = (JobApplicationAssignments) jaxbUnmarshaller.unmarshal(file);
	    		
	    		for(JobApplicationAssignment ass : profiles.getJobApplicationAssignments()){
	    			contentStoreJobApplicationAssignment.put(ass.getId(), ass);
	          	}
	    		
	    		System.out.println(profiles);
	    		
			} catch (JAXBException e) {
				e.printStackTrace();
			}
	  	}
	}
	
	//checks if the two given reviewer ids are from the specified company
	public boolean reviewersFromCompany(String companyId, String reviewer1, String reviewer2){
		loadHiringTeamsFromFile();
		
		for(HiringTeamStore team : contentStoreHiringTeam.values()){
			//for
			if(team.getCompanyProfileId().matches(companyId)){
				System.out.println("company" + companyId);
				//check the team
				if(team.getMember1id().matches(reviewer1) || team.getMember2id().matches(reviewer1) || team.getMember3id().matches(reviewer1) || team.getMember4id().matches(reviewer1) || team.getMember5id().matches(reviewer1)){
					if(team.getMember1id().matches(reviewer2) || team.getMember2id().matches(reviewer2) || team.getMember3id().matches(reviewer2) || team.getMember4id().matches(reviewer2) || team.getMember5id().matches(reviewer2)){
						return true;
					}
				}
			}
		}

		
		return false;
	}
	
	public boolean existsInHiringTeam(TeamMemberProfile member){
		loadHiringTeamsFromFile();
		
		String memberId = member.getId();
		for(HiringTeamStore team : contentStoreHiringTeam.values()){
			//for
			
			//check the team
			if(team.getMember1id().matches(memberId) || team.getMember2id().matches(memberId) || 
					team.getMember3id().matches(memberId) || team.getMember4id().matches(memberId) || team.getMember5id().matches(memberId)){
				return true;	
			}
			
		}

		return false;
		
	}
	
	public boolean assignmentAlreadyExists(JobApplication app){
		loadJobApplicationAssignmentsFromFile();
		
		String appId = app.getId();
		for(JobApplicationAssignment assign : contentStoreJobApplicationAssignment.values()){
			//for
			//check the team
			if(assign.getJobApplicationId().matches(appId)){
				return true;	
			}
			
		}

		return false;
		
	}
    
}	
