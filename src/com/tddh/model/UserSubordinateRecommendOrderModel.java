package com.tddh.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * get user subordinate or recommend user order by user id and product id
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
	private Integer product_counts;
	private Double product_proxy_price;
	private Double real_get_total_price;
	private Double order_deduct;

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

	public Double getReal_get_total_price() {
		return real_get_total_price;
	}

	public void setReal_get_total_price(Double real_get_total_price) {
		this.real_get_total_price = real_get_total_price;
	}

	public Integer getProxy_level() {
		return proxy_level;
	}

	public void setProxy_level(Integer proxy_level) {
		this.proxy_level = proxy_level;
	}

	public Double getOrder_deduct() {
		return order_deduct;
	}

	public void setOrder_deduct(Double order_deduct) {
		this.order_deduct = order_deduct;
	}

	@Override
	public String toString() {
		return "{user_id:" + user_id + ", user_nickname:" + user_nickname + ", proxy_id:" + proxy_id + ", proxy_value:"
				+ proxy_value + ", product_counts:" + product_counts + ", product_proxy_price:" + product_proxy_price
				+ ", real_get_total_price:" + real_get_total_price + ", proxy_level:" + proxy_level + ", order_deduct:"
				+ order_deduct + "}";
	}

}
