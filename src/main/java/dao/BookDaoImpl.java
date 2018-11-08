package dao;

import config.HibernateUtil;
import config.SpringConfig;
import model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Repository

public class BookDaoImpl implements BookDao {
    // созаем Logger для этого класса
    private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);


    // создаем фабрику сессии (Hibernate) для создания сессия с БД
    private final SessionFactory sessionFactory;

    @Autowired
    public BookDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    // https://docs.spring.io/spring/docs/3.0.x/spring-framework-reference/html/orm.html#orm-session-factory-setup
    // 13.3.2 Implementing DAOs based on plain Hibernate 3 API
    public Collection loadProductsByCategory(String category) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM test.Product product WHERE product.category=?")
                .setParameter(0, category)
                .list();
    }

    public void addBook(Book book) {
        // создаем сессию и просим фабрику сессий получить текущую сесссию
        Session session = sessionFactory.getCurrentSession();

        //сессия будет сохранять объект который мы ей передали
        session.persist(book);

        // логируем информацию
        logger.info("Book successfully saved. Book details: " + book.toString());
    }

    public void updateBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.update(book);
        logger.info("Book successfully updated. Book details: " + book.toString());
    }

    public void removeBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        // создаем книгу получая из сессии по ID
        Book book = (Book) session.load(Book.class, new Integer(id));
        if(book!=null){
            session.delete(book);
        }
        logger.info("Book successfully removed. Book details: " + book);
    }

    public Book getBookById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, new Integer(id));
        logger.info("Book successfully loaded. Book details: " + book.toString());
        return book;
    }
    @SuppressWarnings("unchecked") // ?????????????
    public List<Book> listBooks() {
        System.out.println("Проверка" + sessionFactory);
        Session session = sessionFactory.getCurrentSession();
        List<Book> bookList = session.createQuery("from books").list();

        for (Book book:bookList) {
            logger.info("Book list details: " + book.toString());
        }
        return bookList;
    }

}
