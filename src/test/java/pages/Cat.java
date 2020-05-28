package pages;

import org.apache.logging.log4j.core.util.JsonUtils;

public class Cat extends Pet {
    //fields
    // set private access-modifier using ENCAPSULATION
//    private String name;
//
//    public Cat(){
//        name = "nameless one";
//    }
//    // static POLYMORPHISM (same class, different argument)
    public Cat(String value){
        name = value;
    }
//
//    public void setName(String value){
//        name = value;
//    }
//
//    public  String getName(){
//        return name;
//    }
//
//    // methods
//    public void walk() {
//        System.out.println(name+ " is walking!");
//    }
//    public void eat(String what) {
//        System.out.println(name + " is eating " + what);
//    }
     public void speak(){
         System.out.println(name + " is meowing ");
     }


}
