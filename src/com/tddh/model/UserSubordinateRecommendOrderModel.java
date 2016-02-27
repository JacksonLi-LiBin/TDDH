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
	private Integer product_id;
	private Integer p_counts;
	private Double product_proxy_price;
	private Double p_t_price;
	private Integer order_id;
	private Double o_deduct;

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

	public Integer getP_counts() {
		return p_counts;
	}

	public void setP_counts(Integer p_counts) {
		this.p_counts = p_counts;
	}

	public Double getProduct_proxy_price() {
		return product_proxy_price;
	}

	public void setProduct_proxy_price(Double product_proxy_price) {
		this.product_proxy_price = product_proxy_price;
	}

	public Double getP_t_price() {
		return p_t_price;
	}

	public void setP_t_price(Double p_t_price) {
		this.p_t_price = p_t_price;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Double getO_deduct() {
		return o_deduct;
	}

	public void setO_deduct(Double o_deduct) {
		this.o_deduct = o_deduct;
	}

	@Override
	public String toString() {
		return "{user_id:" + user_id + ", user_nickname:" + user_nickname + ", proxy_id:" + proxy_id + ", proxy_value:"
				+ proxy_value + ", proxy_level:" + proxy_level + ", product_id:" + product_id + ", p_counts:" + p_counts
				+ ", product_proxy_price:" + product_proxy_price + ", p_t_price:" + p_t_price + ", order_id:" + order_id
				+ ", o_deduct:" + o_deduct + "}";
	}

}
