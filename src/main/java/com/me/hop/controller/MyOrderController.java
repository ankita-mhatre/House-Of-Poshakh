package com.me.hop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.hop.mysession.MySession;
import com.me.hop.dao.OrderDAO;
import com.me.hop.model.InCart;
import com.me.hop.model.PoshakhInfo;
import com.me.hop.model.ProductCart;
import com.me.hop.pojo.OrderDetails;
import com.me.hop.pojo.Poshakh;
import com.me.hop.mysession.MySession;
import com.me.hop.model.PoshakhInfo;
import com.me.hop.model.ProductCart;
import com.me.hop.pojo.OrderDetails;
import com.me.hop.pojo.Poshakh;

@Controller
public class MyOrderController {
	@Autowired
	@Qualifier("orderDAO")
	OrderDAO odao;
	
	 @RequestMapping(value = "/displayOrderForm.htm", method = RequestMethod.GET)
	    public String createOrderForm(HttpServletRequest request) {
		 	//model.addAttribute("myCart", new CartInfo());
		
	        return "create-order";
	    }
	    @RequestMapping(value = "/displayOrderForm.htm", method = RequestMethod.POST)
	    public String handleCreateForm(HttpServletRequest request, ModelMap map) {
	    	
	    	
	    	 InCart myCart = MySession.getCartInSession(request);
	    	  if (myCart.isEmpty()) {
	              // Redirect to shoppingCart page.
	              return "redirect:/shoppingCart";
	          } 
	    	 	List<Poshakh> productList = new ArrayList();
	    	 	
	    	 	for(ProductCart c : myCart.getCartItems()) {
	    	 		productList.add(c.getProduct());
	    	 		System.out.println(c.getProduct().getProdName());
	    	 	}
	    	 	
	            String customerName = request.getParameter("customerName");
	            String customerAddress = request.getParameter("customerAddress");
	            OrderDetails order = new OrderDetails();
	            order.setCaddress(customerAddress);
	            order.setCname(customerName);
	            order.settPrice(myCart.getAmountTotal());
	            order.setQuantity(myCart.getQuantityTotal());
	            order.setPoshakhList(productList);
	            
	            try {
	            	Boolean saved = odao.addOrder(order);
	             
	             
	             
	                
	            } catch (Exception e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	            MySession.removeCartInSession(request);
	            return "order-success";
	    }
	    
	    @RequestMapping(value = "/userOrders.htm", method = RequestMethod.GET)
	    public String viewOrders() {
		 	
	        return "user-orders";
	    }
	    
}
