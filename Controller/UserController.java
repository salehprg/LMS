package Controller;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.awt.image.*;
import javax.imageio.ImageIO;

import Helper.SqlManager;
import Interface.*;
import Model.*;

public class UserController extends SqlManager implements IUserController   {

    public UserController() throws SQLException {
        super();

    }

    @Override
    public boolean Register(UserModel userModel) {
        ArrayList<UserModel> users = DB_GetAllUsers();

        for (UserModel user : users) {
            if (user.UserName.equals(userModel.UserName)) {
                return false;
            }
        }
        boolean result = (DB_AddUser(userModel) != -1 ? true : false);

        return result;
    }

    @Override
    public UserModel Login(String UserName, String Password) {
        ArrayList<UserModel> users = DB_GetAllUsers();

        for (UserModel user : users) {
            if (user.UserName.equals(UserName) && user.Password.equals(Password)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public boolean EnrolQuiz(int UserId, int QuizId) {
        QuizesModel quizesModel = new QuizesModel();
        quizesModel = DB_GetQuizInfo(QuizId);

        TriesModel triesModel = new TriesModel();
        triesModel = DB_GetUserTries(UserId, QuizId);

        Date currentTime = new Date(System.currentTimeMillis());

        if (quizesModel.StartTime.compareTo(currentTime) < 0
                && quizesModel.EndTime.compareTo(currentTime) >= 0)
            {
            if (triesModel.TryTime == null) {
                triesModel = new TriesModel();
                triesModel.QuizId = QuizId;
                triesModel.UserId = UserId;
                triesModel.TryTime = currentTime;

                DB_EnrolUserToQuiz(triesModel);

                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    @Override
    public ArrayList<AllowQuizList> GetQuizes(int UserId) {
        return DB_GetUserQuizes(UserId);
    }

    @Override
    public ArrayList<QuestionsModel> GetQuestions(int QuizId) {
        QuizesModel quizesModel = new QuizesModel();
        quizesModel = DB_GetQuizInfo(QuizId);

        ArrayList<QuestionsModel> questionsModels = DB_GetQuestionInQuiz(QuizId);

        if (quizesModel.Random) {
            ArrayList<QuestionsModel> randomisedQuestions = new ArrayList<>();

            while (questionsModels.size() > 0) {
                int randomId = new Random().nextInt(questionsModels.size());
                randomisedQuestions.add(questionsModels.get(randomId));
                questionsModels.remove(randomId);
            }

            return randomisedQuestions;
        }

        return questionsModels;
    }

    @Override
    public ArrayList<QuestionsModel> Review_GetQuestions(int QuizId) {
        QuizesModel quizesModel = new QuizesModel();
        quizesModel = DB_GetQuizInfo(QuizId);

        if (quizesModel.CanReview) {
            return GetQuestions(QuizId);
        }

        return null;
    }

    @Override
    public AnswersModel Review_GetUserAnswer(int UserId, int QuestionId) {
        AnswersModel answersModel = DB_GetUserAnswer(UserId, QuestionId);

        return answersModel;
        // QuestionsModel questionModel = DB_GetQuestionInfo(QuestionId);

        // switch (questionModel.QuestionType) {
        //     case Testi:
        //         ArrayList<OptionsModel> optionsModels = DB_GetQuestionOptions(QuestionId);

        //         int AnswerId = Integer.parseInt(answersModel.Answer);
        //         for (OptionsModel optionsModel : optionsModels) {
        //             if (optionsModel.Id == AnswerId) {
        //                 optionsModel.UserAnswer = true;
        //                 optionsModels.set(optionsModels.indexOf(optionsModel), optionsModel);
        //             }
        //         }

        //         return optionsModels;

        //     case Tashrihi:
        //         return answersModel.Answer;

        //     case TrueFalse:
        //         return Boolean.parseBoolean(answersModel.Answer);
        // }
        // return DB_SubmitAnswer(answersModel);
    }

    @Override
    public boolean SubmitAnswer(AnswersModel answersModel) {
        if(answersModel.FileAddress != null)
        {
            UploadFile(answersModel.FileAddress);
        }
        return DB_SubmitAnswer(answersModel);
    }

    @Override
    public boolean EditAnswer(AnswersModel answersModel) {
        if(answersModel.FileAddress != null)
        {
            UploadFile(answersModel.FileAddress);
        }
        return DB_EditAnswer(answersModel);
    }

    @Override
    public boolean SubmitSurvey(SurveyModel surveyModel) {
    
        return DB_SubmitSurvey(surveyModel);
    }

    void UploadFile(String address)
    {
        BufferedImage bImage = null;
        try {
            File initialImage = new File(address);
            bImage = ImageIO.read(initialImage);

            String imgname = initialImage.getName();
            File saveTo =new File("AnswerImage/" + imgname);

            ImageIO.write(bImage, "jpg", saveTo);

        } catch (Exception e) {
              System.out.println("Exception occured :" + e.getMessage());
        }
    }


}