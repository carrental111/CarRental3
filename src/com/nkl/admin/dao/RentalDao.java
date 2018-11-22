package com.nkl.admin.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.admin.domain.Rental;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class RentalDao {

	public int addRental(Rental rental, Connection conn){
		String sql = "INSERT INTO rental(rental_id,apply_date,custom_id,car_id,rental_count,rental_date1,rental_date2,rental_money,rental_flag) values(null,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			rental.getApply_date(),
			rental.getCustom_id(),
			rental.getCar_id(),
			rental.getRental_count(),
			rental.getRental_date1(),
			rental.getRental_date2(),
			rental.getRental_money(),
			rental.getRental_flag()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delRental(String rental_id, Connection conn){
		String sql = "DELETE FROM rental WHERE rental_id=?";

		Object[] params = new Object[] { new Integer(rental_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delRentals(String[] rental_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <rental_ids.length; i++) {
			sBuilder.append("?");
			if (i !=rental_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM rental WHERE rental_id IN(" +sBuilder.toString()+")";

		Object[] params = rental_ids;

		return BaseDao.executeUpdate(sql, params, conn);	}

	public int updateRental(Rental rental, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE rental SET ");
		if (rental.getCustom_id()!=0) {
			sBuilder.append(" ,custom_id =" + rental.getCustom_id() +" ");
		}
		if (rental.getCar_id()!=0) {
			sBuilder.append(" ,car_id =" + rental.getCar_id() +" ");
		}
		if (!StringUtil.isEmptyString(rental.getRental_date1())) {
			sBuilder.append(" ,rental_date1 ='" + rental.getRental_date1() +"' ");
		}
		if (!StringUtil.isEmptyString(rental.getRental_date2())) {
			sBuilder.append(" ,rental_date2 ='" + rental.getRental_date2() +"' ");
		}
		if (rental.getRental_count()!=0) {
			sBuilder.append(" ,rental_count =" + rental.getRental_count() +" ");
		}
		if (rental.getRental_money()!=0) {
			sBuilder.append(" ,rental_money =" + rental.getRental_money() +" ");
		}
		if (rental.getRental_flag()!=0) {
			sBuilder.append(" ,rental_flag =" + rental.getRental_flag() +" ");
		}
		
		sBuilder.append(" where rental_id = " + rental.getRental_id());

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString().replaceFirst(",", ""), params, conn);	
	}
	
	public Rental getRental(Rental rental, Connection conn){
		Rental _rental=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT c.*,c2.custom_name,c2.custom_phone,c3.car_name,c3.car_model FROM rental c ");
		sBuilder.append("  join custom c2 on c.custom_id=c2.custom_id ");
		sBuilder.append("  join car c3 on c.car_id=c3.car_id WHERE 1=1 ");
		if (rental.getRental_id()!=0) {
			sBuilder.append(" and c.rental_id =" + rental.getRental_id() +" ");
		}
		
		List<Object> list = BaseDao.executeQuery(Rental.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _rental = (Rental)list.get(0);
		}
		return _rental;
	}

	public List<Rental>  listRentals(Rental rental, Connection conn){
		List<Rental> rentals = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT c.*,c2.custom_name,c2.custom_phone,c3.car_name,c3.car_model FROM rental c ");
		sBuilder.append("  join custom c2 on c.custom_id=c2.custom_id ");
		sBuilder.append("  join car c3 on c.car_id=c3.car_id WHERE 1=1 ");
		if (rental.getRental_id()!=0) {
			sBuilder.append(" and c.rental_id =" + rental.getRental_id() +" ");
		}
		if (!StringUtil.isEmptyString(rental.getRental_date1())) {
			sBuilder.append(" and c.rental_date1 ='" + rental.getRental_date1() +"' ");
		}
		if (!StringUtil.isEmptyString(rental.getRental_dateMin())) {
			sBuilder.append(" and c.rental_date1 >='" + rental.getRental_dateMin() +"' ");
		}
		if (!StringUtil.isEmptyString(rental.getRental_dateMax())) {
			sBuilder.append(" and c.rental_date1 <='" + rental.getRental_dateMax() +"' ");
		}
		if (rental.getCustom_id()!=0) {
			sBuilder.append(" and c.custom_id =" + rental.getCustom_id() +" ");
		}
		if (!StringUtil.isEmptyString(rental.getCustom_name())) {
			sBuilder.append(" and c2.custom_name like '%" + rental.getCustom_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(rental.getCar_name())) {
			sBuilder.append(" and c3.car_name like '%" + rental.getCar_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(rental.getCar_model())) {
			sBuilder.append(" and c3.car_model like '%" + rental.getCar_model() +"%' ");
		}
		if (rental.getRental_flag()!=0) {
			sBuilder.append(" and rental_flag =" + rental.getRental_flag() +" ");
		}
		sBuilder.append(" order by apply_date desc,rental_id asc) t");

		if (rental.getStart() != -1) {
			sBuilder.append(" limit " + rental.getStart() + "," + rental.getLimit());
		}
		
		List<Object> list = BaseDao.executeQuery(Rental.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			rentals = new ArrayList<Rental>();
			for (Object object : list) {
				rentals.add((Rental)object);
			}
		}
		return rentals;
	}
	
	public int listRentalsCount(Rental rental, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM rental c ");
		sBuilder.append("  join custom c2 on c.custom_id=c2.custom_id ");
		sBuilder.append("  join car c3 on c.car_id=c3.car_id WHERE 1=1 ");
		if (rental.getRental_id()!=0) {
			sBuilder.append(" and c.rental_id =" + rental.getRental_id() +" ");
		}
		if (!StringUtil.isEmptyString(rental.getRental_date1())) {
			sBuilder.append(" and c.rental_date1 ='" + rental.getRental_date1() +"' ");
		}
		if (!StringUtil.isEmptyString(rental.getRental_dateMin())) {
			sBuilder.append(" and c.rental_date1 >='" + rental.getRental_dateMin() +"' ");
		}
		if (!StringUtil.isEmptyString(rental.getRental_dateMax())) {
			sBuilder.append(" and c.rental_date1 <='" + rental.getRental_dateMax() +"' ");
		}
		if (rental.getCustom_id()!=0) {
			sBuilder.append(" and c.custom_id =" + rental.getCustom_id() +" ");
		}
		if (!StringUtil.isEmptyString(rental.getCustom_name())) {
			sBuilder.append(" and c2.custom_name like '%" + rental.getCustom_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(rental.getCar_name())) {
			sBuilder.append(" and c3.car_name like '%" + rental.getCar_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(rental.getCar_model())) {
			sBuilder.append(" and c3.car_model like '%" + rental.getCar_model() +"%' ");
		}
		if (rental.getRental_flag()!=0) {
			sBuilder.append(" and rental_flag =" + rental.getRental_flag() +" ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
