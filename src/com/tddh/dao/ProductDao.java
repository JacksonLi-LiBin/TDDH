package com.tddh.dao;

import java.util.List;

import com.tddh.model.ProductModel;
import com.tddh.model.ProxyProductModel;

/**
 * define product database operation functions
 * 
 * @author jackson
 *
 */
public interface ProductDao {
	/**
	 * get all products list
	 * 
	 * @return
	 */
	public List<ProductModel> getAllProductService();

	/**
	 * get product information by proxy id and product id
	 * 
	 * @param proxy_id
	 * @param product_id
	 * @return
	 */
	public ProxyProductModel getProductByProxyAndId(int proxy_id, int product_id);
}
