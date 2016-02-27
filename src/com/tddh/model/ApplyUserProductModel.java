package com.tddh.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * user apply product model
 * 
 * @author jackson
 *
 */
@XmlRootElement
public class ApplyUserProductModel implements Serializable {
	private static final long serialVersionUID = 4434552742765405419L;
	private Integer userId;
	private String rpId;
	private ApplyOrderProductModel proxyProduct;
	private Integer proxyLevel;
	private Integer payType;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRpId() {
		return rpId;
	}

	public void setRpId(String rpId) {
		this.rpId = rpId;
	}

	public ApplyOrderProductModel getProxyProduct() {
		return proxyProduct;
	}

	public void setProxyProduct(ApplyOrderProductModel proxyProduct) {
		this.proxyProduct = proxyProduct;
	}

	public Integer getProxyLevel() {
		return proxyLevel;
	}

	public void setProxyLevel(Integer proxyLevel) {
		this.proxyLevel = proxyLevel;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	@Override
	public String toString() {
		return "{userId:" + userId + ", rpId:" + rpId + ", proxyProduct:" + proxyProduct + ", proxyLevel:" + proxyLevel
				+ ", payType:" + payType + "}";
	}

}
