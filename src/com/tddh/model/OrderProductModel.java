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
	private int order_id;
	private int product_id;
	private int product_counts;
	private double product_proxy_price;
	private double product_total_price;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getProduct_counts() {
		return product_counts;
	}

	public void setProduct_counts(int product_counts) {
		this.product_counts = product_counts;
	}

	public double getProduct_proxy_price() {
		return product_proxy_price;
	}

	public void setProduct_proxy_price(double product_proxy_price) {
		this.product_proxy_price = product_proxy_price;
	}

	public double getProduct_total_price() {
		return product_total_price;
	}

	public void setProduct_total_price(double product_total_price) {
		this.product_total_price = product_total_price;
	}

	@Override
	public String toString() {
		return "{order_id:" + order_id + ", product_id:" + product_id + ", product_counts:" + product_counts
				+ ", product_proxy_price:" + product_proxy_price + ", product_total_price:" + product_total_price + "}";
	}

}
