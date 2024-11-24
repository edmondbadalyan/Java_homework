package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.Date;
@Entity
@Data
public class Order {
	
	public Order(Long id, String customerName, String customerAddress, Date date, List<Product> products) {
	    this.id = id;
	    this.customerName = customerName;
	    this.customerAddress = customerAddress;
	    this.date = date;
	    this.products = products;
	}
	public Order() {
	    // Конструктор по умолчанию
	}

	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  
    private User user;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerAddress;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToMany
    @JoinTable(
        name = "order_products",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
}