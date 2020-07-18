package Model;

import java.io.Serializable;

import Model.QuestionsModel.QType;

public class AnswersForGrading  implements Serializable{
    public int AnswerId;
    public String Answer;
    public float UserGrade;
    
    
    public int UserId;
    public String FirstName;
    public String LastName;
    public String IdNumber;

    public int QuestionId;
    public String QuestionText;
    public QType QuestionType;
    public String QuestionAnswer;
    public String FileAddress;
    public float Grade;

    public int QuizeId;
    public String QuizName;
}