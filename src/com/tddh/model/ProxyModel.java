package com.tddh.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * proxy model
 * 
 * @author jackson
 *
 */
@XmlRootElement
public class ProxyModel implements Serializable {
	private static final long serialVersionUID = -5143813022975979359L;

	private int proxy_id;
	private String proxy_value;
	private int proxy_level;

	public int getProxy_id() {
		return proxy_id;
	}

	public void setProxy_id(int proxy_id) {
		this.proxy_id = proxy_id;
	}

	public String getProxy_value() {
		return proxy_value;
	}

	public void setProxy_value(String proxy_value) {
		this.proxy_value = proxy_value;
	}

	public int getProxy_level() {
		return proxy_level;
	}

	public void setProxy_level(int proxy_level) {
		this.proxy_level = proxy_level;
	}

	@Override
	public String toString() {
		return "{proxy_id:" + proxy_id + ", proxy_value:" + proxy_value + ", proxy_level:" + proxy_level + "}";
	}

}
