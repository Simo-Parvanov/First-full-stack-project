package com.svc.myproject.domain.models.services;

public class CategoryServiceModel {
    private String categoryFirst;
    private String categorySecond;

    public CategoryServiceModel(String categoryFirst, String categorySecond) {
        this.categoryFirst = categoryFirst;
        this.categorySecond = categorySecond;
    }

    public String getCategoryFirst() {
        return categoryFirst;
    }

    public void setCategoryFirst(String categoryFirst) {
        this.categoryFirst = categoryFirst;
    }

    public String getCategorySecond() {
        return categorySecond;
    }

    public void setCategorySecond(String categorySecond) {
        this.categorySecond = categorySecond;
    }
}
