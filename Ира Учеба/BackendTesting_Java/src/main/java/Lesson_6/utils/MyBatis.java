package Lesson_6.utils;

import com.github.javafaker.Faker;
import db.model.Categories;
import db.model.Products;



public class MyBatis {
    String resource = "mybatis_config.xml";
    static Faker faker = new Faker();

    public static void main(String[] args) {

        db.dao.CategoriesMapper categoriesMapper = DBUtils.getCategoriesMapper();
        db.dao.ProductsMapper productsMapper = DBUtils.getProductsMapper();

        long numberOfCategories = countNumberofCategories(categoriesMapper);

        deleteProductById(productsMapper);
        Categories newCategory = new Categories();
        newCategory.setTitle(faker.harryPotter().character());
        long categoryNumber = (numberOfCategories + 1);
        newCategory.setId((int) categoryNumber);
        categoriesMapper.insert(String.valueOf(newCategory));

        productsMapper.insert(new Products(faker.space().planet(), 1111, categoryNumber));
        productsMapper.insert(new Products(faker.space().planet(), 2222, categoryNumber));

    }

    private static long countNumberofCategories(db.dao.CategoriesMapper categoriesMapper) {
        long categoriesCount = categoriesMapper.countByExample(new db.model.CategoriesExample());
        System.out.println("Number of categories is: " + categoriesCount);
        return categoriesCount;
    }

    private static void deleteProductById(db.dao.ProductsMapper productsMapper) {
        Products product3200 = productsMapper.selectByPrimaryKey(3200L);
        productsMapper.deleteByPrimaryKey(product3200.getId());
    }
}
