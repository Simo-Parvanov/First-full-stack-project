package com.svc.myproject.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "categoryes")
public class Category extends BaseEntity{

    @Column(name = "first_category")
    @Enumerated(EnumType.STRING)
    private CategoryEnum firstCategory;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private SubcategoryTwo subcategoryTwo;

    public Category() {
    }

    public Category(CategoryEnum firstCategory, SubcategoryTwo subcategoryTwo) {
        this.firstCategory = firstCategory;
        this.subcategoryTwo = subcategoryTwo;
    }

    public Category(CategoryEnum name) {
        this.firstCategory = name;
    }

    public CategoryEnum getFirstCategory() {
        return firstCategory;
    }

    public void setFirstCategory(CategoryEnum firstCategory) {
        this.firstCategory = firstCategory;
    }

    public SubcategoryTwo getSubcategoryTwo() {
        return subcategoryTwo;
    }

    public void setSubcategoryTwo(SubcategoryTwo subcategoryTwo) {
        this.subcategoryTwo = subcategoryTwo;
    }
}
