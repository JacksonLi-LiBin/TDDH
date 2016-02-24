package com.tddh.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.tddh.dao.ProductDao;
import com.tddh.db.utils.DBConnectionUtils;
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
				}
			} catch (Exception e2) {
			}
		}
	}

}
