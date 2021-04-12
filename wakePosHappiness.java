import org.apache.commons.csv.*;
import edu.duke.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class wakePosHappiness {
    static ArrayList<Integer> backPoses = new ArrayList<Integer>(Arrays.asList(1,4,5,12,13,14,16,20,22));
    static ArrayList<Integer> leftPoses = new ArrayList<Integer>(Arrays.asList(3,6,11,21));
    static ArrayList<Integer> rightPoses = new ArrayList<Integer>(Arrays.asList(9,18,19,23));
    static ArrayList<Integer> stomachPoses = new ArrayList<Integer>(Arrays.asList(2,7,8,10,15,17,24));
    
    public static void wakePosHappiness (CSVParser parser) {
        int countBackPoses=0, countLeftPoses=0, countRightPoses=0, countStomachPoses=0;
        int backHappyAvg=0, leftHappyAvg=0, rightHappyAvg=0, stomachHappyAvg=0;
        double right=0.0, left=0.0, back=0.0, stomach=0.0;
        ArrayList<Integer> happinesses = new ArrayList<Integer>();
        ArrayList<Integer> wakePoses = new ArrayList<Integer>();
        for (CSVRecord record : parser) {
            String happiness = record.get("happiness");
            String wakePos = record.get("wakePos");
            int wakePosInt = Integer.parseInt(wakePos);
            int happy = Integer.parseInt(happiness);
            if (happy != 0) {
                happinesses.add(happy);
            }
            wakePoses.add(wakePosInt);
            
            if (backPoses.contains(wakePosInt)) {
                countBackPoses+=1;
                backHappyAvg+=happy;
            }
            if (leftPoses.contains(wakePosInt)) {
                countLeftPoses+=1;
                leftHappyAvg+=happy;
            }
            if (rightPoses.contains(wakePosInt)) {
                countRightPoses+=1;
                rightHappyAvg+=happy;
            }
            if (stomachPoses.contains(wakePosInt)) {
                countStomachPoses+=1;
                stomachHappyAvg+=happy;
            }
            double countBackPosesD=countBackPoses,countLeftPosesD=countLeftPoses,
                countRightPosesD=countRightPoses, countStomachPosesD=countStomachPoses,
                backHappyAvgD=backHappyAvg, leftHappyAvgD=leftHappyAvg, rightHappyAvgD=rightHappyAvg,
                stomachHappyAvgD=stomachHappyAvg;
            right = rightHappyAvgD/countRightPosesD;
            left = leftHappyAvgD/countLeftPosesD;
            back = backHappyAvgD/countBackPosesD;
            stomach = stomachHappyAvgD/countStomachPosesD;
        }
        System.out.println("WAKING POSITIONS - HAPPINESS");
        System.out.println("The average happiness for poses on the right side is " + right);
        System.out.println("The average happiness for poses on the left side is " + left);
        System.out.println("The average happiness for poses on the back side is " + back);
        System.out.println("The average happiness for poses on the stomach side is " + stomach);
    }
    
    public static void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println("");
        wakePosHappiness(parser);
    }
}
