package com.tddh.model;

import java.io.Serializable;

/**
 * proxy order model
 * 
 * @author jackson
 *
 */
public class ProxyOrderModel implements Serializable {
	private static final long serialVersionUID = 5312529598022730123L;
	private Integer order_id;
	private Integer product_id;
	private Integer product_counts;
	private Double product_total_price;
	private String product_name;
	private String product_parameter;

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Integer getProduct_counts() {
		return product_counts;
	}

	public void setProduct_counts(Integer product_counts) {
		this.product_counts = product_counts;
	}

	public Double getProduct_total_price() {
		return product_total_price;
	}

	public void setProduct_total_price(Double product_total_price) {
		this.product_total_price = product_total_price;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_parameter() {
		return product_parameter;
	}

	public void setProduct_parameter(String product_parameter) {
		this.product_parameter = product_parameter;
	}

	@Override
	public String toString() {
		return "{order_id:" + order_id + ", product_id:" + product_id + ", product_counts:" + product_counts
				+ ", product_total_price:" + product_total_price + ", product_name:" + product_name
				+ ", product_parameter:" + product_parameter + "}";
	}

}
