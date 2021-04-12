import org.apache.commons.csv.*;
import edu.duke.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class wakePosScariness {
    static ArrayList<Integer> backPoses = new ArrayList<Integer>(Arrays.asList(1,4,5,12,13,14,16,20,22));
    static ArrayList<Integer> leftPoses = new ArrayList<Integer>(Arrays.asList(3,6,11,21));
    static ArrayList<Integer> rightPoses = new ArrayList<Integer>(Arrays.asList(9,18,19,23));
    static ArrayList<Integer> stomachPoses = new ArrayList<Integer>(Arrays.asList(2,7,8,10,15,17,24));
    
    public static void wakePosScariness (CSVParser parser) {
        int countBackPoses=0, countLeftPoses=0, countRightPoses=0, countStomachPoses=0;
        int backScaryAvg=0, leftScaryAvg=0, rightScaryAvg=0, stomachScaryAvg=0;
        double right=0.0, left=0.0, back=0.0, stomach=0.0;
        ArrayList<Integer> scarinesses = new ArrayList<Integer>();
        ArrayList<Integer> wakePoses = new ArrayList<Integer>();
        for (CSVRecord record : parser) {
            String scariness = record.get("scariness");
            String wakePos = record.get("wakePos");
            int wakePosInt = Integer.parseInt(wakePos);
            int scary = Integer.parseInt(scariness);
            if (scary != 0) {
                scarinesses.add(scary);
            }
            wakePoses.add(wakePosInt);
            
            if (backPoses.contains(wakePosInt)) {
                countBackPoses+=1;
                backScaryAvg+=scary;
            }
            if (leftPoses.contains(wakePosInt)) {
                countLeftPoses+=1;
                leftScaryAvg+=scary;
            }
            if (rightPoses.contains(wakePosInt)) {
                countRightPoses+=1;
                rightScaryAvg+=scary;
            }
            if (stomachPoses.contains(wakePosInt)) {
                countStomachPoses+=1;
                stomachScaryAvg+=scary;
            }
            double countBackPosesD=countBackPoses,countLeftPosesD=countLeftPoses,
                countRightPosesD=countRightPoses, countStomachPosesD=countStomachPoses,
                backScaryAvgD=backScaryAvg, leftScaryAvgD=leftScaryAvg, rightScaryAvgD=rightScaryAvg,
                stomachScaryAvgD=stomachScaryAvg;
            right = rightScaryAvgD/countRightPosesD;
            left = leftScaryAvgD/countLeftPosesD;
            back = backScaryAvgD/countBackPosesD;
            stomach = stomachScaryAvgD/countStomachPosesD;
        }
        System.out.println("WAKING POSITIONS - SCARINESS");
        System.out.println("The average scariness for poses on the right side is " + right);
        System.out.println("The average scariness for poses on the left side is " + left);
        System.out.println("The average scariness for poses on the back side is " + back);
        System.out.println("The average scariness for poses on the stomach side is " + stomach);
    }
    
    public static void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println("");
        wakePosScariness(parser);
    }
}

