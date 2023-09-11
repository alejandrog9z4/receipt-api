package com.fetch.receipt.models.dto;


import javax.validation.constraints.NotNull;

public class ItemDto {
	
    public ItemDto() {

	}

    private Integer id;

    @NotNull
    private String shortDescription;
    
    @NotNull
    private String price;

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
