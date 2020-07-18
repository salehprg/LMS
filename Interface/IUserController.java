package Interface;

import java.util.ArrayList;

import Model.*;

public interface IUserController {
    public boolean Register(UserModel userModel);

    public UserModel Login(String UserName , String Password);

    public boolean EnrolQuiz(int UserId , int QuizId);

    public ArrayList<AllowQuizList> GetQuizes(int UserId);

    public ArrayList<QuestionsModel> GetQuestions(int QuizId);

    public ArrayList<QuestionsModel> Review_GetQuestions(int QuizId);

    public Object Review_GetUserAnswer(int UserId , int QuestionId);


    public boolean SubmitAnswer(AnswersModel answersModel);

    public boolean SubmitSurvey(SurveyModel surveyModel);

}