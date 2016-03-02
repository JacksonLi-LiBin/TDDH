package com.tddh.dao;

import java.util.List;

import com.tddh.model.ProductCategoryListModel;
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

	/**
	 * get product order price depend on proxy level
	 * 
	 * @param product_id
	 * @param proxy_id
	 * @return
	 */
	public double getProductOrderPrice(int product_id, int proxy_id);

	/**
	 * 
	 * @param categoryId
	 *            0 最新产品 1 明星产品
	 * @return
	 */
	public List<ProductCategoryListModel> getProductCategoryByCategoryId(int categoryId);
}
