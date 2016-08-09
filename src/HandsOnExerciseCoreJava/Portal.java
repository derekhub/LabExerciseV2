package HandsOnExerciseCoreJava;

import java.util.ArrayList;
import java.util.Map.Entry;

public class Portal {
	
	private ArrayList<Project> projects = new ArrayList<Project>();
	private ArrayList<Donor> donors = new ArrayList<Donor>();

	
	public Donor createDonor(int donorId, String name, String email){
		Donor d = new Donor(donorId, name, email);
		donors.add(d);
		return d;
	}
	
	public Project createProject(int projectId, String name, String details, double projectCost, ArrayList<String> photos){
		Project p = new Project(projectId, name, details, projectCost, photos);
		projects.add(p);
		return p;
		
	}
	
	//1 Add the Project Details
	public void addProjectDetails(Project project, String details){
		project.setDetails(details);
	}
	
	//2 The Donors should be able to view List of Available Project and its details 
	public void displayProjectsAndDetails(){
		for(Project p: projects){
			System.out.println("Project: " + p.getName());
			System.out.println("Details: " + p.getDetails());
			System.out.println("Project Cost: " + p.getProjectCost());
			System.out.println("Total donations: " + p.getDonationAmount());
			System.out.println("Pending amount: " + p.getPendingAmount());
			System.out.print("Photos: ");
			for(String photo: p.getPhotos()){
				System.out.print(photo + " ");
			}
			System.out.println("\n");
		}
	}
	
	//3 The Donors should be able to Donate to the Project of his Choice
	public void donate(Project project, Donor donor, Integer amount){
		
		project.receiveDonation(amount, donor);
		donor.addProjectToDonationList(project);
		
	}
	
	//4 Should Print the List of all the Project along with its  Donors
	public void displayAllProjectsAndDonors(){
		for(Project p: projects){
			System.out.println("Project: " + p.getName());
			System.out.println("Donors: ");
			for(Entry<Donor, Integer> d: p.getDonorAmountDonated().entrySet()){
				System.out.println(d.getKey().getName() + " = $" + d.getValue());
			}
			System.out.println();
		}
	}
	
	//5 Should be able to the Print List of Donors for a Specific Project 
	public void displayDonorsForProject(Project project){
		System.out.println("Donors for project " + project.getName() + ":");
		for(Entry<Donor, Integer> d: project.getDonorAmountDonated().entrySet()){
			System.out.println(d.getKey().getName() + " = $" + d.getValue());
		}
	}
	
	//5
//	public void displayDonorsForProject(Project project){
//		System.out.println("Donors for project " + project.getName() + ":");
//		for(Donor d: project.getDonors()){
//			System.out.println(d.getName());
//		}
//	}

	

}
