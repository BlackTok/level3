package Lesson1.hw3;

import java.util.ArrayList;
import java.util.List;

public class FruitBox<T extends Fruit> {
    private final List<T> content = new ArrayList<>();

    /**
     * Метод добавляет фрукт определенного типа в коробку
     * @param fruit экземпляр класса, расширяющего класс Fruit
     */
    public void addFruit(T fruit) {
        content.add(fruit);
    }

    /**
     * Возвращает вес коробки
     * @return вес типа float
     */
    public float getWeight() {
        float weight = 0;

        for (T t : content) {
            weight += t.getWeight();
        }

        return weight;
    }

    /**
     * Сравнивает вес двух коробок с фруктами
     * @param box экземпляр класса, расширяющего класс Box
     * @return true, если вес коробок равен
     */
    public <B extends Fruit> boolean compare(FruitBox<B> box) {
        return this.getWeight() == box.getWeight();
    }

    /**
     * очищает коробку
     */
    private void clearBox() {
        this.content.clear();
    }

    /**
     * Пересыпает фрукты одинакового типа из данной коробки в коробку, переданную в качестве параметра
     * @param newBox экземпляр класса Box
     */
    public void replaceFruit(FruitBox<T> newBox) {
        for (T fruit : content) {
            newBox.addFruit(fruit);
        }

        clearBox();
    }
}
