package com.example.demo.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "books", schema = "demo", catalog = "")
public class Book {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer iId;

  @Column(name = "author")
  private String author;

  @Column(name = "title")
  private String title;

  @Column(name = "genre")
  private String genre;

  @Column(name = "price")
  private Float price;

  @Column(name = "user_rating")
  @OrderBy(value = "DESC")
  private Float userRating;

  @Column(name = "published_date")
  private LocalDate publishedDate;

  @Column(name = "description")
  private String description;

  public Integer getiId() {
    return iId;
  }

  public void setiId(Integer iId) {
    this.iId = iId;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Float getUserRating() {
    return userRating;
  }

  public void setUserRating(Float userRating) {
    this.userRating = userRating;
  }

  public LocalDate getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(LocalDate publishedDate) {
    this.publishedDate = publishedDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Book(Integer iId, String author, String title, String genre, Float price,
      Float userRating, LocalDate publishedDate, String description) {
    super();
    this.iId = iId;
    this.author = author;
    this.title = title;
    this.genre = genre;
    this.price = price;
    this.userRating = userRating;
    this.publishedDate = publishedDate;
    this.description = description;
  }

  public Book() {

  }

  @Override
  public String toString() {
    return "Book [iId=" + iId + ", author=" + author + ", title=" + title + ", genre=" + genre
        + ", price=" + price + ", userRating=" + userRating + ", publishedDate=" + publishedDate
        + ", description=" + description + "]";
  }
  
  

}
