package umanizales.book.Model;

import lombok.Data;
import org.springframework.stereotype.Service;
import umanizales.book.Model.DTO.OrderBookCategoryDTO;
import umanizales.book.exception.ListSEException;

import java.util.Objects;

@Service
@Data
public class ListSE {
    private Node head;
    private int size;

    public void addEnd(Book rq) throws ListSEException {
        if (head != null) {
            Node temp = this.head;
            while (temp.getNext() != null) {
                if (temp.getData().getCode().equals(rq.getCode())) {
                    throw new ListSEException("The book is already in the list");
                }
                temp = temp.getNext();
            }
            if (temp.getData().getCode().equals(rq.getCode())) {
                throw new ListSEException("The book is already in the list");
            }

            //Parado en el ultimo.
            Node newNode = new Node(rq);
            temp.setNext(newNode);
        } else {
            head = new Node(rq);
        }
        size++;
    }

    public void addStart(Book rq) throws ListSEException {
        if (head != null) {

            Node temp = head;
            while (temp.getNext() != null) {
                if (temp.getData().getCode().equals(rq.getCode())) {
                    throw new ListSEException("The book is already in the list");
                }
                temp = temp.getNext();
            }
            Node newNode = new Node(rq);
            newNode.setNext(head);
            head = newNode;
        } else {
            head = new Node(rq);
        }
        size++;
    }

    public String addByPosition(Book book, int position) throws ListSEException {
        if (head == null || position == 1) {
            addStart(book);
            return "Book Successfully Added";
        }
        Node exist = head;
        while (exist != null) {
            if (exist.getData().getCode().equals(book.getCode())) {
                return "Already in the list!";
            }
            exist = exist.getNext();
        }
        if (position > size) {
            addEnd(book);
        } else {
            Node temp = head;
            int count = 1;
            while (count < (position - 1)) {
                temp = temp.getNext();
                count++;
            }
            Node newNode = new Node(book);
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
            size++;
        }
        return "Book Successfully Added";
    }

    public String deleteByCode(String id) {
        if (head != null) {
            if (Objects.equals(id, head.getData().getCode())) {
                head = head.getNext();
            } else {
                Node temp = this.head;
                while (temp.getNext() != null && !Objects.equals(temp.getNext().getData().getCode(), id)) {
                    temp = temp.getNext();
                }
                if (temp.getNext() == null) {
                    return "That data is already out of the list or it wasn't in the list!";
                }
                temp.setNext(temp.getNext().getNext());
            }
            size--;
            return "The data was eliminated successfully!";
        } else {
            return "There is no data in the list";
        }
    }

    public String invert() throws ListSEException {
        if (head != null) {
            ListSE newList = new ListSE();
            Node temp = this.head;
            while (temp != null) {
                newList.addStart(temp.getData());
                temp = temp.getNext();
            }
            head = newList.getHead();
            return "Inverted List!";
        } else {
            return "There is no data in the list";
        }
    }

    public String changeExtremes() {
        if (head != null) {
            Node last;
            Node temp = this.head;
            //Ubicarse en el penultimo
            while (temp.getNext().getNext() != null) {
                temp = temp.getNext();
            }
            last = temp.getNext();
            temp.setNext(head);
            last.setNext(head.getNext());
            head = last;
            temp.getNext().setNext(null);
            return "Extremes Changed!";
        } else {
            return "There is no data in the list";
        }
    }

    public String orderByCategory(OrderBookCategoryDTO orderBook) throws ListSEException {
        if (head != null) {
            ListSE newListSE = new ListSE();
            for (String orderCategory : orderBook.getCategories()) {
                Node temp = head;
                while (temp != null) {
                    if (temp.getData().getCategory().getName().equals(orderCategory)) {
                        newListSE.addEnd(temp.getData());
                    }
                    temp = temp.getNext();
                }
            }
            head = newListSE.getHead();
            return "List Ordered by Categories!";
        } else {
            return "There is no data in the list!";
        }
    }

    public int getQuantityBooksByCategoryCode(String code) throws ListSEException {
        int count = 0;
        if (this.head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getCategory().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public String getOrderBookByPages() throws ListSEException {
        if (head == null) {
            return "There is no data in the list!";
        }
        ListSE newList = new ListSE();
        Node temp = head.getNext();
        newList.addEnd(head.getData());
        while (temp != null) {
            int tempListQuantityPages = Integer.parseInt(temp.getData().getPages());
            Node temp2 = newList.getHead();
            int count = 1;

            //Si la cantidad de hojas del temp es más grande que las de la cabeza de la nueva lista
            if (tempListQuantityPages > Integer.parseInt(newList.getHead().getData().getPages())) {
                newList.addStart(temp.getData());
            } else {
                while (true) {
                    int tempNewListQuantityPages = Integer.parseInt(temp2.getData().getPages());
                    //Avanza al sig
                    if (tempNewListQuantityPages > tempListQuantityPages) {
                        if (temp2.getNext() == null) {
                            newList.addEnd(temp.getData());
                            break;
                        }
                        count++;
                        temp2 = temp2.getNext();
                    }
                    if (tempNewListQuantityPages <= tempListQuantityPages) {
                        if (tempNewListQuantityPages == tempListQuantityPages) {
                            newList.addEnd(temp.getData());
                            break;
                        }
                        newList.addByPosition(temp.getData(), count);
                        break;
                    }
                }
            }
            temp = temp.getNext();
        }
        head = newList.getHead();
        return "List Ordered by Pages!";
    }
}