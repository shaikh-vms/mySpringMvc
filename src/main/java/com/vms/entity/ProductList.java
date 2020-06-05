package com.vms.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlRootElement
@NoArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)//giving name to element in this case
//generates a products as field and other varible in getter which conflicts to jaxb and cause
//error so we indicate only to convert field to xml and not other things
public class ProductList {
	
	@XmlElement(name="product")//as we are refering single product we are giving a sigular name to xmltag 
	//instead default name of field
	private List<Product> products;


}
