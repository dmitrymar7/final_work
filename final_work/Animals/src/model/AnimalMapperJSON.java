package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.*;

public class AnimalMapperJSON implements IAnimalMapper {

    @Override
    public String map(List<Animal> list) {
        List<Map> result = new ArrayList<>();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        for (Animal animal : list){
            Map map = new HashMap<>();
            map.put("name", animal.getName());
            map.put("birthday", animal.getBirthday());
            map.put("commands", animal.getCommands());
            map.put("kind", getKindAnimal(animal));
            map.put("id", animal.getId());
            result.add(map);
        }

        return gson.toJson(result);
    }

    @Override
    public List<Animal> map(String text) throws Exception{
        Gson gson = new Gson();
        List<Animal> list = new ArrayList<>();

        List<Map> listGSON = null;

        try {
            listGSON = gson.fromJson(text, ArrayList.class);

            if (listGSON == null){
                return list;
            }

            for (Map map : listGSON){
                String name = (String) map.get("name");
                Date birthday;
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    birthday = format.parse((String) map.getOrDefault("birthday", ""));
                }catch (Exception e){
                    birthday = null;
                }
                String commands = (String) map.getOrDefault("commands", null);
                KindAnimal kindAnimal = (KindAnimal.valueOf((String) map.getOrDefault("kind", null)));
                String id = (String) map.getOrDefault("id", null);

                Animal animal = GeneralMethods.createAnimal(id, name, kindAnimal, birthday, commands);
                list.add(animal);
            }
        }catch (Exception e){
            throw new Exception("Файл некорректный");
        }

        return list;
    }
    
    private KindAnimal getKindAnimal(Animal animal){
        KindAnimal kindAnimal = null;
        if (animal instanceof Cat){
            kindAnimal = KindAnimal.CAT; 
        } else if (animal instanceof Dog) {
            kindAnimal = KindAnimal.DOG;
        } else if (animal instanceof Donkey) {
            kindAnimal = KindAnimal.DONKEY;
        } else if (animal instanceof  Hamster) {
            kindAnimal = KindAnimal.HAMSTER;
        } else if (animal instanceof Horse) {
            kindAnimal = KindAnimal.HORSE;
        }

        return kindAnimal;
    }

}
