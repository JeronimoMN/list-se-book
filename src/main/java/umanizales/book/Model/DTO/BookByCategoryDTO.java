package umanizales.book.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryQuantityDTO {
    private String name;
    private int quantity;
}
