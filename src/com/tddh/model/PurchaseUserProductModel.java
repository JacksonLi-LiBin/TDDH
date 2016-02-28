package com.tddh.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * purchase products model
 * 
 * @author jackson
 *
 */
@XmlRootElement
public class PurchaseUserProductModel implements Serializable {
	private static final long serialVersionUID = -746401561899413327L;
	private Integer userId;
	private List<ApplyOrderProductModel> purchaseProducts;
	private Integer addressId;
	private Integer payType;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<ApplyOrderProductModel> getPurchaseProducts() {
		return purchaseProducts;
	}

	public void setPurchaseProducts(List<ApplyOrderProductModel> purchaseProducts) {
		this.purchaseProducts = purchaseProducts;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	@Override
	public String toString() {
		return "{userId:" + userId + ", purchaseProducts:" + purchaseProducts + ", addressId:" + addressId
				+ ", payType:" + payType + "}";
	}

}
