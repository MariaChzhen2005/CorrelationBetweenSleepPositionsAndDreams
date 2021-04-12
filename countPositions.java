import org.apache.commons.csv.*;
import edu.duke.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class countPositions {
    static ArrayList<Integer> backPoses = new ArrayList<Integer>(Arrays.asList(1,4,5,12,13,14,16,20,22));
    static ArrayList<Integer> leftPoses = new ArrayList<Integer>(Arrays.asList(3,6,11,21));
    static ArrayList<Integer> rightPoses = new ArrayList<Integer>(Arrays.asList(9,18,19,23));
    static ArrayList<Integer> stomachPoses = new ArrayList<Integer>(Arrays.asList(2,7,8,10,15,17,24));
    public static void countPositions (CSVParser parser) {
        int countBackPoses=0, countLeftPoses=0, countRightPoses=0, countStomachPoses=0;
        for (CSVRecord record : parser) {
            String wakePos = record.get("wakePos");
            int wakePosInt = Integer.parseInt(wakePos);
            if (backPoses.contains(wakePosInt)) {
                countBackPoses+=1;
            }
            if (leftPoses.contains(wakePosInt)) {
                countLeftPoses+=1;
            }
            if (rightPoses.contains(wakePosInt)) {
                countRightPoses+=1;
            }
            if (stomachPoses.contains(wakePosInt)) {
                countStomachPoses+=1;
            }
        }
        System.out.println("COUNTER");
        System.out.println("There were " + countBackPoses + " people sleeping on the back.");
        System.out.println("There were " + countStomachPoses + " people sleeping on the stomach.");
        System.out.println("There were " + countRightPoses + " people sleeping on the right.");
        System.out.println("There were " + countLeftPoses + " people sleeping on the left.");
    }
    
    public static void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        countPositions(parser);
    }
}
