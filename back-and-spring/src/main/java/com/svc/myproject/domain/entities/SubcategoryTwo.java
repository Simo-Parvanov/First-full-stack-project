package com.svc.myproject.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "sub_categories_two")
public class SubcategoryTwo extends BaseEntity{

    @Column(name = "second_category")
    @Enumerated(EnumType.STRING)
    private CategoryEnumTwo secondCategory;

    public SubcategoryTwo() {
    }
    public SubcategoryTwo(CategoryEnumTwo secondCategory) {
        this.secondCategory = secondCategory;
    }

    public CategoryEnumTwo getSecondCategory() {
        return secondCategory;
    }

    public void setSecondCategory(CategoryEnumTwo categoryEnumTwo) {
        this.secondCategory = categoryEnumTwo;
    }
}
