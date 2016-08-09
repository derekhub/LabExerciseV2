package sql.stuff;

import java.sql.SQLException;
import java.util.Collection;

import HandsOnExerciseCoreJava.Donor;

public class TestDonorSql {

    public static void main(String[] args) throws SQLException {

        DonorSql donorSql = new DonorSql();
        int key = 6;
        {
            switch (key) {
            case 1:
                // add donors
                int rowAdded = donorSql.add(new Donor(001, "Derek", "d.hotmail.com"));
                donorSql.add(new Donor(002, "Calvin", "c.hotmail.com"));
                donorSql.add(new Donor(003, "Simon", "s.hotmail.com"));
                donorSql.add(new Donor(004, "Louise", "l.hotmail.com"));
                System.out.println("row added lel: " + rowAdded);
                break;

            case 2:
                // delete donor
                int rowDeleted = donorSql.delete(1);
                System.out.println("row deleted lel: " + rowDeleted);
                break;

            case 3:
                // find all donors
                Collection<Donor> findAll = donorSql.findAll();
                for (Donor d : findAll) {
                    System.out.println(d.getDonorId() + ", " + d.getName().trim() + ", " + d.getEmail().trim());
                }
                break;

            case 4:
                // find donor by Id
                System.out.println(donorSql.find(1).getName());
                break;

            case 5:
                // add donor with a procedure
                int rowAddedd = donorSql.addWithProcedure(new Donor(006, "Becky", "goodhair@hotmail.com"));
                System.out.println("row added lel: " + rowAddedd);
                break;

            case 6:
                // authenticate
                System.out.println(donorSql.authenticate("Calvin", "c.hotmail.com"));
                break;

            default:
                break;
            }

        }

    }
}
