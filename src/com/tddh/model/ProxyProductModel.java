package com.tddh.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * class recode product and proxy information
 * 
 * @author jackson
 *
 */
@XmlRootElement
public class ProxyProductModel implements Serializable {
	private static final long serialVersionUID = -8987813568264495415L;
	private double product_proxy_price;
	private int first_order_counts;
	private int order_task;
	private int product_id;
	private String product_name;
	private double product_sale_price;
	private String product_parameter;
	private String product_introduction;
	private String product_list_image;
	private int proxy_id;
	private String proxy_value;

	public int getProxy_id() {
		return proxy_id;
	}

	public void setProxy_id(int proxy_id) {
		this.proxy_id = proxy_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public double getProduct_proxy_price() {
		return product_proxy_price;
	}

	public void setProduct_proxy_price(double product_proxy_price) {
		this.product_proxy_price = product_proxy_price;
	}

	public int getFirst_order_counts() {
		return first_order_counts;
	}

	public void setFirst_order_counts(int first_order_counts) {
		this.first_order_counts = first_order_counts;
	}

	public int getOrder_task() {
		return order_task;
	}

	public void setOrder_task(int order_task) {
		this.order_task = order_task;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getProduct_sale_price() {
		return product_sale_price;
	}

	public void setProduct_sale_price(double product_sale_price) {
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

	public String getProxy_value() {
		return proxy_value;
	}

	public void setProxy_value(String proxy_value) {
		this.proxy_value = proxy_value;
	}

	@Override
	public String toString() {
		return "{proxy_id:" + proxy_id + ", product_id:" + product_id + ", product_proxy_price:" + product_proxy_price
				+ ", first_order_counts:" + first_order_counts + ", order_task:" + order_task + ", product_name:"
				+ product_name + ", product_sale_price:" + product_sale_price + ", product_parameter:"
				+ product_parameter + ", product_introduction:" + product_introduction + ", product_list_image:"
				+ product_list_image + ", proxy_value:" + proxy_value + "}";
	}

}
