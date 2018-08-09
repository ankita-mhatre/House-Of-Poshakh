package com.me.hop.controller;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.me.hop.mysession.MySession;
import com.me.hop.dao.ProductDAO;
import com.me.hop.model.InCart;
import com.me.hop.model.ProductCart;
import com.me.hop.model.PoshakhInfo;
import com.me.hop.pojo.Poshakh;

@Controller
public class ShoppingCartController{
	@Autowired
	@Qualifier("productDAO")
	ProductDAO pdao;
	
	   @RequestMapping(value = "/displayCart.htm", method = RequestMethod.GET)
	    public String showCart() {
		   
	        return "shoppingCart";
	    }
	
	 @RequestMapping({ "/buyProduct.htm" })
	    public String listProductHandler(HttpServletRequest request, Model model, //
	            @RequestParam(value = "code", defaultValue = "") int code) throws Exception {
		 Poshakh product = null;
		 //List<Product> productList = new ArrayList<Product>();
	  HttpSession session = request.getSession();
      if (code > 0) {
           try {
			product = pdao.findProduct(code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
      if (product != null) {
    	  
          // Cart info stored in Session.
          InCart cartInfo = MySession.getCartInSession(request);

          PoshakhInfo productInfo = new PoshakhInfo(product);
          
          Poshakh p = new Poshakh();
          p = pdao.findProduct_trial(productInfo.getCode()); 
          cartInfo.addProduct(productInfo, 1, p);
          session.setAttribute("cartInfo", cartInfo);
          for(ProductCart c:cartInfo.getCartItems()) {
        	  System.out.println(c.getQuantity());
        	 // productList.add(c.getProduct());
          }
   
          
          
          
      }
    
      
      //model.addAttribute(cart);
      // Redirect to shoppingCart page.
      
      return "shoppingCart";
  }
	 
	 @RequestMapping({ "/shoppingCartRemoveProduct.htm" })
	    public String removeProductHandler(HttpServletRequest request, Model model, //
	            @RequestParam(value = "code", defaultValue = "") int code) {
		 Poshakh product = null;
	        if (code > 0) {
	            try {
					product = pdao.findProduct(code);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        if (product != null) {
	 
	            // Cart Info stored in Session.
	            InCart cartInfo = MySession.getCartInSession(request);
	 
	            PoshakhInfo productInfo = new PoshakhInfo(product);
	 
	            cartInfo.removeProduct(productInfo);
	 
	        }
	        // Redirect to shoppingCart page.
	        
	        return "shoppingCart";
	    }
	 @RequestMapping({ "/clearCart.htm" })
	    public String clearCartHandler(HttpServletRequest request, Model model) {
		 InCart cartInfo = MySession.getCartInSession(request);
	           cartInfo.getCartItems().clear();
	           HttpSession session = request.getSession();
	           session.setAttribute("cartInfo", cartInfo);
	       
	            return "customer-dashboard";
	        }
	        // Redirect to shoppingCart page.
	        
	      
} 
