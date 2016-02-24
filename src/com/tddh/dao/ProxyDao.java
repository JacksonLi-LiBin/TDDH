package com.tddh.dao;

import java.util.List;

import com.tddh.model.ProxyModel;

/**
 * define proxy database operation functions
 * 
 * @author jackson
 *
 */
public interface ProxyDao {
	/**
	 * get all proxies information according to proxy level
	 * 
	 * @return
	 */
	public List<ProxyModel> getSpecifiedProxies(String request_condition, int user_id, int product_id);
}
