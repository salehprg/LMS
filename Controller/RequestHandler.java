package Controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Model.*;

public class RequestHandler implements Runnable {

    private final Socket clientSocket;
    private final ServerSocket serverSocket;
    private int clientNumber;

    private ObjectInputStream fromClient;
    private ObjectOutputStream toClient;

    // constructor
    public RequestHandler(Socket clientSocket, int clientNumber, ServerSocket serverSocket) {
        this.clientSocket = clientSocket;
        this.clientNumber = clientNumber;
        this.serverSocket = serverSocket;

        System.out.println("New connection with client #" + clientNumber + " at " + clientSocket);
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    // main brains
    public void run() {
        int funcId;

        try {
            // initialize input and output streams
            toClient = new ObjectOutputStream(clientSocket.getOutputStream());
            fromClient = new ObjectInputStream(clientSocket.getInputStream());


            while (true) {
                // read and parse client response
                funcId = (int) fromClient.readObject();

                handleData(funcId);
            }

        } catch (Exception e) {
            System.out.println("Error handling client #" + clientNumber + ": " + e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Error closing client connection");
            }
        }

        System.out.println("Connection with client #" + clientNumber + " has been terminated.");
    }

    void handleData(int funcId) {
        Object response = null;
        RequestList requestList = RequestList.get(funcId);

        
        try {
            AdminController adminController;
            UserController userController = new UserController();
        
            adminController = new AdminController();
            userController = new UserController();

            switch (requestList)
            {
                case Admin_AssignUserToQuiz:
                    UserModel userModel = (UserModel)fromClient.readObject();
                    int quizId = (int)fromClient.readObject();

                    response = adminController.AssignUserToQuiz(userModel, quizId);
                    break;

                case Admin_BanUserFromQuiz:
                    String idNumber = (String)fromClient.readObject();
                    quizId = (int)fromClient.readObject();

                    response = adminController.BanUserFromQuiz(idNumber, quizId);
                    break;

                case Admin_Grading_getQuestions:
                    quizId = (int)fromClient.readObject();

                    response = adminController.getQuestions(quizId);
                    break;

                case Admin_Grading_getUserAnswers:
                    int IdUser = (int)fromClient.readObject();
                    quizId = (int)fromClient.readObject();

                    response = adminController.getUserAnswers(IdUser, quizId);
                    break;

                case Admin_RemoveUserFromQuiz:
                    IdUser = (int)fromClient.readObject();
                    quizId = (int)fromClient.readObject();

                    response = adminController.RemoveUserFromQuiz(IdUser, quizId);
                    break;

                case Admin_addQuestionToQuiz:
                    QuestionsModel questionsModel = (QuestionsModel)fromClient.readObject();

                    response = adminController.addQuestionToQuiz(questionsModel);
                    break;

                case Admin_addQuestionToQuizWithOption:
                    questionsModel = (QuestionsModel)fromClient.readObject();
                    ArrayList<OptionsModel> optionsModel = (ArrayList<OptionsModel>)fromClient.readObject();

                    response = adminController.addQuestionToQuiz(questionsModel, optionsModel);
                    break;

                case Admin_createNewQuiz:
                    QuizesModel quizesModel = (QuizesModel)fromClient.readObject();

                    response = adminController.createNewQuiz(quizesModel);
                    break;

                case Admin_AdduserFromExcel:
                    String fileUrl = (String)fromClient.readObject();

                    response = adminController.AdduserFromExcel(fileUrl);
                    break;

                case Admin_getOptions:
                    int QuestionId = (int)fromClient.readObject();
                    response = adminController.getOptions(QuestionId);
                    break;

                case Admin_reportQuizByQuiz:
                    response = adminController.reportQuizByQuiz();
                    break;

                case Admin_reportStudentByStudent:
                    int QuizId = (int)fromClient.readObject();
                    response = adminController.reportStudentByStudent(QuizId);
                    break;

                case Admin_saveExcelQuizByQuiz:
                    response = adminController.saveExcelQuizByQuiz();
                    break;

                case Admin_saveExcelUserGrades:
                    QuizId = (int)fromClient.readObject();
                    response = adminController.saveExcelUserGrades(QuizId);
                    break;
                    
                case Admin_autoGrading:
                    QuizId = (int)fromClient.readObject();
                    response = adminController.autoGrading(QuizId);
                    break;

                case Admin_getAllQuiz:
                    response = adminController.getAllQuiz();
                    break;

                case Admin_getQuizArchive:
                    response = adminController.getQuizArchive();
                    break;

                case Admin_getQuizProgram:
                    response = adminController.getQuizProgram();
                    break;

                case Admin_getQuizSurvey:
                    QuizId = (int)fromClient.readObject();
                    response = adminController.getQuizSurvey(QuizId);

                    break;

                case Admin_getUsersInQuiz:
                    QuizId = (int)fromClient.readObject();
                    response = adminController.getUsersInQuiz(QuizId);
                    break;

                case Admin_getQuestions:
                    QuizId = (int)fromClient.readObject();
                    response = adminController.getQuestions(QuizId);
                    break;

                case Admin_SubmitGrade:
                    int AnswerId = (int)fromClient.readObject();
                    float UserGrade = (float)fromClient.readObject();
                    response = adminController.SubmitGrade(AnswerId, UserGrade);
                    
                    break;
                case User_EnrolQuiz:
                    IdUser = (int)fromClient.readObject();
                    quizId = (int)fromClient.readObject();

                    response = userController.EnrolQuiz(IdUser , quizId);
                    break;

                case User_Login:
                    String userName = (String)fromClient.readObject();
                    String password = (String)fromClient.readObject();

                    response = userController.Login(userName , password);
                    break;

                case User_Register:
                    UserModel useModel = (UserModel)fromClient.readObject();

                    response = userController.Register(useModel);
                    break;

                case User_Review_GetQuestions:
                    quizId = (int)fromClient.readObject();

                    response = userController.Review_GetQuestions(quizId);
                    break;

                case User_Review_GetUserAnswer:
                    IdUser = (int)fromClient.readObject();
                    int questionId = (int)fromClient.readObject();

                    response = userController.Review_GetUserAnswer(IdUser , questionId);
                    break;

                case User_SubmitAnswer:
                    
                    AnswersModel answersModel = (AnswersModel)fromClient.readObject();

                    response = userController.SubmitAnswer(answersModel);
                    break;

                case User_GetQuestions:
                    quizId = (int)fromClient.readObject();

                    response = userController.GetQuestions(quizId);
                    break;

                case User_GetQuizes:
                    IdUser = (int)fromClient.readObject();

                    response = userController.GetQuizes(IdUser);
                    break;

                case User_SubmitSurvey:
                    SurveyModel surveyModel = (SurveyModel)fromClient.readObject();
                    response = userController.SubmitSurvey(surveyModel);
                    break;
                    
                default:
                    break;
                
                
            }
            
            toClient.writeObject(response);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}