package service;

import model.Book;

import java.util.List;

// понлнотью повторяем методы из BookDao
public interface BookService {

    void addBook(Book book);

    void updateBook(Book book);

    void removeBook(int id);

    Book getBookById(int id);

    List<Book> listBooks();
}
