package view;

import controller.IController;
import model.Animal;

import java.util.*;

public class ViewOperationMainMenu extends ViewAnimal{

    public ViewOperationMainMenu(IController controller) {
        super(controller);
        this.prevView = this;
    }

    private void mainMenu(){

        while (true) {
            Map<Integer, String> menu = new HashMap<>();
            menu.put(1, "Завести новое животное");
            menu.put(2, "Отобразить список всех животных");
            menu.put(3, "Найти животное");
            menu.put(4, "Количество операций добавления в текущей сессии");
            menu.put(5, "Выход");

            String text = "Выберите пункт\n";

            List<Integer> list = new ArrayList<>(menu.keySet());
            Collections.sort(list);
            for (int i : list) {
                text += String.format("%s. %s \n", i, menu.getOrDefault(i, ""));
            }

            String id = prompt(text);

            switch (id) {
                case "5":
                    return;
                case "1":
                    ViewOperationCreateAnimal createAnimal = new ViewOperationCreateAnimal(controller, this);
                    createAnimal.startView();
                    break;
                case "2":
                    try {
                        List<Animal> animals = controller.getAllAnimals();
                        for (Animal animal : animals){
                            System.out.println(animal.toString());
                        }
                        System.out.println();
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    ViewOperationWithAnimal operationWithAnimal = new ViewOperationWithAnimal(controller);
                    operationWithAnimal.startView();
                    break;
                case "4":
                    System.out.println(controller.getAnimalCounter());
                    break;
            }
        }

    }

    public void startView(){
        mainMenu();
    }

}
