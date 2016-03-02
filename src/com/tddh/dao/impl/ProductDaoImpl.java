package com.tddh.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.tddh.dao.ProductDao;
import com.tddh.db.utils.DBConnectionUtils;
import com.tddh.model.ProductCategoryListModel;
import com.tddh.model.ProductCategoryModel;
import com.tddh.model.ProductModel;
import com.tddh.model.ProxyProductModel;
import com.tddh.utils.PropertiesUtils;

public class ProductDaoImpl implements ProductDao {
	private QueryRunner queryRunner = new QueryRunner();

	@Override
	public List<ProductModel> getAllProductService() {
		Connection conn = null;
		try {
			conn = (Connection) DBConnectionUtils.getConnection();
			List<ProductModel> products = queryRunner.query(conn,
					PropertiesUtils.readProperties("sql", "load_all_products"),
					new BeanListHandler<ProductModel>(ProductModel.class));
			return products;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e2) {
			}
		}
	}

	@Override
	public ProxyProductModel getProductByProxyAndId(int proxy_id, int product_id) {
		Connection conn = null;
		ProxyProductModel proxyProductModel = null;
		try {
			conn = DBConnectionUtils.getConnection();
			proxyProductModel = queryRunner.query(conn,
					PropertiesUtils.readProperties("sql", "load_product_by_proxy_and_id"),
					new BeanHandler<ProxyProductModel>(ProxyProductModel.class), product_id, proxy_id);
			return proxyProductModel;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e2) {

			}
		}
	}

	@Override
	public double getProductOrderPrice(int product_id, int proxy_id) {
		Connection conn = null;
		try {
			conn = DBConnectionUtils.getConnection();
			ProxyProductModel proxyProductModel = queryRunner.query(conn,
					PropertiesUtils.readProperties("sql", "load_product_by_proxy"),
					new BeanHandler<ProxyProductModel>(ProxyProductModel.class), product_id, proxy_id);
			return proxyProductModel.getProduct_proxy_price();
		} catch (Exception e) {
			return 0;
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e2) {
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<ProductCategoryListModel> getProductCategoryByCategoryId(int categoryId) {
		Connection conn = null;
		try {
			conn = DBConnectionUtils.getConnection();
			List<ProductCategoryModel> models = null;
			if (categoryId == -1) {
				models = queryRunner.query(conn, PropertiesUtils.readProperties("sql", "load_product_category_all"),
						new BeanListHandler<ProductCategoryModel>(ProductCategoryModel.class));
			} else {
				models = queryRunner.query(conn,
						PropertiesUtils.readProperties("sql", "load_product_category_by_category"),
						new BeanListHandler<ProductCategoryModel>(ProductCategoryModel.class), categoryId);
			}
			if (models != null && models.size() > 0) {
				Map<String, ProductCategoryListModel> map = new HashMap<String, ProductCategoryListModel>();
				for (ProductCategoryModel model : models) {
					boolean flag = false;
					Iterator iterator = map.entrySet().iterator();
					while (iterator.hasNext()) {
						Map.Entry<String, ProductCategoryListModel> entry = (Entry<String, ProductCategoryListModel>) iterator
								.next();
						if (entry.getKey().equals(model.getCategory_value())) {
							ProductCategoryListModel pcm = entry.getValue();
							pcm.getCategoryProducts().add(model);
							flag = true;
							break;
						}
					}
					if (!flag) {
						ProductCategoryListModel pcl = new ProductCategoryListModel();
						pcl.setCategoryName(model.getCategory_value() == null ? "null" : model.getCategory_value());
						List<ProductCategoryModel> list = new ArrayList<>();
						list.add(model);
						pcl.setCategoryProducts(list);
						map.put(model.getCategory_value() == null ? "null" : model.getCategory_value(), pcl);
					}
				}
				Iterator iterator = map.entrySet().iterator();
				List<ProductCategoryListModel> pclm = new ArrayList<ProductCategoryListModel>();
				while (iterator.hasNext()) {
					Map.Entry<String, ProductCategoryListModel> entry = (Entry<String, ProductCategoryListModel>) iterator
							.next();
					ProductCategoryListModel model = entry.getValue();
					pclm.add(model);
				}
				return pclm;
			}
			return null;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e2) {
			}
		}
	}

}
