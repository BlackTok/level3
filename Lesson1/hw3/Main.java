package Lesson1.hw3;

public class Main {
    public static void main(String[] args) {
        FruitBox<Apple> fruitBoxApple1 = new FruitBox<>();
        FruitBox<Apple> fruitBoxApple2 = new FruitBox<>();
        FruitBox<Orange> fruitBoxOrange = new FruitBox<>();

        for (int i = 0; i < 5; i++) {
            fruitBoxApple1.addFruit(new Apple(1.0f));
        }

        fruitBoxOrange.addFruit(new Orange(1.5f));
        fruitBoxOrange.addFruit(new Orange(1.8f));
        fruitBoxOrange.addFruit(new Orange(1.7f));

        System.out.println("The first box with apple weighs " + fruitBoxApple1.getWeight());
        System.out.println("The box with orange weighs " + fruitBoxOrange.getWeight());

        System.out.println(fruitBoxApple1.compare(fruitBoxOrange)?"The boxes weigh the same":"The boxes don't weigh the same");

        fruitBoxApple1.replaceFruit(fruitBoxApple2);

        System.out.println("Now first box weighs " + fruitBoxApple1.getWeight());
        System.out.println("And second - " + fruitBoxApple2.getWeight());

    }
}
