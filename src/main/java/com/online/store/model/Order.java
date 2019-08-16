package com.online.store.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "order")
public class Order {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    private ShoppingCart shoppingCart;

    public Order() {
    }

    public Order(User user, ShoppingCart shoppingCart) {
        this.user = user;
        this.shoppingCart = shoppingCart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(user, order.user) &&
                Objects.equals(id, order.id) &&
                Objects.equals(shoppingCart, order.shoppingCart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, id, shoppingCart);
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", id=" + id +
                ", shoppingCart=" + shoppingCart +
                '}';
    }
}
