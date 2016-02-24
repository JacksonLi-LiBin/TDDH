package com.tddh.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.tddh.dao.ProxyDao;
import com.tddh.db.utils.DBConnectionUtils;
import com.tddh.model.ProxyModel;
import com.tddh.utils.PropertiesUtils;

public class ProxyDaoImpl implements ProxyDao {
	private QueryRunner queryRunner = new QueryRunner();

	@Override
	public List<ProxyModel> getSpecifiedProxies(String request_condition, int user_id, int product_id) {
		Connection conn = null;
		List<ProxyModel> proxyModels = null;
		try {
			conn = DBConnectionUtils.getConnection();
			if ("all".equals(request_condition)) {
				proxyModels = queryRunner.query(conn, PropertiesUtils.readProperties("sql", "load_all_proxies"),
						new BeanListHandler<ProxyModel>(ProxyModel.class));
			} else if ("high_level".equals(request_condition)) {
				proxyModels = queryRunner.query(conn, PropertiesUtils.readProperties("sql", "load_specified_proxies"),
						new BeanListHandler<ProxyModel>(ProxyModel.class), user_id, product_id);
			}
			return proxyModels;
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
}
