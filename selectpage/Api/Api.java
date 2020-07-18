package selectpage.Api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;

import Interface.IAdminController;
import Interface.IUserController;
import Model.*;
import java.util.Date;

public class Api {

    public static RequestList requestList;

    private static int port = 9876;
    private static ObjectInputStream fromServer;
    private static ObjectOutputStream toServer;
    private static Socket clientSocket;
    

    public static int ActiveUserId;
    public static int CurrentQuizId;
    public static String WarningMassage ;
    
    static boolean SendRequest(int funcId, Object[] dataObjects) {
        try {

            clientSocket = new Socket("localhost", port);

            toServer = new ObjectOutputStream(clientSocket.getOutputStream());
            fromServer = new ObjectInputStream(clientSocket.getInputStream());

            toServer.writeObject(funcId);

            for (Object object : dataObjects) {
                toServer.writeObject(object);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    static Object ReadResponse() {
        try 
        {
            return fromServer.readObject();
        } 
        catch (ClassNotFoundException | IOException e) 
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            try {
                toServer.close();
                fromServer.close();
                clientSocket.close();

            } catch (IOException e) {

                e.printStackTrace();
            }
            
        }

        
    }

    
    public static ArrayList<QuestionsModel> Admin_GetQuestions(int QuizId) {
        if(SendRequest(RequestList.Admin_getQuestions.getId(), new Object[] { QuizId }))
        {
            return (ArrayList<QuestionsModel>)ReadResponse();
        }
        return null;
    }

    public static ArrayList<QuizSurvey> Admin_getQuizSurvey(int QuizId)
    {
        if(SendRequest(RequestList.Admin_getQuizSurvey.getId(),new Object[]{QuizId}))
        {
            return (ArrayList<QuizSurvey>)ReadResponse();
        }
        return null;
    }

    public static boolean User_submitSurvey(SurveyModel surveyModel)
    {
        if(SendRequest(RequestList.User_SubmitSurvey.getId(), new Object[]{surveyModel}))
        {
            return (boolean)ReadResponse();
        }
        return false;
    }

    public static ArrayList<QuizesModel> Admin_getAllQuiz()
    {
        if(SendRequest(RequestList.Admin_getAllQuiz.getId(),null))
        {
            return (ArrayList<QuizesModel>)ReadResponse();
        }
        return null;
    }

    public static ArrayList<QuizesModel> Admin_getQuizArchive()
    {
        if(SendRequest(RequestList.Admin_getQuizArchive.getId(), null))
        {
            return (ArrayList<QuizesModel>)ReadResponse();
        }
        return null;
    }

    public static ArrayList<QuizesModel> Admin_getQuizProgram()
    {
        if(SendRequest(RequestList.Admin_getQuizProgram.getId(),null ))
        {
            return (ArrayList<QuizesModel>)ReadResponse();
        }
        return null;
    }

    public static boolean Admin_AutoGrading(int QuizId)
    {
        if(SendRequest(RequestList.Admin_autoGrading.getId(), new Object[]{QuizId}))
        {
            return (boolean)ReadResponse();
        }
        return false;
    }

    public static boolean Admin_saveExcelQuizByQuiz()
    {
        if(SendRequest(RequestList.Admin_saveExcelQuizByQuiz.getId(), null))
        {
            return (boolean)ReadResponse();
        }
        return false;
    }

    public static boolean Admin_saveExcelUserGrades(int QuizId)
    {
        if(SendRequest(RequestList.Admin_saveExcelUserGrades.getId(), new Object[] { QuizId }))
        {
            return (boolean)ReadResponse();
        }
        return false;
    }

    public static ArrayList<UserGradesInQuiz> Admin_reportStudentByStudent(int QuizId)
    {
        if(SendRequest(RequestList.Admin_reportStudentByStudent.getId(), new Object[] { QuizId }))
        {
            return (ArrayList<UserGradesInQuiz>)ReadResponse();
        }
        return null;
    }

    public static ArrayList<AvgQuizGrade> Admin_reportQuizByQuiz()
    {
        if(SendRequest(RequestList.Admin_reportQuizByQuiz.getId(), null))
        {
            return (ArrayList<AvgQuizGrade>)ReadResponse();
        }
        return null;
    }

    public static boolean AddUserFromExcel(String fileUrl)
    {
        if(SendRequest(RequestList.Admin_AdduserFromExcel.getId(), new Object[] { fileUrl }))
        {
            return (boolean)ReadResponse();
        }
        return false;
    }

    public static int createNewQuiz(QuizesModel quizesModel) {
        
        if(SendRequest(RequestList.Admin_createNewQuiz.getId(), new Object[] { quizesModel }))
        {
            return (int)ReadResponse();
        }
        return -1;
    }

    
    public static boolean addQuestionToQuiz(QuestionsModel questionsModel) {
    
        
        
        
        if(SendRequest(RequestList.Admin_addQuestionToQuiz.getId(), new Object[] { questionsModel }))
        {
            return (boolean)ReadResponse();
        }
        return false;
    }

    
    public static boolean addQuestionToQuiz(QuestionsModel questionsModel, ArrayList<OptionsModel> optionsModels) {
        if(SendRequest(RequestList.Admin_addQuestionToQuizWithOption.getId(), new Object[] { questionsModel , optionsModels }))
        {
            return (boolean)ReadResponse();
        }
        return false;
    }

    
    public static boolean AssignUserToQuiz(UserModel _userModel, int QuizId) {
        if(SendRequest(RequestList.Admin_AssignUserToQuiz.getId(), new Object[] { _userModel , QuizId }))
        {
            return (boolean)ReadResponse();
        }
        return false;
    }

    
    public static boolean RemoveUserFromQuiz(int userId, int QuizId) {
        if(SendRequest(RequestList.Admin_RemoveUserFromQuiz.getId(), new Object[] { userId , QuizId }))
        {
            return (boolean)ReadResponse();
        }
        return false;
    }

    
    public static boolean BanUserFromQuiz(String IdNumber, int QuizId) {
        if(SendRequest(RequestList.Admin_BanUserFromQuiz.getId(), new Object[] { IdNumber , QuizId}))
        {
            return (boolean)ReadResponse();
        }
        return false;
    }

    
    public static ArrayList<AnswersForGrading> getUserAnswers(int UserId, int QuizId) {
        if(SendRequest(RequestList.Admin_Grading_getUserAnswers.getId(), new Object[] { UserId , QuizId}))
        {
            return (ArrayList<AnswersForGrading>)ReadResponse();
        }
        return null;
    }

    
    public static ArrayList<QuestionsModel> Admin_getQuestions_Grading(int QuizId) {
        if(SendRequest(RequestList.Admin_Grading_getQuestions.getId(), new Object[] { QuizId }))
        {
            return (ArrayList<QuestionsModel>)ReadResponse();
        }
        return null;
    }

    
    public static boolean Register(UserModel userModel) {
        if(SendRequest(RequestList.User_Register.getId(), new Object[] { userModel }))
        {
            return (boolean)ReadResponse();
        }
        return false;
    }

    /**
     * Return userId if Login Info Valid 
     * otherwise Return -1
     * @param UserName
     * @param Password
     * @return
     */
    public static int Login(String UserName, String Password) {

        if(SendRequest(RequestList.User_Login.getId(), new Object[] { UserName , Password }))
        {
            int responseId = (int)ReadResponse();
            if(responseId != -1)
            {
                ActiveUserId = responseId;
                return responseId;
            }
        }
        return -1;
    }

    
    public static boolean EnrolQuiz(int QuizId) {
        if(SendRequest(RequestList.User_EnrolQuiz.getId(), new Object[] { ActiveUserId , QuizId}))
        {
            return (boolean)ReadResponse();
        }
        return false;
    }

    
    //public static ArrayList<AllowQuizList> GetQuizes(int UserId) {
    public static ArrayList<AllowQuizList> GetQuizes() {
        if(SendRequest(RequestList.Admin_addQuestionToQuiz.getId(), new Object[] { ActiveUserId }))
        {
            return (ArrayList<AllowQuizList>)ReadResponse();
        }
        return null;
    }

    
    public static ArrayList<QuestionsModel> User_GetQuestions(int QuizId) {
        if(SendRequest(RequestList.User_GetQuestions.getId(), new Object[] { QuizId }))
        {
            return (ArrayList<QuestionsModel>)ReadResponse();
        }
        return null;
    }

    public static ArrayList<OptionsModel> GetOptionss(int QuestionId) {
        if(SendRequest(RequestList.Admin_getOptions.getId(), new Object[] { QuestionId }))
        {
            return (ArrayList<OptionsModel>)ReadResponse();
        }
        return null;
    }
    
    public static ArrayList<QuestionsModel> Review_GetQuestions(int QuizId) {
        if(SendRequest(RequestList.User_Review_GetQuestions.getId(), new Object[] { ActiveUserId }))
        {
            return (ArrayList<QuestionsModel>)ReadResponse();
        }
        return null;
    }

    
    //public static Object Review_GetUserAnswer(int UserId, int QuestionId) {
    public static Object Review_GetUserAnswer(int QuestionId) {
        if(SendRequest(RequestList.User_Review_GetUserAnswer.getId(), new Object[] { ActiveUserId , QuestionId }))
        {
            return (Object)ReadResponse();
        }
        return null;
    }

    
    public static boolean SubmitAnswer(AnswersModel answersModel) {
        if(SendRequest(RequestList.User_SubmitAnswer.getId(), new Object[] { answersModel }))
        {
            return (boolean)ReadResponse();
        }
        return false;
    }

}
