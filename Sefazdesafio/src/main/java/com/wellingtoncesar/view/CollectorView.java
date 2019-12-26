package com.wellingtoncesar.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import com.wellingtoncesar.view.Book;



@Named
@ViewScoped
public class CollectorView implements Serializable {
     
    private Book book;
     
    private List<Book> books;
     
    @PostConstruct
    public void init() {
        book = new Book();
        books = new ArrayList<Book>();
         
    }
     
    public void createNew() {
        if(books.contains(book)) {
            FacesMessage msg = new FacesMessage("Dublicated", "This book has already been added");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } 
        else {
            books.add(book);
            book = new Book();
        }
    }
     
    public String reinit() {
        book = new Book();
         
        return null;
    }
 
    public Book getBook() {
        return book;
    }
 
    public List<Book> getBooks() {
        return books;
    }
}
