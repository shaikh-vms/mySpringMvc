package com.vms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "products")
@ToString
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement//allowing jvm to convert to xml ..no need xml api as it is included from java 6 onwards as jacksB
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {
	@Id
	@Column(name = "product_id")
	@GeneratedValue(generator = "increment")
	private Integer productId;
	@Column(name = "product_name")
	private String productName;
	@Column(name = "supplier_id", insertable = false, updatable = false)
	@JsonIgnore
	@XmlTransient
	private Integer supplierId;

	@ManyToOne//(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

	@JsonIgnore
	@XmlTransient
	@Column(name = "category_id", insertable = false, updatable = false)
	private Integer categoryId;

	/*
	 * using the following in put requiest if we update supplier name it wont effect
	 * @ManyToOne
	   @JoinColumn(name = "category_id")
	   private Category category;
	 * to chane its cascade
	 */
	@ManyToOne//(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name = "category_id")
	private Category category;

	@Column(name = "quantity_per_unit")
	private String quantityPerUnit;
	@Column(name = "unit_price")
	private Double unitPrice;
	@Column(name = "units_in_stock")
	private Integer unitsInStock;
	@Column(name = "units_on_order")
	private Integer unitsOnOrder;
	@Column(name = "reorder_level")
	private Integer reorderLevel;
	private Integer discontinued;
}
