package Interface;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.SqlManager;
import Model.*;
import Model.QuestionsModel.QType;

public interface IAdminController {


//#region Quiz

    boolean createNewQuiz(QuizesModel quizesModel);

    boolean addQuestionToQuiz(QuestionsModel questionsModel);

    boolean addQuestionToQuiz(QuestionsModel questionsModel , ArrayList<OptionsModel> optionsModels);

    boolean AssignUserToQuiz(UserModel _userModel , int QuizId);

    boolean RemoveUserFromQuiz(int userId , int QuizId);

    boolean BanUserFromQuiz(String IdNumber , int QuizId);

    
//#endregion
    ArrayList<AnswersForGrading> getUserAnswers(int UserId , int QuizId);

    ArrayList<QuestionsModel> getQuestions(int QuizId);
}