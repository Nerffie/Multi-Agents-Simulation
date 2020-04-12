package util;

/**
 * Classe qui gère les nombres aléatoires
 * Elle peut être utilisée pour les déplacements des agents
 * ainsi que pour le nombre d'agent et d'informations dans la map
 */
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

    /**
     * Genere un nombre aléatoire
     * @param borneSup la valeur maximal que peut avoir le nombre aléatoire généré
     * @return un réel
     */
    public double genererRandom(int borneSup) {
        return Math.random() * borneSup;
    }

}
