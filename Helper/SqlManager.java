package Helper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import lms.Model.*;
import lms.Model.QuestionsModel.QType;


public class SqlManager {

    Connection conn;
    Statement statement;

    public SqlManager () throws SQLException {
        String connectionUrl = "jdbc:sqlserver://localhost\\SQLSERVER2008R2:1433;databaseName=Javalms;integratedSecurity=true";

        conn = DriverManager.getConnection(connectionUrl);
        statement = conn.createStatement();
    }

//#region Quiz

    protected int DB_CreateQuiz(QuizesModel quizesModel)
    {
        String sqlQuery = String.format("INSERT INTO [dbo].[Quizes]",
        "([StartTime]",
        ",[EndTime]",
        ",[Duration]",
        ",[Random]",
        ",[CanReview])",
        "VALUES",
        "({0},{1},{2},{3},{4})" , quizesModel.StartTime , quizesModel.EndTime , quizesModel.Duration
                                , quizesModel.Random , quizesModel.CanReview);

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

        //Get currently quiz id
        int QuizId = -1;
        sqlQuery = "SELECT * from Quizes ORDER BY Id DESC";

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                QuizId = resultSet.getInt("Id");
            }
            
            return QuizId;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return -1;
    }

    protected int DB_AddQuestion(QuestionsModel questionsModel)
    {
        String sqlQuery = String.format("INSERT INTO [dbo].[Questions]",
        "([QuizId]",
        ",[QuestionText]",
        ",[QuestionType]",
        ",[Duration]",
        ",[Answer]",
        ",[Grade])",
        "VALUES ({0},{1},{2},{3},{4},{5})" , questionsModel.QuizId , questionsModel.QuestionText , questionsModel.QuestionType
                                , questionsModel.Duration , questionsModel.Answer);

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

        //Get currently Question id
        int QuestionId = -1;
        sqlQuery = "SELECT * from Questions ORDER BY Id DESC";

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                QuestionId = resultSet.getInt("Id");
            }
            
            return QuestionId;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    //Used when question Type is testi
    protected boolean DB_AddOptions(OptionsModel optionsModel)
    {
        String sqlQuery = String.format("INSERT INTO [dbo].[Options]",
        "([QuestionId]",
        ",[OptionText])",
        "VALUES ({0},{1})" , optionsModel.QuestionId , optionsModel.OptionText);

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    protected ArrayList<QuestionsModel> DB_GetQuestionInQuiz(int QuizId)
    {
        // Create and execute a SELECT SQL statement.
        String sqlQuery = "SELECT * from Questions Where QuizId = " + QuizId;

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            

            ArrayList<QuestionsModel> questionsModels = new ArrayList<>();
            while (resultSet.next()) {
                QuestionsModel data = new QuestionsModel();

                data.Id = resultSet.getInt("Id");
                data.QuestionText = resultSet.getString("QuestionText");
                data.QuestionType = QType.get(resultSet.getInt("QuestionType"));
                data.Duration = resultSet.getFloat("Duration");
                data.Answer = resultSet.getString("Answer");
                data.Grade = resultSet.getFloat("Grade");

                questionsModels.add(data);
            }
            
            return questionsModels;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    protected boolean DB_AddUserToQuiz(int UserId , int QuizId)
    {
        String sqlQuery = String.format("INSERT INTO [dbo].[AccesList]",
        "([UserId]",
        ",[QuizId]",
        ",[Allow])",
       " VALUES",
        "({0},{1},{2})" , UserId , QuizId , true);

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    protected boolean DB_RemoveUserFromQuiz(int UserId , int QuizId)
    {
        String sqlQuery = String.format("DELETE FROM [dbo].[AccesList]",
        "WHERE UserId = {0} And QuizId = {1}"  , UserId , QuizId , true);

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    protected boolean DB_BanUser(int UserId , int QuizId)
    {
        String sqlQuery = String.format("INSERT INTO [dbo].[AccesList]",
        "([UserId]",
        ",[QuizId]",
        ",[Allow])",
       " VALUES",
        "({0},{1},{2})" , UserId , QuizId , false);

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    protected QuizesModel DB_GetQuizInfo(int QuizId)
    {
        // Create and execute a SELECT SQL statement.
        String sqlQuery = "SELECT * from Quizes Where Id = " + QuizId;

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            QuizesModel data = new QuizesModel();

            while (resultSet.next()) {
                
                data.Id = resultSet.getInt("Id");
                data.QuizName = resultSet.getString("QuizName");
                data.StartTime = resultSet.getDate("StartTime");
                data.EndTime = resultSet.getDate("EndTime");
                data.Duration = resultSet.getFloat("Duration");
                data.Random = resultSet.getBoolean("Random");
                data.CanReview = resultSet.getBoolean("CanReview");
            }
            
            return data;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    protected boolean DB_EnrolUserToQuiz(TriesModel triesModel)
    {
        String sqlQuery = String.format("INSERT INTO [dbo].[Tries]",
        "([QuizId]",
        ",[UserId]",
        ",[TryTime])",
        "VALUES",
        "({0},{1},{2})" , triesModel.QuizId , triesModel.UserId , triesModel.TryTime , true);

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
 
    protected TriesModel DB_GetUserTries(int UserId , int QuizId)
    {
        // Create and execute a SELECT SQL statement.
        String sqlQuery = "SELECT * from Tries Where QuizId= " + QuizId + " UserId=" + UserId;

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            TriesModel data = new TriesModel();

            while (resultSet.next()) {
                
                data.TryTime = resultSet.getDate("TryTime");
            }
            
            return data;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    protected ArrayList<AllowQuizList> DB_GetUserQuizes(int UserId)
    {
        // Create and execute a SELECT SQL statement.
        String sqlQuery = "SELECT * from AllowQuizList Where UserId= " + UserId;

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            ArrayList<AllowQuizList> quizesModels = new ArrayList<>();

            while (resultSet.next()) {
                AllowQuizList data = new AllowQuizList();

                data.QuizId = resultSet.getInt("QuizId");
                data.UserId = resultSet.getInt("UserId");
                data.IdNumber = resultSet.getString("IdNumber");
                data.Allow = resultSet.getBoolean("Allow");
                data.QuizName = resultSet.getString("QuizName");
                data.StartTime = resultSet.getDate("StartTime");
                data.EndTime = resultSet.getDate("EndTime");

                quizesModels.add(data);
            }
            
            return quizesModels;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    protected boolean DB_SubmitAnswer(AnswersModel answersModel)
    {
        String sqlQuery = String.format("INSERT INTO [dbo].[Answers]",
        "([UserId]",
        ",[QuestionId]",
        ",[Answer]",
        ",[UserGrade])",
        "VALUES",
        "({0},{1},{2},{3})" , answersModel.UserId , answersModel.QuestionId , answersModel.Answer , 0);

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    protected AnswersModel DB_GetUserAnswer(int UserId , int QuestionId)
    {
        // Create and execute a SELECT SQL statement.
        String sqlQuery = "SELECT * from Answers Where UserId= " + UserId + " QuestionId=" + QuestionId;

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            AnswersModel data = new AnswersModel();

            while (resultSet.next()) {
                
                data.Id = resultSet.getInt("Id");
                data.Answer = resultSet.getString("Answer");
                data.UserGrade = resultSet.getFloat("UserGrade");
            }
            
            return data;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    protected QuestionsModel DB_GetQuestionInfo(int QuestionId)
    {
        // Create and execute a SELECT SQL statement.
        String sqlQuery = "SELECT * from Questions Where Id = " + QuestionId;

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            QuestionsModel data = new QuestionsModel();

            while (resultSet.next()) {
               

                data.Id = resultSet.getInt("Id");
                data.QuestionText = resultSet.getString("QuestionText");
                data.QuestionType = QType.get(resultSet.getInt("QuestionType"));
                data.Duration = resultSet.getFloat("Duration");
                data.Answer = resultSet.getString("Answer");
                data.Grade = resultSet.getFloat("Grade");
            }
            
            return data;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    //when Question type is testi
    protected ArrayList<OptionsModel> DB_GetQuestionOptions(int QuestionId)
    {
        // Create and execute a SELECT SQL statement.
        String sqlQuery = "SELECT * from Options Where QuestionId = " + QuestionId;

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            ArrayList<OptionsModel> optionsModels = new ArrayList<>();

            while (resultSet.next()) {
                OptionsModel data = new OptionsModel();

                data.Id = resultSet.getInt("Id");
                data.OptionText = resultSet.getString("OptionText");

                optionsModels.add(data);
            }
            
            return optionsModels;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    protected ArrayList<AnswersForGrading> DB_GetAnswersForGrading(int QuizId , int UserId)
    {
        // Create and execute a SELECT SQL statement.
        String sqlQuery = "SELECT * from AnswersForGrading Where UserId = " + UserId + " And QuizeId=" + QuizId;
        ArrayList<AnswersForGrading> answersForGradings = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                
                AnswersForGrading data = new AnswersForGrading();

                data.Answer = resultSet.getString("Answer");
                data.AnswerId = resultSet.getInt("AnswerId");
                data.FirstName = resultSet.getString("FirstName");
                data.LastName = resultSet.getString("LastName");
                data.Grade = resultSet.getFloat("Grade");
                data.IdNumber = resultSet.getString("IdNumber");
                data.QuestionAnswer = resultSet.getString("QuestionAnswer");
                data.QuestionId = resultSet.getInt("QuestionId");
                data.QuestionText = resultSet.getString("QuestionText");
                data.QuestionType = QType.get(resultSet.getInt("QuestionType"));
                data.QuizName = resultSet.getString("QuizName");
                data.QuizeId = resultSet.getInt("QuizeId");
                data.UserGrade = resultSet.getFloat("UserGrade");
                data.UserId = resultSet.getInt("UserId");

                answersForGradings.add(data);
            }
            
            return answersForGradings;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    //#endregion

    protected UserModel DB_GetUserInfo(String IdNumber)
    {
        // Create and execute a SELECT SQL statement.
        String sqlQuery = "SELECT * from Users Where IdNumber = " + IdNumber;

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            UserModel data = new UserModel();

            while (resultSet.next()) {
                
                data.Id = resultSet.getInt("Id");
                data.FirstName = resultSet.getString("FirstName");
                data.LastName = resultSet.getString("LastName");
                data.IdNumber = resultSet.getString("IdNumber");
                data.UserName = resultSet.getString("UserName");
                data.Password = resultSet.getString("Password");
            }
            
            return data;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    protected ArrayList<UserModel> DB_GetAllUsers()
    {
        // Create and execute a SELECT SQL statement.
        String sqlQuery = "SELECT * from Users";

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            ArrayList<UserModel> userModels = new ArrayList<>();

            while (resultSet.next()) {
                UserModel data = new UserModel();

                data.Id = resultSet.getInt("Id");
                data.FirstName = resultSet.getString("FirstName");
                data.LastName = resultSet.getString("LastName");
                data.IdNumber = resultSet.getString("IdNumber");
                data.UserName = resultSet.getString("UserName");
                data.Password = resultSet.getString("Password");

                userModels.add(data);
            }
            
            return userModels;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    protected boolean DB_AddUser(UserModel userModel) 
    {
        String sqlQuery = String.format("INSERT INTO [dbo].[Users]",
        "([FirstName]",
        ",[LastName]",
        ",[IdNumber]",
        ",[UserName]",
        ",[Password]",
        ",[RoleId])",
        "VALUES",
        "({0},{1},{2},{3},{4},{5})" , userModel.FirstName , userModel.LastName , userModel.IdNumber , userModel.UserName ,
                                        userModel.Password , userModel.RoleId);

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


}