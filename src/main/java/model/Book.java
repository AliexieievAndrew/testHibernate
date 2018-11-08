package model;

// для того чтобы связать объект с БД
import javax.persistence.*;

// сообщаем что данный класс является сущностью
@Entity

//указываем с какой таблицей он будет связан
@Table(name = "books")
public class Book {

    @Id
    // связываем переменную с столбцом в таблице
    @Column(name = "id")
    // указываем, что это будет генерируемое значение
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "bookTitle")
    private String title;

    @Column(name = "bookAuthor")
    private String author;

    @Column(name = "bookPrice")
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
