package model;

import java.util.Date;
import java.util.List;

public class Horse extends PackAnimal{
    public Horse(String name, Date birthday, String commands) {
        super(name, birthday, commands);
    }

    public Horse(String id, String name, Date birthday, String commands) {
        super(id, name, birthday, commands);
    }
}
