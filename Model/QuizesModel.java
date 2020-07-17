package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class QuizesModel  implements Serializable{
    public int Id;
    public String QuizName;
    public Date StartTime;
    public Date EndTime;
    public float Duration;
    public boolean Random;
    public boolean CanReview;

}