package com.example.demo.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.example.demo.models.Book;
import com.example.demo.repositories.IBookRepository;
 
@RestController
@RequestMapping(value = "/books")
public class BookController {

  @Autowired
  IBookRepository bookRepo;

  @GetMapping("/getAll")
  public List<Book> getBooks() {
    return bookRepo.findAll();
  }

  @GetMapping("/save")
  public List<Book> saveAll() throws Exception {
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    File file = new File("lib/books.xml");
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document document = db.parse(file);
    document.getDocumentElement().normalize();
    System.out.println("Root element :" + document.getDocumentElement().getNodeName());
    
    NodeList nList = document.getElementsByTagName("Book");
    List<Book> books = new ArrayList<>();
    for (int temp = 0; temp < nList.getLength(); temp++) {
      Book book = new Book();
      Node nNode = nList.item(temp);
      System.out.println("\nCurrent Element :" + nNode.getNodeName());
      Element eElement = (Element) nNode;
      
      book.setAuthor(eElement.getElementsByTagName("Author").item(0).getTextContent());
      book.setTitle(eElement.getElementsByTagName("Title").item(0).getTextContent());
      book.setGenre(eElement.getElementsByTagName("Genre").item(0).getTextContent());
      book.setPrice(Float.parseFloat(eElement.getElementsByTagName("Price").item(0).getTextContent()));
      book.setUserRating(Float.parseFloat(eElement.getElementsByTagName("UserRating").item(0).getTextContent()));
      book.setPublishedDate(LocalDate.parse(eElement.getElementsByTagName("PublishDate").item(0).getTextContent(), formatter));
      book.setDescription(eElement.getElementsByTagName("Description").item(0).getTextContent());
      books.add(book);
      }
    bookRepo.deleteAll();
    bookRepo.saveAll(books);
    
    List<Book> books2 = bookRepo.findAll();
    books2.sort(Comparator.comparing(Book::getUserRating).reversed());

    FileOutputStream fileOut = new FileOutputStream("lib/sample.txt");
    ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
    StringBuilder sb = new StringBuilder();
    for(Book b: books2) {
      sb.append(b.toString());
    }
    objectOut.writeObject(sb.toString());
    objectOut.close();
    return books2;
  }
}

