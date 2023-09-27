package org.NNS.library.DAO;


import org.NNS.library.DTO.Book;
import java.sql.SQLException;
import java.util.List;

public interface IBookDAO {

    List<Book> getAllBooks() throws SQLException, ClassNotFoundException;

    Book getBookByISBN(String ISBN) throws SQLException, ClassNotFoundException;

    Book getBookById(int id) throws SQLException;

    int deleteBook(int id) throws SQLException, ClassNotFoundException;

    int saveBook(Book book) throws SQLException, ClassNotFoundException;

    int updateBook(Book book) throws SQLException,ClassNotFoundException;
}

