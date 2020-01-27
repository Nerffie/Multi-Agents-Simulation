package environnement;

import entities.Agent;
import entities.AgentNormal;
import entities.Entity;
import entities.Information;
import util.Aleatoire;
import util.IdGenerator;

public class Grid {
    private Entity[][] map;
    private int taille;
    public Agent[] listeAgents;
    private Information[] listeInformations;

    public Grid(int size) {
        taille = size;
        map = new Entity[taille][taille];
    }

    public void initialiserGrid(int nombreAgents, int nombreInformations) {
        listeAgents = new Agent[nombreAgents];
        listeInformations = new Information[nombreInformations];
        int compteur = 0;
        int x, y;
        while (compteur != nombreAgents) {
            x = (int) Aleatoire.getInstance().genererRandom(taille);
            y = (int) Aleatoire.getInstance().genererRandom(taille);
            if (map[x][y] == null) {
                map[x][y] = new AgentNormal(IdGenerator.getId(),x,y);
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

    public Entity getCell(int x,int y){
        return map[x][y];
    }

    public void setCell(int x,int y,Entity e){
        map[x][y] = e;
    }
    public void removeCell(int x,int y){
        map[x][y]=null;
    }
}
