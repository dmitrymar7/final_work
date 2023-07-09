package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Animal {
    private String name;
    private Date birthday;
    private String commands;
    private String id = "";

    public Animal(String name, Date birthday, String commands){
        this.name = name;
        this.birthday = birthday;
        this.commands = commands;
    }

    public Animal(String id, String name, Date birthday, String commands){
        this(name, birthday, commands);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCommands() {
        return commands;
    }

    public void setCommands(String commands) {
        this.commands = commands;
    }

    public void addCommand(String command){

        if (commands == null){
            commands = "";
        }

        if (!commands.isEmpty()){
            commands += "; ";
        }
        this.commands += command;
    }

    @Override
    public String toString() {

        String date = "";

        if (birthday != null){

            DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");

            date = dateFormat.format(birthday);
        }

        String res =  String.format("%s. Идентификатор: %s \n    имя: %s \n    дата рожденя: %s \n    умения: %s",
                this.getClass().getSimpleName(), id, name, date, commands);

        res = res.replaceAll("null", "");

        return res;
    }
}
