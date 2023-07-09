package model;

import java.util.Date;
import java.util.List;

public class Donkey extends PackAnimal{

    public Donkey(String name, Date birthday, String commands) {
        super(name, birthday, commands);
    }

    public Donkey(String id, String name, Date birthday, String commands) {
        super(id, name, birthday, commands);
    }

}
