package org.example.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tblCategories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 150, nullable = false)
    private String name;
    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    public CategoryEntity() {

    }

    public CategoryEntity(String name) {
        this.name = name;
        this.dateCreated = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
