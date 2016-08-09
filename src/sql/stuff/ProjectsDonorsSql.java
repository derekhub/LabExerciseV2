package sql.stuff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import HandsOnExerciseCoreJava.Donor;
import HandsOnExerciseCoreJava.MySQLConnection;
import HandsOnExerciseCoreJava.Project;

public class ProjectsDonorsSql {

    private Connection con;

    public ProjectsDonorsSql() {
        super();
        con = MySQLConnection.getMyOracleConnection();
    }

    public void donate(Project project, Donor donor, int amount) {

        try {
            // insert row into projectsdonors table (repeated pid or did is
            // fine)
            String sql = "insert into ProjectsDonors values(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, project.getProjectId());
            pstmt.setInt(2, donor.getDonorId());
            pstmt.setInt(3, amount);
            pstmt.executeUpdate();

            // return the current project cost and current donation amount and
            // calculate new pending amount and donation amount
            Statement projectStatement = con.createStatement();
            ResultSet rs = projectStatement
                    .executeQuery("select * from Project WHERE projectId=" + project.getProjectId());
            rs.next();

            double currentProjectCost = rs.getDouble("projectCost");
            double currentDonationAmount = rs.getDouble("donationAmount");
            double newDonationAmount = currentDonationAmount + amount;
            double newPendingAmount = currentProjectCost - newDonationAmount;

            // update the project in project table with new pending amount and
            // new donation amount
            ProjectSql pSql = new ProjectSql();
            pSql.update(project.getProjectId(), "donationAmount", "" + newDonationAmount);
            pSql.update(project.getProjectId(), "pendingAmount", "" + newPendingAmount);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public ArrayList<Donor> listOfDonorsForProject(Project project) {
        HashSet<Integer> hset = new HashSet<Integer>();
        ArrayList<Donor> dlist = new ArrayList<Donor>();
        try {
            Statement pStatement = con.createStatement();
            ResultSet rs = pStatement
                    .executeQuery("select * from ProjectsDonors WHERE projectId=" + project.getProjectId());
            while (rs.next()) {
                int os1 = rs.getInt("donorId");
                hset.add(os1);
            }

            DonorSql dsql = new DonorSql();
            for (int x : hset) {
                Donor d = dsql.find(x);
                dlist.add(d);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return dlist;

    }

    public void listProjectDetailsById(int projectId) {
        String returnString = "";

        Statement pstmt;
        ResultSet rs = null;
        try {
            pstmt = con.createStatement();
            rs = pstmt.executeQuery("select * from Project where projectId='" + projectId + "'");
            rs.next();
            returnString += "Project ID: " + rs.getInt(1) + "\n";
            returnString += "Name: " + rs.getString(2) + "\n";
            returnString += "Details: " + rs.getString(3) + "\n";
            returnString += "Amount Collected: " + rs.getDouble(5) + "\n";
            returnString += "Pending Amount: " + rs.getDouble(6) + "\n";
            returnString += "Donors: \n";
            ArrayList<Donor> dlist = listOfDonorsForProject(new Project(projectId, "", "", 0, null));
            for (Donor d : dlist) {
                returnString += d.getName().trim() + "\n";
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(returnString);
    }

    public void listOfDonationsForPeriodOfTime() {

    }

}
