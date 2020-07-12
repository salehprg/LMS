package lms.Model;

import java.time.LocalDateTime;
import java.util.Date;

public class QuizesModel {
    public int Id;
    public String QuizName;
    public Date StartTime;
    public Date EndTime;
    public float Duration;
    public boolean Random;
    public boolean CanReview;

}