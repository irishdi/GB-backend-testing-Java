package Lesson_4;

public class Triangle {


        public static double AreaTriangle (double a, double b, double c){
            if (a<=0 || b <=0 || c <=0) {
                System.out.println("Сторона равна нулю или отрицательное число");
            }
            double p = (a + b + c)/2;
            double area = Math.sqrt(p*(p-a)*(p-b)*(p-c));

            if (area <=0){
                System.out.println("Это не треугольник");
            }
            return area;
        }

}
