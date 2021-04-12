import org.apache.commons.csv.*;
import edu.duke.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class sleepLongHappiness {
    static ArrayList<Integer> backPoses = new ArrayList<Integer>(Arrays.asList(1,4,5,12,13,14,16,20,22));
    static ArrayList<Integer> leftPoses = new ArrayList<Integer>(Arrays.asList(3,6,11,21));
    static ArrayList<Integer> rightPoses = new ArrayList<Integer>(Arrays.asList(9,18,19,23));
    static ArrayList<Integer> stomachPoses = new ArrayList<Integer>(Arrays.asList(2,7,8,10,15,17,24));
    
    public static void sleepLongHappiness (CSVParser parser) {
        int countBackPoses=0, countLeftPoses=0, countRightPoses=0, countStomachPoses=0;
        int backVividAvg=0, leftVividAvg=0, rightVividAvg=0, stomachVividAvg=0;
        double right=0.0, left=0.0, back=0.0, stomach=0.0;
        ArrayList<Integer> vividities = new ArrayList<Integer>();
        ArrayList<Integer> wakePoses = new ArrayList<Integer>();
        for (CSVRecord record : parser) {
            String happiness = record.get("happiness");
            String wakePos = record.get("wakePos");
            String sleepAvg = record.get("sleepAvg");
            int wakePosInt = Integer.parseInt(wakePos);
            int happy = Integer.parseInt(happiness);
            int sleepAvgInt=0;
            if (sleepAvg.length()<2) {
                sleepAvgInt = Integer.parseInt(sleepAvg);
            }
            if (happy != 0 && (sleepAvgInt==6||sleepAvgInt==7||sleepAvgInt==8||sleepAvgInt==9||sleepAvgInt==10||sleepAvgInt==11||sleepAvgInt==12)) {
                vividities.add(happy);
                wakePoses.add(wakePosInt);
                if (backPoses.contains(wakePosInt)) {
                    countBackPoses+=1;
                    backVividAvg+=happy;
                }
                if (leftPoses.contains(wakePosInt)) {
                    countLeftPoses+=1;
                    leftVividAvg+=happy;
                }
                if (rightPoses.contains(wakePosInt)) {
                    countRightPoses+=1;
                    rightVividAvg+=happy;
                }
                if (stomachPoses.contains(wakePosInt)) {
                    countStomachPoses+=1;
                    stomachVividAvg+=happy;
                }
            
                double countBackPosesD=countBackPoses,countLeftPosesD=countLeftPoses,
                    countRightPosesD=countRightPoses, countStomachPosesD=countStomachPoses,
                    backVividAvgD=backVividAvg, leftVividAvgD=leftVividAvg, rightVividAvgD=rightVividAvg,
                    stomachVividAvgD=stomachVividAvg;
                right = rightVividAvgD/countRightPosesD;
                left = leftVividAvgD/countLeftPosesD;
                back = backVividAvgD/countBackPosesD;
                stomach = stomachVividAvgD/countStomachPosesD;
            }
            
        }
        System.out.println("SLEEP LENGTH: 6-12 HOURS - WAKING POSITIONS - HAPPINESS");
        System.out.println("The average happiness for poses on the right side is " + right);
        System.out.println("The average happiness for poses on the left side is " + left);
        System.out.println("The average happiness for poses on the back side is " + back);
        System.out.println("The average happiness for poses on the stomach side is " + stomach);
    }
    
    public static void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println("");
        sleepLongHappiness(parser);
    }
}
