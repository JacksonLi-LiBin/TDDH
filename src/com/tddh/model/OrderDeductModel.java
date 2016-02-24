package com.tddh.model;

import java.io.Serializable;

/**
 * deduct model superior deduct / recommend deduct
 * 
 * @author jacks
 *
 */
public class OrderDeductModel implements Serializable {
	private static final long serialVersionUID = 4588349139187296216L;
	private Integer order_id;
	private Integer order_deduct_id;
	private Integer product_id;
	private Double order_deduct;

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getOrder_deduct_id() {
		return order_deduct_id;
	}

	public void setOrder_deduct_id(Integer order_deduct_id) {
		this.order_deduct_id = order_deduct_id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Double getOrder_deduct() {
		return order_deduct;
	}

	public void setOrder_deduct(Double order_deduct) {
		this.order_deduct = order_deduct;
	}

	@Override
	public String toString() {
		return "{order_id:" + order_id + ", order_deduct_id:" + order_deduct_id + ", product_id:" + product_id
				+ ", order_deduct:" + order_deduct + "}";
	}

}
