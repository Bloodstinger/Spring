package com.online.store.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "item_shopping_cart",
    joinColumns = {@JoinColumn(name = "shopping_cart_id")},
    inverseJoinColumns = {@JoinColumn(name = "item_id")})
    private List<Item> items = new ArrayList<>();

    public ShoppingCart() {
    }

    public ShoppingCart(User user) {
        this.user = user;
    }

    public ShoppingCart(User user, List<Item> items) {
        this.user = user;
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(Item item) {
        items.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return id == that.id &&
                Objects.equals(user, that.user) &&
                Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, id, items);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "user=" + user +
                ", id=" + id +
                ", items=" + items +
                '}';
    }
}
