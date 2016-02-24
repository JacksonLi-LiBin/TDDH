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
	private Double order_superior_deduct;
	private Double order_recommend_deduct;

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Double getOrder_superior_deduct() {
		return order_superior_deduct;
	}

	public void setOrder_superior_deduct(Double order_superior_deduct) {
		this.order_superior_deduct = order_superior_deduct;
	}

	public Double getOrder_recommend_deduct() {
		return order_recommend_deduct;
	}

	public void setOrder_recommend_deduct(Double order_recommend_deduct) {
		this.order_recommend_deduct = order_recommend_deduct;
	}

	@Override
	public String toString() {
		return "{order_id:" + order_id + ", order_superior_deduct:" + order_superior_deduct
				+ ", order_recommend_deduct:" + order_recommend_deduct + "}";
	}

}
