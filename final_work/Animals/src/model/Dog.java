package model;

import java.util.Date;
import java.util.List;

public class Dog extends HomeAnimal{
    public Dog(String name, Date birthday, String commands) {
        super(name, birthday, commands);
    }

    public Dog(String id, String name, Date birthday, String commands) {
        super(id, name, birthday, commands);
    }
}
