package com.tddh.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * apply product model
 * 
 * @author jackson
 *
 */
@XmlRootElement
public class ApplyOrderProductModel implements Serializable {
	private static final long serialVersionUID = 4434552742765405419L;
	private Integer productId;
	private Integer productCounts;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductCounts() {
		return productCounts;
	}

	public void setProductCounts(Integer productCounts) {
		this.productCounts = productCounts;
	}

	@Override
	public String toString() {
		return "{productId:" + productId + ", productCounts:" + productCounts + "}";
	}

}
