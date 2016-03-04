package com.tddh.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * get user subordinate or recommend user order by user id
 * 
 * @author jacks
 *
 */
@XmlRootElement
public class UserSubordinateRecommendOrderModel implements Serializable {
	private static final long serialVersionUID = -4984051601455827095L;
	private Integer user_id;
	private String user_nickname;
	private Integer proxy_id;
	private String proxy_value;
	private Integer proxy_level;
	private Integer product_id;
	private String product_name;
	private Double total_amount;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
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

	public Integer getProxy_level() {
		return proxy_level;
	}

	public void setProxy_level(Integer proxy_level) {
		this.proxy_level = proxy_level;
	}

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

	public Double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Double total_amount) {
		this.total_amount = total_amount;
	}

	@Override
	public String toString() {
		return "{user_id:" + user_id + ", user_nickname:" + user_nickname + ", proxy_id:" + proxy_id + ", proxy_value:"
				+ proxy_value + ", proxy_level:" + proxy_level + ", product_id:" + product_id + ", product_name:"
				+ product_name + ", total_amount:" + total_amount + "}";
	}
}
