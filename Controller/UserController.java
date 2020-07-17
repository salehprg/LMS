package Controller;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import Helper.SqlManager;
import Model.AllowQuizList;
import Model.AnswersModel;
import Model.OptionsModel;
import Model.QuestionsModel;
import Model.QuizesModel;
import Model.TriesModel;
import Model.UserModel;

public class UserController extends SqlManager {

    public UserController() throws SQLException {
        super();
        
    }


    public boolean Register(UserModel userModel)
    {
        ArrayList<UserModel> users = DB_GetAllUsers();

        for (UserModel user : users) {
            if(user.UserName == userModel.UserName)
            {
                return false;
            }
        }
        boolean result = DB_AddUser(userModel);

        return result;
    }

    public boolean Login(String UserName , String Password)
    {
        ArrayList<UserModel> users = DB_GetAllUsers();

        for (UserModel user : users) {
            if(user.UserName == UserName && user.Password == Password)
            {
                return true;
            }
        }

        return false;
    }

    public boolean EnrolQuiz(int UserId , int QuizId)
    {
        QuizesModel quizesModel = new QuizesModel();
        quizesModel = DB_GetQuizInfo(QuizId);

        TriesModel triesModel = new TriesModel();
        triesModel = DB_GetUserTries(UserId, QuizId);

        Date currentTime = new Date();

        if(quizesModel.StartTime.getTime() < currentTime.getTime() && quizesModel.EndTime.getTime() > currentTime.getTime())
        {
            if(triesModel == null)
            {
                triesModel = new TriesModel();
                triesModel.QuizId = QuizId;
                triesModel.UserId = UserId;
                triesModel.TryTime = new Date();

                DB_EnrolUserToQuiz(triesModel);

                return true;
            }
            else
            {
                return false;
            }
        }

        return false;
    }

    public ArrayList<AllowQuizList> GetQuizes(int UserId)
    {
        return DB_GetUserQuizes(UserId);
    }

    public ArrayList<QuestionsModel> GetQuestions(int QuizId)
    {
        QuizesModel quizesModel = new QuizesModel();
        quizesModel = DB_GetQuizInfo(QuizId);

        ArrayList<QuestionsModel> questionsModels = DB_GetQuestionInQuiz(QuizId);

        if(quizesModel.Random)
        {
            ArrayList<QuestionsModel> randomisedQuestions = new ArrayList<>();

            for (int i = 0;i < questionsModels.size() ;i++) 
            {
                int randomId = new Random().nextInt(questionsModels.size());
                randomisedQuestions.add(questionsModels.get(randomId));
                questionsModels.remove(randomId);
            }

            return randomisedQuestions;
        }

        return questionsModels;
    }

    public ArrayList<QuestionsModel> Review_GetQuestions(int QuizId)
    {
        QuizesModel quizesModel = new QuizesModel();
        quizesModel = DB_GetQuizInfo(QuizId);

        if(quizesModel.CanReview)
        {
            return GetQuestions(QuizId);
        }

        return null;
    }

    public Object Review_GetUserAnswer(int UserId , int QuestionId)
    {
        AnswersModel answersModel = DB_GetUserAnswer(UserId, QuestionId);

        QuestionsModel questionModel = DB_GetQuestionInfo(QuestionId);

        switch(questionModel.QuestionType)
        {
            case Testi:
                ArrayList<OptionsModel> optionsModels = DB_GetQuestionOptions(QuestionId);

                int AnswerId = Integer.parseInt(answersModel.Answer);
                for (OptionsModel optionsModel : optionsModels) {
                    if(optionsModel.Id == AnswerId)
                    {
                        optionsModel.UserAnswer = true;
                        optionsModels.set(optionsModels.indexOf(optionsModel), optionsModel);
                    }
                }

                return optionsModels;

            case Tashrihi:
                return answersModel.Answer;

            case TrueFalse:
                return Boolean.parseBoolean(answersModel.Answer);  
        }
        return DB_SubmitAnswer(answersModel);
    }


    public boolean SubmitAnswer(AnswersModel answersModel)
    {
        return DB_SubmitAnswer(answersModel);
    }


}
