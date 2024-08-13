package umanizales.book.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private String code;
    private String title;
    private String author;
    private String pages;
    private Category category;
    private String sinopsis;
}
