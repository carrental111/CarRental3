package com.nkl.admin.manager;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.nkl.admin.dao.CarDao;
import com.nkl.admin.dao.CustomDao;
import com.nkl.admin.dao.RentalDao;
import com.nkl.admin.dao.UserDao;
import com.nkl.admin.domain.Car;
import com.nkl.admin.domain.Custom;
import com.nkl.admin.domain.Rental;
import com.nkl.admin.domain.User;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.StringUtil;

public class AdminManager {

	RentalDao rentalDao = new RentalDao();
	CarDao carDao = new CarDao();
	UserDao userDao = new UserDao();
	CustomDao customDao = new CustomDao();

	/**
	 * @Title: listUsers
	 * @Description: 用户查询
	 * @param user
	 * @return List<User>
	 */
	public List<User> listUsers(User user, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = userDao.listUsersCount(user, conn);
		}
		List<User> users = userDao.listUsers(user, conn);

		BaseDao.closeDB(null, null, conn);
		return users;
	}

	/**
	 * @Title: queryUser
	 * @Description: 用户查询
	 * @param user
	 * @return User
	 */
	public User queryUser(User user) {
		Connection conn = BaseDao.getConnection();
		User _user = userDao.getUser(user, conn);
		BaseDao.closeDB(null, null, conn);
		return _user;
	}

	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public void addUser(User user) {
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		user.setUser_type(1);
		user.setUser_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		userDao.addUser(user, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void updateUser(User user) {
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.updateUser(user, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @param user
	 * @return void
	 */
	public void delUsers(User user) {
		Connection conn = BaseDao.getConnection();
		userDao.delUsers(user.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: listCars
	 * @Description: 汽车信息查询
	 * @param car
	 * @return List<Car>
	 */
	public List<Car> listCars(Car car, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = carDao.listCarsCount(car, conn);
		}
		List<Car> cars = carDao.listCars(car, conn);

		BaseDao.closeDB(null, null, conn);
		return cars;
	}

	/**
	 * @Title: queryCar
	 * @Description: 汽车信息查询
	 * @param car
	 * @return Car
	 */
	public Car queryCar(Car car) {
		Connection conn = BaseDao.getConnection();
		Car _car = carDao.getCar(car, conn);
		BaseDao.closeDB(null, null, conn);
		return _car;
	}

	/**
	 * @Title: addCar
	 * @Description: 添加汽车信息
	 * @param car
	 * @return void
	 */
	public void addCar(Car car) {
		Connection conn = BaseDao.getConnection();
		carDao.addCar(car, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateCar
	 * @Description: 更新汽车信息信息
	 * @param car
	 * @return void
	 */
	public void updateCar(Car car) {
		Connection conn = BaseDao.getConnection();
		carDao.updateCar(car, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delCars
	 * @Description: 删除汽车信息信息
	 * @param car
	 * @return void
	 */
	public void delCars(Car car) {
		Connection conn = BaseDao.getConnection();
		carDao.delCars(car.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	
	/**
	 * @Title: listRentals
	 * @Description: 汽车租赁查询
	 * @param rental
	 * @return List<Rental>
	 */
	public List<Rental> listRentals(Rental rental, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = rentalDao.listRentalsCount(rental, conn);
		}
		List<Rental> rentals = rentalDao.listRentals(rental, conn);

		BaseDao.closeDB(null, null, conn);
		return rentals;
	}

	/**
	 * @Title: queryRental
	 * @Description: 汽车租赁查询
	 * @param rental
	 * @return Rental
	 */
	public Rental queryRental(Rental rental) {
		Connection conn = BaseDao.getConnection();
		Rental _rental = rentalDao.getRental(rental, conn);
		BaseDao.closeDB(null, null, conn);
		return _rental;
	}

	/**
	 * @Title: addRental
	 * @Description: 添加汽车租赁
	 * @param rental
	 * @return void
	 */
	public void addRental(Rental rental) {
		Connection conn = BaseDao.getConnection();
		rental.setRental_flag(1);//
		rental.setApply_date(DateUtil.getCurDate());
		rentalDao.addRental(rental, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateRental
	 * @Description: 更新汽车租赁信息
	 * @param rental
	 * @return void
	 */
	public void updateRental(Rental rental) {
		Connection conn = BaseDao.getConnection();
		rentalDao.updateRental(rental, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delRentals
	 * @Description: 删除汽车租赁信息
	 * @param rental
	 * @return void
	 */
	public void delRentals(Rental rental) {
		Connection conn = BaseDao.getConnection();
		rentalDao.delRentals(rental.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listCustoms
	 * @Description: 客户查询
	 * @param custom
	 * @return List<Custom>
	 */
	public List<Custom> listCustoms(Custom custom, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = customDao.listCustomsCount(custom, conn);
		}
		List<Custom> customs = customDao.listCustoms(custom, conn);

		BaseDao.closeDB(null, null, conn);
		return customs;
	}

	/**
	 * @Title: queryCustom
	 * @Description: 客户查询
	 * @param custom
	 * @return Custom
	 */
	public Custom queryCustom(Custom custom) {
		Connection conn = BaseDao.getConnection();
		Custom _custom = customDao.getCustom(custom, conn);
		BaseDao.closeDB(null, null, conn);
		return _custom;
	}

	/**
	 * @Title: addCustom
	 * @Description: 添加客户
	 * @param custom
	 * @return void
	 */
	public void addCustom(Custom custom) {
		Connection conn = BaseDao.getConnection();
		custom.setCustom_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		customDao.addCustom(custom, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateCustom
	 * @Description: 更新客户信息
	 * @param custom
	 * @return void
	 */
	public void updateCustom(Custom custom) {
		Connection conn = BaseDao.getConnection();
		customDao.updateCustom(custom, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delCustoms
	 * @Description: 删除客户信息
	 * @param custom
	 * @return void
	 */
	public void delCustoms(Custom custom) {
		Connection conn = BaseDao.getConnection();
		customDao.delCustoms(custom.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
}
