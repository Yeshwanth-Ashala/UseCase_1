Python_USECASE_1:Smart Expense Tracker with Insights
    Tools: Pyton libraries(tkinter,csv,os,matplotlib)
    Modules and Packages concepts used.
    GUI is built with the help of tkinter.
    main file requests or  uses methods and functionality or analytics and tracker modules.
    User can add his/her expenses and view his montly expenses in pic chart form , also get maximum category expenses and category-wise expenses with the help of the application.
    Also handled exceptions while reading and writing in to  file.
    All the data is managed by "expenses.csv" file.
    ![Summary](<Screenshot 2026-04-08 081942.png>) 
    ![Monthly expenses](<Screenshot 2026-04-08 081930.png>) 
    ![Overall Summary](<Screenshot 2026-04-08 081856.png>) 
    ![Data read and saved into csv](<Screenshot 2026-04-08 081650.png>) 
    ![GUI](<Screenshot 2026-04-08 081528.png>)

SQL_USECASE_1:Online Retail Sales Analysis Database
    Tools: Oracle SQL,Oracle Database XE,SQL Plus.
    Relational Database concepts used.
    Database Schema is built with three primary tables:books,users,and transactions.
    The system uses Primary Keys and Foreign Keys to maintain data integrity and link users to their borrowed books.
    The database manages the logic for available_copies,ensuring the library never issues a book that is out of stock.
    Includes transaction tracking with issue_date and return_date to monitor book movements and calculate student accountability.
    Handled constraints to prevent duplicate book_id entries and ensured mandatory fields like title and author are never null.
    All relational data is managed within the Oracle SQL instance.
    ![Monthly earnings](<Screenshot 2026-04-08 083804.png>) 
    ![Inactive customer](<Screenshot 2026-04-08 083647.png>) 
    ![Category-wise earnings](<Screenshot 2026-04-08 083631.png>) 
    ![Top customer](<Screenshot 2026-04-08 083539.png>) 
    ![Top sold products by price](<Screenshot 2026-04-08 083506.png>) 
    ![Tables](<Screenshot 2026-04-08 083207.png>)


Java_USECASE_1:Library Management System
    Tools: Java (JDK 11/17), JDBC API,Oracle JDBC Driver(ojdbc8.jar).
    Object-Oriented Programming (OOP) & Design Patterns used.
    Built using the Data Access Object  Pattern to separate the database interactions from the business logic.
    Database Connectivity is managed via a Singleton-style Connection Class to ensure efficient resource handling and prevent multiple open connections.
    Console-based UI handles user inputs for issuing, returning, adding, and searching for books.
    Implemented dynamic fine calculation logic using the java.time package,automatically penalizing late returns based on a 15-day policy.
    Automated User Registration check: During the book-issuing process,the system automatically verifies if a user exists and prompts for registration if they are new.
    Defensive Programming techniques used to handle NullPointerExceptions and SQL errors,ensuring the application doesn't crash during database downtime.
    All business logic is managed by the BookDAO, UserDAO, and Transaction classes.
    ![Return Book](<Screenshot 2026-04-08 084801.png>) 
    ![Issue Book](<Screenshot 2026-04-08 084741.png>) 
    ![Filter book by title and author](<Screenshot 2026-04-08 084654.png>) 
    ![Add Book](<Screenshot 2026-04-08 084610.png>) 
    ![Menu](<Screenshot 2026-04-08 084528.png>)



