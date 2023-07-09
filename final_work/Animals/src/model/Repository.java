package model;

import java.util.List;

public class Repository {

    private IAnimalMapper mapper;
    private IFileOperation fileOperation;

    public Repository(IAnimalMapper animalMapper, IFileOperation fileOperation) {
        this.mapper = animalMapper;
        this.fileOperation = fileOperation;
    }

    public void createAnimal(Animal animal) throws Exception{

        List<Animal> animals = getAllAnimals();
        int maxId = 0;
        for (Animal currentAnimal : animals){
            int id = Integer.parseInt(currentAnimal.getId());
            if (maxId < id){
                maxId = id;
            }
        }
        int newId = maxId + 1;
        String id = String.format("%d", newId);
        animal.setId(id);


        animals.add(animal);

        String text = mapper.map(animals);
        fileOperation.saveText(text);

    }

    public List<Animal> getAllAnimals() throws Exception{
        String text = fileOperation.readText();
        List<Animal> animals = mapper.map(text);
        return animals;
    }

    public void saveAnimal(Animal animal) throws Exception{

        if (animal == null){
            return;
        }

        List<Animal> list = getAllAnimals();

        for (int i = 0; i < list.size(); i++) {
            Animal currentAnimal = list.get(i);
            if (currentAnimal.getId().equals(animal.getId())){
                list.set(i, animal);
                break;
            }
        }

        String text = mapper.map(list);
        fileOperation.saveText(text);

    }

    public void deleteAnimal(Animal animal) throws Exception{
        List<Animal> list = getAllAnimals();

        int findIndex = -1;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(animal.getId())){
                findIndex = i;
                break;
            }
        }

        if (findIndex > -1){
            list.remove(findIndex);
        }

        String text = mapper.map(list);
        fileOperation.saveText(text);

    }

}
