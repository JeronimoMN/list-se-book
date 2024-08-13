package umanizales.book.Model;

import lombok.Data;
import org.springframework.stereotype.Service;
import umanizales.book.Model.DTO.BookByPositionDTO;
import umanizales.book.Model.DTO.OrderBookCategoryDTO;

import java.util.Objects;

@Service
@Data
public class ListSE {
    private Node head;
    private int size;

    /*
    Algoritmo para adicionar un libro (niño).

    ¿Hay nodo en la cabeza?
    si
        crear una variable temporal
        Recorrer la lista hasta estar en el ultimo nodo
        Se envuelve el dato en un nodo
        y le digo al ultimo que tome el nuevo nodo.
    no
        Se envuelve el dato en un nodo y ese nodo es la cabeza
     */

    public void add (Book rq){
        if (head != null) {
            Node temp = this.head;
            while (temp.getNext() != null){
                temp = temp.getNext();
            }
            //Parado en el ultimo Nodo.
            Node newNode = new Node(rq);
            temp.setNext(newNode);
        }else {
            head = new Node(rq);
        }
        size++;
    }

    /*
    Algoritmo para adicionar un libro (niño) al inicio.
    Si la cabeza esta vacia
    si
        Se envuelve el dato en un nodo y ese nodo es la cabeza
    no
        Se envuelve el dato en un nodo
        El nodo agarra la cabeza
        La cabeza es igual a nuevo costal

     */

    public void addStart (Book rq){
        if(head != null){
            Node newNode = new Node(rq);
            newNode.setNext(head);
            head = newNode;
        }else{
            head = new Node(rq);
        }
        size++;
    }

    /*
    Algoritmo para agregar un libro en una posición especifica.
    Si hay datos en la cabeza?
    Si
        Se guarda el dato en el nodo y este es cabeza
    No
        declarar temporal
        Mientras i sea menor que la posición
            temporal avanza al siguiente

        Se guarda el dato en el nodo
        nuevoNodo toma el sig del temporal
        Temporal toma el nuevo nodo

     */

    public String addByPosition (Book book, int position){
        if( position > 0){
            if(position == 1){
                addStart(book);
                return "Book Successfully Added!";
            }else{
                if(position > this.size){
                    add(book);
                    return "Book Successfully Added at the final!";
                }
                else{
                    int i= 1;
                    Node temp= this.head;
                    while(i < (position-1)){
                        temp= temp.getNext();
                        i+= 1;
                    }
                    Node newNode = new Node(book);
                    newNode.setNext(temp.getNext());
                    temp.setNext(newNode);
                    return "Book Successfully Added";
                }
            }
        }else{
            return "It can not be added in that position!";
        }
    }


    /*
    Algoritmo para eliminar un libro (niño).
    La lista esta vacia
    Si
        Si el dato a buscar esta en la cabeza
        Si
            Cabeza es igual al siguiente nodo
        No
            Crear un temporal
            Mientras el sig del sig del temp sea diferente a null y el id del sig del temporal sea diferente al buscado
                temporal pasa al sig
            El sig del temporal es igual al sig del sig del temporal.
    No
        Devolver un mensaje "No hay ningun dato"
     */

    public void delete (String id){
        if (head != null) {

            //Si el nodo a buscar es la cabeza
            if(id == head.getData().getCode()){
                head= head.getNext();
            }
            else{
                Node temp = this.head;
                while (temp.getNext().getNext() != null && !Objects.equals(temp.getNext().getData().getCode(), id)){
                    temp = temp.getNext();
                }
                temp.setNext(temp.getNext().getNext());
            }
            size--;
        }else{
            System.out.println("There is nothing to delete");
        }
    }

    /*
    Algoritmo para invertir el orden de la lista
    La lista esta vacia?
    Si
        Mostrar un mensaje: "No hay ningun dato en la lista"
    No
        Crear una nueva lista simplemente enlazada
        Crear un temporal que sea igual a cabeza

        Mientras temp sea diferente a Null
            En la nueva lista, se agrega al inicio los datos del temporal
            temporal pasa al sig

        Cabeza es igual a la cabeza de la nueva lista.
     */

    public void invert (){
        if(head != null){
            ListSE newList = new ListSE();
            Node temp= this.head;
            while(temp != null){
                newList.addStart(temp.getData());
                temp= temp.getNext();
            }
            head = newList.getHead();
        }else{
            System.out.println("Ningun dato para mostrar");
        }
    }

    /*
        Algoritmo para cambiar los extremos de la lista.
        La lista esta vacia
        Si
            Mostrar un mensaje: "No hay ningun dato en la lista"
        No
            Crear un temporal y este toma la cabeza

            Mietras el sig del sig del temporal sea diferente a nulo
                Temporal pasa al sig

            Crear una variable, esta guarda el sig del temporal el cual es el ultimo.
            Temporal apunta a cabeza
            La variable apunta al temporal
            La cabeza es igual a ultimo
            Y el sig del sig del temporal es igual a nulo

     */

    public void changeExtremes (){
        if(head != null){
            Node ultimo;
            Node temp = this.head;
            while(temp.getNext().getNext() != null){
                temp = temp.getNext();
            }
            ultimo = temp.getNext();
            temp.setNext(head);
            ultimo.setNext(temp);
            head = ultimo;
            temp.getNext().setNext(null);
        }else{
            System.out.println("Ningun dato que mostrar");
        }
    }

    /*
    Quiero que me aparezcan los temas en el siguiente orden: Post-apocaliptico, Suspenso, Superación Personal.

    Algoritmo para organizar por categoria
    La lista esta vacia
        Si
            Devolver un mensaje: "No hay datos para ordenar"
        No


     */

    public String orderByCategory(OrderBookCategoryDTO orderBook){
        if(head != null){
            ListSE newListSE = new ListSE();
            for(String orderCategory: orderBook.getCategories()){
                Node temp= head;
                while(temp != null){
                    if(temp.getData().getCategory().getName().equals(orderCategory)){
                        newListSE.add(temp.getData());
                    }
                    temp = temp.getNext();
                }
            }
            head= newListSE.getHead();
            return "List Ordered!";
        }else{
            return "There is not any data to order!";
        }
    }


    public int getCountKidsByCategoryCode(String code){
        int count = 0;
        if(this.head != null){
            Node temp = this.head;
            while(temp != null){

                if(temp.getData().getCategory().getCode().equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }



    /*
    4. Organizar por categoria
    6. Suma total por precio del libro
    7. Contar libros por categoria.

     */

}
