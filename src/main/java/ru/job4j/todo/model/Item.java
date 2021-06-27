package ru.job4j.todo.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private Timestamp created;
    @Column(name = "is_done")
    private Boolean isDone;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Item() {
    }

    public Item(String description, User user) {
        this.description = description;
        this.created = new Timestamp(System.currentTimeMillis());
        this.isDone = true;
        this.user = user;
    }

    public Item(Integer id, String description, Timestamp created, Boolean isDone) {
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

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
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
}
