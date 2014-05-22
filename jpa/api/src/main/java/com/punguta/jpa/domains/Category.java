package com.punguta.jpa.domains;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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

    private boolean hidden;

    @ManyToOne
    private Category ancestry;

    @OneToMany(mappedBy = "ancestry", cascade = CascadeType.ALL)
    private Set<Category> children = new HashSet<Category>();

    Category() {
    }

    public Category(final Category ancestry) {
        if (ancestry != null) {
            this.ancestry = ancestry;
            registerInParentsChilds();
        }
    }

    private void registerInParentsChilds() {
        this.ancestry.children.add(this);
    }

    public Set<Category> getChildren() {
        return Collections.unmodifiableSet(this.children);
    }

    public static Category createRoot() {
        return new Category();
    }

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

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public Category getAncestry() {
        return ancestry;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", budget=" + budget +
                ", hidden=" + hidden +
                '}';
    }
}
