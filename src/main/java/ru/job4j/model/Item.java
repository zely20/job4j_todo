package ru.job4j.model;

import java.sql.Timestamp;
import java.util.Objects;

public class Item {

    private Integer id;
    private String description;
    private Timestamp created;
    private Boolean isDone;

    public Item() {
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
        return Objects.equals(id, item.id) && Objects.equals(description, item.description) && Objects.equals(created, item.created) && Objects.equals(isDone, item.isDone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, created, isDone);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", isDone=" + isDone +
                '}';
    }
}
