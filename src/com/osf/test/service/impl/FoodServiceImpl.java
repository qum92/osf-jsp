package com.osf.test.service.impl;

import java.util.List;

import com.osf.test.dao.FoodDAO;
import com.osf.test.dao.Impl.FoodDAOImpl;
import com.osf.test.service.FoodService;
import com.osf.test.vo.Food;

public class FoodServiceImpl implements FoodService {
	private FoodDAO fdao = new FoodDAOImpl();

	public List<Food> selectFoodList() {
		return fdao.selectFoodList();
	}


	public Food selectFood(Integer foodNum) {
		return fdao.selectFood(foodNum);
	}


	public int insertFood(Food food) {
		return fdao.insertFood(food);
	}


	public int updateFood(Food food) {
		return fdao.updateFood(food);
	}


	public int deleteFood(Integer foodNum) {
		return fdao.deleteFood(foodNum);
	}

}
