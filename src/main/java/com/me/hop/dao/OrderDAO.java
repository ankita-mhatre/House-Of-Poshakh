package com.me.hop.dao;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.me.hop.pojo.OrderDetails;
import com.me.hop.pojo.Poshakh;
import com.me.hop.pojo.User;

public class OrderDAO extends DAO{

	
	public OrderDAO() {
    
    }
    
    

    public Boolean addOrder(OrderDetails o) throws Exception{
        try {
   begin();
getSession().save(o);
commit();
            return true;
            
        } catch (Exception ex) {
            System.out.println("Cannot save object" + ex.getMessage());
            return false;
        } 

    }
    
    
    public OrderDetails findOrder(int id) throws Exception {
    	OrderDetails order = new OrderDetails();
    	 String select_query= "from Order where ID=:ID";
    	 
    	 try {
         	
 
             Query q=getSession().createQuery(select_query);
             q.setParameter("id", id);
             
             order = (OrderDetails) q.uniqueResult();
      //here 
         } catch (Exception ex) {
             System.out.println("Cannot retrieve data " + ex.getMessage());
         }   
         
         return order;
    
    }
    
    public List<OrderDetails> displayOrder() throws Exception{
        List<OrderDetails> ordertList = new ArrayList<OrderDetails>();
        begin();
        String select_query= "from OrderDetails";
        try {
        	
        	
            Query q=getSession().createQuery(select_query);
            
            ordertList = q.list();
            commit(); 
        } catch (Exception ex) {
            System.out.println("Cannot retrieve data " + ex.getMessage());
        }   
        
        return ordertList;
    }
    
    public List<OrderDetails> displayOrderForCustomer(User u) throws Exception{
    	System.out.println("hereeeeeeeeeeeeeeeeeee"+u.getUserEmail());
        List<OrderDetails> ordertList = new ArrayList<OrderDetails>();
        begin();
        String select_query= "from OrderDetails where cname=:cname";
        
        try {
        	
            Query q=getSession().createQuery(select_query);
            q.setString("cname", u.getUserEmail());
          
            ordertList = q.list();
            commit(); 
        } catch (Exception ex) {
            System.out.println("Cannot retrieve data " + ex.getMessage());
        }   
        
        return ordertList;
    }
        
    public List<Poshakh> displayOrderProducts(int id) throws Exception{
        List<Poshakh> poshakhList = new ArrayList<Poshakh>();
        begin();
        String select_query= "SELECT o.productList from OrderDetails o where o.ID=:ID";
        try {
        	
        	
            Query q=getSession().createQuery(select_query);
            q.setParameter("ID", id);
            poshakhList = q.list();
            commit(); 
        } catch (Exception ex) {
            System.out.println("Cannot retrieve data " + ex.getMessage());
        }   
        
        return poshakhList;
    }
    
}
