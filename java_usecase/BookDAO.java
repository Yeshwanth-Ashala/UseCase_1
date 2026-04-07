import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO {
    public void addBook(int bookId,String title,String author,int totalCopies){
        String query="insert into books (book_id, title, author, total_copies, available_copies) values (?, ?, ?, ?, ?)";
        try(Connection con=DataBaseConnection.getConnection()){
            try(PreparedStatement stmt=con.prepareStatement(query)){
                stmt.setInt(1, bookId);
                stmt.setString(2, title);
                stmt.setString(3, author);
                stmt.setInt(4, totalCopies);
                stmt.setInt(5, totalCopies);
                int rs=stmt.executeUpdate();
                if(rs>0){
                    System.out.println("Book Added Successfully");
                }
            }
        }
        catch (Exception e) {
            System.out.println("Exception"+e);
        }
    }

    public void removeBook(int bookId){
        String query="select total_copies, available_copies from books where book_id = ?";
        String delquery="delete from books where book_id = ?";
        try(Connection con=DataBaseConnection.getConnection()){
            try(PreparedStatement stmt=con.prepareStatement(query)){
                stmt.setInt(1, bookId);
                ResultSet rs=stmt.executeQuery();
                if(rs.next()){
                    int totalCopies=rs.getInt(1);
                    int availableCopies=rs.getInt(2);
                    if(totalCopies!=availableCopies){
                        System.out.println("Cannot be as few are issued but not returened");
                        return;
                    }
                    try (PreparedStatement delstmt = con.prepareStatement(delquery)) {
                        delstmt.setInt(1, bookId);
                        int rowsAffected = delstmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("\nSUCCESS: Book ID " + bookId + " has been completely removed from the library.");
                        }
                    }
                }
                else{
                    System.out.println("Failed due to wrong input bookid");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateBook(int bookId,String newTitle,String newAuthor){
        String query="update books set title = ?, author = ? where book_id = ?";
        try(Connection con = DataBaseConnection.getConnection()){
            try(PreparedStatement stmt=con.prepareStatement(query)){
                stmt.setString(1, newTitle);
                stmt.setString(2, newAuthor);
                stmt.setInt(3,bookId);
                int rs=stmt.executeUpdate();
                if(rs>0){
                    System.out.println("Updated Successfully");
                }
                else{
                    System.out.println("Update Failed due to wrong bookId");
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void searcBook(String author,String title){
        String query="select * from books where lower(author) like lower(?) and lower(title) like lower(?)";
        try(Connection con=DataBaseConnection.getConnection()){
            try(PreparedStatement stmt=con.prepareStatement(query)){
                stmt.setString(1, "%" + author + "%");
                stmt.setString(2, "%" + title + "%");
                ResultSet rs=stmt.executeQuery();
                boolean found=false;
                while (rs.next()) {
                    found=true;
                    System.out.printf("ID: %d - Title: %s - Author: %s - Available: %d/%d%n",rs.getInt("book_id"),rs.getString("title"),rs.getString("author"),rs.getInt("available_copies"),rs.getInt("total_copies"));
                }
                if(!found){
                    System.out.println("Book Not Found");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
