package entities;

public class Information implements Entity {
    private int idInformation;
    private int[] timeOfAcquisition;

    public Information(int id) {

        this.idInformation = id;
        timeOfAcquisition = new int[2];
    }

    public Information(int id,int grandTour,int petitTour){
        this.idInformation = id;
        timeOfAcquisition = new int[2];
        timeOfAcquisition[0]=grandTour;
        timeOfAcquisition[1]=petitTour;

    }

    public int getIdInformation() {
        return idInformation;
    }

    @Override
    public boolean equals(Object obj) {
        Information inf = (Information)obj;
        return idInformation==inf.getIdInformation();
    }

    @Override
    public int hashCode() {
        return idInformation;
    }
}
