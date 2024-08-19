package umanizales.book.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umanizales.book.Model.Book;
import umanizales.book.Model.Category;
import umanizales.book.Model.DTO.*;
import umanizales.book.Service.CategoryService;
import umanizales.book.Service.ListSEService;
import umanizales.book.exception.ListSEException;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<ResponseDTO> addBookEnd(@RequestBody Book book) {
        Category category = categoryService.getCategoryByCode(book.getCategory().getCode());
        if (category == null) {
            return new ResponseEntity<>(new ResponseDTO(200, "That category don't exist", null), HttpStatus.OK);
        }
        try {
            this.listSEService.getBooks().addEnd(book);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200, "Book Added Successfully", null), HttpStatus.OK);

    }

    @PostMapping(path = "/to_start")
    public ResponseEntity<ResponseDTO> addBookStart(@RequestBody Book book) {
        Category category = categoryService.getCategoryByCode(book.getCategory().getCode());
        if (category == null) {
            return new ResponseEntity<>(new ResponseDTO(200, "That category don't exist", null), HttpStatus.OK);
        }
        try {
            this.listSEService.getBooks().addStart(book);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200, "Book Added Successfully", null), HttpStatus.OK);
    }

    @PostMapping(path = "/by_position")
    public ResponseEntity<ResponseDTO> addBookByPosition(@RequestBody BookByPositionDTO rq) throws ListSEException {
        return new ResponseEntity<>(new ResponseDTO(200, this.listSEService.getBooks().addByPosition(rq.getBook(), rq.getPosition()), null), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDTO> deleteBook(@RequestBody String code) {
        return new ResponseEntity<>(new ResponseDTO(200, this.listSEService.getBooks().deleteByCode(code), null), HttpStatus.OK);
    }

    @GetMapping(path = "/invert")
    public ResponseEntity<ResponseDTO> invert() throws ListSEException {
        return new ResponseEntity<>(new ResponseDTO(200, this.listSEService.getBooks().invert(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        return new ResponseEntity<>(new ResponseDTO(200, this.listSEService.getBooks().changeExtremes(), null), HttpStatus.OK);
    }

    @PostMapping(path = "/order_books_category")
    public ResponseEntity<ResponseDTO> orderBook(@RequestBody OrderBookCategoryDTO orderBookCategoryDTO) throws ListSEException {
        return new ResponseEntity<>(new ResponseDTO(200, this.listSEService.getBooks().orderByCategory(orderBookCategoryDTO), null), HttpStatus.OK);
    }

    @GetMapping(path = "/books_category")
    public ResponseEntity<ResponseDTO> getBooksByCategory() {
        List<Category> listCategories = this.categoryService.getListCategories();
        if (listCategories == null) {
            return new ResponseEntity<>(new ResponseDTO(200, "There are no categories at the moment", null), HttpStatus.OK);
        }
        try {
            ArrayList<BookByCategoryDTO> booksByCategory = new ArrayList<>();
            for (Category cat : categoryService.getListCategories()) {
                int count = listSEService.getBooks().getQuantityBooksByCategoryCode(cat.getCode());
                if (count > 0) {
                    booksByCategory.add(new BookByCategoryDTO(cat, count));
                }
            }
            return new ResponseEntity<>(new ResponseDTO(200, booksByCategory, null), HttpStatus.OK);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.OK);
        }
    }

    @GetMapping(path = "order_book_category_pages")
    public ResponseEntity<ResponseDTO> orderBookByPages() throws ListSEException {
        return new ResponseEntity<>(new ResponseDTO(200, this.listSEService.getBooks().getOrderBookByPages(), null), HttpStatus.OK);
    }
}