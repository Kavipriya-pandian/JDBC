package Book_Management;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.Scanner;
import java.sql.*;
/*

Book Management System

1. Add New Book 
2. Delete Book 
3. Update Book 
4. Search Book 
5. Show Book
6.Exit 

enter your choice : 1



Welcome to BMS 

enter book id : 1
Enter Book Name : introduction Java
Author Name : Daniel liang
Edition : 9th


enter your choice : 4
 enter bookid  to search :
 enter Name  to search : 

*/

public class Management_System {

	// values specific to each database
	   
	   // variables
	   private static   final String url = "jdbc:mysql:///book_management";
	   private static final String user = "root";
	   private static  final String password = "root";
	   // SQL Query
	   private static final String INSERT_book_QUERY =
	                "INSERT INTO book VALUES (?,?,?,?)";
	   	private static final String DELETE_book_QUERY="DELETE FROM book WHERE Book_Id=?";
	   private static final String UPDATE_book_QUERY=
			"UPDATE book SET Book_Name=?,Author_Name=?,Edition=? WHERE Book_Id=?";
	   private static final String SEARCH_book_QUERY=
			   "SELECT * FROM book WHERE Book_Name=?";
	   private static final String SHOW_book_QUERY=
			   "SELECT * FROM book";
	
  
		 public static void main(String[] args ) {

		     // declare variables
		     Scanner scan = null;
		     int id = 0;
		   
		     String bookName = null, authorName = null;
		     int edition=0;
		     
		     
		     String query = null;
		     Connection con = null;
		     PreparedStatement ps = null;
		     int result=0;
		     int n=0;

		     try {
		        // create Scanner class object
		        scan = new Scanner(System.in);
		        System.out.println("Welcome to BooK Management System");
		        System.out.println("\n************************************");
		        System.out.println("\nPress 1 to Add new book \nPress 2 to Delete specified records in book \nPress 3 to Update Book Record\nPress 4 to Search in book Records \nPress 5 to Show book Records \nPress 6 to Exit");
		        
		        // take number of students
		        if(scan != null) {
		            System.out.print("\nEnter your choice : ");
		            n = scan.nextInt();
		         
		        }

		        // establish the connection
		        con = DriverManager.getConnection(url, user, password);
			      // create JDBC statement object
			    

		        // compile SQL query and store it in
		        // PreparedStatement object
		        if(n==1) //Add new book
		        {
		        if(con != null) {
		           ps = con.prepareStatement(INSERT_book_QUERY);
		        }
		        
		        if(scan != null && ps != null) 
		        {
		           for(int i=0; i<n; i++) 
		           {

		              // read input values
		              System.out.print("\nEnter Book Id :");
		             id= scan.nextInt();
		              System.out.print("Enter Book Name : ");
		              bookName = scan.next();
		              System.out.print("Author Name: ");
		              authorName= scan.next();
		              System.out.print("Edition : ");
		              edition= scan.nextInt();
		              

		              // set parameters values
		              ps.setInt(1, id);
		              ps.setString(2, bookName);
		              ps.setString(3, authorName);
		             
		              ps.setInt(4, edition);
		              

		              // execute the query
		              result = ps.executeUpdate();
		           }
		        }
		        
		        // process the result
		        if(result == 0) {
		           System.out.println("\nRecords insertion failed");
		        } else {
		           System.out.println("\nRecords inserted successfully.");
		        }
		        }
		        else if(n==2) //Delete book
		        {
		        
		        	
				           ps = con.prepareStatement(DELETE_book_QUERY);
				        
				        
				       

				         // read input values
				              System.out.print("\nEnter Book Id :");
				             id= scan.nextInt();
				              
				              

				              // set parameters values
				              
				             ps.setInt(1, id);
				              

				              // execute the query
				              result = ps.executeUpdate();
				          
				        
				        // process the result
				        if(result == 0) {
				           System.out.println("\nRecords deletion failed on selected row");
				        } else {
				           System.out.println("\nRecords deleted successfully on selected row.");
				        }
		        	
		        }
		       else if (n==3) //Update book
		        {
		        
				           ps = con.prepareStatement(UPDATE_book_QUERY);
				        
		        	System.out.println(" Which record you need to update");
				        
				        
				         

				              // read input values
				              System.out.print("Enter Book Name : ");
				              bookName = scan.next();
				              System.out.print("Author Name: ");
				              authorName= scan.next();
				              System.out.print("Edition : ");
				              edition= scan.nextInt();
				              System.out.print("\nEnter Book Id :");
					             id= scan.nextInt();
				              

				              // set parameters values
				              ps.setString(1, bookName);
				              ps.setString(2, authorName);
				              ps.setInt(3, edition);
				             
				              ps.setInt(4, id);
				             
				              

				              // execute the query
				              result = ps.executeUpdate();
				           
				        
				        
				        // process the result
				        if(result == 0) {
				           System.out.println("\nRecords updation failed");
				        } else {
				           System.out.println("\nRecords updated successfully.");
				        }
		        	
		        }
		       else if(n==4)//search book
		        {
		    	   ResultSet result1 = null;
		   
		    		   ps = con.prepareStatement(SEARCH_book_QUERY);
			    	   
			    	   System.out.println("Enter the Book Name which you want to search");
			       // read input values
					           System.out.print("Enter Book Name : ");
					              bookName = scan.next();
					             
					              

					              // set parameters values
					              ps.setString(1, bookName);
					              result1=ps.executeQuery();
					              if (result1.next())
					              {
					            	    do
					            	    {
					            	        // process the result
					            	    System.out.println(result1.getInt(1)+","+result1.getString(2)+","+result1.getString(3)+","+result1.getInt(4));	
					            	  
					            	    }while(result1.next());
					            	    System.out.println("\nRecords which you search was found successfully.");
					          	}
					              else
					              {
					            	  System.out.println("\nRecords you search was failed because it was not found");
					              }
				            	 // while(result1.next())
				            	  //{
				            		//  String name=result1.getString(bookName);
				            	//	System.out.println(bookName);
				            	  //}
			
				        
				        
		        	
		        }
		       if(n==5)//show book
		        {
		        	
		    	   ResultSet result1=null;
				           ps = con.prepareStatement(SHOW_book_QUERY);
				           boolean flag=false;
				           result1=ps.executeQuery();
				           while(result1.next())
				           {
				        	   // execute the query
				        	   flag = true;
				  	         System.out.println(result1.getInt(1) + " " + result1.getString(2) + 
				  	                  " " + result1.getString(3)+" "+result1.getInt(4));
				  	      }
				           // process the result
				  	      if (flag == true) {
				  	         System.out.println("\nRecords retrieved and displayed");
				  	      } else {
				  	         System.out.println("Record not found");
				  	      }
				 }
		       if(n==6) //exit
		       {
		    	   System.out.println("Thank you!!!");
		       }
		     } catch(SQLException se) {
		        se.printStackTrace();
		     } catch(Exception e) {
		        e.printStackTrace();
		     } // try-catch block 

		     finally {
		        // close JDBC objects
		        try {
		           if(ps != null) ps.close();
		        } catch(SQLException se) {
		           se.printStackTrace();
		        }
		        try {
		           if(con != null) con.close();
		        } catch(SQLException se) {
		           se.printStackTrace();
		        }
		        try {
		           if(scan != null) scan.close();
		        } catch(Exception e) {
		           e.printStackTrace();
		        }
		     } // finally
		   } // main()
	}
