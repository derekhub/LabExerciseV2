package HandsOnExerciseCoreJava;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class MyDAO implements IMyDAO<Donor>{

    private Connection con;
    
    public MyDAO(){
        super();
        con=MySQLConnection.getMyOracleConnection();
    }
    
    @Override
    public int add(Donor object) {

        int rowAdded = 0;
        
        try {
            String sql = "insert into Donor values(?,?)";
            
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, object.getName());
            pstmt.setString(2, object.getEmail());
            
            rowAdded = pstmt.executeUpdate();
            
            
        } catch (Exception e) {
            // TODO: handle exception
        }

        return rowAdded;
    }

    @Override
    public int update(String name) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Donor find(String name) {


        Statement pstmt;
        ResultSet rs = null;
        try {
            pstmt = con.createStatement();
            
            rs = pstmt.executeQuery ("select 'email' from Donor where name='" + name + "'");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
                     
       
         try {
            System.out.println(rs.next());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
        return null;
    }

    @Override
    public int delete(String name) {
        
        int rowDeleted = 0;
        
        try {
            String sql = "DELETE FROM Donor WHERE name = '" + name + "'";
            
            System.out.println(sql);

            
            PreparedStatement pstmt = con.prepareStatement(sql);
                        
            rowDeleted = pstmt.executeUpdate();
            
            
            
        } catch (Exception e) {
            // TODO: handle exception
        }

        return rowDeleted;
    }

    @Override
    public ArrayList<Donor> findAll() throws SQLException {
        // TODO Auto-generated method stub
        
        
        ArrayList<Donor> alist = new ArrayList<Donor>(); 

        
        Statement pstmt = con.createStatement();
        
         ResultSet  rs = pstmt.executeQuery ("select * from Donor");
                      
         while(rs.next())
         {

         int os1 = rs.getInt("projectId");
         String os2 = rs.getString("name");
         String os3 = rs.getString("email");
          
          Donor d = new Donor(os1,os2, os3);
        alist.add(d);
         }

        
        
        
        
        return alist;
    }

}
