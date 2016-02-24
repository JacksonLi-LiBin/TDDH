package com.tddh.model;

import java.io.Serializable;

/**
 * order product model
 * 
 * @author jacks
 *
 */
public class OrderProductModel implements Serializable {
	private static final long serialVersionUID = 5294662993972905306L;
	private Integer order_id;
	private Integer product_id;
	private Integer product_counts;
	private Double product_proxy_price;
	private Double product_total_price;

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

	public Double getProduct_proxy_price() {
		return product_proxy_price;
	}

	public void setProduct_proxy_price(Double product_proxy_price) {
		this.product_proxy_price = product_proxy_price;
	}

	public Double getProduct_total_price() {
		return product_total_price;
	}

	public void setProduct_total_price(Double product_total_price) {
		this.product_total_price = product_total_price;
	}

	@Override
	public String toString() {
		return "{order_id:" + order_id + ", product_id:" + product_id + ", product_counts:" + product_counts
				+ ", product_proxy_price:" + product_proxy_price + ", product_total_price:" + product_total_price + "}";
	}

}
