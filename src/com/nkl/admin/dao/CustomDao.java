package com.nkl.admin.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.admin.domain.Custom;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class CustomDao {

	public int addCustom(Custom custom, Connection conn){
		String sql = "INSERT INTO custom(custom_id,custom_name,custom_phone,custom_address,custom_date) values(null,?,?,?,?)";
		Object[] params = new Object[] {
			custom.getCustom_name(),
			custom.getCustom_phone(),
			custom.getCustom_address(),
			custom.getCustom_date()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delCustom(String custom_id, Connection conn){
		String sql = "DELETE FROM custom WHERE custom_id=?";

		Object[] params = new Object[] { new Integer(custom_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delCustoms(String[] custom_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <custom_ids.length; i++) {
			sBuilder.append("?");
			if (i !=custom_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM custom WHERE custom_id IN(" +sBuilder.toString()+")";

		Object[] params = custom_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateCustom(Custom custom, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE custom SET custom_id = " + custom.getCustom_id() +" ");
		if (!StringUtil.isEmptyString(custom.getCustom_name())) {
			sBuilder.append(", custom_name = '" + custom.getCustom_name() +"' ");
		}
		if (!StringUtil.isEmptyString(custom.getCustom_phone())) {
			sBuilder.append(", custom_phone = '" + custom.getCustom_phone() +"' ");
		}
		if (!StringUtil.isEmptyString(custom.getCustom_address())) {
			sBuilder.append(", custom_address = '" + custom.getCustom_address() +"' ");
		}
		if (!StringUtil.isEmptyString(custom.getCustom_name())) {
			sBuilder.append(", custom_name = '" + custom.getCustom_name() +"' ");
		}
		sBuilder.append("where custom_id = " + custom.getCustom_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public Custom getCustom(Custom custom, Connection conn){
		Custom _custom=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM custom WHERE 1=1");
		if (custom.getCustom_id()!=0) {
			sBuilder.append(" and custom_id = " + custom.getCustom_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(Custom.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _custom = (Custom)list.get(0);
		}
		return _custom;
	}

	public List<Custom>  listCustoms(Custom custom, Connection conn){
		List<Custom> customs = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT * FROM custom WHERE 1=1");

		if (custom.getCustom_id()!=0) {
			sBuilder.append(" and custom_id = " + custom.getCustom_id() +" ");
		}
		if (!StringUtil.isEmptyString(custom.getCustom_name())) {
			sBuilder.append(" and custom_name like '%" + custom.getCustom_name() +"%' ");
		}
		sBuilder.append(" order by custom_id asc) t");

		if (custom.getStart() != -1) {
			sBuilder.append(" limit " + custom.getStart() + "," + custom.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Custom.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			customs = new ArrayList<Custom>();
			for (Object object : list) {
				customs.add((Custom)object);
			}
		}
		return customs;
	}

	public int  listCustomsCount(Custom custom, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM custom WHERE 1=1");

		if (custom.getCustom_id()!=0) {
			sBuilder.append(" and custom_id = " + custom.getCustom_id() +" ");
		}
		if (!StringUtil.isEmptyString(custom.getCustom_name())) {
			sBuilder.append(" and custom_name like '%" + custom.getCustom_name() +"%' ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
