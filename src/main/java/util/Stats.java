package util;


import entities.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Stats {
    private static final Logger loggerstats = LoggerFactory.getLogger("stats");
    public static boolean generateStats(Agent[] agents,int max_infos) {

        // Pourcentage des infos collectées pour chaque agent
        // Pourcentage des infos collectées global

        StringBuilder sb = new StringBuilder();
        double globalPercentage =0;
        double currentPercentage;
        boolean all = true;
        loggerstats.info("Tour n° "+String.valueOf(Tour.getTour()));
        for (int i=0;i<agents.length;i++){
            currentPercentage=Math.round((double)agents[i].getInformationConnue().size()/(double)max_infos*100);
            globalPercentage+=currentPercentage;
            sb.append("Agent #"+String.valueOf(agents[i].getIdAgent())+" "+String.valueOf(currentPercentage)+"%\n");
            if (agents[i].getInformationConnue().size()!=max_infos) all =false;
        }
        globalPercentage=Math.round(globalPercentage/agents.length);
        loggerstats.info("Global Percentage : "+String.valueOf(globalPercentage)+"%\n"+sb.toString());

        return all;
    }

}


