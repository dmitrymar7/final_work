package model;

import java.util.List;

public interface IAnimalMapper
{

    public String map(List<Animal> list);
    public List<Animal> map(String text) throws Exception;

}
