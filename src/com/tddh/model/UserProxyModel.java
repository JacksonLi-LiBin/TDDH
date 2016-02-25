package com.tddh.model;

import java.io.Serializable;

/**
 * user proxy model
 * 
 * @author jacks
 *
 */
public class UserProxyModel implements Serializable {
	private static final long serialVersionUID = -4227889036610448725L;
	private Integer proxy_id;
	private Integer proxy_level;

	public Integer getProxy_id() {
		return proxy_id;
	}

	public void setProxy_id(Integer proxy_id) {
		this.proxy_id = proxy_id;
	}

	public Integer getProxy_level() {
		return proxy_level;
	}

	public void setProxy_level(Integer proxy_level) {
		this.proxy_level = proxy_level;
	}

	@Override
	public String toString() {
		return "{proxy_id:" + proxy_id + ", proxy_level:" + proxy_level + "}";
	}

}
