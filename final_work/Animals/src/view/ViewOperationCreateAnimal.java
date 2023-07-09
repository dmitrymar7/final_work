package view;

import controller.IController;
import model.Animal;
import model.GeneralMethods;
import model.KindAnimal;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

public class ViewOperationCreateAnimal extends ViewAnimal{

    public ViewOperationCreateAnimal(IController controller, IViewAnimal prevView) {
        super(controller, prevView);
    }

    private KindAnimal kindAnimal;
    private String name;
    private Date birthDate;
    private String commands;
    private boolean stopOperation = false;

    @Override
    public void startView() {
        getKind();
        if (stopOperation){
            return;
        }else if (kindAnimal == null){
            System.out.println("Действие неопределено");
            getKind();
        }

        String question;

        getName();
        if (name.isEmpty()){
            System.out.println("Имя не задано");
            question = prompt("Если хотите выйти введите exit: ");
            if (question.equals("exit")){
                return;
            }
            getName();
        }

        getBirthday();

        getCommand();

        Animal animal = GeneralMethods.createAnimal(name, kindAnimal, birthDate, commands);
        try {
            controller.createAnimal(animal);
            System.out.println("Питомец создан");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private void getKind(){
        Map<Integer, String> menu = new HashMap<>();
        menu.put(1, "Кошка");
        menu.put(2, "Собака");
        menu.put(3, "Хомяк");
        menu.put(4, "Лошадь");
        menu.put(5, "Осел");
        menu.put(6, "Выход");

        String text = "Выберите животное, которое хотите завести \n";

        List<Integer> list = new ArrayList<>(menu.keySet());
        Collections.sort(list);
        for (int i : list){
            text += String.format("%s. %s \n", i, menu.getOrDefault(i, ""));
        }

        String id = prompt(text);

        switch (id){
            case "6":
                this.stopOperation = true;
                return;
            case "1":
                kindAnimal = KindAnimal.CAT;
                break;
            case "2":
                kindAnimal = KindAnimal.DOG;
                break;
            case "3":
                kindAnimal = KindAnimal.HAMSTER;
                break;
            case "4":
                kindAnimal = KindAnimal.DONKEY;
                break;
            case "5":
                kindAnimal = KindAnimal.HORSE;
                break;
        }

    }

    private void getName(){
        name = prompt("Выберите имя: ");
    }

    private void getBirthday(){
        String question = prompt("Хотите указать дату рождения? (у - для указания): ");
        if (!question.equals("y")){
            return;
        }

        String date = prompt("Укажите дату в формате dd.MM.yyyy: ");

        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            birthDate = format.parse(date);
        }catch (Exception e){
            System.out.println("Дата указана не корректно");
            getBirthday();
        }

    }

    private void getCommand(){
        String question = prompt("Хотите указать способности питомца? (у - для указания): ");
        if (!question.equals("y")){
            return;
        }

        commands = prompt("Укажите способности питомца: \n");
    }

}
