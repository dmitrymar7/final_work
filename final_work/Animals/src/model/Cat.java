package model;

import java.util.Date;
import java.util.List;

public class Cat extends HomeAnimal{
    public Cat(String name, Date birthday, String commands) {
        super(name, birthday, commands);
    }

    public Cat(String id, String name, Date birthday, String commands) {
        super(id, name, birthday, commands);
    }
}
