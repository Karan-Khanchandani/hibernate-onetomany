package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "order_item")

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_category")
    String productCategory;
    Double price;
    @Column(name = "currency_code")
    String currencyCode;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
