package com.me.hop.dao;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.me.hop.model.PR;
import com.me.hop.model.PoshakhInfo;
import com.me.hop.pojo.Poshakh;

public class ProductDAO extends DAO{

	public ProductDAO() {
    
    }

    public Boolean addProduct(Poshakh mv) throws Exception{
        try {
        	System.out.println("in pro dao");
begin();
getSession().save(mv);
commit();
            return true;
            
        } catch (Exception ex) {
            System.out.println("Cannot save object" + ex.getMessage());
            return false;
        } 

    }
    
    public void printThis(){
    	System.out.println("check");
    }
    
    public Poshakh findProduct(int code) throws Exception {
    	Poshakh product = new Poshakh();
    	 String select_query= "from Poshakh where prodID=:product_code";
    	
    	 try {
       begin();
             Query q=getSession().createQuery(select_query);
             q.setParameter("product_code", code);
            System.out.println(code+"This is code");
             product = (Poshakh) q.uniqueResult();
             close(); 
              System.out.println(product.getProdName());
              System.out.println("1");
             return product;
     
         } catch (Exception ex) {
             System.out.println("Cannot retrieve data " + ex.getMessage());
         }   
         System.out.println("2");
         return null;
    
    }
    
    public Poshakh findProduct_trial(int code) throws Exception {
    	Poshakh product = new Poshakh();
    	 String select_query= "from Poshakh where prodID=:product_code";
    	
    	 try { 
       begin();
             Query q=getSession().createQuery(select_query);
             q.setParameter("product_code", code);
             System.out.println("in findproduct trial");
             product = (Poshakh) q.uniqueResult();
          commit();
             return product;
     
         } catch (Exception ex) {
             System.out.println("Cannot retrieve data " + ex.getMessage());
         }   
      
         return null;
    
    }
    
    public PR<PoshakhInfo> queryProducts(int page,
            int maxResult, int maxNavigationPage  ){
    	
				return null;
    	
    }

public PR<Poshakh> queryProducts(int page, int maxResult,
            int maxNavigationPage, String likeName){
	 String sql = "Select new " + PoshakhInfo.class.getName() //
             + "(p.prod_id, p.prodName, p.prodPrice) " + " from "//
             + Poshakh.class.getName() + " p ";
     if (likeName != null && likeName.length() > 0) {
         sql += " Where lower(p.prodName) like :likeName ";
     }
     //sql += " order by p.createDate desc ";
     
     Session session = sessionFactory.getCurrentSession();

     Query query = session.createQuery(sql);
     if (likeName != null && likeName.length() > 0) {
         query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
     }
     return new PR<Poshakh>(query, page, maxResult, maxNavigationPage);
	
}
    public List<Poshakh> displayProduct() throws Exception{
        List<Poshakh> productList = new ArrayList<Poshakh>();
//        Movie movie;
        
        String select_query= "from Poshakh";
//        String select_query= "from Movie where title= :title";
        try {
        begin();
            Query q=getSession().createQuery(select_query);
            
            productList = q.list();
            
        } catch (Exception ex) {
            System.out.println("Cannot retrieve data " + ex.getMessage());
        }   
        
        return productList;
    }
    
    public List<Poshakh> displayOrderProduct(int Id) throws Exception{
        List<Poshakh> productList = new ArrayList<Poshakh>();
        
        String select_query= "from OrderDetails where id=:ID";
        try {
        begin();
            Query q=getSession().createQuery(select_query);
            
            productList = q.list();
            
        } catch (Exception ex) {
            System.out.println("Cannot retrieve data " + ex.getMessage());
        }   
        
        return productList;
    }
        
    
}
