package org.NNS;

import org.NNS.library.DAO.BookDAOImplt;
import org.NNS.library.DTO.Book;

import java.sql.Array;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Scanner scan = new Scanner(System.in);
        BookDAOImplt bookdao = new BookDAOImplt();
        int choice =-1;
        while (choice!=7){
            switch (choice){
                case 1:
                    //get data
                   try {
                           List<Book> booklist= bookdao.getAllBooks();
                           System.out.println("List of books: ");
                           for (Book book : booklist){
                               System.out.println("Book "+ book.getId() + " : "+ book.toString());
                           }
                           System.out.println("--------------------------------------------");
                           choice=-1;
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                            break;
                case 2:
                        //get book detail by given id
                        try {
                            System.out.println("Give the Book ID: ");
                            int bookid = scan.nextInt();
                            Book book= bookdao.getBookById(bookid);
                            if(book.getId() == 0)
                            {System.out.println("Incorrect BookId: "+ bookid);
                            }
                            else{System.out.println("Book "+ book.getId() + " : "+ book.toString());
                            System.out.println("--------------------------------------------");}
                            choice=-1;
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    break;
                case 3:
                    //get book detail by given id
                    try {
                        System.out.println("Give the Book ISBN: ");
                        String isbn = scan.next();
                        Book book= bookdao.getBookByISBN(isbn);
                        if(book.getId() == 0)
                        {System.out.println("Incorrect ISBN: "+ isbn);
                        }
                        else{System.out.println("Book "+ book.getId() + " : "+ book.toString());
                            System.out.println("--------------------------------------------");}
                        choice=-1;
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 4:
                    try {
                        Book book = new Book();
                        System.out.println("Provide Book detail for new entry as asked. ");
                        System.out.println("BOOK ISBN: ");
                        book.setISBN(scan.next());
                        scan.nextLine();
                        System.out.println("Book Name: ");
                        book.setName(scan.nextLine());

                        int result= bookdao.saveBook(book);
                        if(result == 0)
                        {System.out.println("Something Went Wrong. Try Again");
                            choice =-1;
                        }
                        else {
                            System.out.println("Book entry successfully completed.");
                            choice = 1;
                        }
                    }catch (SQLException | ClassNotFoundException e) {
                           throw new RuntimeException(e);
                    }
                    System.out.println("--------------------------------------------");
                    break;
                case 5:
                    try{
                        String newIsbn;
                        String newBookName;
                        System.out.print("Provide the Id of Book that getting updated:");
                        int bookid = scan.nextInt();
                        Book book = bookdao.getBookById(bookid);
                        if (book !=null){
                            System.out.println("Are we changing ISBN of book? Y or N: ");
                            char yesorno = (scan.next()).toUpperCase().charAt(0);
                            if (yesorno == 'Y'){
                                System.out.println("Provide new ISBN: ");
                                newIsbn = scan.next();
                                book.setISBN(newIsbn);
                            }

                            yesorno='N';
                            System.out.println("Are we changing name of book? Y or N: ");
                            yesorno = (scan.next()).toUpperCase().charAt(0);
                            if (yesorno == 'Y'){
                                scan.nextLine();
                                System.out.println("Provide new Book Name: ");
                                newBookName = scan.nextLine();
                                book.setName(newBookName);
                            }

                            //now call dao function to update in db
                            int result = bookdao.updateBook(book);
                            if (result !=0) { System.out.println("Book data has updated successfully.");}
                            else{System.out.println("Something went wrong in updating. Try Again.");}
                            choice =-1;
                        }
                        else{
                            System.out.println("The BOok for given id not available to update.");
                        }


                        System.out.println("---------------------------------------------------------");

                    }catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                {}

                    break;
                case 6:
                    try {
                        System.out.println("Provide Book ID whose entry you want to delete: ");
                       int bookid = scan.nextInt();
                        int result= bookdao.deleteBook(bookid);
                        if(result == 0)
                          {System.out.println("Incorrect Book ID: "+ bookid);
                        }
                        else {
                            System.out.println("Book " + bookid + " : " + "successfully has deleted");
                            choice = 1;
                        }
                    }catch
                        (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 7:
                    System.out.println("We are closing the application.");
                    break;
                default:
                    System.out.println("Choose work you want to do from following: \n from 1 to 7");
                    System.out.println(" 1: Get all books information.");
                    System.out.println(" 2: Get book detail by book id.");
                    System.out.println(" 3: Get book detail by ISBN number.");
                    System.out.println(" 4: Add new book.");
                    System.out.println(" 5: Update book detail.");
                    System.out.println(" 6: Delete a book information.");
                    System.out.println(" 7: close the program.");
                    System.out.println("Your choice: ");
                    choice = scan.nextInt();
                    break;
            }

        }

    }
}
