package umanizales.book.Service;

import lombok.Data;
import org.springframework.stereotype.Service;
import umanizales.book.Model.Book;
import umanizales.book.Model.ListSE;

@Service
@Data
public class ListSEService {
    private ListSE books;


    public ListSEService(){
        books = new ListSE();
        books.add(new Book("1", "Los juegos del hambre", "Suzzane Collins", "400", "Post-Apocaliptico", "El comienzo de una gran historia"));
        books.add(new Book("2", "En Llamas", "Suzzane Collins", "500", "Post-Apocaliptico", "Muy Romantico"));
        books.add(new Book("3", "Sinsajo", "Suzzane Collins", "600", "Post-Apocaliptico", "El final de una triste historia"));
    }
}
