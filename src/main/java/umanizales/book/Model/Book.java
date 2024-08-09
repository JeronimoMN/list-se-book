package umanizales.book.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private String id;
    private String titulo;
    private String autor;
    private String cantHojas;
    private String genero;
    private String sinopsis;
}
