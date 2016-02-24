package com.tddh.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * product model
 * 
 * @author jackson
 *
 */
@XmlRootElement
public class ProductModel implements Serializable {
	private static final long serialVersionUID = 2150070436550140809L;
	private Integer product_id;
	private String product_name;
	private Double product_sale_price;
	private String product_parameter;
	private String product_introduction;
	private String product_list_image;

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Double getProduct_sale_price() {
		return product_sale_price;
	}

	public void setProduct_sale_price(Double product_sale_price) {
		this.product_sale_price = product_sale_price;
	}

	public String getProduct_parameter() {
		return product_parameter;
	}

	public void setProduct_parameter(String product_parameter) {
		this.product_parameter = product_parameter;
	}

	public String getProduct_introduction() {
		return product_introduction;
	}

	public void setProduct_introduction(String product_introduction) {
		this.product_introduction = product_introduction;
	}

	public String getProduct_list_image() {
		return product_list_image;
	}

	public void setProduct_list_image(String product_list_image) {
		this.product_list_image = product_list_image;
	}

	@Override
	public String toString() {
		return "{product_id:" + product_id + ", product_name:" + product_name + ", product_sale_price:"
				+ product_sale_price + ", product_parameter:" + product_parameter + ", product_introduction:"
				+ product_introduction + ", product_list_image:" + product_list_image + "}";
	}

}
