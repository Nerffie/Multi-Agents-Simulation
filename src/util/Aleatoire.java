package util;

public class Aleatoire {

    private static Aleatoire single_instance = null;

    // variable of type String
    public String s;

    // private constructor restricted to this class itself
    private Aleatoire() {
        s = "Hello I am a string part of Singleton class";
    }

    // static method to create instance of Singleton class
    public static Aleatoire getInstance() {
        if (single_instance == null)
            single_instance = new Aleatoire();

        return single_instance;

    }

    public double genererRandom(int borneSup) {
        return Math.random() * borneSup;
    }

}
