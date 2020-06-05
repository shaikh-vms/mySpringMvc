package com.vms.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vms.entity.Category;
import com.vms.entity.Product;
import com.vms.entity.Supplier;

@Transactional(rollbackFor = {DaoException.class}, readOnly = true)
public interface ProductDao {
	// CRUD OPERATIONS
	
	@Transactional(readOnly = false)
	public default void addProduct(Product product) throws DaoException {
		throw new DaoException("Method not implemented");
	}

	@Transactional(readOnly = false)
	public default void updateProduct(Product product) throws DaoException  {
		throw new DaoException("Method not implemented");
	}

	public default Product getProduct(Integer productId) throws DaoException  {
		throw new DaoException("Method not implemented");
	}

	@Transactional(readOnly = false)
	public default void deleteProduct(Integer productId) throws DaoException  {
		throw new DaoException("Method not implemented");
	}

	// QUERIES
	public default List<Product> getAllProducts() throws DaoException  {
		throw new DaoException("Method not implemented");
	}

	public default List<Product> getProductsByPriceRange(Double min, Double max) throws DaoException  {
		throw new DaoException("Method not implemented");
	}

	public default List<Product> getProductsInCategory(Integer categoryId) throws DaoException  {
		throw new DaoException("Method not implemented");
	}

	public default List<Product> getProductsNotInStock() throws DaoException  {
		throw new DaoException("Method not implemented");
	}

	public default List<Product> getProductsOnOrder() throws DaoException  {
		throw new DaoException("Method not implemented");
	}

	public default List<Product> getDiscontinuedProducts() throws DaoException  {
		throw new DaoException("Method not implemented");
	}

	public default long count() throws DaoException  {
		throw new DaoException("Method not implemented");
	}
	
	public default List<Category> getAllCategories() throws DaoException  {
		throw new DaoException("Method not implemented");
	}
	public default List<Supplier> getAllSuppliers() throws DaoException  {
		throw new DaoException("Method not implemented");
	}
}
