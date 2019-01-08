package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity(name = "person")
@Table
public class Person {
    @Id
    @Column(name = "user_id", nullable = false)
    String userId;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    String email;
    @Column(name = "phone_number", length = 10)
    String phoneNumber;
    @OneToMany(mappedBy = "person")
    Set<Order> orderSet;
}
