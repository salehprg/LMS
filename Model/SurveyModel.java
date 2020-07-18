package Model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SurveyModel  implements Serializable{
    public int Id;
    public int UserId;
    public int QuizId;
    /**
     * 1 = Easy , 2 = Normal , 3 = Hard
     */
    public int QuizLevel;

    
}