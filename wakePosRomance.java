import org.apache.commons.csv.*;
import edu.duke.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class wakePosRomance {
    static ArrayList<Integer> backPoses = new ArrayList<Integer>(Arrays.asList(1,4,5,12,13,14,16,20,22));
    static ArrayList<Integer> leftPoses = new ArrayList<Integer>(Arrays.asList(3,6,11,21));
    static ArrayList<Integer> rightPoses = new ArrayList<Integer>(Arrays.asList(9,18,19,23));
    static ArrayList<Integer> stomachPoses = new ArrayList<Integer>(Arrays.asList(2,7,8,10,15,17,24));
    
    public static void wakePosRomance (CSVParser parser) {
        int countBackPoses=0, countLeftPoses=0, countRightPoses=0, countStomachPoses=0;
        int backRomanticAvg=0, leftRomanticAvg=0, rightRomanticAvg=0, stomachRomanticAvg=0;
        double right=0.0, left=0.0, back=0.0, stomach=0.0;
        
        ArrayList<Integer> romances = new ArrayList<Integer>();
        ArrayList<Integer> wakePoses = new ArrayList<Integer>();
        for (CSVRecord record : parser) {
            String romance = record.get("romance");
            String wakePos = record.get("wakePos");
            int wakePosInt = Integer.parseInt(wakePos);
            int romantic = Integer.parseInt(romance);
            if (romantic != 0) {
                romances.add(romantic);
            }
            wakePoses.add(wakePosInt);
            
            if (backPoses.contains(wakePosInt)) {
                countBackPoses+=1;
                backRomanticAvg+=romantic;
            }
            if (leftPoses.contains(wakePosInt)) {
                countLeftPoses+=1;
                leftRomanticAvg+=romantic;
            }
            if (rightPoses.contains(wakePosInt)) {
                countRightPoses+=1;
                rightRomanticAvg+=romantic;
            }
            if (stomachPoses.contains(wakePosInt)) {
                countStomachPoses+=1;
                stomachRomanticAvg+=romantic;
            }
            double countBackPosesD=countBackPoses,countLeftPosesD=countLeftPoses,
                countRightPosesD=countRightPoses, countStomachPosesD=countStomachPoses,
                backRomanticAvgD=backRomanticAvg, leftRomanticAvgD=leftRomanticAvg,
                rightRomanticAvgD=rightRomanticAvg,stomachRomanticAvgD=stomachRomanticAvg;
            right = rightRomanticAvgD/countRightPosesD;
            left = leftRomanticAvgD/countLeftPosesD;
            back = backRomanticAvgD/countBackPosesD;
            stomach = stomachRomanticAvgD/countStomachPosesD;
            
        }
        System.out.println("WAKING POSITIONS - ROMANCE");
        System.out.println("The average romance for poses on the right side is " + right);
        System.out.println("The average romance for poses on the left side is " + left);
        System.out.println("The average romance for poses on the back side is " + back);
        System.out.println("The average romance for poses on the stomach side is " + stomach);
    }
    public static void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println("");
        wakePosRomance(parser);
    }
}

