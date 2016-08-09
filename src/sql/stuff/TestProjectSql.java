package sql.stuff;

import java.sql.SQLException;
import java.util.Collection;

import HandsOnExerciseCoreJava.Project;

public class TestProjectSql {

    public static void main(String[] args) throws SQLException {
        // TODO Auto-generated method stub

        ProjectSql projectSql = new ProjectSql();
        int key = 1;
        {
            switch (key) {
            case 1:
                int rowAdded = projectSql.add(new Project(001, "CureIndia", "Cures diseases in India", 1000000, null));
                projectSql.add(new Project(002, "Bananas", "Give bananas to the poor", 750000, null));
                projectSql.add(new Project(003, "Animal Help", "Clean and feed trash pigs", 1500000, null));
                projectSql.add(new Project(004, "CleanWater", "Provides clean water for poor", 20000000, null));
                System.out.println("row added lel: " + rowAdded);
                break;

            case 2:
                int rowDeleted = projectSql.delete(1);
                System.out.println("row deleted lel: " + rowDeleted);
                break;

            case 3:
                Collection<Project> findAll = projectSql.findAll();
                for (Project p : findAll) {
                    System.out.println(p.getProjectId() + ", " + p.getName().trim() + ", " + p.getDetails().trim()
                            + ", " + p.getProjectCost() + ", " + p.getDonationAmount());
                }
                break;

            case 4:
                System.out.println(projectSql.find(1).getName());
                break;

            case 5:
                System.out.println(projectSql.update(2, "donationamount", "5000"));
                break;

            default:
                break;
            }
        }
    }
}
