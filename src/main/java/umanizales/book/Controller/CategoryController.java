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
        return this.categoryService.getCategory();
    }

    @GetMapping("/kk")
    public Category getCategoryById(){
        return this.categoryService.getCategoryByCode("2");
    }
}
