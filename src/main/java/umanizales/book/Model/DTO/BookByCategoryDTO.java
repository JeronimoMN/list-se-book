package umanizales.book.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import umanizales.book.Model.Category;

@Data
@AllArgsConstructor
public class BookByCategoryDTO {
    private Category category;
    private int quantity;
}
