package umanizales.book.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umanizales.book.Model.Book;
import umanizales.book.Model.Category;
import umanizales.book.Model.DTO.BookByCategoryDTO;
import umanizales.book.Model.DTO.BookByPositionDTO;
import umanizales.book.Model.DTO.OrderBookCategoryDTO;
import umanizales.book.Model.Node;
import umanizales.book.Service.CategoryService;
import umanizales.book.Service.ListSEService;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Node getBooks() {
        return this.listSEService.getBooks().getHead();
    }

    @PostMapping
    public String addBook(@RequestBody Book book) {
        this.listSEService.getBooks().add(book);
        return "Book Successfully Added!";
    }

    @PostMapping(path = "/toStart")
    public String addBookStart(@RequestBody Book book) {
        this.listSEService.getBooks().addStart(book);
        return "Book Successfully Added!";
    }

    @PostMapping(path = "/byPosition")
    public String addBookByPosition(@RequestBody BookByPositionDTO rq) {
        return this.listSEService.getBooks().addByPosition(rq.getBook(), rq.getPosition());
    }

    @DeleteMapping
    public String deleteBook(@RequestBody String id) {
        this.listSEService.getBooks().delete(id);
        return "Book Successfully Deleted!";
    }

    @GetMapping(path = "/invert")
    public String invert() {
        listSEService.getBooks().invert();
        return "Inverted List!";
    }

    @GetMapping(path = "/change_extremes")
    public String changeExtrems() {
        listSEService.getBooks().changeExtremes();
        return "Extremes Changed!";
    }

    @PostMapping(path = "/order_books")
    public String orderBook(@RequestBody OrderBookCategoryDTO orderBookCategoryDTO) {
        return this.listSEService.getBooks().orderByCategory(orderBookCategoryDTO);
    }

    @GetMapping(path = "/booksbycategory")
    public ArrayList getBooksByCategory() {
        ArrayList<BookByCategoryDTO> booksByCategory = new ArrayList<>();
        for (Category cat : categoryService.getListCategories()){
            int count = listSEService.getBooks().getCountKidsByCategoryCode(cat.getCode());
            if (count > 0) {
                booksByCategory.add(new BookByCategoryDTO(cat, count));
            }
        }
        return booksByCategory;
    }
}