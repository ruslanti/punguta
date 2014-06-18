package com.punguta.jpa.domains;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * User: ruslan
 * Date: 5/15/14
 */
@Entity
public class Book extends AbstractEntity {

    @OneToOne
    private User user;

    @ManyToOne
    private Commodity defaultCommodity;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Asset> assets;

    @OneToOne
    private Income income;

    @OneToOne
    private Expense expense;

    @OneToOne
    private Loan loan;

    @OneToOne
    private Liability liability;

    @OneToMany
    private Set<Category> categories;

    public Commodity getDefaultCommodity() {
        return defaultCommodity;
    }

    public void setDefaultCommodity(Commodity defaultCommodity) {
        this.defaultCommodity = defaultCommodity;
    }

    public Set<Asset> getAssets() {
        return assets;
    }

    public void setAssets(Set<Asset> assets) {
        this.assets = assets;
    }

    public void addAsset(Asset asset) {
        if (assets == null) {
            assets = new HashSet<Asset>();
        }
        assets.add(asset);
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public Liability getLiability() {
        return liability;
    }

    public void setLiability(Liability liability) {
        this.liability = liability;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        if (categories == null) {
            categories = new HashSet<Category>();
        }
        categories.add(category);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
