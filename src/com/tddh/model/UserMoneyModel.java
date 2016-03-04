package com.tddh.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * user money model
 * 
 * @author jackson
 *
 */
@XmlRootElement
public class UserMoneyModel implements Serializable {
	private static final long serialVersionUID = -464239791980781661L;
	private Integer user_id;
	private Double purchaseAmount;
	private Double deductAmount;
	private Double userWealth;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Double getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(Double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public Double getDeductAmount() {
		return deductAmount;
	}

	public void setDeductAmount(Double deductAmount) {
		this.deductAmount = deductAmount;
	}

	public Double getUserWealth() {
		return userWealth;
	}

	public void setUserWealth(Double userWealth) {
		this.userWealth = userWealth;
	}

	@Override
	public String toString() {
		return "{user_id:" + user_id + ", purchaseAmount:" + purchaseAmount + ", deductAmount:" + deductAmount
				+ ", userWealth:" + userWealth + "}";
	}

}
