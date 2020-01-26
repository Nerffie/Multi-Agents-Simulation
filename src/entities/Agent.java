package entities;

public abstract class Agent implements Entity {

    protected int idAgent;
    protected Information[] listeInformations;

    public Information[] getInformation(){
        return listeInformations;
    }
}
