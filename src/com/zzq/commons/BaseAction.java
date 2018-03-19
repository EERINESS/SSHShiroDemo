package com.zzq.commons;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zzq.service.EmployeeService;
import com.zzq.service.StaffService;
import com.zzq.service.UserService;


/**
 * 通用Action实现
 * @param <T>
 */

public class BaseAction<T> extends ActionSupport implements ModelDriven<T>  {
	private static final long serialVersionUID = 1L;

	@Resource
	protected UserService userService;
	@Resource
	protected StaffService staffService;
	@Resource
	protected EmployeeService employeeService;
	
	DetachedCriteria detachedCriteria = null;
	//模型对象 
	protected T model;
	public T getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	/**
	 * 在构造方法中动态获得实现类型，通过反射创建模型对象
	 */
	public BaseAction() {
		ParameterizedType genericSuperclass = null;
		if (this.getClass().getGenericSuperclass() instanceof ParameterizedType) {
			genericSuperclass=(ParameterizedType) this
					.getClass().getGenericSuperclass();
		}else {//为当前action创建了代理
			genericSuperclass=(ParameterizedType) this.getClass().getSuperclass().getGenericSuperclass();
		}
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		//获得实体类型
		Class<T> entityClass = (Class<T>) actualTypeArguments[0];
		detachedCriteria = DetachedCriteria.forClass(entityClass);
		try {
			//通过反射创建对象
			model = entityClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
