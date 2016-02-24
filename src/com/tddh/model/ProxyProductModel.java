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
	private Integer product_id;
	private String product_name;
	private Double product_sale_price;
	private String product_parameter;
	private String product_introduction;
	private String product_list_image;
	private Integer proxy_id;
	private String proxy_value;
	private Double product_proxy_price;
	private Integer first_order_counts;
	private Integer order_task;

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

	public Integer getProxy_id() {
		return proxy_id;
	}

	public void setProxy_id(Integer proxy_id) {
		this.proxy_id = proxy_id;
	}

	public String getProxy_value() {
		return proxy_value;
	}

	public void setProxy_value(String proxy_value) {
		this.proxy_value = proxy_value;
	}

	public Double getProduct_proxy_price() {
		return product_proxy_price;
	}

	public void setProduct_proxy_price(Double product_proxy_price) {
		this.product_proxy_price = product_proxy_price;
	}

	public Integer getFirst_order_counts() {
		return first_order_counts;
	}

	public void setFirst_order_counts(Integer first_order_counts) {
		this.first_order_counts = first_order_counts;
	}

	public Integer getOrder_task() {
		return order_task;
	}

	public void setOrder_task(Integer order_task) {
		this.order_task = order_task;
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
