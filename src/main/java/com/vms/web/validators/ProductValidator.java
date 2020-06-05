package com.vms.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.vms.entity.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Product.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(
				errors, "productName", "productName.empty", "'Product Name' is mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(
				errors, "unitPrice", "unitPrice.empty", "'Unit Price' is mandatory");
		Product pr = (Product) target;
		Double unitPrice = pr.getUnitPrice();
		Integer unitsInStock = pr.getUnitsInStock();
		Integer unitsOnOrder = pr.getUnitsOnOrder();
		Integer reorderLevel = pr.getReorderLevel();
		
		//adding custom errors to errors collection
		if(unitPrice!=null && unitPrice<0) {
			//errors.rejectValue(field, errorCode, defaultMessage);
			errors.rejectValue("unitPrice", "unitPrice.invalid", "'unit price' must be > 0");
		}
		if(unitsInStock!=null && unitsInStock<0) {
			errors.rejectValue("unitsInStock", "unitsInStock.invalid", "'Units In Stock' must be > 0");
		}
		
		if(unitsOnOrder!=null && unitsOnOrder<0) {
			errors.rejectValue("unitsOnOrder", "unitsOnOrder.invalid", "'Units On Order' must be > 0");
		}
		if(reorderLevel!=null && reorderLevel<0) {
			errors.rejectValue("reorderLevel", "reorderLevel.invalid", "'Reorder Level' must be > 0");
		}
		
		
		//for select category or supplier
		//we have given id as -1 to default
		Integer supplierId = pr.getSupplier().getSupplierId();
		Integer categoryId = pr.getCategory().getCategoryId();
		
		if(categoryId==-1) {
			errors.rejectValue("category.categoryId", "category.not.selected", "select one of the categories");
		}
		
		if(supplierId==-1) {
			errors.rejectValue("supplier.supplierId", "supplier.not.selected", "select one of the suppliers");
		}
		

		Integer discontinued = pr.getDiscontinued();
		if (discontinued == null) {
			errors.rejectValue("discontinued", "discontinued.not.selected", "Please select an option");
		}
	}

}
