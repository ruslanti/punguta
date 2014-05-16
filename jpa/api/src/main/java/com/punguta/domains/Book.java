package com.punguta.domains;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

/**
 * User: ruslan
 * Date: 5/15/14
 */
@Entity
public class Book extends AbstractEntity {

    private Commodity defaultCommodity;

    @OneToMany(mappedBy = "book")
    private Set<Asset> assets;

    @OneToOne(mappedBy = "book")
    private Income income;

    @OneToOne(mappedBy = "book")
    private Expense expense;

    @OneToOne(mappedBy = "book")
    private Loan loan;

    @OneToOne(mappedBy = "book")
    private Liability liability;

    @OneToMany(mappedBy = "book")
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
}
