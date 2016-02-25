package com.tddh.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * user proxy product detail id/name/price
 * 
 * @author jacks
 *
 */
@XmlRootElement
public class UserProxyProductDetailModel implements Serializable {
	private static final long serialVersionUID = 571609930720190893L;
	private Integer product_id;
	private String product_name;
	private Double product_proxy_price;

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

	public Double getProduct_proxy_price() {
		return product_proxy_price;
	}

	public void setProduct_proxy_price(Double product_proxy_price) {
		this.product_proxy_price = product_proxy_price;
	}

	@Override
	public String toString() {
		return "{product_id:" + product_id + ", product_name:" + product_name + ", product_proxy_price:"
				+ product_proxy_price + "}";
	}
}
