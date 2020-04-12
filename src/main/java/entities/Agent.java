package entities;

import ch.qos.logback.core.net.SyslogOutputStream;
import environnement.Grid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Aleatoire;
import util.IdGenerator;
import util.Tour;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe abstraite Agent qui implémente l'interface entity
 * @see entities.Entity
 */
public abstract class Agent implements Entity {

    protected int idAgent;
    protected ArrayList<Information> listeInformationsConnues;
    protected int x;
    protected int y;
    private static final Logger loggerActivity = LoggerFactory.getLogger("activity");
    public int getIdAgent() {
        return idAgent;
    }

    /**
     * Getter de la liste des informations connues par l'agent
     * @return Une ArrayList des informations
     * @see ArrayList
     */
    public ArrayList<Information> getInformationConnue() {
        return listeInformationsConnues;
    }


    /**
     * Setter de la liste des informations connues par l'agent
     */
    public void setListeInformationsConnues(ArrayList<Information> liste){
        this.listeInformationsConnues = new ArrayList<Information>(liste);
    }

    /**
     * Constructeur de la classe Agent
     * @param id l'identifiant de l'agent
     * @param x abscisse du joueur dans la matrice
     * @param y ordonnée du joueur dans la matrice
     * @param tailleMaxInformations Le nombre maximum d'information que peut avoir un agent
     */
    public Agent(int id, int x, int y, int tailleMaxInformations) {
        this.idAgent = id;
        this.x = x;
        this.y = y;
        listeInformationsConnues = new ArrayList<Information>(tailleMaxInformations);
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }





    /**
     * Fait bouger un agent dans la map dans un sens aléatoire
     * @param grid la matrice du jeu
     * @see Aleatoire
     */
    public void move(Grid grid) {
        boolean moved = false;
        int iterations = 0;
        int draw;
        while (!moved || iterations<25) {
            draw = (int) Aleatoire.getInstance().genererRandom(9);
            iterations++;
            switch (draw) {
                case 0:
                    moved = tryMove(grid, -1, -1);
                    break;
                case 1:
                    moved = tryMove(grid, 0, -1);
                    break;
                case 2:
                    moved = tryMove(grid, 1, -1);
                    break;
                case 3:
                    moved = tryMove(grid, -1, 0);
                    break;
                case 4:
                    moved = tryMove(grid, 0, 0);
                    break;
                case 5:
                    moved = tryMove(grid, 1, 0);
                    break;
                case 6:
                    moved = tryMove(grid, -1, 1);
                    break;
                case 7:
                    moved = tryMove(grid, 0, 1);
                    break;
                case 8:
                    moved = tryMove(grid, 1, 1);
                    break;
            }
        }
    }

    /**
     * Valide un mouvement dans la map
     * @param grid la matrice du jeu
     * @return un booleen            
     */
    private boolean tryMove(Grid grid, int deltaX, int deltaY) {
        try {
            if (grid.getCell(x + deltaX, y + deltaY) == null) {
                grid.setCell(x + deltaX, y + deltaY, this);
                grid.removeCell(x, y);
                x+=deltaX;
                y+=deltaY;
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            //e.printStackTrace();
            
            return false;
        }
        return false;
    }

    /**
     * Gerer l'interaction d'un Agent avec son entourage
     * @param grid la matrice du jeu
     */
    public void interactWithSurroundings(Grid grid) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                try {
                    /*This if skips all empty nearby cells and skips the cell occupied by the current agent */
                    if (grid.getCell(x + i, y + j) != null && !(i == 0 && j == 0)) {
                        if (grid.getCell(x + i, y + j).getClass().getName().equals("entities.AgentNormal") || grid.getCell(x + i, y + j).getClass().getName().equals("entities.AgentPorteur")) {
                            exchangeInformation((Agent) grid.getCell(x + i, y + j));
                            loggerActivity.info("Tour n° "+String.valueOf(Tour.getTour())+"-"+String.valueOf(Tour.getMiniTour())+" Information has been exchanged between agent #" + idAgent + " and agent #" + ((Agent) grid.getCell(x + i, y + j)).getIdAgent() + ".");
                        }
                        if (grid.getCell(x + i, y + j).getClass().getName().equals("entities.Information")) {
                            collectInformation((Information) grid.getCell(x + i, y + j));
                            loggerActivity.info("Tour n° "+String.valueOf(Tour.getTour())+"-"+String.valueOf(Tour.getMiniTour())+" Agent #" + idAgent + " has collected an information #" + ((Information) grid.getCell(x + i, y + j)).getIdInformation() + ".");
                            grid.removeCell(x + i, y + j);

                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
        }
    }

    /**
     * Ajoute une information collecté dans la liste des informations connues
     * @param i la nouvelle information récemment collectée
     */
    public void collectInformation(Information i) {
        listeInformationsConnues.add(i);
    }

    /**
     * Gerer l'échange des informations entre 2 agents différents
     * @param a l'agent qui va fournir la nouvelle information
     */
    public void exchangeInformation(Agent a) {
        Set<Information> set = new LinkedHashSet<Information>(listeInformationsConnues);
        set.addAll(a.getInformationConnue());
        setListeInformationsConnues(new ArrayList<Information>(set));
        a.setListeInformationsConnues(new ArrayList<Information>(set));
    }
}
