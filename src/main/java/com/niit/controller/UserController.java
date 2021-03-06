package com.niit.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.niit.dao.CartDao;
import com.niit.dao.CategoryDao;
import com.niit.dao.OrderDetailsDao;
import com.niit.dao.ProductDao;
import com.niit.dao.SupplierDao;
import com.niit.dao.UserDetailsDao;
import com.niit.model.Cart;
import com.niit.model.Category;
import com.niit.model.OrderDetails;
import com.niit.model.Product;
import com.niit.model.Supplier;
import com.niit.model.UserDetails;

@Controller
public class UserController 
{
	
	@Autowired
	UserDetailsDao userDetailsDAO;
	@Autowired
	CategoryDao categoryDAO;
	@Autowired
	OrderDetailsDao orderDetailsDAO;
	@Autowired
	CartDao cartDAO;
	
	
	@RequestMapping("login")
	public ModelAndView login(@RequestParam(value="id",required=false) String id)
	{	ModelAndView m=new ModelAndView("Login");
		if(id!=null){
		if(id.equals("1"))
			m.addObject("msg","Invalid Username or Password");
		else if(id.equals("2"))
			m.addObject("msg","You've been logged out");
		else if(id.equals("3"))
			m.addObject("msg","Your Account has been Deactivated");		
		}
		return m;	
	}
	
	@RequestMapping("register")
	public String register(){
		return "Register";
	}
	
	@RequestMapping(value="/login_success",method=RequestMethod.POST)
		public String loginsuccess(HttpSession session)
		{ 
			String username= SecurityContextHolder.getContext().getAuthentication().getName();
			session.setAttribute("username",username);
			session.setAttribute("loggedIn",true);
			@SuppressWarnings("unchecked")
			Collection<GrantedAuthority> authorities =(Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			for(GrantedAuthority role:authorities)
			{
				System.out.println("Role:"+role.getAuthority()+"username"+username);
				if(role.getAuthority().equals("ROLE_ADMIN"))
					session.setAttribute("user",null);
				else
					session.setAttribute("user","user");
				}
			return "redirect:index";
		}
	

	@RequestMapping(value="/addUserDetails",method=RequestMethod.POST)
	public ModelAndView addProduct(@RequestParam Map<String,String> user,@RequestParam("mobile") long mobile)
	{	ModelAndView m=new ModelAndView();
		UserDetails userDetails=userDetailsDAO.getUserDetails(user.get("username"));
		if(userDetails!=null&&userDetails.isEnabled()==true){
		m.addObject("userExist","Username Already Exist");
		m.setViewName("Register");
		}
		else{
		userDetails =new UserDetails();
		userDetails.setFull_name(user.get("full_name"));
		userDetails.setAddress(user.get("address"));
		userDetails.setEmail(user.get("email"));
		userDetails.setUsername(user.get("username"));
		userDetails.setPassword(user.get("password"));
		userDetails.setMobile(mobile);
		userDetails.setEnabled(true);
		userDetails.setRole("ROLE_USER");
		userDetails.setCart_id(100);
		userDetailsDAO.insertOrUpdateUserDetails(userDetails);
		m.addObject("userCreate","Account Created");
		m.setViewName("Login");
		}
		return m;
	}
	
	/*@RequestMapping(value="/myAccount")
	public ModelAndView myAccount(HttpSession session)
	{	ModelAndView m=new ModelAndView("MyAccount");
		String user=(String)session.getAttribute("username");
		UserDetails userDetails=userDetailsDAO.getUserDetails(user);	
		m.addObject("user",userDetails);
		return m;
	}
	
	@RequestMapping(value="/changePass")
	public ModelAndView changePass(@RequestParam("currentPass") String cPass,@RequestParam("newPass") String nPass,HttpSession session)
	{	ModelAndView m=new ModelAndView("MyAccount");
		String user=(String)session.getAttribute("username");
		UserDetails userDetails=userDetailsDAO.getUserDetails(user);	
		if(cPass.equals(userDetails.getPassword())){
			userDetails.setPassword(nPass);
			userDetailsDAO.insertOrUpdateUserDetails(userDetails);
			m.addObject("info","password changed successfully");
		}
		else{
			m.addObject("info","Error while changing password");	
		}
		m.addObject("user",userDetails);
		return m;
	}
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	public ModelAndView updateUser(@RequestParam Map<String,String> user,HttpSession session)
	{	ModelAndView m=new ModelAndView("redirect:myAccount");
		String username=(String)session.getAttribute("username");
		UserDetails userDetails=userDetailsDAO.getUserDetails(username);
		if(user.get("full_name")!=null)
		userDetails.setFull_name(user.get("full_name"));
		if(user.get("address")!=null)
		userDetails.setAddress(user.get("address"));
		if(user.get("email")!=null)
		userDetails.setEmail(user.get("email"));
		if(user.get("mobile")!=null)
		userDetails.setMobile(Long.parseLong(user.get("mobile")));	
		userDetailsDAO.insertOrUpdateUserDetails(userDetails);
		return m;
	}
	
	@RequestMapping(value="/deactivateAccount")
	public String deactivateAccount(HttpSession session)
	{	String user=(String)session.getAttribute("username");
		UserDetails userDetails=userDetailsDAO.getUserDetails(user);	
		userDetails.setEnabled(false);
		userDetailsDAO.insertOrUpdateUserDetails(userDetails);
		List<Cart> cartList=cartDAO.getCartItems(user);
		if(!cartList.isEmpty())
			for(Cart cart:cartList)
				cartDAO.deleteCartItem(cart);
		cartList=cartDAO.getPurchasedCartItems(user);
		if(!cartList.isEmpty())
			for(Cart cart:cartList)
				cartDAO.deleteCartItem(cart);
		orderDetailsDAO.deleteOrderDetails(user);
		session.invalidate();
		return "redirect:login?id=3";
	}
*/	
	
	@ModelAttribute
	public void homeCatDetails(Model m){
		List<Category> list=categoryDAO.getCategoryDetails();
		m.addAttribute("catDetails",list);
	}
	
	
}
