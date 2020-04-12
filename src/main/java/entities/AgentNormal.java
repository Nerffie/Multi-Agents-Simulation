package entities;

/**
 * Classe agent normal qui hérite de la classe abstraite Agent
 * @see Agent
 */
public class AgentNormal extends Agent {

    /**
     * Constructeur de la classe AgentNormal
     * @param id l'identifiant de l'agent
     * @param x abscisse de l'agent dans la map
     * @param y ordonnée de l'agent dans la map
     * @param tailleMaxInformations la taille maximale des informations qu'il peut collecter
     */
    public AgentNormal(int id,int x,int y,int tailleMaxInformations) {
        super(id,x,y,tailleMaxInformations);
    }
}
