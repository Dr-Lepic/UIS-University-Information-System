

import java.io.Serializable;
import java.util.ArrayList;

public class CourseInfo implements Serializable {

    ArrayList<String> quiz = new ArrayList<>();
    float midMark;
    float finalMark;
    int attendance;
    ArrayList<String> topics;

    CourseInfo(){
        quiz = new ArrayList<>();
        topics = new ArrayList<>();
    }

}
