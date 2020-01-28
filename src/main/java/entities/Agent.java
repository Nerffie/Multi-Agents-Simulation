package entities;

import environnement.Grid;
import util.Aleatoire;
import util.Temps;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public abstract class Agent implements Entity {

    protected int idAgent;
    protected ArrayList<Information> listeInformationsConnues;
    protected int x;
    protected int y;

    public int getIdAgent() {
        return idAgent;
    }

    public ArrayList<Information> getInformationConnue() {
        return listeInformationsConnues;
    }

    public void setListeInformationsConnues(ArrayList<Information> liste){
        this.listeInformationsConnues = new ArrayList<Information>(liste);
    }

    public Agent(int id, int x, int y, int tailleMaxInformations) {
        this.idAgent = id;
        this.x = x;
        this.y = y;
        listeInformationsConnues = new ArrayList<Information>(tailleMaxInformations);
    }

    public void move(Grid grid) {
        boolean moved = false;
        int draw;
        while (!moved) {
            draw = (int) Aleatoire.getInstance().genererRandom(9);
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

    private boolean tryMove(Grid grid, int deltaX, int deltaY) {
        try {
            if (grid.getCell(x + deltaX, y + deltaY) == null) {
                grid.setCell(x + deltaX, y + deltaY, this);
                grid.removeCell(x, y);
                x--;
                y--;
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            //e.printStackTrace();
            return false;
        }
        return false;
    }

    public void interactWithSurroundings(Grid grid) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                try {
                    /*This if skips all empty nearby cells and skips the cell occupied by the current agent */
                    if (grid.getCell(x + i, y + j) != null && !(i == 0 && j == 0)) {
                        if (grid.getCell(x + i, y + j).getClass().getName().equals("entities.AgentNormal") || grid.getCell(x + i, y + j).getClass().getName().equals("entities.AgentPorteur")) {
                            if(exchangeInformation((Agent) grid.getCell(x + i, y + j))){
                                System.out.println("Information has been exchanged between agent #" + idAgent + " and agent #" + ((Agent) grid.getCell(x + i, y + j)).getIdAgent() + " at ("+Temps.getGrandTour()+","+Temps.getPetitTour()+").");
                            }

                        }
                        if (grid.getCell(x + i, y + j).getClass().getName().equals("entities.Information")) {
                            collectInformation((Information) grid.getCell(x + i, y + j));
                            System.out.println("Agent #" + idAgent + " has collected an information #" + ((Information) grid.getCell(x + i, y + j)).getIdInformation() + ".");
                            grid.removeCell(x + i, y + j);

                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
        }
    }

    public void collectInformation(Information i) {
        listeInformationsConnues.add(i);
    }

    public boolean exchangeInformation(Agent a) {
        /*Set<Information> set = new LinkedHashSet<Information>(listeInformationsConnues);
        set.addAll(a.getInformationConnue());
        setListeInformationsConnues(new ArrayList<Information>(set));
        a.setListeInformationsConnues(new ArrayList<Information>(set));*/

        boolean result = false;
        for(Information i : listeInformationsConnues){
            if(!a.getInformationConnue().contains(i)){
                a.getInformationConnue().add(new Information(i.getIdInformation(), Temps.getGrandTour(),Temps.getPetitTour()));
                result = true;
            }
        }

        for(Information i : a.getInformationConnue()){
            if(!listeInformationsConnues.contains(i)){
                listeInformationsConnues.add(new Information(i.getIdInformation(),Temps.getGrandTour(),Temps.getPetitTour()));
                result = true;
            }
        }
        return result;
    }


}
