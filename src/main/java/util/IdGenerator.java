package util;

public class IdGenerator {

    static int compteur = 0;

    public static int getId() {
        return compteur++;
    }
}
