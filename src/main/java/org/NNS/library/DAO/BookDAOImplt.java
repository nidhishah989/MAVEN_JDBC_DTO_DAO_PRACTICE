package org.NNS.library.DAO;

import org.NNS.library.DTO.Book;
import org.NNS.library.util.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImplt extends DatabaseConnector implements IBookDAO{

    private Connection dbconn;

    @Override
    public List<Book> getAllBooks() throws SQLException, ClassNotFoundException {
        try {
            dbconn = DatabaseConnector.getConnectedtoDB();
            String sqlqr = "select * from books";
            Statement statement = dbconn.createStatement();
            ResultSet rs = statement.executeQuery(sqlqr);

            List<Book> bookList = new ArrayList<>();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setISBN(rs.getString("isbn"));
                book.setName(rs.getString("name"));
                bookList.add(book);

            }

            return bookList;
        }catch (SQLException exception){
            throw  new SQLException("SQL State: %s\n%s" + exception.getSQLState() + exception.getMessage());

        }
    }

    @Override
    public Book getBookByISBN(String ISBN) throws SQLException, ClassNotFoundException {
        try {
            dbconn = DatabaseConnector.getConnectedtoDB();
            String sqlqr = "select * from books where isbn=?";
            PreparedStatement preparedStatement = dbconn.prepareStatement(sqlqr);
            preparedStatement.setString(1,ISBN);
            ResultSet rs = preparedStatement.executeQuery();


            Book book = new Book();
            while (rs.next()) {

                book.setId(rs.getInt("id"));
                book.setISBN(rs.getString("isbn"));
                book.setName(rs.getString("name"));
            }

            return book;
        }catch (SQLException exception){
            throw  new SQLException("SQL State: %s\n%s" + exception.getSQLState() + exception.getMessage());

        }
    }

    @Override
    public Book getBookById(int id) throws SQLException {
        try {
            dbconn = DatabaseConnector.getConnectedtoDB();
            String sqlqr = "select * from books where id=?";
            PreparedStatement preparedStatement = dbconn.prepareStatement(sqlqr);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();


            Book book = new Book();
            while (rs.next()) {

                book.setId(rs.getInt("id"));
                book.setISBN(rs.getString("isbn"));
                book.setName(rs.getString("name"));
            }

            return book;
        }catch (SQLException exception){
            throw new SQLException(String.format("SQL State: %s\n%s", exception.getSQLState(), exception.getMessage()));

        }
    }

    @Override
    public int deleteBook(int id) throws SQLException, ClassNotFoundException {

        try{
            dbconn = DatabaseConnector.getConnectedtoDB();
            String sqlqr = "delete from books where id = ?";
            PreparedStatement preparedStatement = dbconn.prepareStatement(sqlqr);
            preparedStatement.setInt(1,id);
            int val = preparedStatement.executeUpdate();
            return val;
        }
        catch (SQLException ex)
        {
            throw  new SQLException("SQL State: %s\n%s" + ex.getSQLState() + ex.getMessage());

        }
    }



    @Override
    public int saveBook(Book book) throws SQLException, ClassNotFoundException {
        try{
            dbconn = DatabaseConnector.getConnectedtoDB();
            String sqlqr = "insert into books(isbn, name) values(?,?)";
            PreparedStatement preparedStatement = dbconn.prepareStatement(sqlqr);
            preparedStatement.setString(1,book.getISBN());
            preparedStatement.setString(2,book.getName());
            int result = preparedStatement.executeUpdate();
            return result;
        }
        catch (SQLException e)
        {
            if (e.getSQLState().equals("23000")) {
                // Handle the duplicate entry error
                System.out.println("ISBN already exists in the database. Please enter a different ISBN.");
                return 0;
            } else {
                // Handle other SQLExceptions
                throw new RuntimeException("SQL State: " + e.getSQLState() + "\n" + e.getMessage(), e);
            }

        }
    }

    @Override
    public int updateBook(Book book) throws SQLException, ClassNotFoundException {
        try{
            dbconn = DatabaseConnector.getConnectedtoDB();
            String sqlqr = "update books set isbn=?, name=? where id=?";
            PreparedStatement preparedStatement = dbconn.prepareStatement(sqlqr);
            preparedStatement.setString(1,book.getISBN());
            preparedStatement.setString(2,book.getName());
            preparedStatement.setInt(3,book.getId());
            return preparedStatement.executeUpdate();
        }
        catch (SQLException ex)
        {
            throw  new SQLException("SQL State: %s\n%s" + ex.getSQLState() + ex.getMessage());

        }
    }
}
