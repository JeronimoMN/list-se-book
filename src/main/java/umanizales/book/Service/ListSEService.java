package umanizales.book.Service;

import lombok.Data;
import org.springframework.stereotype.Service;
import umanizales.book.Model.Book;
import umanizales.book.Model.Category;
import umanizales.book.Model.ListSE;

@Service
@Data
public class ListSEService {
    private ListSE books;


    public ListSEService(){
        books = new ListSE();
        books.add(new Book("1", "Los juegos del hambre", "Suzzane Collins", "400", new Category("1", "Post-Apocaliptico"), "El comienzo de una gran historia"));
        books.add(new Book("2", "En Llamas", "Suzzane Collins", "500", new Category("1", "Post-Apocaliptico"), "Muy Romantico"));
        books.add(new Book("3", "Sinsajo", "Suzzane Collins", "600", new Category("1", "Post-Apocaliptico"), "El final de una triste historia"));
        books.add(new Book("4", "Hábitos Átomicos", "James Clear", "400", new Category("4", "Superación Personal"), "Avanza teniendo en cuenta tus hábitos"));
        books.add(new Book("5", "El Arte de ser nosotros", "Inma Rubiales", "300", new Category("3", "Romance"), "El final de una triste historia"));
        books.add(new Book("6", "La chica del tren", "Paula Hackwings", "600", new Category("2", "Suspenso"), "El final de una triste historia"));
        books.add(new Book("7", "Satanas", "Mario Mendoza", "250", new Category("2", "Suspenso"), "El final de una triste historia"));

    }
}
