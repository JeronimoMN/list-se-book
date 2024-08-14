package umanizales.book.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umanizales.book.Model.Category;
import umanizales.book.Service.CategoryService;

import java.util.List;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getCategories (){
        return this.categoryService.getListCategories();
    }

    @PostMapping("/categorybycode")
    public Category getCategoryById(@RequestBody String code){
        return this.categoryService.getCategoryByCode(code);
    }

    @PostMapping
    public String addCategory(@RequestBody Category category){ return this.categoryService.newCategory(category); }

    @DeleteMapping
    public String deleteCategeryByCode(@RequestBody String code){
        return this.categoryService.deleteCategory(code);
    }
}
