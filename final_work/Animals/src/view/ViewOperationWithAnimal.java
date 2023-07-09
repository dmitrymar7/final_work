package view;

import controller.IController;
import model.Animal;
import model.HomeAnimal;
import model.PackAnimal;

import java.util.*;

public class ViewOperationWithAnimal extends ViewAnimal{

    private Animal animal;
    private boolean stopOperation = false;
    public ViewOperationWithAnimal(IController controller, IViewAnimal prevView, Animal animal) {
        super(controller, prevView);
        this.animal = animal;
    }

    public ViewOperationWithAnimal(IController controller) {
        this(controller, null, null);
    }

    public ViewOperationWithAnimal(IController controller, Animal animal) {
        this(controller, null, animal);
    }

    public ViewOperationWithAnimal(IController controller, IViewAnimal prevView) {
        this(controller, prevView, null);
    }

    private void setAnimal(Animal animal) {
        this.animal = animal;
    }

    private void findAnimal(){
        String id = prompt("Введите идентификатор питомца: ");
        try {
            animal = controller.getAnimalById(id);
        }catch (Exception e){
            animal = null;
            stopOperation = true;
        }

        if (animal != null){
            System.out.println(animal);
        }else{
            System.out.println("Питомец не найден");
        }

    }

    private void changeInfoAnimal(){

        if (animal == null){
            stopOperation = true;
            return;
        }

        Map<Integer, String> menu = new HashMap<>();
        menu.put(1, "Отобразить данные");
        menu.put(2, "Обучить новым командам");
        menu.put(3, "Убрать из списка");
        menu.put(4, "Узнать класс");
        menu.put(5, "Выход");

        String text = "Выберите действие \n";

        List<Integer> list = new ArrayList<>(menu.keySet());
        Collections.sort(list);
        for (int i : list) {
            text += String.format("%s. %s \n", i, menu.getOrDefault(i, ""));
        }

        String id_quest = prompt(text);

        switch (id_quest){
            case "5":
                stopOperation = true;
                return;
            case "1":
                System.out.println(animal);
                break;
            case "2":
                String command = prompt("Введите новую способность питомца\n");
                if (!command.isEmpty()) {
                    animal.addCommand(command);
                    try {
                        controller.saveAnimal(animal);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                break;
            case "3":
                try {
                    controller.deleteAnimal(animal);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                stopOperation = true;
                return;
            case "4":
                if (animal instanceof HomeAnimal){
                    System.out.println("Домашнее животное");
                } else if (animal instanceof PackAnimal) {
                    System.out.println("Вьючное животное");
                }else {
                    System.out.println("Не удалось определить класс");
                }
                break;
        }

    }

    @Override
    public void startView() {
        findAnimal();

        if (stopOperation){
            return;
        }

        while (!stopOperation){
            changeInfoAnimal();
        }
    }


}
