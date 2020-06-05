package com.vms.web.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vms.dao.DaoException;
import com.vms.dao.ProductDao;
import com.vms.entity.ErrorRespnse;
import com.vms.entity.Product;
import com.vms.entity.ProductList;

@RestController//can be used besides @Controller spring 4.**
//@Controller
@RequestMapping("/api/products")
public class ProductResource {

	@Autowired
	ProductDao dao;
	
	@RequestMapping(method= RequestMethod.GET,
			produces = {"application/json","application/xml"})//url is default as class
	/*
	 * as we know this as handler for request returns a model and logical view name
	 * which is then resolved by view resolver here instead of returning a view
	 * name(String data type) we returning response body with another data Type.
	 * which is handled by ContentNegotiatingViewResolver and converted to json/xml
	 * according header notations like accept/content-type
	 *  
	 *accept: user defined data type which is acceptable(json/xml)
	 *content-type: _________________"_______ sent by user
	 */
	//@ResponseBody rest controller takes care of it
	
	
	
	/*Works fine for json
	 * public List<Product> getAllProducts() throws DaoException { return
	 * dao.getAllProducts(); }
	 * but we wrap a list into a class ProductList with annotation as XmlRootElement
	 * to allow jvm to convert it xml as list is generic class and we can't modify it
	 */
	public ResponseEntity<ProductList> getAllProducts() throws DaoException {
		ProductList pl= new ProductList();
		pl.setProducts(dao.getAllProducts());
		return ResponseEntity.ok(pl);
	}
	
	@RequestMapping(path="/{id}",method = RequestMethod.GET,
			produces = {"application/json","application/xml"})
	/*
	 * //public ResponseEntity<Product> getProductById(@PathVariable("id") Integer
	 * id) throws DaoException {//id in pv is optional as name is same if(pr ==
	 * null) { return ResponseEntity.notFound().build(); }
	 */
	/*
	 * public ResponseEntity<?> getProductById(@PathVariable Integer id) throws
	 * DaoException{ Product pr= dao.getProduct(id); if(pr==null) { Map<String,
	 * Object> m = new HashMap<>(); m.put("message", "No product found");
	 * m.put("Product id",id); return new ResponseEntity<>(m, HttpStatus.NOT_FOUND);
	 * } return ResponseEntity.ok(pr); }
	 * 
	 * ......this also works fine for json but if error occurs map can not be
	 * annotated with xmlrootelement so we replace it with a class (ErrorResopnse)
	 */
	public ResponseEntity<?> getProductById(@PathVariable Integer id) throws DaoException{
		Product pr= dao.getProduct(id);
		if(pr==null) {
		ErrorRespnse er = new ErrorRespnse();
		er.setMessage("No product found");
		er.setData(id);
		return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(pr);
	}
	
	
	
	
	@RequestMapping(method = RequestMethod.POST,
			produces = {"application/json","application/xml"},
			consumes = {"application/json","application/xml"})
	public ResponseEntity<?> addProduct(@RequestBody Product pr){
		try {
			dao.addProduct(pr);//this creates a new record in table with all foreign table data
			//as we have supplied abstract data and returning the same we get some fields as null
			//to overcome fetch the product from table and then return them
			pr =dao.getProduct(pr.getProductId());
			return ResponseEntity.ok(pr);
		} catch (DaoException e) {
			/*
			 * Map<String, Object> m = new HashMap<>(); m.put("message", e.getMessage());
			 * m.put("Product",pr);
			 */
			ErrorRespnse er = new ErrorRespnse();
			er.setMessage(e.getMessage());
			er.setData(pr);
			return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
	}
	
	@RequestMapping(path="/{id}",method = RequestMethod.PUT,
			produces = {"application/json","application/xml"},
			consumes = {"application/json","application/xml"})
	public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody Product pr){
		try {
			pr.setProductId(id);
			dao.updateProduct(pr);
			pr=dao.getProduct(pr.getProductId());
			return ResponseEntity.ok(pr);
		} catch (DaoException e) {
			/*
			 * Map<String, Object> m = new HashMap<>(); m.put("message", e.getMessage());
			 * m.put("Product",pr); return new ResponseEntity<>(m,
			 * HttpStatus.INTERNAL_SERVER_ERROR);
			 */
			ErrorRespnse er = new ErrorRespnse();
			er.setMessage(e.getMessage());
			er.setData(pr);
			return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
		
	}
	
	@RequestMapping(path="/{id}",method = RequestMethod.DELETE,
			produces = {"application/json","application/xml"})
	public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
		try {
			Product pr= dao.getProduct(id);
			if(pr==null) {
				/*
				 * Map<String, Object> m = new HashMap<>(); m.put("message",
				 * "No product found"); m.put("Product id",id); return new ResponseEntity<>(m,
				 * HttpStatus.NOT_FOUND);
				 */
				ErrorRespnse er = new ErrorRespnse();
				er.setMessage("No product found");
				er.setData(id);
				return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
			
			}
			
			dao.deleteProduct(id);
			pr= dao.getProduct(id);
			return ResponseEntity.ok(pr);
		} catch (Exception e) {
			/*
			 * Map<String, Object> m = new HashMap<>(); m.put("message", e.getMessage());
			 * return new ResponseEntity<>(m, HttpStatus.INTERNAL_SERVER_ERROR);
			 */
			ErrorRespnse er = new ErrorRespnse();
			er.setMessage(e.getMessage());
			return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
	}
	
}
