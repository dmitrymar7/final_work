package controller;

import model.Animal;
import model.KindAnimal;

import java.util.Date;
import java.util.List;

public interface IController {

    public int getAnimalCounter();

    public void createAnimal(Animal animal) throws Exception;

    public List<Animal> getAllAnimals() throws Exception;

    public Animal getAnimalById(String noteId) throws Exception;

    public void saveAnimal(Animal animal) throws Exception;

    public void deleteAnimal(Animal animal) throws Exception;

}
