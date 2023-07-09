package model;

import java.util.Date;
import java.util.List;

public class Hamster extends HomeAnimal{
    public Hamster(String name, Date birthday, String commands) {
        super(name, birthday, commands);
    }

    public Hamster(String id, String name, Date birthday, String commands) {
        super(id, name, birthday, commands);
    }
}
