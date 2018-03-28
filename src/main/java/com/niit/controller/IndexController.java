package com.niit.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.niit.dao.HomeProductsDao;
import com.niit.dao.OrderDetailsDao;
import com.niit.dao.ProductDao;
import com.niit.dao.SupplierDao;
import com.niit.dao.UserDetailsDao;
import com.niit.model.Cart;
import com.niit.model.Category;
import com.niit.model.HomeProducts;
import com.niit.model.OrderDetails;
import com.niit.model.Product;
import com.niit.model.Supplier;
import com.niit.model.UserDetails;

@Controller
public class IndexController {
	@Autowired
	CategoryDao categoryDAO;
	@Autowired
	HomeProductsDao homeProductsDAO;
	@Autowired
	ProductDao productDAO;
	@Autowired
	SupplierDao supplierDAO;
	
	/*@RequestMapping(value={"index","home","/"})
	public ModelAndView index(){
		ModelAndView m=new ModelAndView("index");
		List<HomeProducts> list=homeProductsDAO.getHomeProductsList();
		List<Supplier> supplist=supplierDAO.getSupplierDetails();
		List<Product> prodList=productDAO.getProductDetails();
		List<Category> catlist=categoryDAO.getCategoryDetails();
		m.addObject("homeProducts",list);
		m.addObject("product",prodList);
		m.addObject("supplier",supplist);
		m.addObject("category",catlist);
		
		return m;
	}*/
	
	@RequestMapping(value={"index","home","/"}, method = RequestMethod.GET) 
	public String index(Model model) { 
		model.addAttribute("category", new Category()); 
		model.addAttribute("supplier", new Supplier()); 
		model.addAttribute("product", new Product()); 
		
		model.addAttribute("supplist", supplierDAO.retrieve());
		model.addAttribute("catlist", categoryDAO.retrieve());
		model.addAttribute("prodList", productDAO.retrieve());
		return "index"; 	
	}
	
	@RequestMapping("header")
	public String header(){
		return "header";
	}
	
	@RequestMapping("403Error")
	public String Error(){
		return "403";
	}
	
	@RequestMapping("/homeProducts")
	public ModelAndView homeProduct(@ModelAttribute("homeProduct") HomeProducts homeProduct){
		ModelAndView m=new ModelAndView("HomeProducts");
		List<HomeProducts> list=homeProductsDAO.getHomeProductsList();
		m.addObject("homeProducts",list);
		return m;
	}
	@RequestMapping("/updateHomeProducts")
	public ModelAndView updateHomeProduct(@RequestParam("id") int srNo){
		ModelAndView m=new ModelAndView("HomeProducts");
		HomeProducts homeProducts= homeProductsDAO.getHomeProducts(srNo);
		List<Product> pro_list=productDAO.getProductDetails();
		LinkedHashMap<Integer,String> prolist=new  LinkedHashMap<Integer,String>();
		for(Product product:pro_list)
			prolist.put(product.getPro_id(), product.getPro_name());
		List<HomeProducts> list=homeProductsDAO.getHomeProductsList();
		m.addObject("homeProducts",list);
		m.addObject("prolist",prolist);
		m.addObject("homeProduct",homeProducts);
		m.addObject("flag",true);
		return m;
	}
	@RequestMapping("/updateHomeProduct")
	public ModelAndView updateHomeProduct(@ModelAttribute("homeProduct") HomeProducts homeProduct){
		ModelAndView m=new ModelAndView("redirect:homeProducts");
		homeProductsDAO.insertOrUpdateHomeProducts(homeProduct);
		return m;
	}
	
	@RequestMapping("AboutUs")
	public String aboutUs(){
		return "AboutUs";
	}
	
	@RequestMapping("ContactUs")
	public String contactUs(){
		return "ContactUs";
	}
	
	@RequestMapping("policy")
	public String policy()
	{
		return "policy";
	}
	@RequestMapping("terms")
	public String terms()
	{
		return "terms";
	}
	@ModelAttribute
	public void homeCatDetails(Model m){
		List<Category> list=categoryDAO.getCategoryDetails();
		m.addAttribute("catDetails",list);
	}
	

}
