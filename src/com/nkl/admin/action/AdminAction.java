package com.nkl.admin.action;

import java.util.List;

import com.nkl.admin.domain.Car;
import com.nkl.admin.domain.Custom;
import com.nkl.admin.domain.Rental;
import com.nkl.admin.domain.User;
import com.nkl.admin.manager.AdminManager;
import com.nkl.common.action.BaseAction;
import com.nkl.common.util.Param;

public class AdminAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	AdminManager adminManager = new AdminManager();
	
	//抓取页面参数
	User paramsUser;
	Rental paramsRental;
	Car paramsCar;
	Custom paramsCustom;
	
	String tip;
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveAdmin(){
		try {
			//验证学生会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人信息
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUser_id(paramsUser.getUser_id());
			admin = adminManager.queryUser(admin);
			Param.setSession("admin", admin);
			
			setSuccessTip("编辑成功", "modifyInfo.jsp");
			
		} catch (Exception e) {
			setErrorTip("编辑异常", "modifyInfo.jsp");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: saveAdminPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveAdminPass(){
		try {
			//验证学生会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人密码
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = (User)Param.getSession("admin");
			if (admin!=null) {
				admin.setUser_pass(paramsUser.getUser_pass());
				Param.setSession("admin", admin);
			}
			
			setSuccessTip("修改成功", "modifyPwd.jsp");
		} catch (Exception e) {
			setErrorTip("修改异常", "modifyPwd.jsp");
		}
		
		return "infoTip";
	}
	
	
	/**
	 * @Title: listUsers
	 * @Description: 查询用户
	 * @return String
	 */
	public String listUsers(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			//设置分页信息
			setPagination(paramsUser);
			//总的条数
			int[] sum={0};
			//查询用户列表
			paramsUser.setUser_type(1);
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "main.jsp");
			return "infoTip";
		}
		
		return "userShow";
	}
	
	/**
	 * @Title: addUserShow
	 * @Description: 显示添加用户页面
	 * @return String
	 */
	public String addUserShow(){
		return "userEdit";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @return String
	 */
	public String addUser(){
		try {
			 //添加用户
			adminManager.addUser(paramsUser);
			
			setSuccessTip("添加成功", "Admin_listUsers.action");
		} catch (Exception e) {
			setErrorTip("添加用户异常", "Admin_listUsers.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editUser
	 * @Description: 编辑用户
	 * @return String
	 */
	public String editUser(){
		try {
			 //得到用户
			User user = adminManager.queryUser(paramsUser);
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "Admin_listUsers.action");
			return "infoTip";
		}
		
		return "userEdit";
	}
	
	/**
	 * @Title: saveUser
	 * @Description: 保存编辑用户
	 * @return String
	 */
	public String saveUser(){
		try {
			 //保存编辑用户
			adminManager.updateUser(paramsUser);
			
			setSuccessTip("编辑成功", "Admin_listUsers.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			
			return "userEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户
	 * @return String
	 */
	public String delUsers(){
		try {
			 //删除用户
			adminManager.delUsers(paramsUser);
			
			setSuccessTip("删除用户成功", "Admin_listUsers.action");
		} catch (Exception e) {
			setErrorTip("删除用户异常", "Admin_listUsers.action");
		}
		
		return "infoTip";
	}
	
	
	/**
	 * @Title: listCars
	 * @Description: 查询汽车
	 * @return String
	 */
	public String listCars(){
		try {
			if (paramsCar==null) {
				paramsCar = new Car();
			}
			//设置分页信息
			setPagination(paramsCar);
			//总的条数
			int[] sum={0};
			//查询汽车列表
			List<Car> cars = adminManager.listCars(paramsCar,sum); 
			
			Param.setAttribute("cars", cars);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询汽车异常", "main.jsp");
			return "infoTip";
		}
		
		return "carShow";
	}
	
	/**
	 * @Title: addCarShow
	 * @Description: 显示添加汽车页面
	 * @return String
	 */
	public String addCarShow(){
		return "carEdit";
	}
	
	/**
	 * @Title: addCar
	 * @Description: 添加汽车
	 * @return String
	 */
	public String addCar(){
		try {
			 //添加汽车
			adminManager.addCar(paramsCar);
			
			setSuccessTip("添加成功", "Admin_listCars.action");
		} catch (Exception e) {
			setErrorTip("添加汽车异常", "Admin_listCars.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editCar
	 * @Description: 编辑汽车
	 * @return String
	 */
	public String editCar(){
		try {
			 //得到汽车
			Car car = adminManager.queryCar(paramsCar);
			Param.setAttribute("car", car);
			
		} catch (Exception e) {
			setErrorTip("查询汽车异常", "Admin_listCars.action");
			return "infoTip";
		}
		
		return "carEdit";
	}
	
	/**
	 * @Title: saveCar
	 * @Description: 保存编辑汽车
	 * @return String
	 */
	public String saveCar(){
		try {
			 //保存编辑汽车
			adminManager.updateCar(paramsCar);
			
			setSuccessTip("编辑成功", "Admin_listCars.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("car", paramsCar);
			
			return "carEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delCars
	 * @Description: 删除汽车
	 * @return String
	 */
	public String delCars(){
		try {
			 //删除汽车
			adminManager.delCars(paramsCar);
			
			setSuccessTip("删除汽车成功", "Admin_listCars.action");
		} catch (Exception e) {
			setErrorTip("删除汽车异常", "Admin_listCars.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listRentals
	 * @Description: 查询汽车租赁
	 * @return String
	 */
	public String listRentals(){
		try {
			if (paramsRental==null) {
				paramsRental = new Rental();
			}
			//设置分页信息
			setPagination(paramsRental);
			//总的条数
			int[] sum={0};
			//查询汽车租赁列表
			List<Rental> rentals = adminManager.listRentals(paramsRental,sum); 
			
			Param.setAttribute("rentals", rentals);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("查询汽车租赁异常", "main.jsp");
			return "infoTip";
		}
		
		return "rentalShow";
	}
	
	/**
	 * @Title: addRentalShow
	 * @Description: 显示添加汽车租赁页面
	 * @return String
	 */
	public String addRentalShow(){
		//查询客户
		Custom custom = new Custom();
		custom.setStart(-1);
		List<Custom> customs = adminManager.listCustoms(custom, null);
		Param.setAttribute("customs", customs);
		
		//查询汽车
		Car car = new Car();
		car.setStart(-1);
		List<Car> cars = adminManager.listCars(car, null);
		Param.setAttribute("cars", cars);
		
		return "rentalEdit";
	}
	
	/**
	 * @Title: addRental
	 * @Description: 添加汽车租赁
	 * @return String
	 */
	public String addRental(){
		try {
			 //添加汽车租赁
			adminManager.addRental(paramsRental);
			
			setSuccessTip("添加成功", "Admin_listRentals.action");
		} catch (Exception e) {
			setErrorTip("添加汽车租赁异常", "Admin_listRentals.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editRental
	 * @Description: 编辑汽车租赁
	 * @return String
	 */
	public String editRental(){
		try {
			 //得到汽车租赁
			Rental rental = adminManager.queryRental(paramsRental);
			Param.setAttribute("rental", rental);
			
			//查询客户
			Custom custom = new Custom();
			custom.setStart(-1);
			List<Custom> customs = adminManager.listCustoms(custom, null);
			Param.setAttribute("customs", customs);
			
			//查询汽车
			Car car = new Car();
			car.setStart(-1);
			List<Car> cars = adminManager.listCars(car, null);
			Param.setAttribute("cars", cars);
		} catch (Exception e) {
			setErrorTip("查询汽车租赁异常", "Admin_listRentals.action");
			return "infoTip";
		}
		
		return "rentalEdit";
	}
	
	/**
	 * @Title: saveRental
	 * @Description: 保存编辑汽车租赁
	 * @return String
	 */
	public String saveRental(){
		try {
			 //保存编辑汽车租赁
			adminManager.updateRental(paramsRental);
			
			setSuccessTip("编辑成功", "Admin_listRentals.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("rental", paramsRental);
			
			//查询客户
			Custom custom = new Custom();
			custom.setStart(-1);
			List<Custom> customs = adminManager.listCustoms(custom, null);
			Param.setAttribute("customs", customs);
			
			//查询汽车
			Car car = new Car();
			car.setStart(-1);
			List<Car> cars = adminManager.listCars(car, null);
			Param.setAttribute("cars", cars);
			
			return "rentalEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: extendRental
	 * @Description: 续租汽车
	 * @return String
	 */
	public String extendRental(){
		try {
			 //得到汽车租赁
			Rental rental = adminManager.queryRental(paramsRental);
			Param.setAttribute("rental", rental);
			
		} catch (Exception e) {
			setErrorTip("查询汽车租赁异常", "Admin_listRentals.action");
			return "infoTip";
		}
		
		return "rentalExtend";
	}
	
	/**
	 * @Title: saveExtendRental
	 * @Description: 保存汽车续租
	 * @return String
	 */
	public String saveExtendRental(){
		try {
			 //保存编辑汽车租赁
			paramsRental.setRental_flag(2);
			adminManager.updateRental(paramsRental);
			
			setSuccessTip("续租成功", "Admin_listRentals.action");
		} catch (Exception e) {
			tip="续租失败";
			Param.setAttribute("rental", paramsRental);
			return "rentalExtend";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: returnRental
	 * @Description: 汽车归还
	 * @return String
	 */
	public String returnRental(){
		try {
			 //汽车归还
			paramsRental.setRental_flag(3);
			adminManager.updateRental(paramsRental);
			
			setSuccessTip("归还成功", "Admin_listRentals.action");
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("归还失败", "Admin_listRentals.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delRentals
	 * @Description: 删除汽车租赁
	 * @return String
	 */
	public String delRentals(){
		try {
			 //删除汽车租赁
			adminManager.delRentals(paramsRental);
			
			setSuccessTip("删除汽车租赁成功", "Admin_listRentals.action");
		} catch (Exception e) {
			setErrorTip("删除汽车租赁异常", "Admin_listRentals.action");
		}
		
		return "infoTip";
	}
	
	
	/**
	 * @Title: listCustoms
	 * @Description: 查询客户
	 * @return String
	 */
	public String listCustoms(){
		try {
			if (paramsCustom==null) {
				paramsCustom = new Custom();
			}
			//设置分页信息
			setPagination(paramsCustom);
			//总的条数
			int[] sum={0};
			//查询客户列表
			List<Custom> customs = adminManager.listCustoms(paramsCustom,sum); 
			
			Param.setAttribute("customs", customs);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询客户异常", "main.jsp");
			return "infoTip";
		}
		
		return "customShow";
	}
	
	/**
	 * @Title: addCustomShow
	 * @Description: 显示添加客户页面
	 * @return String
	 */
	public String addCustomShow(){
		return "customEdit";
	}
	
	/**
	 * @Title: addCustom
	 * @Description: 添加客户
	 * @return String
	 */
	public String addCustom(){
		try {
			 //添加客户
			adminManager.addCustom(paramsCustom);
			
			setSuccessTip("添加成功", "Admin_listCustoms.action");
		} catch (Exception e) {
			setErrorTip("添加客户异常", "Admin_listCustoms.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editCustom
	 * @Description: 编辑客户
	 * @return String
	 */
	public String editCustom(){
		try {
			 //得到客户
			Custom custom = adminManager.queryCustom(paramsCustom);
			Param.setAttribute("custom", custom);
			
		} catch (Exception e) {
			setErrorTip("查询客户异常", "Admin_listCustoms.action");
			return "infoTip";
		}
		
		return "customEdit";
	}
	
	/**
	 * @Title: saveCustom
	 * @Description: 保存编辑客户
	 * @return String
	 */
	public String saveCustom(){
		try {
			 //保存编辑客户
			adminManager.updateCustom(paramsCustom);
			
			setSuccessTip("编辑成功", "Admin_listCustoms.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("custom", paramsCustom);
			
			return "customEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delCustoms
	 * @Description: 删除客户
	 * @return String
	 */
	public String delCustoms(){
		try {
			 //删除客户
			adminManager.delCustoms(paramsCustom);
			
			setSuccessTip("删除客户成功", "Admin_listCustoms.action");
		} catch (Exception e) {
			setErrorTip("删除客户异常", "Admin_listCustoms.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: validateAdmin
	 * @Description: admin登录验证
	 * @return boolean
	 */
	private boolean validateAdmin(){
		User admin = (User)Param.getSession("admin");
		if (admin!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	private void setErrorTip(String tip,String url){
		Param.setAttribute("tipType", "error");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}
	
	private void setSuccessTip(String tip,String url){
		Param.setAttribute("tipType", "success");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public User getParamsUser() {
		return paramsUser;
	}

	public void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
	}

	public Rental getParamsRental() {
		return paramsRental;
	}

	public void setParamsRental(Rental paramsRental) {
		this.paramsRental = paramsRental;
	}

	public Car getParamsCar() {
		return paramsCar;
	}

	public void setParamsCar(Car paramsCar) {
		this.paramsCar = paramsCar;
	}

	public Custom getParamsCustom() {
		return paramsCustom;
	}

	public void setParamsCustom(Custom paramsCustom) {
		this.paramsCustom = paramsCustom;
	}
	
}
