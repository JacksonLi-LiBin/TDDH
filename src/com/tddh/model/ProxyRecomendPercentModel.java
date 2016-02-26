package com.tddh.model;

import java.io.Serializable;

/**
 * proxy recommend percent model
 * 
 * @author jacks
 *
 */
public class ProxyRecomendPercentModel implements Serializable {
	private static final long serialVersionUID = -439258961088091271L;
	private Integer proxy_level;
	private String deduct_percent;

	public Integer getProxy_level() {
		return proxy_level;
	}

	public void setProxy_level(Integer proxy_level) {
		this.proxy_level = proxy_level;
	}

	public String getDeduct_percent() {
		return deduct_percent;
	}

	public void setDeduct_percent(String deduct_percent) {
		this.deduct_percent = deduct_percent;
	}

	@Override
	public String toString() {
		return "{proxy_level:" + proxy_level + ", deduct_percent:" + deduct_percent + "}";
	}

}
