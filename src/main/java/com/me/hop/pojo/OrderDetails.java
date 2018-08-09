package com.me.hop.pojo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="OrderDetails")
public class OrderDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID", length = 50, nullable = false)
    private int id;
    
     @Column(name = "cname", nullable = false)
   private String cname;
    
     @Column(name = "caddress", nullable = false)
   private String caddress;
    
     @Column(name = "tPrice", nullable = false)
   private double tPrice;
   
   @Column(name = "quantity", nullable = false)
   private int quantity;
   
   @ManyToMany(cascade = { CascadeType.ALL })
   @JoinTable(
       name = "OrderDetails_Poshakh",
       joinColumns = { @JoinColumn(name = "ID") },
       inverseJoinColumns = { @JoinColumn(name = "prod_id") }
   )
   private List<Poshakh> poshakhList = new ArrayList<Poshakh>();
 

    public List<Poshakh> getProductList() {
        return poshakhList;
    }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCname() {
		return cname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}


	public String getCaddress() {
		return caddress;
	}


	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}


	public double gettPrice() {
		return tPrice;
	}


	public void settPrice(double tPrice) {
		this.tPrice = tPrice;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public List<Poshakh> getPoshakhList() {
		return poshakhList;
	}


	public void setPoshakhList(List<Poshakh> poshakhList) {
		this.poshakhList = poshakhList;
	}
    
    
}
