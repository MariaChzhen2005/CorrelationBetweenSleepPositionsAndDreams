import org.apache.commons.csv.*;
import edu.duke.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class womenEmotions {

    public static void womenEmotions (CSVParser parser) {
        double vividityCnt=0.0, bizarrenessCnt=0.0, happinessCnt=0.0, scarinessCnt=0.0, romanceCnt=0.0;
        double vividityAvg=0.0, bizarrenessAvg=0.0, happinessAvg=0.0, scarinessAvg=0.0, romanceAvg=0.0;
        int genderCount=0;
        
        int vivid=0, bizarre=0, happy=0, scary=0, romantic=0;
        ArrayList<Integer> vividities = new ArrayList<Integer>();
        ArrayList<Integer> bizarrenesses = new ArrayList<Integer>();
        ArrayList<Integer> happinesses = new ArrayList<Integer>();
        ArrayList<Integer> scarinesses = new ArrayList<Integer>();
        ArrayList<Integer> romances = new ArrayList<Integer>();

        for (CSVRecord record : parser) {
            String gender = record.get("gender");
            String vividity = record.get("vividity");
            String bizarreness = record.get("bizarreness");
            String happiness = record.get("happiness");
            String scariness = record.get("scariness");
            String romance = record.get("romance");
            
            vivid = Integer.parseInt(vividity);
            bizarre = Integer.parseInt(bizarreness);
            happy = Integer.parseInt(happiness);
            scary = Integer.parseInt(scariness);
            romantic = Integer.parseInt(romance);       
            
            if (vivid != 0) {
                vividities.add(vivid);
            }
            if (bizarre != 0) {
                bizarrenesses.add(bizarre);
            }
            if (happy != 0) {
                happinesses.add(happy);
            }
            if (scary != 0) {
                scarinesses.add(scary);
            }
            if (romantic != 0) {
                romances.add(romantic);
            }

            //Avg = Cnt/genCount
            if (gender.equals("Female")) {
                vividityCnt += vivid;
                bizarrenessCnt += bizarre;
                happinessCnt += happy;
                scarinessCnt += scary;
                romanceCnt += romantic;
                genderCount++;
                System.out.println(genderCount);
            }
            vividityAvg = vividityCnt/genderCount;
            bizarrenessAvg = bizarrenessCnt/genderCount;
            happinessAvg = happinessCnt/genderCount;
            scarinessAvg = scarinessCnt/genderCount;
            romanceAvg = romanceCnt/genderCount;
        }
        System.out.println("AVERAGE EMOTIONAL PARAMETERS - WOMEN");
        System.out.println("The average vividity is " + vividityAvg);
        System.out.println("The average bizarreness is " + bizarrenessAvg);
        System.out.println("The average happiness is " + happinessAvg);
        System.out.println("The average scariness is " + scarinessAvg);
        System.out.println("The average romance is " + romanceAvg);
    }
    
    public static void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        womenEmotions(parser);
    }
}
