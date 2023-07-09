package model;

import java.util.Date;
import java.util.List;

public abstract class PackAnimal extends Animal{
    public PackAnimal(String name, Date birthday, String commands) {
        super(name, birthday, commands);
    }

    public PackAnimal(String id, String name, Date birthday, String commands) {
        super(id, name, birthday, commands);
    }
}
