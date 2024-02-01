package library;

public abstract class Person {
    private Identity whoYouAre;
    private String name;


    public Person(Identity whoYouAre, String name) {
        this.whoYouAre = whoYouAre;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Identity getWhoYouAre() {
        return whoYouAre;
    }

}
