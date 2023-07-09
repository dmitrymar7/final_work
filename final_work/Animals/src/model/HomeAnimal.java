package model;

import java.util.Date;
import java.util.List;

public abstract class HomeAnimal extends Animal{
    public HomeAnimal(String name, Date birthday, String commands) {
        super(name, birthday, commands);
    }

    public HomeAnimal(String id, String name, Date birthday, String commands) {
        super(id, name, birthday, commands);
    }
}
