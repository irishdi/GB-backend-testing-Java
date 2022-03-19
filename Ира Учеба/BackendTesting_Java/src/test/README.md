**CreateProductTest**
1) createProductInElectronicCategoryTest - позитивный тест на создание продукта в категории "Электроника".
2) createProductWithNullTitleAndPrice - позитивный тест на создание продукта без названия и цены.
3) createProductWithPresetIdTest - негативный тест на создание продукта с заранее определенным ID.
Поле ID должно быть пустым.
4) createProductWithNewCategoryTest - негативный тест на создание новой категории продуктов
5) createProductWithNoCategory - негативный тест на создание продукта без категории
6) createProductWithNegativePrice - тест на создание продукта с отрицательным значением цены
**DeleteProductTest**
1) deleteProductByIdPositiveTest - позитивный тест на удаление продукта по известному значению ID
2) deleteProductWithoutIdTest - негативный тест на удаление продукта с несуществующим ID
**GetCategoryTest**
1) getCategoryByIdPositiveTest - позитивный тест на получение категории продукта по ID
2) getCategoryByIdNegativeTest - негативный тест на получение категории продукта по несуществующиму ID
**GetProductByIdTest**
1)getProductsByIdTest - позитивный тест на получение продукта по известному ID
2)getProductByNonExistentId - негативный тест на позитивный продукта по несуществующиму ID
**GetProductsTest**
1)getAllProductsTest - получение всех продуктов
**ModifyProductTest**
1)modifyProductTitlePriceCategoryTest - изменение заголовка, цены, категории продукта
2)modifyProductWithUnknownId - изменение продукта с несуществующим ID
3)modifyProductWithNullData - изменение нулевыми данными
4)modifyDeletedProductTest - изменение удаленного продукта