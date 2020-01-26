package environnement;

import entities.AgentNormal;
import entities.Entity;
import entities.Information;
import util.Aleatoire;
import util.IdGenerator;

public class Grid {
    private Entity[][] map;
    private int taille;

    public Grid(int size) {
        taille = size;
        map = new Entity[taille][taille];
    }

    public void initialiserGrid(int nombreAgents, int nombreInformations) {
        int compteur = nombreAgents;
        int x, y;
        while (nombreAgents != 0) {
            x = (int) Aleatoire.getInstance().genererRandom(taille);
            y = (int) Aleatoire.getInstance().genererRandom(taille);
            if (map[x][y] == null) {
                map[x][y] = new AgentNormal(IdGenerator.getId());
                nombreAgents--;
            }
        }

        while (nombreInformations != 0) {
            x = (int) Aleatoire.getInstance().genererRandom(taille);
            y = (int) Aleatoire.getInstance().genererRandom(taille);
            if (map[x][y] == null) {
                map[x][y] = new Information(IdGenerator.getId());
                nombreInformations--;
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
}
