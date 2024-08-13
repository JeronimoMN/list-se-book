package umanizales.book.Service;

import lombok.Data;
import org.springframework.stereotype.Service;
import umanizales.book.Model.Category;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class CategoryService {
    private List<Category> category;

    public CategoryService (){
        category = new ArrayList<>();
        category.add(new Category("1", "Post-Apocaliptico"));
        category.add(new Category("2", "Suspenso"));
        category.add(new Category("3", "Romance"));
        category.add(new Category("4", "Superación Personal"));
    }


    /*
    Obtener categoria por código

    Mientras categoria sea menor al tamaño de la lista
        Si la identificación de la categoria es igual al id ingresado por el usuario
            Retornar  la categoria
    No
        Retornar Null
     */

    public Category getCategoryByCode (String id) {
        for( Category cat: category){
            if(cat.getCode() == id){
                return cat;
            }
        }
        return null;
    }
}
