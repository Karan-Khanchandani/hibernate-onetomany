package entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "order")
@Table
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "order_name")
    String orderName;
    @Column(name = "total_amount")
    Double totalAmount = 0.0;
    @Column(name = "currency_code")
    String currencyCode;
    @OneToMany(mappedBy = "order")
    List<OrderItem> orderItems;
    @ManyToOne
    @JoinColumn(name = "person_id")
    Person person;
}
