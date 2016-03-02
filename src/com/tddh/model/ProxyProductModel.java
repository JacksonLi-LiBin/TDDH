package com.tddh.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * class recode product and proxy information
 * 
 * @author jackson
 *
 */
@XmlRootElement
public class ProxyProductModel implements Serializable {
	private static final long serialVersionUID = -8987813568264495415L;
	private Integer product_id;
	private Integer proxy_id;
	private Double product_proxy_price;
	private Integer first_order_counts;
	private Integer order_task;
	private String product_parameter;

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

	public Double getProduct_proxy_price() {
		return product_proxy_price;
	}

	public void setProduct_proxy_price(Double product_proxy_price) {
		this.product_proxy_price = product_proxy_price;
	}

	public Integer getFirst_order_counts() {
		return first_order_counts;
	}

	public void setFirst_order_counts(Integer first_order_counts) {
		this.first_order_counts = first_order_counts;
	}

	public Integer getOrder_task() {
		return order_task;
	}

	public void setOrder_task(Integer order_task) {
		this.order_task = order_task;
	}

	public String getProduct_parameter() {
		return product_parameter;
	}

	public void setProduct_parameter(String product_parameter) {
		this.product_parameter = product_parameter;
	}

	@Override
	public String toString() {
		return "{product_id:" + product_id + ", proxy_id:" + proxy_id + ", product_proxy_price:" + product_proxy_price
				+ ", first_order_counts:" + first_order_counts + ", order_task:" + order_task + ", product_parameter:"
				+ product_parameter + "}";
	}

}
