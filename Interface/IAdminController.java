package Interface;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.SqlManager;
import Model.*;
import Model.QuestionsModel.QType;

public interface IAdminController {


//#region Quiz

    int createNewQuiz(QuizesModel quizesModel);

    ArrayList<QuizesModel> getAllQuiz();

    ArrayList<QuizesModel> getQuizProgram();

    ArrayList<QuizesModel> getQuizArchive();

    boolean addQuestionToQuiz(QuestionsModel questionsModel);

    boolean addQuestionToQuiz(QuestionsModel questionsModel , ArrayList<OptionsModel> optionsModels);

    boolean AssignUserToQuiz(UserModel _userModel , int QuizId);

    boolean RemoveUserFromQuiz(int userId , int QuizId);

    boolean BanUserFromQuiz(String IdNumber , int QuizId);

    boolean AdduserFromExcel(String fileUrl , int QuizId);
//#endregion
    ArrayList<AnswersForGrading> getUserAnswers(int UserId , int QuizId);

    ArrayList<QuestionsModel> getQuestions(int QuizId);
    
    ArrayList<OptionsModel> getOptions(int QuestionId);

    ArrayList<AvgQuizGrade> reportQuizByQuiz();

    ArrayList<UserGradesInQuiz> reportStudentByStudent(int QuizId);

    boolean saveExcelQuizByQuiz();

    boolean saveExcelUserGrades(int QuizId);

    boolean autoGrading(int QuizId);

    ArrayList<QuizSurvey> getQuizSurvey(int QuizId);

    ArrayList<UserModel> getUsersInQuiz(int QuizId);

    boolean SubmitGrade(int AnswerId , float UserGrade);

    
}