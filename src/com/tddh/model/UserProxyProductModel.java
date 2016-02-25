package com.tddh.model;

import java.io.Serializable;

/**
 * user proxy product model
 * 
 * @author jackson
 *
 */
public class UserProxyProductModel implements Serializable {
	private static final long serialVersionUID = 8564331721234620174L;
	private Integer user_id;
	private Integer product_id;
	private Integer proxy_id;
	private Integer user_superior_id;
	private Integer user_recommend_id;
	private Integer product_proxy_state;

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

	public Integer getProxy_id() {
		return proxy_id;
	}

	public void setProxy_id(Integer proxy_id) {
		this.proxy_id = proxy_id;
	}

	public Integer getUser_superior_id() {
		return user_superior_id;
	}

	public void setUser_superior_id(Integer user_superior_id) {
		this.user_superior_id = user_superior_id;
	}

	public Integer getUser_recommend_id() {
		return user_recommend_id;
	}

	public void setUser_recommend_id(Integer user_recommend_id) {
		this.user_recommend_id = user_recommend_id;
	}

	public Integer getProduct_proxy_state() {
		return product_proxy_state;
	}

	public void setProduct_proxy_state(Integer product_proxy_state) {
		this.product_proxy_state = product_proxy_state;
	}

	@Override
	public String toString() {
		return "{user_id:" + user_id + ", product_id:" + product_id + ", proxy_id:" + proxy_id + ", user_superior_id:"
				+ user_superior_id + ", user_recommend_id:" + user_recommend_id + ", product_proxy_state:"
				+ product_proxy_state + "}";
	}

}
