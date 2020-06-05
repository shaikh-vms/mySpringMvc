package com.vms.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vms.dao.DaoException;
import com.vms.dao.ProductDao;
import com.vms.entity.Category;
import com.vms.entity.Product;
import com.vms.entity.Supplier;
import com.vms.web.validators.ProductValidator;

@Controller
public class ProductController {

	@Autowired
	ProductDao htDao;

	// this function returns a view name with model to a ds from where vr is called

	@RequestMapping(method = RequestMethod.GET, path = "/all-products")
	public String getAllProducts(Model model) throws DaoException {
		// List<Product> list = htDao.getAllProducts();
		// model.addAttribute("products",list);
		/*
		 * as we are passing the view and model we generally put data in request and
		 * forword it but here in spring we don,nt have an object of servlet we can ask
		 * an object of same by typing that variable as parameter and spring will inject
		 * it as dependency but instead we have Model interface where the data is stored
		 * and collected by spring at the end
		 */
		model.addAttribute("pageTitle", "List of All Products");
		model.addAttribute("products", htDao.getAllProducts());

		/*
		 * this is a physical file but if we changed file location we have to chang path
		 * to overcome this spring has view resolver
		 * 
		 * return "/WEB-INF/pages/show-products.jsp";
		 */

		return "show-products";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/products-by-price-range")
	public String getProductsByPriceRange(@RequestParam Double min, @RequestParam Double max, Model model)
			throws DaoException {

		model.addAttribute("pageTitle", "products between $" + min + " and $" + max);
		model.addAttribute("products", htDao.getProductsByPriceRange(min, max));

		return "show-products";
	}

	@RequestMapping(path = "/product-details")
	public String getProductDetails(@RequestParam Integer id, Model model) throws DaoException {

		model.addAttribute("prDtl", htDao.getProduct(id));
		return "product-details";
	}

	// adds product to screen
	@RequestMapping(path = "/add-product", method = RequestMethod.GET)
	public String addProduct(Model model) throws DaoException {
		/*
		 * Product pr = new Product(); pr.setProductName("demo name"); this links a
		 * field of entity product to getters of and setters of field in spring form
		 * path attribute
		 */
		model.addAttribute("pr", new Product());
		/*
		 * model.addAttribute("categories", htDao.getAllCategories());
		 * model.addAttribute("suppliers", htDao.getAllSuppliers());
		 * 
		 * multiple functions here require model attributes of supplier and category
		 * besides every time declaring a model parameter and saving attribute
		 * we write a function for automatically adding attributes and puting them in request scope
		 * using @ModelAttribute ..line 89
		 */
		return "add-product-form";
	}
	
	@RequestMapping(path = "/edit-product" ,method = RequestMethod.GET)
	public String editProduct(Model model,@RequestParam Integer id) throws DaoException {
		model.addAttribute("pr", htDao.getProduct(id));
		return "add-product-form";
		
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategoriesList() throws DaoException {
		return htDao.getAllCategories();
	}
	@ModelAttribute("suppliers")
	public List<Supplier> getSuppliersList() throws DaoException {
		return htDao.getAllSuppliers();
	}
	
	

	// binding result returns a bucket of errors instead of breaking the app
	@RequestMapping(path = "/save-product", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("pr") Product pr, BindingResult errors) throws DaoException {

		/*
		 * we have aur custom errors to be added to *errors for this we have a validator
		 * interface from spring
		 */
		
		ProductValidator pv = new ProductValidator();
		pv.validate(pr, errors);
		
		/*
		 * we are displaying the add-product-form instead the product-details redirect
		 * but it requires model attribute with pr suppliers and categories as commented
		 * in above fun to overcome this we can have anothe parameter with model and
		 * implemeting the addAttribute for the same dependencies
		 * one of its dependencies is pr which is an parameter here so we made it as @ModelAttribute
		 */
		if(errors.hasErrors()) {
			return "add-product-form";
			//errors are transfer to form to handle them use sForm:error
		}
		
		if(pr.getProductId()==null) {
			htDao.addProduct(pr);
		}else {
			htDao.updateProduct(pr);
		}
		
		return "redirect:product-details?id=" + pr.getProductId();
	}

	
}
