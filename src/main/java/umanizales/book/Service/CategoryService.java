package umanizales.book.Service;

import lombok.Data;
import org.springframework.stereotype.Service;
import umanizales.book.Model.Category;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class CategoryService {
    private List<Category> listCategories;

    public CategoryService (){
        listCategories = new ArrayList<>();
        listCategories.add(new Category("1", "Post-Apocaliptico"));
        listCategories.add(new Category("2", "Suspenso"));
        listCategories.add(new Category("3", "Romance"));
        listCategories.add(new Category("4", "Superación Personal"));
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
        for( Category cat: listCategories){
            if(cat.getCode() == id){
                return cat;
            }
        }
        return null;
    }

    public String newCategory (Category category){
        if(category != null){
            for (int i = 0; i < listCategories.size(); i++) {
                if(listCategories.get(i) == category){
                    return null;
                }
                i++;
            }
            listCategories.add(category);
            return "Category Added Successfully!";
        }
        return null;
    }

    public String deleteCategory (String code){
        if(listCategories != null){
            for (int i = 0; i < listCategories.size(); i++) {
                if(listCategories.get(i).getCode().equals(code)){
                    listCategories.remove(i);
                }
            }
            return "Category Eliminated!";
        }
        return "That Category doesn't exists";
    }

}
