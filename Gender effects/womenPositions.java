import org.apache.commons.csv.*;
import edu.duke.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class womenPositions {
    //vividity bizarreness happiness scariness romance
    static ArrayList<Integer> backPoses = new ArrayList<Integer>(Arrays.asList(1,4,5,12,13,14,16,20,22));
    static ArrayList<Integer> leftPoses = new ArrayList<Integer>(Arrays.asList(3,6,11,21));
    static ArrayList<Integer> rightPoses = new ArrayList<Integer>(Arrays.asList(9,18,19,23));
    static ArrayList<Integer> stomachPoses = new ArrayList<Integer>(Arrays.asList(2,7,8,10,15,17,24));
    public static void womenPositions (CSVParser parser) {
        int back=0, front=0, left=0, right=0;
        double genderCount=0.0;
        
        for (CSVRecord record : parser) {
            String gender = record.get("gender");
            String wakePos = record.get("wakePos");
            int wakePosInt = Integer.parseInt(wakePos);
            
            if (gender.equals("Female")) {
                if (backPoses.contains(wakePosInt)) {
                    back++;
                }
                if (stomachPoses.contains(wakePosInt)) {
                    front++;
                }
                if (leftPoses.contains(wakePosInt)) {
                    left++;
                }
                if (rightPoses.contains(wakePosInt)) {
                    right++;
                }
                genderCount++;
            }
        }
        double backPerc=(back/genderCount)*100, frontPerc=(front/genderCount)*100,
                leftPerc=(left/genderCount)*100, rightPerc=(right/genderCount)*100;
        
        System.out.println("AVERAGE EMOTIONAL PARAMETERS - WOMEN");
        System.out.println("The amount of women sleeping on the back is " + backPerc);
        System.out.println("The amount of women sleeping on the front is " + frontPerc);
        System.out.println("The amount of women sleeping on the left is " + leftPerc);
        System.out.println("The amount of women sleeping on the right is " + rightPerc);
        
    }
    
    public static void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        womenPositions(parser);
    }
}