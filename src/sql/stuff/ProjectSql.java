package sql.stuff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import HandsOnExerciseCoreJava.MySQLConnection;
import HandsOnExerciseCoreJava.Project;

public class ProjectSql {

    private Connection con;

    public ProjectSql() {
        super();
        con = MySQLConnection.getMyOracleConnection();
    }

    public int add(Project object) {
        int rowAdded = 0;
        try {
            String sql = "insert into Project values(?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, object.getProjectId());
            pstmt.setString(2, object.getName());
            pstmt.setString(3, object.getDetails());
            pstmt.setDouble(4, object.getProjectCost());
            pstmt.setDouble(5, object.getDonationAmount());
            pstmt.setDouble(6, object.getPendingAmount());
            rowAdded = pstmt.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return rowAdded;
    }

    public int update(int projectId, String columnName, String value) {
        // TODO Auto-generated method stub
        int rowUpdated = 0;
        try {
            String sql = "UPDATE Project SET " + columnName + " = '" + value + "' WHERE projectId = '" + projectId
                    + "'";
            System.out.println(sql);
            PreparedStatement pstmt = con.prepareStatement(sql);
            rowUpdated = pstmt.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return rowUpdated;
    }

    public Project find(int projectId) throws SQLException {
        Statement pstmt;
        ResultSet rs = null;
        try {
            pstmt = con.createStatement();
            rs = pstmt.executeQuery("select * from Project where projectId='" + projectId + "'");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            // System.out.println(rs.next());
            rs.next();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Project p = new Project(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), null);
        p.setDonationAmount(rs.getDouble(5));
        return p;
    }

    public int delete(int projectId) {
        int rowDeleted = 0;
        try {
            String sql = "DELETE FROM Project WHERE projectId = '" + projectId + "'";
            System.out.println(sql);
            PreparedStatement pstmt = con.prepareStatement(sql);
            rowDeleted = pstmt.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return rowDeleted;
    }

    public Collection<Project> findAll() throws SQLException {
        Collection<Project> alist = new ArrayList<Project>();
        Statement pstmt = con.createStatement();
        ResultSet rs = pstmt.executeQuery("select * from Project");
        while (rs.next()) {
            int os1 = rs.getInt("projectId");
            String os2 = rs.getString("name");
            String os3 = rs.getString("details");
            double os4 = rs.getDouble("projectCost");
            double os5 = rs.getDouble("donationAmount");

            Project p = new Project(os1, os2, os3, os4, null);
            p.setDonationAmount(os5);
            alist.add(p);
        }
        return alist;
    }

}
