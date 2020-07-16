package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.ExcelFile;
import Helper.SqlManager;
import Interface.IAdminController;
import Model.*;
import Model.QuestionsModel.QType;

public class AdminController extends SqlManager implements IAdminController {

    public AdminController() throws SQLException {
        super();

    }

    // #region Quiz

    @Override
    public int createNewQuiz(QuizesModel quizesModel) {
        int quizId = DB_CreateQuiz(quizesModel);

        return quizId;
    }

    @Override
    public boolean addQuestionToQuiz(QuestionsModel questionsModel) {
        return (DB_AddQuestion(questionsModel) != -1 ? true : false);
    }

    @Override
    public boolean addQuestionToQuiz(QuestionsModel questionsModel, ArrayList<OptionsModel> optionsModels) {
        int QuestionId = DB_AddQuestion(questionsModel);

        if (questionsModel.QuestionType == QType.Testi) {
            for (OptionsModel opt : optionsModels) {
                opt.QuestionId = QuestionId;
                DB_AddOptions(opt);
            }
        }

        return true;
    }

    @Override
    public boolean AssignUserToQuiz(UserModel _userModel, int QuizId) {
        UserModel user = DB_GetUserInfo(_userModel.IdNumber);

        // If user does not exist Register it
        if (user == null) {
            _userModel.UserName = _userModel.IdNumber;
            _userModel.Password = _userModel.IdNumber;

            if (DB_AddUser(_userModel)) {
                user = DB_GetUserInfo(_userModel.IdNumber);
            } else {
                return false;
            }
        }

        return DB_AddUserToQuiz(user.Id, QuizId);

    }

    @Override
    public boolean RemoveUserFromQuiz(int userId, int QuizId) {
        return DB_RemoveUserFromQuiz(userId, QuizId);
    }

    @Override
    public boolean BanUserFromQuiz(String IdNumber, int QuizId) {
        UserModel user = DB_GetUserInfo(IdNumber);

        // If user does not exist Register it
        if (user != null) {
            return DB_BanUser(user.Id, QuizId);
        }

        return false;

    }

    // #endregion

    @Override
    public ArrayList<AnswersForGrading> getUserAnswers(int UserId, int QuizId) {
        return DB_GetAnswersForGrading(QuizId, UserId);
    }

    @Override
    public ArrayList<QuestionsModel> getQuestions(int QuizId) {
        return getQuestions(QuizId);
    }

    @Override
    public boolean AdduserFromExcel(String fileUrl) {
        try {
            ArrayList<UserModel> users = ExcelFile.ReadStudentData(fileUrl);

            for (UserModel userModel : users) 
            {
                DB_AddUser(userModel);
            }

            return true;
        } catch (IOException e) {
            
            e.printStackTrace();
            return false;
        }
        
    }
}