package sql.stuff;

import java.sql.Date;
import java.util.ArrayList;

import HandsOnExerciseCoreJava.Donor;
import HandsOnExerciseCoreJava.Project;

public class TestProjectsDonorsSql {

    public static void main(String[] args) {

        // ATTENTION: CREATING A NEW PROJECT EVERYTIME DOESNT MATTER BECAUSE THE
        // CODE ONLY LOOKS AT THE PROJECTID. NOTHING ELSE MATTERS
        ProjectsDonorsSql projectsDonorsSql = new ProjectsDonorsSql();
        int key = 4;
        {
            switch (key) {
            case 1:
                // donate
                projectsDonorsSql.donate(new Project(1, "CureIndia", "Cures diseases in India", 1000000, null),
                        new Donor(1, "Derek", "d.hotmail.com"), 5000);
                projectsDonorsSql.donate(new Project(2, "CureIndia", "Cures diseases in India", 1000000, null),
                        new Donor(2, "Calvin", "d.hotmail.com"), 9000);
                break;

            case 2:
                // list of donors for a particular project
                ArrayList<Donor> dlist = projectsDonorsSql
                        .listOfDonorsForProject(new Project(1, "CureIndia", "Cures diseases in India", 1000000, null));

                for (Donor d : dlist) {
                    System.out.println(d.getDonorId() + "," + d.getName().trim() + "," + d.getEmail().trim());
                }
                break;

            case 3:
                // return project details by code
                projectsDonorsSql.listProjectDetailsById(1);
                break;

            case 4:
                // return donations based on date
                Date date = new Date(100000);
                System.out.println(date);

                break;

            case 5:
                break;

            default:
                break;
            }
        }

    }

}
