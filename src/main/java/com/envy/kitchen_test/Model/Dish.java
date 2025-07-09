package com.envy.kitchen_test.Model;

import jakarta.persistence.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(id, dish.id) && Objects.equals(name, dish.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Dish{" +
               "name='" + name + '\'' +
               ", id=" + id +
               '}';
    }
}
