import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public boolean userExists(int userId){
        String query="select user_id from users where user_id = ?";
        try(Connection con=DataBaseConnection.getConnection()){
            try(PreparedStatement stmt=con.prepareStatement(query)){
                stmt.setInt(1,userId);
                ResultSet rs=stmt.executeQuery();
                if(rs.next()){
                    return true;
                }
            }
        }
        catch(Exception e){
            System.out.println("Exception"+e);
        }
        return false;
    }

    public void registerUser(int userId,String name){
        String query="insert into users (user_id, name) values (?, ?)";
        try(Connection con=DataBaseConnection.getConnection()){
            try(PreparedStatement stmt=con.prepareStatement(query)){
                stmt.setInt(1,userId);
                stmt.setString(2,name);
                int rs=stmt.executeUpdate();
                if(rs>0){
                    System.out.println("New User Registered Successfully");
                }
            }
        }
        catch(Exception e){
            System.out.println("Exception"+e);
        }
    }
}