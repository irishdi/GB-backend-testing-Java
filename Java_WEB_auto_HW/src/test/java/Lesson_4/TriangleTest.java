package Lesson_4;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleTest {

  private static Logger logger = LoggerFactory.getLogger(TriangleTest.class);

    @BeforeAll
    static void BeforeAll(){
        logger.info("Начало теста");
    }

    @Test
    @DisplayName("Стороны и площадь треугольника целые числа")
    void areaIsRoundNumber(){
      assertEquals(6, Triangle.AreaTriangle(3, 4, 5));
    }

    @Test
    @DisplayName("Площадь треугольника нецелое число")
    void areaIsDoubleNumber(){
        assertEquals( 3.799671038392666, Triangle.AreaTriangle(2,4,5));
    }

    @ParameterizedTest
    @DisplayName("Одна из сторон треугольника нецелое число")
    @CsvSource({"4, 6, 7.5, 11.952869686815799", "4.5, 6, 7, 13.377774244993073", "4, 6.5, 7, 12.792667577561765"})
    void areaIsDoubleNumber2(double a, double b, double c, double result){
        assertEquals(result, Triangle.AreaTriangle(a, b, c));
    }


    @Test
    @DisplayName("Стороны отрицательные числа или ноль")
    void triangleSideNegativeOrNull(){
    assertAll(
            () -> Triangle.AreaTriangle(0, 2, 4),
            () -> Triangle.AreaTriangle(2, 0, 4),
            () -> Triangle.AreaTriangle(2,4, 0),
            () -> Triangle.AreaTriangle(-1,4, 2),
            () -> Triangle.AreaTriangle(2,-1, 4),
            () -> Triangle.AreaTriangle(2,4, -1)
    );
    }
    @Test
    @DisplayName("Стороны не образуют треугольник")
    void isNotTriangle(){
        assertAll(
                ()-> Triangle.AreaTriangle(2, 1, 1),
                ()-> Triangle.AreaTriangle(8, 1, 7),
                () -> Triangle.AreaTriangle(0.5, 0.5, 1)
        );
    }

    @AfterAll
    static void AfterAll(){
        logger.info("Тест завершен");
    }
}
