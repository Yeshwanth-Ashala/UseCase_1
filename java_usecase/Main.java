import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        BookDAO bookDAO=new BookDAO();
        Transaction transaction=new Transaction();
        UserDAO userDAO=new UserDAO();

        while(true){
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Update Book");
            System.out.println("4. Search Book");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Register User");
            System.out.println("8. Exit");
            System.out.println("Enter choice:");
            int choice=sc.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Enter bookId:");
                    int bookId=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter title:");
                    String title=sc.nextLine();
                    System.out.println("Enter author:");
                    String author=sc.nextLine();
                    System.out.println("Enter totalCopies:");
                    int totalCopies=sc.nextInt();
                    bookDAO.addBook(bookId,title,author,totalCopies);
                    break;
                case 2:
                    System.out.println("Enter bookId:");
                    int rBookId=sc.nextInt();
                    bookDAO.removeBook(rBookId);
                    break;
                case 3:
                    System.out.println("Enter bookId:");
                    int uBookId=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter new title:");
                    String newTitle=sc.nextLine();
                    System.out.println("Enter new author:");
                    String newAuthor=sc.nextLine();
                    bookDAO.updateBook(uBookId,newTitle,newAuthor);
                    break;
                case 4:
                    sc.nextLine();
                    System.out.println("Enter author:");
                    String sAuthor=sc.nextLine();
                    System.out.println("Enter title:");
                    String sTitle=sc.nextLine();
                    bookDAO.searcBook(sAuthor,sTitle);
                    break;
                case 5:
                    System.out.println("Enter transactionId:");
                    int transId=sc.nextInt();
                    System.out.println("Enter bookId:");
                    int iBookId=sc.nextInt();
                    System.out.println("Enter userId:");
                    int iUserId=sc.nextInt();
                    sc.nextLine();
                    if(!userDAO.userExists(iUserId)){
                        System.out.println("User not found enter name to register:");
                        String name=sc.nextLine();
                        userDAO.registerUser(iUserId,name);
                    }
                    transaction.issueBook(transId,iBookId,iUserId);
                    break;
                case 6:
                    System.out.println("Enter bookId:");
                    int retBookId=sc.nextInt();
                    System.out.println("Enter userId:");
                    int retUserId=sc.nextInt();
                    transaction.returnBook(retBookId,retUserId);
                    break;
                case 7:
                    System.out.println("Enter userId:");
                    int newUserId=sc.nextInt();
                    sc.nextLine();
                    if(!userDAO.userExists(newUserId)){
                        System.out.println("Enter name:");
                        String newName=sc.nextLine();
                        userDAO.registerUser(newUserId,newName);
                    }
                    else{
                        System.out.println("User already exists");
                    }
                    break;
                case 8:
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }
}