package model;

import java.util.Date;

public abstract class GeneralMethods {

    public static Animal createAnimal(String name, KindAnimal kindAnimal, Date date, String command) {

        return createAnimal("", name, kindAnimal, date, command);
    }

    public static Animal createAnimal(String id, String name, KindAnimal kindAnimal, Date date, String command) {

        Animal animal = null;
        if (kindAnimal.equals(KindAnimal.CAT)){
            animal = new Cat(id, name, date, command);
        } else if (kindAnimal.equals(KindAnimal.DOG)) {
            animal = new Dog(id, name, date, command);
        } else if (kindAnimal.equals(KindAnimal.DONKEY)) {
            animal = new Donkey(id, name, date, command);
        }else if (kindAnimal.equals(KindAnimal.HAMSTER)) {
            animal = new Hamster(id, name, date, command);
        }else if (kindAnimal.equals(KindAnimal.HORSE)) {
            animal = new Horse(id, name, date, command);
        }

        return animal;
    }


    public static void addCommand(Animal animal, String command) {

    }


}
