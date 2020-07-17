package Controller;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.SqlManager;
import Model.*;
import Model.QuestionsModel.QType;

public class AdminController extends SqlManager {

    public AdminController() throws SQLException {
        super();
        
    }

//#region Quiz

    boolean createNewQuiz(QuizesModel quizesModel)
    {
        return (DB_CreateQuiz(quizesModel) != -1 ? true : false);
    }

    boolean addQuestionToQuiz(QuestionsModel questionsModel)
    {
        return (DB_AddQuestion(questionsModel) != -1 ? true : false);
    }

    boolean addQuestionToQuiz(QuestionsModel questionsModel , ArrayList<OptionsModel> optionsModels)
    {
        int QuestionId = DB_AddQuestion(questionsModel);

        if(questionsModel.QuestionType == QType.Testi)
        {
            for (OptionsModel opt : optionsModels) {
                opt.QuestionId = QuestionId;
                DB_AddOptions(opt);
            }
        }
        
        return true;
    } 

    boolean AssignUserToQuiz(UserModel _userModel , int QuizId)
    {
        UserModel user = DB_GetUserInfo(_userModel.IdNumber);
        
        //If user does not exist Register it
        if(user == null)
        {
            _userModel.UserName = _userModel.IdNumber;
            _userModel.Password = _userModel.IdNumber;

            if(DB_AddUser(_userModel))
            {
                user = DB_GetUserInfo(_userModel.IdNumber);
            }
            else
            {
                return false;
            }
        }

        return DB_AddUserToQuiz(user.Id , QuizId);

    }

    boolean RemoveUserFromQuiz(int userId , int QuizId)
    {
        return DB_RemoveUserFromQuiz(userId, QuizId);
    }

    boolean BanUserFromQuiz(String IdNumber , int QuizId)
    {
        UserModel user = DB_GetUserInfo(IdNumber);
        
        //If user does not exist Register it
        if(user != null)
        {
            return DB_BanUser(user.Id, QuizId);
        }

        return false;

    }

    
//#endregion
    ArrayList<AnswersForGrading> getUserAnswers(int UserId , int QuizId)
    {
        return DB_GetAnswersForGrading(QuizId , UserId);
    }

    ArrayList<QuestionsModel> getQuestions(int QuizId)
    {
        return getQuestions(QuizId);
    }
}