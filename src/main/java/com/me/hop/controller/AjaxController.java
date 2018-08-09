package com.me.hop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.me.hop.dao.OrderDAO;
import com.me.hop.dao.ProductDAO;
import com.me.hop.pojo.Poshakh;

@Controller

public class AjaxController {
	
	@Autowired
	@Qualifier("productDAO")
	ProductDAO pdao;
	
	@Autowired
	@Qualifier("orderDAO")
	OrderDAO odao;
	
    ArrayList<String> courseList;
    public AjaxController(){
        courseList = new ArrayList<String>();
        courseList.add("AED");
        courseList.add("Web tools");
        courseList.add("Web Design");
        courseList.add("Cloud computing");
        courseList.add("Data Science");
    }
    
    @RequestMapping(value = "/ajaxservice.htm", method = RequestMethod.POST)
    @ResponseBody
    public String ajaxService(HttpServletRequest request)
    {
    	 try {
			List<Poshakh> productlist = pdao.displayProduct();
			
			  String queryString = request.getParameter("course");
		        String result = "";
		        for(int i =0;i<=productlist.size();i++){
		            if(productlist.get(i).getProdName().toLowerCase().contains(queryString.toLowerCase())){
		                result +=productlist.get(i)+",";
		            }
		        }
		        
		        return result;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return "";
    }

}