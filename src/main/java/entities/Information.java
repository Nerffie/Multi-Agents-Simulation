package entities;

/**
 * Classe qui définit une information
 * Cette information peut se trouver directement sur la map ou dans la liste des informations d'un agent
 */
public class Information implements Entity {
    private int idInformation;

    /**
     * Constructeur de la classe Information
     * @param id identifiant unique d'une information
     */
    public Information(int id) {
        this.idInformation = id;
    }

    /**
     * Getter d'une information
     * @return Information
     * @see Information
     */
    public int getIdInformation() {
        return idInformation;
    }
}
