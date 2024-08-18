package umanizales.book.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umanizales.book.Model.Category;
import umanizales.book.Model.DTO.ResponseDTO;
import umanizales.book.Service.CategoryService;
import umanizales.book.exception.CategoryException;

import java.util.List;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getCategories (){
        return new ResponseEntity<>(new ResponseDTO(200, this.categoryService.getListCategories(), null), HttpStatus.OK);
    }

    @PostMapping("/categorybycode")
    public Category getCategoryById(@RequestBody String code){
        return this.categoryService.getCategoryByCode(code);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addCategory(@RequestBody Category category){
        if(category == null){
            return new ResponseEntity<>(new ResponseDTO(200, "The category is incorrect", null), HttpStatus.OK);
        }try{
            return new ResponseEntity<>(new ResponseDTO(200,  this.categoryService.newCategory(category), null), HttpStatus.OK);
        }catch (CategoryException e){
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.OK);
        }
    }

    @DeleteMapping
    public String deleteCategeryByCode(@RequestBody String code){
        return this.categoryService.deleteCategory(code);
    }
}
