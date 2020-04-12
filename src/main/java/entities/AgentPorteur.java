package entities;

/**
 * Classe agent porteur qui hérite de la classe abstraite Agent
 * Tout agent normal qui acquiert une information devient un agent porteur
 * @see Agent
 */
public class AgentPorteur extends Agent {

    /**
     * Constructeur de la classe AgentPorteur
     * @param id l'identifiant de l'agent
     * @param x abscisse de l'agent dans la map
     * @param y ordonnée de l'agent dans la map
     * @param tailleMaxInformations la taille maximale des informations qu'il peut collecter
     */
    public AgentPorteur(int id,int x,int y,int tailleMaxInformations) {
        super(id,x,y,tailleMaxInformations);
    }
}
