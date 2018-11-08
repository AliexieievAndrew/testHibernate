package service;

import dao.BookDao;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


// данный класс является сервисом
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }


    // управление тразакциями
    @Transactional // ????????????????????????????????????????
    public void addBook(Book book) {
        bookDao.addBook(book);
    }
    @Transactional
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }
    @Transactional
    public void removeBook(int id) {
        bookDao.removeBook(id);
    }
    @Transactional
    public Book getBookById(int id) {
        return bookDao.getBookById(id);
    }
    @Transactional
    public List<Book> listBooks() {
        return bookDao.listBooks();
    }
}
