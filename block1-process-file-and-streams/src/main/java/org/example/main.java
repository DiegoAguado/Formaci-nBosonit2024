package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class main {
    public static void main(String[] args){
        for (Person p : getPeople()){
            System.out.println(p.toString());
        }
        //System.out.println();
        //filterAgeStream();
    }

    private static List<Person> getPeople(){
        List<Person> listPeople = new ArrayList<>();
            Path path = Paths.get("src/main/java/org/example/people.csv");

        List<String> personas = null;
        try {
            personas = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            personas.forEach(person ->{
                try{
                if(person.contains(":") && !person.split(":")[0].equals("") && person.split(":").length==3){
                    listPeople.add(new Person(person.split(":")[0],
                            person.split(":")[1].equals("")?"unknown":person.split(":")[1],
                            person.split(":")[2].equals("")?0:Integer.parseInt(person.split(":")[2])

                    ));
                }else
                    throw new InvalidLineFormatException("Línea \"" + person +  "\" no válida");
                }catch (InvalidLineFormatException e){
                    System.err.println(e.getMessage());
                }
            });

        return listPeople;
    }

    private static List<Person> filterAge(){

            List<Person> listPeople = getPeople();
            List<Person> filteredList = new ArrayList<>();

            listPeople.forEach(person -> {
                if(person.getAge()<25 && person.getAge()!=0) filteredList.add(person);
            });
            return filteredList;
    }

    private static List<Person> filterName() {
        List<Person> listPeople = getPeople();
        List<Person> filteredList = new ArrayList<>();

        listPeople.forEach(person -> {
            if(!person.getName().startsWith("A") &&  !person.getName().startsWith("Á")) filteredList.add(person);
        });

        return filteredList;
    }

    private static void filterNameStream(){
        List<Person> filteredList = filterName();

        Optional<Person> optional = filteredList.stream()
                        .filter(person -> person.getTown().equals("Madrid"))
                .findFirst();

        System.out.println(optional.isEmpty()?"No hay coincidencias":optional.get());
    }

    private static void filterAgeStream(){
        List<Person> filteredList = filterAge();

        Optional<Person> optional = filteredList.stream().
                filter(person -> person.getTown().equals("Barcelona"))
                .findFirst();

        System.out.println(optional.isEmpty()?"No hay coincidencias":optional.get());
    }
}