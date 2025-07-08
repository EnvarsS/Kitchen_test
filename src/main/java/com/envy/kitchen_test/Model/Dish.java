package com.envy.kitchen_test.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public Dish() {
    }

    public Dish(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
