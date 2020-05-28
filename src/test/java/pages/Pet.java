package pages;

public abstract class Pet {
    // fields
    //set privat access modifier using ENCAPSULATION
    protected String name;

    public Pet(){
        name = "nameless";
    }

    public void setName(String value){
        name = value;
    }

    public String getName(){
        return name;
    }

    public void walk() {
        System.out.println(name + " is walking!");
    }
    public void eat(String what) {
        System.out.println(name + " is eating " + what);
    }
    public abstract void speak();
}
