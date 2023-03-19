package ru.job4j.io.objectstream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectStream {

    public static void main(String[] args) {
        Car car = new Car("Фирма", "Модель", 2000);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/objectstream/serialized.dat"))) {
            out.writeObject(car);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/objectstream/serialized.dat"))) {
            Car deserialized = (Car) in.readObject();
            System.out.println(deserialized.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
