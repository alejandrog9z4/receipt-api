package com.fetch.receipt.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table( name ="items" )
public class Item {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String shortDescription;
    private String price;
    

    public Item(String shortDescription, String price) {
		this.shortDescription = shortDescription;
		this.price = price;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
