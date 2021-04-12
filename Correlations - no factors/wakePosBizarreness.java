import org.apache.commons.csv.*;
import edu.duke.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class wakePosBizarreness {
    static ArrayList<Integer> backPoses = new ArrayList<Integer>(Arrays.asList(1,4,5,12,13,14,16,20,22));
    static ArrayList<Integer> leftPoses = new ArrayList<Integer>(Arrays.asList(3,6,11,21));
    static ArrayList<Integer> rightPoses = new ArrayList<Integer>(Arrays.asList(9,18,19,23));
    static ArrayList<Integer> stomachPoses = new ArrayList<Integer>(Arrays.asList(2,7,8,10,15,17,24));
    
    public static void wakePosBizareness (CSVParser parser) {
        int countBackPoses=0, countLeftPoses=0, countRightPoses=0, countStomachPoses=0;
        int backBizarreAvg=0, leftBizarreAvg=0, rightBizarreAvg=0, stomachBizarreAvg=0;
        double right=0.0, left=0.0, back=0.0, stomach=0.0;
        
        ArrayList<Integer> bizarrenesses = new ArrayList<Integer>();
        ArrayList<Integer> wakePoses = new ArrayList<Integer>();
        for (CSVRecord record : parser) {
            String bizarreness = record.get("bizarreness");
            String wakePos = record.get("wakePos");
            int wakePosInt = Integer.parseInt(wakePos);
            int bizarre = Integer.parseInt(bizarreness);
            if (bizarre != 0) {
                bizarrenesses.add(bizarre);
            }
            wakePoses.add(wakePosInt);
            
            if (backPoses.contains(wakePosInt)) {
                countBackPoses+=1;
                backBizarreAvg+=bizarre;
            }
            if (leftPoses.contains(wakePosInt)) {
                countLeftPoses+=1;
                leftBizarreAvg+=bizarre;
            }
            if (rightPoses.contains(wakePosInt)) {
                countRightPoses+=1;
                rightBizarreAvg+=bizarre;
            }
            if (stomachPoses.contains(wakePosInt)) {
                countStomachPoses+=1;
                stomachBizarreAvg+=bizarre;
            }
            double countBackPosesD=countBackPoses,countLeftPosesD=countLeftPoses,
                countRightPosesD=countRightPoses, countStomachPosesD=countStomachPoses,
                backBizarreAvgD=backBizarreAvg, leftBizarreAvgD=leftBizarreAvg, rightBizarreAvgD=rightBizarreAvg,
                stomachBizarreAvgD=stomachBizarreAvg;
            right = rightBizarreAvgD/countRightPosesD;
            left = leftBizarreAvgD/countLeftPosesD;
            back = backBizarreAvg/countBackPosesD;
            stomach = stomachBizarreAvgD/countStomachPosesD;
            
        }
        System.out.println("WAKING POSITIONS - BIZARRENESS");
        System.out.println("The average bizarreness for poses on the right side is " + right);
        System.out.println("The average bizarreness for poses on the left side is " + left);
        System.out.println("The average bizarreness for poses on the back side is " + back);
        System.out.println("The average bizarreness for poses on the stomach side is " + stomach);
    }
    public static void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println("");
        wakePosBizareness(parser);
    }
}
