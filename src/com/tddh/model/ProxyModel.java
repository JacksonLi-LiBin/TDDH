package com.tddh.model;

import java.io.Serializable;

/**
 * proxy model
 * 
 * @author jackson
 *
 */
public class ProxyModel implements Serializable {
	private static final long serialVersionUID = -5143813022975979359L;

	private int proxyID;
	private String proxyValue;

	public int getProxyID() {
		return proxyID;
	}

	public void setProxyID(int proxyID) {
		this.proxyID = proxyID;
	}

	public String getProxyValue() {
		return proxyValue;
	}

	public void setProxyValue(String proxyValue) {
		this.proxyValue = proxyValue;
	}

	@Override
	public String toString() {
		return "{proxyID:" + proxyID + ", proxyValue:" + proxyValue + "}";
	}

}
