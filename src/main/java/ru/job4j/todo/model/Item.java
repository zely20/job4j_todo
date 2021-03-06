package ru.job4j.todo.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "is_done")
    private Boolean isDone;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "category_id")
    private List<Category> categories = new ArrayList<>();


    public Item() {
    }

    public Item(String description, User user) {
        this.description = description;
        this.created = new Date(System.currentTimeMillis());
        this.isDone = true;
        this.user = user;
    }

    public Item(Integer id, String description, Date created, Boolean isDone) {
        this.id = id;
        this.description = description;
        this.created = created;
        this.isDone = isDone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
    

    public void addCity(Category category) {
        this.categories.add(category);
    }

    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(description, item.description) &&
                Objects.equals(created, item.created) &&
                Objects.equals(isDone, item.isDone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, created, isDone);
    }

    public void addCategory(Category category) {
        categories.add(category);
    }
}
