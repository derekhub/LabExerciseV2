package HandsOnExerciseCoreJava;

import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {

    	Portal portal = new Portal();
    	ArrayList<String> photos = new ArrayList<String>();
    	photos.add("photo1");
    	photos.add("photo2");
    	photos.add("photo3");
    	
    	Donor d1 = portal.createDonor(001, "Derek", "d@hotmail.com");
    	Donor d2 = portal.createDonor(002, "Andrew", "a@hotmail.com");
    	Donor d3 = portal.createDonor(003, "Kevin", "k@hotmail.com");
    	
    	Project p1 = portal.createProject(001, "CureIndia", "For curing India", 10000, photos);
    	Project p2 = portal.createProject(002, "Apples", null, 50000, photos);
    	Project p3 = portal.createProject(003, "Bananas", "Giving bananas to the needy", 800000, photos);

    	
    	//3
    	portal.donate(p1, d1, 1000);
    	portal.donate(p1, d2, 1234);
    	portal.donate(p1, d3, 4567);    	
    	portal.donate(p2, d2, 2500);
    	portal.donate(p3, d3, 8888);
    	
    	//1
    	portal.addProjectDetails(p2, "Giving apples to the poor");
    	        
    	//2
    	portal.displayProjectsAndDetails();
    	    	
    	//4
//    	portal.displayAllProjectsAndDonors();

    	//5
//    	portal.displayDonorsForProject(p1);
    	

    }

}
