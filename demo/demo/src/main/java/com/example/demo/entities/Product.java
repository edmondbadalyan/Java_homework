package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    
	public Product(Long id, String name, Double price, Category category) {
	    this.id = id;
	    this.name = name;
	    this.price = price;
	    this.category = category;
	}

	
	   public Product() {
		    // Конструктор по умолчанию
		}
	public Product(long l, String string, double d, Category electronics) {
		// TODO Auto-generated constructor stub
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}