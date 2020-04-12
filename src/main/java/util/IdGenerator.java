package util;

import java.util.UUID;

/**
 * Classe pour générer les identifiants
 */
public class IdGenerator {

    static int compteur = 0;

    // TODO Documentation
    public static int getId() {
        return compteur++;
    }
}
