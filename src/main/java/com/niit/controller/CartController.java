package com.niit.controller;

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
public class CartController {
	@Autowired
	ProductDao productDAO;
	@Autowired
	CartDao cartDAO;
	@Autowired
	CategoryDao categoryDAO;
	@Autowired
	UserDetailsDao userDetailsDAO;
	@Autowired
	OrderDetailsDao orderDetailsDAO;
	
	@RequestMapping("/showCart")
	public ModelAndView showCart(HttpSession session){
		ModelAndView m=new ModelAndView("Cart");
		String username=(String)session.getAttribute("username");
		List<Cart> cart_list=cartDAO.getCartItems(username);
		m.addObject("cartItems", cart_list);
		m.addObject("cartSize", cart_list.size());
		return m;
	}
	@RequestMapping("/addToCart")
	public ModelAndView addToCart(@RequestParam("proid") int proId,HttpSession session)
	{	ModelAndView m=new ModelAndView("redirect:showCart");
		boolean notexist=true;
		String username=(String)session.getAttribute("username");
		UserDetails userDetails=userDetailsDAO.getUserDetails(username);
		List<Cart> cart_list=cartDAO.getCartItems(username);
		for(Cart cart:cart_list)
			if(cart.getProd_id()==proId){
				notexist=false;
				cart.setQuantity(cart.getQuantity()+1);
				cartDAO.insertOrUpdateCart(cart);
			}
		if(notexist){
		Cart cart=new Cart();
		if(cart_list.size()==0){
			userDetails.setCart_id(userDetails.getCart_id()+1);
			userDetailsDAO.insertOrUpdateUserDetails(userDetails);
		}
		cart.setCart_id(userDetails.getCart_id());
		cart.setQuantity(1);
		cart.setStatus("N");
		cart.setUsername(username);
		cart.setProd_id(proId);
		Product product=productDAO.getProduct(proId);
		cart.setProd_name(product.getPro_name());
		cart.setPrice(product.getPro_price());
		cartDAO.insertOrUpdateCart(cart);
		}
		return m;
	}
	@RequestMapping("/updateCart")
	public ModelAndView updateCart(@RequestParam("id") int cart_item_id,@RequestParam("action") String action)
	{	ModelAndView m=new ModelAndView("redirect:showCart");
		Cart cart=cartDAO.getCartItem(cart_item_id);
		int quantity=cart.getQuantity();
		if(action.equals("increase"))
			cart.setQuantity(quantity+1);
		else if(quantity!=1&&action.equals("decrease"))
			cart.setQuantity(quantity-1);
		cartDAO.insertOrUpdateCart(cart);
		return m;
	}
	@RequestMapping("/deleteCartItem")
	public ModelAndView deleteCartItem(@RequestParam("id") int cart_item_id)
	{	ModelAndView m=new ModelAndView("redirect:showCart");
		Cart cart=cartDAO.getCartItem(cart_item_id);
		cartDAO.deleteCartItem(cart);
		return m;
	}	
	@RequestMapping("/BuyNow")
	public ModelAndView buyNow(@RequestParam("proid") int proId,HttpSession session)
	{	ModelAndView m=new ModelAndView("redirect:checkOut");
		boolean notexist=true;
		String username=(String)session.getAttribute("username");
		UserDetails userDetails=userDetailsDAO.getUserDetails(username);
		List<Cart> cart_list=cartDAO.getCartItems(username);
		for(Cart cart:cart_list)
			if(cart.getProd_id()==proId){
				notexist=false;
				cart.setQuantity(cart.getQuantity()+1);
				cartDAO.insertOrUpdateCart(cart);
			}
		if(notexist){
			Cart cart=new Cart();
			if(cart_list.size()==0){
				userDetails.setCart_id(userDetails.getCart_id()+1);
				userDetailsDAO.insertOrUpdateUserDetails(userDetails);
			}
			cart.setCart_id(userDetails.getCart_id());
			cart.setQuantity(1);
			cart.setStatus("N");
			cart.setUsername(username);
			cart.setProd_id(proId);
			Product product=productDAO.getProduct(proId);
			cart.setProd_name(product.getPro_name());
			cart.setPrice(product.getPro_price());
			cartDAO.insertOrUpdateCart(cart);
		}
		return m;
	}
	
	@RequestMapping("/checkOut")
	public ModelAndView checkOut(HttpSession session){
		ModelAndView m=new ModelAndView("OrderConfirm");
		String username=(String)session.getAttribute("username");
		List<Cart> cart_list=cartDAO.getCartItems(username);
		int totalAmount=0,cartId=0;
		for(Cart cart:cart_list){
			totalAmount+=cart.getQuantity()*cart.getPrice();
			cartId=cart.getCart_id();
		}
		m.addObject("totalAmount",totalAmount);
		m.addObject("cartItems", cart_list);
		m.addObject("cartId", cartId);
		return m;
	}
	
	/*@RequestMapping("/shipping?cartId=${cartId}")
	public ModelAndView shipping(HttpSession session)
	{
		ModelAndView m=new ModelAndView("OrderShipping");
		String username=(String)session.getAttribute("username");
		
		
		return m;
	}
	*/
	
	
	@RequestMapping("/placeOrder")
	public ModelAndView placeOrder(HttpSession session,@RequestParam int mobile,@RequestParam String name,@RequestParam String address,@RequestParam String payMode){
		ModelAndView m=new ModelAndView("PurchaseSummary");
		String username=(String)session.getAttribute("username");
		List<Cart> cart_list=cartDAO.getCartItems(username);
		int order_id=(int)(Math.random()*10000000+Math.random()*7777);
		for(Cart cartItem:cart_list){
		OrderDetails orderDetails=new OrderDetails();
		cartItem.setStatus("Y");
		cartDAO.insertOrUpdateCart(cartItem);
		orderDetails.setCart_id(cartItem.getCart_id());
		
		
		
		/*
		orderDetails.setMobile(mobile);
		orderDetails.setName(name);
		orderDetails.setOrder_amount(cartItem.getQuantity()*cartItem.getPrice());
		orderDetails.setPay_mode(payMode);
		orderDetails.setOrder_id(order_id);
		orderDetails.setProd_id(cartItem.getProd_id());
		orderDetails.setProd_name(cartItem.getProd_name());
		orderDetails.setShip_address(address);
		orderDetails.setStatus("processing");*/
		
	 
		
		
		orderDetails.setUsername(username);
		orderDetailsDAO.insertOrderDetails(orderDetails);
		}			
		m.addObject("orderId",order_id);
		return m;
	}
	
	
	@ModelAttribute
	public void homeCatDetails(Model m){
		List<Category> list=categoryDAO.getCategoryDetails();
		m.addAttribute("catDetails",list);
	}
	
}
