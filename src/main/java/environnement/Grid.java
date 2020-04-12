package environnement;

import entities.Agent;
import entities.AgentNormal;
import entities.Entity;
import entities.Information;
import util.Aleatoire;
import util.IdGenerator;
import util.Tour;

/**
 * Classe qui définit l'environnement dans le quel vas se passer le jeu
 * Il s'agit d'une matrice carré qui contient des informations , des agents et des cellules vides
 */
public class Grid {
    private Entity[][] map;
    private int taille;
    public Agent[] listeAgents;
    private Information[] listeInformations;


    /**
     * Constructeur de la classe Grid
     * @param size définit la taille de la matrice carré
     */
    public Grid(int size) {
        taille = size;
        map = new Entity[taille][taille];
    }

    /**
     * Methode qui donne la liste des agents qui se trouvent sur la map
     * @return liste d'Agents
     * @see Agent
     */
    public Agent[] getListeAgents() {
        return listeAgents;
    }

    /**
     * Methode qui donne la liste des informations qui se trouvent sur la map
     * @return liste d'Informations
     * @see Information
     */
    public Information[] getListeInformations() {
        return listeInformations;
    }

    /**
     * Initialise la map en mettant dedans autant d'informations et d'agents que dans les paramètres
     * @param nombreAgents nombre d'Agents à mettre dans la map au départ
     * @param nombreInformations nombre d'Informations à mettre dans la map au départ
     */
    public void initialiserGrid(int nombreAgents, int nombreInformations) {
        listeAgents = new Agent[nombreAgents];
        listeInformations = new Information[nombreInformations];
        int compteur = 0;
        int x, y;
        while (compteur != nombreAgents) {
            x = (int) Aleatoire.getInstance().genererRandom(taille);
            y = (int) Aleatoire.getInstance().genererRandom(taille);
            if (map[x][y] == null) {
                map[x][y] = new AgentNormal(IdGenerator.getId(),x,y,nombreInformations);
                listeAgents[compteur++] = (AgentNormal)map[x][y];
            }
        }
        compteur=0;
        while (compteur != nombreInformations) {
            x = (int) Aleatoire.getInstance().genererRandom(taille);
            y = (int) Aleatoire.getInstance().genererRandom(taille);
            if (map[x][y] == null) {
                map[x][y] = new Information(IdGenerator.getId());
                listeInformations[compteur++] = (Information)map[x][y];
            }
        }
    }

    /**
     * Affiche la map et son contenu
     * A : Agent Normal
     * P : Agent Porteur
     * I : Information
     * - : Cellule vide
     */
    public void afficherGrid() {
        for (int x = 0; x < taille; x++) {
            for (int y = 0; y < taille; y++) {
                if (map[x][y] != null) {
                    if (map[x][y].getClass().getName().equals("entities.AgentNormal")) {
                        System.out.print("A");
                    } else if (map[x][y].getClass().getName().equals("entities.AgentPorteur")) {
                        System.out.print("P");
                    } else {
                        System.out.print("I");
                    }
                } else {
                    System.out.print("-");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public String buildGridString() {
        StringBuilder sb = new StringBuilder();
        if (Tour.getTour()==0){
            sb.append("Initial Grid : \n");
        }else{
            sb.append("Tour n° "+String.valueOf(Tour.getTour())+"-"+String.valueOf(Tour.getMiniTour())+" :\n");
        }

        for (int x = 0; x < taille; x++) {
            for (int y = 0; y < taille; y++) {
                if (map[x][y] != null) {
                    if (map[x][y].getClass().getName().equals("entities.AgentNormal")) {
                        sb.append("A");
                    } else if (map[x][y].getClass().getName().equals("entities.AgentPorteur")) {
                        sb.append("P");
                    } else {
                        sb.append("I");
                    }
                } else {
                    sb.append("-");
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Getter d'une cellule cellule
     * @param x abscisse
     * @param y ordonnée
     * @return
     */
    public Entity getCell(int x,int y){
        return map[x][y];
    }

    /**
     * Setter d'une cellule
     * @param x abscisse
     * @param y ordonnée
     * @param e un element de l'interface entity qui peut être Agent ou Information
     */
    public void setCell(int x,int y,Entity e){
        map[x][y] = e;
    }

    /**
     * Supprime une cellule de la map
     * @param x abscisse
     * @param y ordonnée
     */
    public void removeCell(int x,int y){
        map[x][y]=null;
    }
}
