package umanizales.book.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umanizales.book.Model.Book;
import umanizales.book.Model.Category;
import umanizales.book.Model.DTO.*;
import umanizales.book.Model.Node;
import umanizales.book.Service.CategoryService;
import umanizales.book.Service.ListSEService;
import umanizales.book.exception.ListSEException;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getBooks() {
        return new ResponseEntity<>(new ResponseDTO(200, listSEService.getBooks().getHead(), null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addBook(@RequestBody Book book) {

        Category category = categoryService.getCategoryByCode(book.getCategory().getCode());
        if (category == null) {
            return new ResponseEntity<>(new ResponseDTO(200, "That category don't exist", null), HttpStatus.OK);
        }
        try {
            this.listSEService.getBooks().add(book);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200, "Book Added Successfully", null), HttpStatus.OK);

    }

    @PostMapping(path = "/to_start")
    public String addBookStart(@RequestBody Book book) {
        this.listSEService.getBooks().addStart(book);
        return "Book Successfully Added!";
    }

    @PostMapping(path = "/by_position")
    public String addBookByPosition(@RequestBody BookByPositionDTO rq) throws ListSEException {
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
    public String orderBook(@RequestBody OrderBookCategoryDTO orderBookCategoryDTO) throws ListSEException {
        return this.listSEService.getBooks().orderByCategory(orderBookCategoryDTO);
    }

    @GetMapping(path = "/books_category")
    public ArrayList<BookByCategoryDTO> getBooksByCategory() {
        ArrayList<BookByCategoryDTO> booksByCategory = new ArrayList<>();
        for (Category cat : categoryService.getListCategories()) {
            int count = listSEService.getBooks().getCountBooksByCategoryCode(cat.getCode());
            if (count > 0) {
                booksByCategory.add(new BookByCategoryDTO(cat, count));
            }
        }
        return booksByCategory;
    }

    @GetMapping(path = "order_book_category_pages")
    public ResponseEntity<ResponseDTO> getBookByCategoryByPages() throws ListSEException {
        return new ResponseEntity<>(new ResponseDTO(200, this.listSEService.getBooks().getOrderBooksByCetegoryByPages(), null), HttpStatus.OK);
    }
}