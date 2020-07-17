package Model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AnswersModel implements Serializable{
    public int Id;
    public int QuestionId;
    public int UserId;
    public String Answer;
    public float UserGrade;

}