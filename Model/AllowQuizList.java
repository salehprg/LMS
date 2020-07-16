package Model;

import java.io.Serializable;
import java.util.Date;

public class AllowQuizList implements Serializable{
    public boolean Allow;
    public String IdNumber;
    public int UserId;
    public int QuizId;
    public String QuizName;
    public Date StartTime;
    public Date EndTime;
    public float Duration;
}