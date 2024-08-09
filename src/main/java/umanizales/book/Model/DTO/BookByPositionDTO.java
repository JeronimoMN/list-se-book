package umanizales.book.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import umanizales.book.Model.Book;

@Data
@AllArgsConstructor
public class BookByPositionDTO {
    private int position;
    private Book book;
}
