package com.nkl.admin.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.admin.domain.Car;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class CarDao {

	public int addCar(Car car, Connection conn){
		String sql = "INSERT INTO car(car_name,car_model,car_desc) values(?,?,?)";
		Object[] params = new Object[] {
			car.getCar_name(),
			car.getCar_model(),
			car.getCar_desc()
		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delCar(String car_id, Connection conn){
		String sql = "DELETE FROM car WHERE car_id=?";

		Object[] params = new Object[] { new Integer(car_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delCars(String[] car_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <car_ids.length; i++) {
			sBuilder.append("?");
			if (i !=car_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM car WHERE car_id IN(" +sBuilder.toString()+")";

		Object[] params = car_ids;

		return BaseDao.executeUpdate(sql, params, conn);	}

	public int updateCar(Car car, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE car SET ");
		if (!StringUtil.isEmptyString(car.getCar_model())) {
			sBuilder.append(" ,car_model ='" + car.getCar_model() +"' ");
		}
		if (!StringUtil.isEmptyString(car.getCar_desc())) {
			sBuilder.append(" ,car_desc ='" + car.getCar_desc() +"' ");
		}

		sBuilder.append(" where car_id = " + car.getCar_id());

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString().replaceFirst(",", ""), params, conn);	
	}
	
	public Car getCar(Car car, Connection conn){
		Car _car=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM car WHERE 1=1");
		if (car.getCar_id()!=0) {
			sBuilder.append(" and car_id =" + car.getCar_id() +" ");
		}
		
		List<Object> list = BaseDao.executeQuery(Car.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _car = (Car)list.get(0);
		}
		return _car;
	}

	public List<Car>  listCars(Car car, Connection conn){
		List<Car> cars = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT * FROM car WHERE 1=1");
		if (car.getCar_id()!=0) {
			sBuilder.append(" and car_id =" + car.getCar_id() +" ");
		}
		if (!StringUtil.isEmptyString(car.getCar_name())) {
			sBuilder.append(" and car_name like '%" + car.getCar_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(car.getCar_model())) {
			sBuilder.append(" and car_model  like '%" + car.getCar_model() +"%' ");
		}
		sBuilder.append(" order by car_id asc) t");

		if (car.getStart() != -1) {
			sBuilder.append(" limit " + car.getStart() + "," + car.getLimit());
		}
		
		List<Object> list = BaseDao.executeQuery(Car.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			cars = new ArrayList<Car>();
			for (Object object : list) {
				cars.add((Car)object);
			}
		}
		return cars;
	}
	
	public int listCarsCount(Car car, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM car WHERE 1=1");
		if (car.getCar_id()!=0) {
			sBuilder.append(" and car_id =" + car.getCar_id() +" ");
		}
		if (!StringUtil.isEmptyString(car.getCar_name())) {
			sBuilder.append(" and car_name like '%" + car.getCar_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(car.getCar_model())) {
			sBuilder.append(" and car_model  like '%" + car.getCar_model() +"%' ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
