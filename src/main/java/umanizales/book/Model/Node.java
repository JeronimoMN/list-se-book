package umanizales.book.Model;

import lombok.Data;

@Data
public class Node {
    private Book data;
    private Node next;

    public Node(Book data) {
        this.data = data;
    }
}
