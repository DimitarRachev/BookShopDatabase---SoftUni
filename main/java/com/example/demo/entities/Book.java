package com.example.demo.entities;

import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(name = "edition_type", nullable = false)
    private EditionType editionType;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    private Author author;

    @ManyToMany
    private Set<Category> categories;

    private int copies;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "age_restriction", nullable = false)
    private AgeRestriction ageRestriction;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book(String title, EditionType editionType,
                BigDecimal price, Author author, Set<Category> categories,
                int copies, LocalDate releaseDate, AgeRestriction ageRestriction) {
        this.title = title;
        this.editionType = editionType;
        this.price = price;
        this.author = author;
        this.categories = categories;
        this.copies = copies;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
