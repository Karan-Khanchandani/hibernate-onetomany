import entity.Order;
import entity.OrderItem;
import entity.Person;
import helper.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashSet;

public class MainClass {
    public static void main(String[] args){
        Transaction transaction = null;
        Person person = new Person();
        person.setUserId("person_1213");
        person.setFirstName("Karan");
        person.setLastName("Khanchandani");
        person.setEmail("person1@gmail.com");
        person.setOrderSet(new HashSet<>());

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProductName("Laptop");
        orderItem1.setProductCategory("Electronics");
        orderItem1.setCurrencyCode("INR");
        orderItem1.setPrice(55000.212);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setProductName("Shirt");
        orderItem2.setProductCategory("Clothing");
        orderItem2.setCurrencyCode("INR");
        orderItem2.setPrice(500.00);

        OrderItem orderItem3 = new OrderItem();
        orderItem3.setProductName("Jeans");
        orderItem3.setProductCategory("Clothing");
        orderItem3.setCurrencyCode("INR");
        orderItem3.setPrice(1022.11);

        OrderItem orderItem4 = new OrderItem();
        orderItem4.setProductName("Keyboard");
        orderItem4.setProductCategory("Electronics");
        orderItem4.setCurrencyCode("INR");
        orderItem4.setPrice(233.11);

        OrderItem orderItem5 = new OrderItem();
        orderItem5.setProductName("Mouse");
        orderItem5.setProductCategory("Electronics");
        orderItem5.setCurrencyCode("INR");
        orderItem5.setPrice(199.99);

        Order order1 = new Order();
        order1.setOrderName("Order 1");
        order1.setOrderItems(new ArrayList<>());
        order1.getOrderItems().add(orderItem1);
        order1.getOrderItems().add(orderItem2);

        Order order2 = new Order();
        order2.setOrderName("Order 1");
        order2.setOrderItems(new ArrayList<>());
        order2.getOrderItems().add(orderItem3);
        order2.getOrderItems().add(orderItem4);
        order2.getOrderItems().add(orderItem5);

        //This step is really important
        person.getOrderSet().add(order1);
        person.getOrderSet().add(order2);
        order1.setPerson(person);
        order2.setPerson(person);

        orderItem1.setOrder(order1);
        orderItem2.setOrder(order1);
        orderItem3.setOrder(order2);
        orderItem4.setOrder(order2);
        orderItem5.setOrder(order2);

        for(OrderItem orderItem: order1.getOrderItems()){
            order1.setTotalAmount(order1.getTotalAmount() + orderItem.getPrice());
            order1.setCurrencyCode(orderItem.getCurrencyCode());
        }

        for(OrderItem orderItem: order2.getOrderItems()){
            order2.setTotalAmount(order2.getTotalAmount() + orderItem.getPrice());
            order2.setCurrencyCode(orderItem.getCurrencyCode());
        }

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            session.save(person);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(transaction != null){
                transaction.rollback();
            }
        }finally {

        }
    }
}
