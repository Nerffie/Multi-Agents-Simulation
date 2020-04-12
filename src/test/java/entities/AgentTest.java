package entities;


import environnement.Grid;
import org.junit.Assert;
import org.junit.Test;


public class AgentTest {

    public static final int GRID_SIZE = 20;
    public static final int NUMBER_OF_AGENTS = 10;
    public static final int NUMBER_OF_INFORMATIONS = 100;
    public static final int NUMBER_OF_ITERATIONS = 250;

    public Grid createDummyGrid(int size,int nombreAgents,int nombreInformations){
        Grid grid = new Grid(size);
        grid.initialiserGrid(nombreAgents,nombreInformations);
        return grid;
    }
    public Agent createDummyAgent(int id,int x,int y,int tailleMaxInformations){
        return new AgentNormal(id,x,y,tailleMaxInformations);
    }

    public void alimenterInformation(Agent a,int size,int offset){
        for(int i=0;i<size;i++){
            a.collectInformation(new Information(i+offset));
        }
    }


    @Test
    public void move() {
        Grid grid = createDummyGrid(GRID_SIZE,NUMBER_OF_AGENTS,NUMBER_OF_INFORMATIONS);
        for (int i=0;i<NUMBER_OF_AGENTS;i++){
            int oldX = grid.getListeAgents()[i].getX();
            int oldY = grid.getListeAgents()[i].getY();
            grid.getListeAgents()[i].move(grid);
            int newX = grid.getListeAgents()[i].getX();
            int newY = grid.getListeAgents()[i].getY();
            Assert.assertTrue(oldX!=newX || oldY!=newY);
        }

    }


    @Test
    public void collectInformation() {
        Agent agent = createDummyAgent(1,0,0,NUMBER_OF_INFORMATIONS);
        int oldSize = agent.getInformationConnue().size();
        agent.collectInformation(new Information(1));
        int newSize = agent.getInformationConnue().size();
        Assert.assertTrue(oldSize+1==newSize);
    }

    @Test
    public void exchangeInformation() {
        Agent a = createDummyAgent(1,0,0,NUMBER_OF_INFORMATIONS);
        Agent b = createDummyAgent(2,1,0,NUMBER_OF_INFORMATIONS);
        alimenterInformation(a,NUMBER_OF_INFORMATIONS/4,0);
        alimenterInformation(b,NUMBER_OF_INFORMATIONS/8,100);
        a.exchangeInformation(b);
        Assert.assertTrue(a.getInformationConnue().size()==b.getInformationConnue().size());
    }
}