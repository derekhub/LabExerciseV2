package sql.stuff;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import HandsOnExerciseCoreJava.Donor;
import HandsOnExerciseCoreJava.MySQLConnection;

public class DonorSql {

    private Connection con;

    public DonorSql() {
        super();
        con = MySQLConnection.getMyOracleConnection();
    }

    public int add(Donor object) {
        int rowAdded = 0;
        try {
            String sql = "insert into Donor values(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, object.getDonorId());
            pstmt.setString(2, object.getName());
            pstmt.setString(3, object.getEmail());
            rowAdded = pstmt.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return rowAdded;
    }

    public int update(String name) {
        // TODO Auto-generated method stub
        return 0;
    }

    public Donor find(int donorId) throws SQLException {
        Statement pstmt;
        ResultSet rs = null;
        try {
            pstmt = con.createStatement();
            rs = pstmt.executeQuery("select * from Donor where donorId='" + donorId + "'");
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
        Donor d = new Donor(rs.getInt(1), rs.getString(2), rs.getString(3));
        return d;
    }

    public int delete(int donorId) {
        int rowDeleted = 0;
        try {
            String sql = "DELETE FROM Donor WHERE donorId = '" + donorId + "'";
            System.out.println(sql);
            PreparedStatement pstmt = con.prepareStatement(sql);
            rowDeleted = pstmt.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return rowDeleted;
    }

    public Collection<Donor> findAll() throws SQLException {
        Collection<Donor> alist = new ArrayList<Donor>();
        Statement pstmt = con.createStatement();
        ResultSet rs = pstmt.executeQuery("select * from Donor");
        while (rs.next()) {
            int os1 = rs.getInt("donorId");
            String os2 = rs.getString("name");
            String os3 = rs.getString("email");
            Donor d = new Donor(os1, os2, os3);
            alist.add(d);
        }
        return alist;
    }

    public int addWithProcedure(Donor object) throws SQLException {
        String hi = "{call hi(?,?,?)}";
        CallableStatement callableStatement = con.prepareCall(hi);
        callableStatement.setInt(1, object.getDonorId());
        callableStatement.setString(2, object.getName());
        callableStatement.setString(3, object.getEmail());
        callableStatement.executeQuery();
        return 1;
    }

    public boolean authenticate(String name, String email) {
        boolean flag = false;
        try {
            Statement pstmt = con.createStatement();
            ResultSet rs = pstmt.executeQuery("select * from Donor");
            while (rs.next()) {
                String os1 = rs.getString("name").trim();
                String os2 = rs.getString("email").trim();
                if ((os1.equals(name)) && (os2.equals(email))) {
                    flag = true;
                    break;
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return flag;
    }

}
