package com.niit.controller;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.dao.CartDao;
import com.niit.dao.CategoryDao;
import com.niit.dao.OrderDetailsDao;
import com.niit.dao.ProductDao;
import com.niit.dao.UserDetailsDao;
import com.niit.model.Cart;
import com.niit.model.Category;
import com.niit.model.OrderDetails;
import com.niit.model.Product;
import com.niit.model.UserDetails;

@Controller
public class MyOrderController {
	@Autowired
	CartDao cartDAO;
	@Autowired
	CategoryDao categoryDAO;
	@Autowired
	OrderDetailsDao orderDetailsDAO;
	
	/*@RequestMapping("/myOrders")
	public ModelAndView myOrders(HttpSession session){
		ModelAndView m=new ModelAndView("MyOrders");
		LinkedHashMap<Cart,Integer> list=new LinkedHashMap<Cart,Integer>();
		String username=(String)session.getAttribute("username");
		List<Cart> cart_list=cartDAO.getPurchasedCartItems(username);
		for(Cart cart:cart_list)
			list.put(cart,orderDetailsDAO.getOrderDetails(username,cart.getCart_id()).getOrder_id());
		m.addObject("list", list.entrySet());
		m.addObject("cartItems", cart_list);
		m.addObject("listSize", cart_list.size());
		return m;
		}	*/
	
	@RequestMapping("/Details")
	public ModelAndView details(HttpSession session,@RequestParam int id){
		ModelAndView m=new ModelAndView("OrderDetails");
		String username=(String)session.getAttribute("username");
		Cart cart=cartDAO.getCartItem(id);
		OrderDetails orderDetails= orderDetailsDAO.getOrderDetails(username,cart.getCart_id());
		m.addObject("product", cart);
		m.addObject("order", orderDetails);
		return m;
		}	
	
	@ModelAttribute
	public void homeCatDetails(Model m){
		List<Category> list=categoryDAO.getCategoryDetails();
		m.addAttribute("catDetails",list);
	}
	
}
