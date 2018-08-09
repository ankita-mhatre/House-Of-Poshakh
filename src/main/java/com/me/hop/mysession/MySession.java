package com.me.hop.mysession;
import javax.servlet.http.HttpServletRequest;

import com.me.hop.model.InCart;
public class MySession {
	public static InCart getCartInSession(HttpServletRequest request) {
		 
        // Get Cart from Session.
		InCart cartInfo = (InCart) request.getSession().getAttribute("myCart");
        
        // If null, create it.
        if (cartInfo == null) {
            cartInfo = new InCart();
            
            // And store to Session.
            request.getSession().setAttribute("myCart", cartInfo);
        }
 
        return cartInfo;
    }
 
    public static void removeCartInSession(HttpServletRequest request) {
        request.getSession().removeAttribute("myCart");
    }
 
    public static void storeLastOrderedCartInSession(HttpServletRequest request, InCart cartInfo) {
        request.getSession().setAttribute("lastOrderedCart", cartInfo);
    }
    
    public static InCart getLastOrderedCartInSession(HttpServletRequest request) {
        return (InCart) request.getSession().getAttribute("lastOrderedCart");
    }
}


