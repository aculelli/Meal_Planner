package com.techelevator.dao;

import com.techelevator.exceptions.MealNotFoundException;
import com.techelevator.exceptions.RecipeNotFoundException;
import com.techelevator.model.Meal;
import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.sql.SQLException;
import java.util.List;

public class JDBCMealDAOTest {

    private static SingleConnectionDataSource dataSource;
    private MealDAO mealDAO;

    @BeforeClass
    public static void setupDataSource() {
        dataSource = new SingleConnectionDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/final_capstone");
        dataSource.setUsername("final_capstone_appuser");
        dataSource.setPassword("finalcapstone");
        dataSource.setAutoCommit(false);
    }
    @AfterClass
    public static void closeDataSource(){
        dataSource.destroy();
    }
    @Before
    public void setUpBeforeTest() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        mealDAO = new JDBCMealDAO(jdbcTemplate);
    }
    @After
    public void rollback() throws SQLException {
        dataSource.getConnection().rollback();
    }

//    @Test
//    public void getMealById() throws MealNotFoundException, RecipeNotFoundException {
//        //add meal in test when functionality is available
//        Meal meal = mealDAO.getMealById(1);
//        Assert.assertEquals(1, meal.getMealId());
//    }
//
//    @Test
//    public void getMealsByDailyPlan() throws MealNotFoundException, RecipeNotFoundException {
//        // Add daily plan with meals in test when that functionality is available
//        // used an id with inserted data for now.
//        List<Meal> mealList = mealDAO.getMealsByDailyPlan(1);
//        Assert.assertTrue(mealList.size()>0);
//    }
//
//    @Test
//    public void deleteMeal() {
//    // add meal whe functionality is available
//    int result = mealDAO.deleteMeal(3);
//    Assert.assertEquals(1,result);
//    }
//
//    @Test
//    public void addMeal() throws RecipeNotFoundException, MealNotFoundException {
//        // add meal whe functionality is available
//        Meal meal = mealDAO.getMealById(1);
//        int result =  mealDAO.addMeal(meal);
//        Assert.assertEquals(1,result);
//    }
//
//    @Test
//    public void updateMeal() throws RecipeNotFoundException, MealNotFoundException {
//        // add meal whe functionality is available
//        Meal meal = mealDAO.getMealById(3);
//        meal.setMealType("LUNCH");
//        meal.setDailyPlanId(1);
//        int result = mealDAO.updateMeal(meal);
//        Assert.assertEquals(1,result);
//    }
}