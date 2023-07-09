package controller;

import model.Animal;
import model.KindAnimal;
import model.Repository;

import java.util.Date;
import java.util.List;

public class AnimalController implements IController{

    private final Repository repository;
    private AnimalCounter counter = new AnimalCounter();

    public AnimalController(Repository repository) {
        this.repository = repository;
    }

    @Override
    public int getAnimalCounter() {
        return counter.getCount();
    }

    @Override
    public void createAnimal(Animal animal) throws Exception{
        try {
            repository.createAnimal(animal);
            counter.add();
        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public List<Animal> getAllAnimals() throws Exception{
        return repository.getAllAnimals();
    }

    public Animal getAnimalById(String noteId) throws Exception{
        List<Animal> animals = repository.getAllAnimals();
        Animal animalFind = null;
        for (Animal animal : animals) {
            if (animal.getId().equals(noteId)) {
                animalFind = animal;
                break;
            }
        }
        return animalFind;
    }

    @Override
    public void saveAnimal(Animal animal) throws Exception {
        repository.saveAnimal(animal);
    }

    @Override
    public void deleteAnimal(Animal animal) throws Exception {
        if (animal == null){
            throw new Exception("Animal not found");
        }

        repository.deleteAnimal(animal);
    }
}
