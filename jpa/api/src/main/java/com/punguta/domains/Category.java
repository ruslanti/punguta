package com.punguta.domains;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * User: ruslan
 * Date: 5/15/14
 */
@Entity
public class Category extends AbstractEntity {

    private String name;

    private int budget;

    @ManyToOne
    private Commodity commodity;

    private boolean hidden;

    @OneToMany
    private Category ancestry;

    @ManyToOne
    private Book book;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public Category getAncestry() {
        return ancestry;
    }

    public void setAncestry(Category ancestry) {
        this.ancestry = ancestry;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
