package com.tddh.model;

import java.io.Serializable;

/**
 * user prvious product proxy model
 * 
 * @author jackson
 *
 */
public class UserPreviousProductProxyModel implements Serializable {
	private static final long serialVersionUID = 6868832691583097597L;
	private Integer user_id;
	private Integer product_id;
	private Integer previous_proxy_id;
	private Integer previous_superior_id;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Integer getPrevious_proxy_id() {
		return previous_proxy_id;
	}

	public void setPrevious_proxy_id(Integer previous_proxy_id) {
		this.previous_proxy_id = previous_proxy_id;
	}

	public Integer getPrevious_superior_id() {
		return previous_superior_id;
	}

	public void setPrevious_superior_id(Integer previous_superior_id) {
		this.previous_superior_id = previous_superior_id;
	}

	@Override
	public String toString() {
		return "{user_id:" + user_id + ", product_id:" + product_id + ", previous_proxy_id:" + previous_proxy_id
				+ ", previous_superior_id:" + previous_superior_id + "}";
	}

}
