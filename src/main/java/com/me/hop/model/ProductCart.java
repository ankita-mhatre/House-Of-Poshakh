package com.me.hop.model;

import com.me.hop.pojo.Poshakh;

public class ProductCart {

	 	private PoshakhInfo productInfo;
	    private int quantity;
	    private Poshakh product;
	    
	    public ProductCart() {
	        this.quantity = 0;
	    }
	 
	    public Poshakh getProduct() {
			return product;
		}

		public void setProduct(Poshakh product) {
			this.product = product;
		}

		public PoshakhInfo getProductInfo() {
	        return productInfo;
	    }
	 
	    public void setProductInfo(PoshakhInfo productInfo) {
	        this.productInfo = productInfo;
	    }
	 
	    public int getQuantity() {
	        return quantity;
	    }
	 
	    public void setQuantity(int quantity) {
	        this.quantity = quantity;
	    }
	 
	    public double getAmount() {
	        return this.productInfo.getPrice() * this.quantity;
	    }
}

