package com.niit.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.niit.dao.CategoryDao;
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
public class AdminController {
	
	@Autowired
	CategoryDao categoryDAO;
	@Autowired
	ProductDao productDAO;
	@Autowired
	SupplierDao supplierDAO;
	
	
	@RequestMapping("/Category")
	public ModelAndView showCategoryPage()
	{	ModelAndView m=new ModelAndView("Category");
		List<Category> list=categoryDAO.getCategoryDetails();
		m.addObject("catDetails",list);
		boolean flag=false;
		m.addObject("flag",flag);
		return m;
	}
	
	@RequestMapping(value="/addCategory",method=RequestMethod.POST)
	public ModelAndView addCategory(@RequestParam("name") String cat_name,@RequestParam("desc") String cat_desc,@RequestParam("home_cat") String home_cat){
		ModelAndView m=new ModelAndView("redirect:Category");
		Category category=new Category();
		category.setHome_cat(home_cat);
		category.setCat_desc(cat_desc);
		category.setCat_name(cat_name);
		categoryDAO.insertOrUpdateCategory(category);
		return m;
	}
	
	@RequestMapping("/deleteCategory")
	public ModelAndView deleteCategory(@RequestParam("catId") int catId){
		ModelAndView m=new ModelAndView();
		Category category=categoryDAO.getCategory(catId);
		try{categoryDAO.deleteCategory(category);
		m.setViewName("redirect:Category");
		}
		catch(Exception e){
			List<Category> list=categoryDAO.getCategoryDetails();
			m.addObject("catDetails",list);
			boolean flag=false;
			m.addObject("flag",flag);
			m.addObject("warning","Edit the Assosiated Products First");
			m.setViewName("Category");
		}
		return m;
	}
	
	@RequestMapping("/updateCategory/{catId}")
	public ModelAndView updateCategory(@PathVariable("catId") int catId){
		ModelAndView m=new ModelAndView("Category");
		Category category=categoryDAO.getCategory(catId);
		m.addObject("category",category);
		List<Category> list=categoryDAO.getCategoryDetails();
		m.addObject("catDetails",list);
		boolean flag=true;
		m.addObject("flag",flag);
		return m;
	}

	@RequestMapping(value="/updateCategory",method=RequestMethod.POST)
	public ModelAndView update_Category(@RequestParam("catId") int catid,@RequestParam("home_cat") String home_cat,@RequestParam("name") String cat_name,@RequestParam("desc") String cat_desc){
		ModelAndView m=new ModelAndView("redirect:Category");
		Category category=new Category();
		category.setCat_id(catid);
		category.setHome_cat(home_cat);
		category.setCat_desc(cat_desc);
		category.setCat_name(cat_name);
		categoryDAO.insertOrUpdateCategory(category);
		return m;
	}

	/*End of category controller*/
	
	
	@RequestMapping("/Supplier")
	public ModelAndView showSupplierPage()
	{	ModelAndView m=new ModelAndView("Supplier");
		List<Supplier> list=supplierDAO.getSupplierDetails();
		m.addObject("supDetails",list);
		boolean flag=false;
		m.addObject("flag",flag);
		return m;
	}
	
	@RequestMapping(value="/addSupplier",method=RequestMethod.POST)
	public ModelAndView addSupplier(@RequestParam("name") String sup_name,@RequestParam("address") String sup_address){
		ModelAndView m=new ModelAndView("redirect:Supplier");
		Supplier supplier=new Supplier();
		supplier.setSup_address(sup_address);
		supplier.setSup_name(sup_name);
		supplierDAO.insertOrUpdateSupplier(supplier);
		return m;
	}
	
	@RequestMapping("/deleteSupplier")
	public ModelAndView deleteSupplier(@RequestParam("supId") int supId){
		ModelAndView m=new ModelAndView();
		Supplier supplier=supplierDAO.getSupplier(supId);
		try{
			supplierDAO.deleteSupplier(supplier);
			m.setViewName("redirect:Supplier");
			}
		catch(Exception e){
			List<Supplier> list=supplierDAO.getSupplierDetails();
			m.addObject("supDetails",list);
			boolean flag=false;
			m.addObject("flag",flag);
			m.addObject("warning","Edit the Assosiated Products First");
			m.setViewName("Supplier");
		}
		return m;
	}
	
	@RequestMapping("/updateSupplier/{supId}")
	public ModelAndView updateSupplier(@PathVariable("supId") int supId){
		ModelAndView m=new ModelAndView("Supplier");
		Supplier supplier=supplierDAO.getSupplier(supId);
		m.addObject("supplier",supplier);
		List<Supplier> list=supplierDAO.getSupplierDetails();
		m.addObject("supDetails",list);
		boolean flag=true;
		m.addObject("flag",flag);
		return m;
	}

	@RequestMapping(value="/updateSupplier",method=RequestMethod.POST)
	public ModelAndView update_Supplier(@RequestParam("supId") int supid,@RequestParam("name") String sup_name,@RequestParam("address") String sup_address){
		ModelAndView m=new ModelAndView("redirect:Supplier");
		Supplier supplier=new Supplier();
		supplier.setSup_id(supid);
		supplier.setSup_address(sup_address);
		supplier.setSup_name(sup_name);
		supplierDAO.insertOrUpdateSupplier(supplier);
		return m;
	}
	@ModelAttribute
	public void homeCatDetails(Model m){
		List<Category> list=categoryDAO.getCategoryDetails();
		m.addAttribute("catDetails",list);
	}
	
	/*End of Supplier Controller*/
	
	
	
	
	@RequestMapping("/Product")
	public ModelAndView showProductPage(@ModelAttribute("product") Product product)
	{	ModelAndView m=new ModelAndView("Product");
		List<Product> pro_list=productDAO.getProductDetails();
		m.addObject("suplist",this.getSupList());
		m.addObject("catlist",this.getCatList());
		m.addObject("proDetails",pro_list);
		m.addObject("flag",false);
		return m;
	}
	
	@RequestMapping(value="/addProduct", method=RequestMethod.POST)
	public ModelAndView saveProduct(@ModelAttribute("product")Product product, @RequestParam("pro_image")MultipartFile pro_image, BindingResult result, HttpServletRequest request, Model model)
	{
		System.out.println("Save Product");
		ModelAndView mv = new ModelAndView("redirect:Product");
		model.addAttribute("category", new Category()); 
		model.addAttribute("supplier", new Supplier()); 
		model.addAttribute("product", new Product()); 
	    String filepath = request.getSession().getServletContext().getRealPath("/");
		String filename = pro_image.getOriginalFilename();
		product.setPro_imagename(filename);
		productDAO.insertOrUpdateProduct(product);
		System.out.println("File Path"+filepath);
		try 
		{
			byte imagebyte[] = pro_image.getBytes();
			BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(filepath+"/resources/"+filename));
			System.out.println(fos);
			fos.write(imagebyte);
			fos.close();
		}catch(IOException e)
		{ 
			e.printStackTrace();
		}
		return mv;
	}
	
	/*@RequestMapping(value="/addProduct",method=RequestMethod.POST)
	public ModelAndView addProduct(@ModelAttribute("product") Product product,@RequestParam("pro_image") MultipartFile image,BindingResult result, HttpServletRequest request, Model model)
	{	System.out.println("Save Product");
		ModelAndView m=new ModelAndView("redirect:Product");
		productDAO.insertOrUpdateProduct(product);
		String imgpath = request.getSession().getServletContext().getRealPath("/");
		String imgpath="C:\\Users\\HP\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\Apnaghar\\";
		String file_info=imgpath+product.getPro_id()+".jpg";
		File f=new File(file_info);
		if(!image.isEmpty()){
			try{
			byte buff[]=image.getBytes();
			BufferedOutputStream bs=new BufferedOutputStream(new FileOutputStream(f));
			bs.write(buff);
			bs.close();
			}
			catch(Exception e){
				System.out.println("Exception");
			}
		}
		return m;
	}*/
	
	@RequestMapping("/deleteProduct")
	public ModelAndView deleteProduct(@RequestParam("proId") int proId,@ModelAttribute("product") Product product1)
	{	ModelAndView m=new ModelAndView("redirect:Product");
		Product product=productDAO.getProduct(proId);
		String file_info="C:\\Users\\HP\\workspace\\Apnaghar\\src\\main\\webapp\\resources\\images\\"+proId+".jpg";
		File f=new File(file_info);
		f.delete();
		productDAO.deleteProduct(product);
		return m;
	}

	@RequestMapping("/updateProduct")
	public ModelAndView updateProduct(@RequestParam("proId") int proId){
		ModelAndView m=new ModelAndView("Product");
		Product product=productDAO.getProduct(proId);
		m.addObject("product",product);
		List<Product> pro_list=productDAO.getProductDetails();
		m.addObject("suplist",this.getSupList());
		m.addObject("catlist",this.getCatList());
		m.addObject("proDetails",pro_list);
		m.addObject("flag",true);
		return m;
	}
	
	@RequestMapping("/viewProducts")
	public ModelAndView viewAllProduct(@RequestParam("id") int catId){
		ModelAndView m=new ModelAndView("ViewProducts");
		Category category=categoryDAO.getCategory(catId);
		List<Product> pro_list=productDAO.getProductCatWise(category);
		Gson g=new Gson();
		String product_list=g.toJson(pro_list);
		m.addObject("proDetails",product_list);
		m.addObject("catName",category.getCat_name());
		m.addObject("search","show");
		return m;	
	}
	
	@RequestMapping("/productDetails")
	public ModelAndView productDetails(@RequestParam("proid") int proId){
		ModelAndView m=new ModelAndView("ProductDetails");
		Product product=productDAO.getProduct(proId);
		m.addObject("product",product);
		return m;	
	}
	
	public LinkedHashMap<Integer, String> getSupList(){
		List<Supplier> sup_list=supplierDAO.getSupplierDetails();
		LinkedHashMap<Integer, String> suplist=new LinkedHashMap<Integer, String>();
		for(Supplier sup:sup_list){suplist.put(sup.getSup_id(),sup.getSup_name());}
		return suplist;
	}
	public LinkedHashMap<Integer, String> getCatList(){
		List<Category> cat_list=categoryDAO.getCategoryDetails();
		LinkedHashMap<Integer, String> catlist=new LinkedHashMap<Integer, String>();
		for(Category cat:cat_list){catlist.put(cat.getCat_id(),cat.getCat_name());}
		return catlist;
	}
	
	/*End of product controller*/
	
	

}
