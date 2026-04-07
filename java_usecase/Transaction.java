import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Transaction{
    public void issueBook(int transactionId,int bookId,int userId){
        String checkAvbQuery="select available_copies from books where book_id = ?";
        String issueQuery="insert into transactions (transaction_id, book_id, user_id, issue_date) values (?, ?, ?, ?)";
        String updateBookQuery="update books set available_copies = available_copies - 1 where book_id = ?";
        try(Connection con=DataBaseConnection.getConnection()){
            try(PreparedStatement checkStmt = con.prepareStatement(checkAvbQuery)){
                checkStmt.setInt(1, bookId);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()){
                    int avbCopies = rs.getInt("available_copies");
                    if (avbCopies > 0){
                        System.out.println("\nBook is available.");
                    }
                    else{
                        System.out.println("\nNo copies available to issue.");
                        return;
                    }
                }
                else{
                    System.out.println("\nIssue Failed: Book ID " + bookId + " does not exist in the library.");
                    return;
                }
        }
        LocalDate today = LocalDate.now();
            try(PreparedStatement issue=con.prepareStatement(issueQuery)){
                issue.setInt(1, transactionId);
                issue.setInt(2, bookId);
                issue.setInt(3, userId);
                issue.setDate(4, Date.valueOf(today));
                issue.executeUpdate();
            }
            try(PreparedStatement updateStmt=con.prepareStatement(updateBookQuery)){
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();
            }
            System.out.println("Book issued Successfully");
    }
    catch (Exception e){
       System.out.println("\nERROR: Failed to issue book. " + e.getMessage());
    }
}

    public void returnBook(int bookId,int userId){
        String validTrxn="select transaction_id, issue_date from transactions where book_id = ? and user_id = ? and return_date is null";
        String updateTrxnQuery="update transactions set return_date = ?, fine_amount = ? where transaction_id = ?";
        String updateBookQuery="update books set available_copies = available_copies + 1 where book_id = ?";
        try(Connection con=DataBaseConnection.getConnection()){
            int trxnId=-1;
            LocalDate issueDate=null;
            try(PreparedStatement stmt=con.prepareStatement(validTrxn)){
                stmt.setInt(1, bookId);
                stmt.setInt(2, userId);
                ResultSet rs=stmt.executeQuery();
                if(rs.next()){
                    trxnId=rs.getInt("transaction_id");
                    issueDate=rs.getDate("issue_date").toLocalDate();
                }
                else{
                    System.out.println("Wrong Book Returned!");
                    return;
                }
                LocalDate returnDate=LocalDate.now();
                LocalDate dueDate=issueDate.plusDays(15);
                double fineAmount=0.0;
                long daysLate=ChronoUnit.DAYS.between(dueDate, returnDate);
                if(daysLate>15){
                    fineAmount = (daysLate)* 1.0;
                }
                try (PreparedStatement updateTransStmt = con.prepareStatement(updateTrxnQuery)) {
                updateTransStmt.setDate(1, Date.valueOf(returnDate));
                updateTransStmt.setDouble(2, fineAmount);
                updateTransStmt.setInt(3, trxnId);
                updateTransStmt.executeUpdate();
            }
            try (PreparedStatement updateBookStmt = con.prepareStatement(updateBookQuery)) {
                updateBookStmt.setInt(1, bookId);
                updateBookStmt.executeUpdate();
            }
            if(fineAmount>0){
                System.out.println("Pay Fine Amount:"+fineAmount);
            }
            System.out.println("Successfully returened");
            }
        }
        catch (Exception e) {
            System.out.println("Exception"+e);
        }
    }

    
}
