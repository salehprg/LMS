package Controller;

import java.io.IOException;
import java.sql.Date;
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

        if (questionsModel.QuestionType == QType.Testi) 
        {
            for (int i = 0;i < optionsModels.size();i++) 
            {
                optionsModels.get(i).QuestionId = QuestionId;
                int OptionId = DB_AddOptions(optionsModels.get(i));

                if(i == Integer.valueOf(questionsModel.Answer))
                {
                    questionsModel.Answer = String.valueOf(OptionId);
                    DB_EditQuestion(questionsModel);
                }
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
        return DB_GetQuestionInQuiz(QuizId);
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

    @Override
    public ArrayList<OptionsModel> getOptions(int QuestionId) {
        return DB_GetQuestionOptions(QuestionId);
    }

    @Override
    public ArrayList<AvgQuizGrade> reportQuizByQuiz() {
        
        return DB_AvgQuizGrade();
    }

    @Override
    public boolean saveExcelQuizByQuiz() {
        
        boolean result;
        try {
            result = ExcelFile.WriteQuizAvg(DB_AvgQuizGrade());
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
    }

    @Override
    public boolean saveExcelUserGrades(int QuizId) {
        
        boolean result;
        try {
            result = ExcelFile.WriteUserGrades(DB_GetUserGradesInQuiz(QuizId));
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
    }

    @Override
    public ArrayList<UserGradesInQuiz> reportStudentByStudent(int QuizId) {

        return DB_GetUserGradesInQuiz(QuizId);
    }

    @Override
    public boolean autoGrading(int QuizId) {
        ArrayList<TriesModel> triesModels = DB_GetTries(QuizId);
        for (TriesModel triesModel : triesModels) 
        {
            ArrayList<AnswersForGrading> answers = DB_GetAnswersForGrading(QuizId, triesModel.UserId);
            for (AnswersForGrading answer : answers) 
            {
                if(answer.Answer.equals(answer.QuestionAnswer))
                {
                    DB_GradeAnswer(answer.Grade , answer.AnswerId);
                }
                else
                {
                    DB_GradeAnswer(0 , answer.AnswerId);
                }
            }

        }
        
        return false;
    }

    @Override
    public ArrayList<QuizesModel> getAllQuiz() {

        return DB_GetQuizes();
    }

    @Override
    public ArrayList<QuizesModel> getQuizProgram() {
        ArrayList<QuizesModel> quizes = DB_GetQuizes();

        ArrayList<QuizesModel> activeQuizes = new ArrayList<>();

        for (QuizesModel quizesModel : quizes) 
        {
            if(quizesModel.EndTime.compareTo(new Date(System.currentTimeMillis())) > 0)
            {
                activeQuizes.add(quizesModel);
            }
        }

        return activeQuizes;
    }

    @Override
    public ArrayList<QuizesModel> getQuizArchive() {
        
        ArrayList<QuizesModel> quizes = DB_GetQuizes();

        ArrayList<QuizesModel> activeQuizes = new ArrayList<>();

        for (QuizesModel quizesModel : quizes) 
        {
            if(quizesModel.EndTime.compareTo(new Date(System.currentTimeMillis())) < 0)
            {
                activeQuizes.add(quizesModel);
            }
        }

        return activeQuizes;
    }
}