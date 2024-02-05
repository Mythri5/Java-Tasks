
abstract class Shape {
    abstract double calcArea();

    abstract double calcPerimeter();
}

class Circle extends Shape {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    double calcArea() {
        return Math.PI * radius * radius;
    }

    double calcPerimeter() {
        return 2 * (Math.PI) * radius;
    }
}

class Rectangle extends Shape {
    private double length;
    private double breadth;

    Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    double calcArea() {
        return length * breadth;
    }

    double calcPerimeter() {
        return 2 * (length + breadth);
    }
}

class Triangle extends Shape {
    private double a;
    private double b;
    private double c;

    Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    double calcArea() {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    double calcPerimeter() {
        return (a + b + c);
    }
}

public class GeometryExample {
    public static void main(String[] args) {
        Circle obj1 = new Circle(14);
        Rectangle obj2 = new Rectangle(50, 20);
        Triangle obj3 = new Triangle(3, 4, 5);

        System.out.println("Circle - Area: " + obj1.calcArea() + ", Perimeter: " + obj1.calcPerimeter());
        System.out.println("Rectangle - Area: " + obj2.calcArea() + ", Perimeter: " + obj2.calcPerimeter());
        System.out.println("Triangle - Area: " + obj3.calcArea() + ", Perimeter: " + obj3.calcPerimeter());
    }
}
