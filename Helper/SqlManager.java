package Helper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import Model.*;
import Model.QuestionsModel.QType;


public class SqlManager {

    Connection conn;
    Statement statement;

    public SqlManager () throws SQLException {

        String connectionUrl = "jdbc:sqlserver://localhost\\SQLSERVER2008R2:1433;databaseName=Javalms;integratedSecurity=true";

        conn = DriverManager.getConnection(connectionUrl);
        statement = conn.createStatement();
    }

    //#region Quiz

    protected ArrayList<QuizesModel> DB_GetQuizes()
    {
        // Create and execute a SELECT SQL statement.
        String sqlQuery = "SELECT * from Quizes";

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            

            ArrayList<QuizesModel> quizesModels = new ArrayList<>();
            
            while (resultSet.next()) {
                QuizesModel data = new QuizesModel();

                data.Id = resultSet.getInt("Id");
                data.QuizName = resultSet.getString("QuizName");
                data.StartTime = new Date();
                data.StartTime.setTime(resultSet.getTime("StartTime").getTime());
                data.StartTime.setYear(resultSet.getDate("StartTime").getYear());
                data.StartTime.setMonth(resultSet.getDate("StartTime").getMonth());
                data.StartTime.setDate(resultSet.getDate("StartTime").getDate());

                data.EndTime = new Date();
                data.EndTime.setTime(resultSet.getTime("EndTime").getTime());
                data.EndTime.setYear(resultSet.getDate("EndTime").getYear());
                data.EndTime.setMonth(resultSet.getDate("EndTime").getMonth());
                data.EndTime.setDate(resultSet.getDate("EndTime").getDate());

                data.Duration = resultSet.getFloat("Duration");
                data.Random = resultSet.getBoolean("Random");
                data.CanReview = resultSet.getBoolean("CanReview");

                quizesModels.add(data);
            }
            
            return quizesModels;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

        
    protected int DB_CreateQuiz(QuizesModel quizesModel)
    {
        String sqlQuery = String.format("INSERT INTO [dbo].[Quizes]"+
        "([QuizName]"+
        ",[StartTime]"+
        ",[EndTime]"+
        ",[Duration]"+
        ",[Random]"+
        ",[CanReview])"+
        "VALUES"+
        "('%s' , '%tF %tT','%tF %tT','%s','%s','%s')" ,quizesModel.QuizName , quizesModel.StartTime , quizesModel.StartTime , quizesModel.EndTime , quizesModel.EndTime , quizesModel.Duration
                                , quizesModel.Random , quizesModel.CanReview);

        Boolean result = false;

        try {
            statement.execute(sqlQuery);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }

        if(result)
        {

            //Get currently quiz id
            int QuizId = -1;
            sqlQuery = "SELECT * from Quizes ORDER BY Id Asc";

            try {
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                while (resultSet.next()) {
                    QuizId = resultSet.getInt("Id");
                }
                
                return QuizId;
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return -1;
    }

    protected int DB_AddQuestion(QuestionsModel questionsModel)
    {
        String sqlQuery = String.format("INSERT INTO [dbo].[Questions]"+
        "([QuizId]"+
        ",[QuestionText]"+
        ",[QuestionType]"+
        ",[Duration]"+
        ",[Answer]"+
        ",[Grade])"+
        "VALUES ('%s','%s','%s','%s','%s','%s')" , questionsModel.QuizId , questionsModel.QuestionText , questionsModel.QuestionType.getId()
                                , questionsModel.Duration , questionsModel.Answer , questionsModel.Grade);

        Boolean result = false;

        try {
            statement.execute(sqlQuery);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }

        if(result)
        {
            //Get currently Question id
            int QuestionId = -1;
            sqlQuery = "SELECT * from Questions ORDER BY Id Asc";

            try {
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                while (resultSet.next()) {
                    QuestionId = resultSet.getInt("Id");
                }
                
                return QuestionId;
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return -1;
    }

    protected int DB_EditQuestion(QuestionsModel questionsModel)
    {
        String sqlQuery = String.format("UPDATE [dbo].[Questions]"+
        "SET [QuizId] = %s"+
        ",[QuestionText] = %s"+
        ",[QuestionType] = %s"+
        ",[Duration] = %s"+
        ",[Answer] = %s"+
        ",[Grade] = %s"+
        "WHERE Id = %s" , questionsModel.QuizId , questionsModel.QuestionText , questionsModel.QuestionType.getId()
                                , questionsModel.Duration , questionsModel.Answer , questionsModel.Grade , questionsModel.QuizId);

        Boolean result = false;

        try {
            statement.execute(sqlQuery);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }

        if(result)
        {
            //Get currently Question id
            int QuestionId = -1;
            sqlQuery = "SELECT * from Questions ORDER BY Id Asc";

            try {
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                while (resultSet.next()) {
                    QuestionId = resultSet.getInt("Id");
                }
                
                return QuestionId;
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return -1;
    }


    //Used when question Type is testi
    protected int DB_AddOptions(OptionsModel optionsModel)
    {
        String sqlQuery = String.format("INSERT INTO [dbo].[Options]"+
        "([QuestionId]"+
        ",[OptionText])"+
        "VALUES ('%s','%s')" , optionsModel.QuestionId , optionsModel.OptionText);

        Boolean result = false;

        try {
            statement.execute(sqlQuery);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }

        if(result)
        {
            //Get currently Question id
            int OptionId = -1;
            sqlQuery = "SELECT * from Options ORDER BY Id Asc";

            try {
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                while (resultSet.next()) {
                    OptionId = resultSet.getInt("Id");
                }
                
                return OptionId;
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return -1;
    }

    protected ArrayList<QuestionsModel> DB_GetQuestionInQuiz(int QuizId)
    {
        // Create and execute a SELECT SQL statement.
        String sqlQuery = "SELECT * from Questions Where QuizId = '" + QuizId + "'";

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
        String sqlQuery = String.format("INSERT INTO [dbo].[AccesList]"+
        "([UserId]"+
        ",[QuizId]"+
        ",[Allow])"+
       " VALUES "+
        "('%s','%s','%s')" , UserId , QuizId , true);

        Boolean result = false;

        try {
            statement.execute(sqlQuery);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    protected boolean DB_RemoveUserFromQuiz(int UserId , int QuizId)
    {
        String sqlQuery = String.format("DELETE FROM [dbo].[AccesList]"+
        "WHERE UserId = '%s' And QuizId = '%s'"  , UserId , QuizId , true);

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
        String sqlQuery = String.format("INSERT INTO [dbo].[AccesList]"+
        "([UserId]"+
        ",[QuizId]"+
        ",[Allow])"+
       " VALUES",
        "('%s','%s','%s')" , UserId , QuizId , false);

        Boolean result = false;

        try {
            statement.execute(sqlQuery);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
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
                data.StartTime = new Date();
                data.StartTime.setTime(resultSet.getTime("StartTime").getTime());
                data.StartTime.setYear(resultSet.getDate("StartTime").getYear());
                data.StartTime.setMonth(resultSet.getDate("StartTime").getMonth());
                data.StartTime.setDate(resultSet.getDate("StartTime").getDate());

                data.EndTime = new Date();
                data.EndTime.setTime(resultSet.getTime("EndTime").getTime());
                data.EndTime.setYear(resultSet.getDate("EndTime").getYear());
                data.EndTime.setMonth(resultSet.getDate("EndTime").getMonth());
                data.EndTime.setDate(resultSet.getDate("EndTime").getDate());

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
        String sqlQuery = String.format("INSERT INTO [dbo].[Tries]"+
        "([QuizId]"+
        ",[UserId]"+
        ",[TryTime])"+
        "VALUES"+
        "('%s','%s','%tF %tT')" , triesModel.QuizId , triesModel.UserId , triesModel.TryTime , triesModel.TryTime , true);

        Boolean result = false;

        try {
            statement.execute(sqlQuery);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }
 
    protected TriesModel DB_GetUserTries(int UserId , int QuizId)
    {
        // Create and execute a SELECT SQL statement.
        String sqlQuery = "SELECT * from Tries Where QuizId= " + QuizId + " And UserId=" + UserId;

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            TriesModel data = new TriesModel();

            while (resultSet.next()) {
                data.TryTime = new Date();
                data.TryTime.setTime(resultSet.getTime("TryTime").getTime());
                data.TryTime.setYear(resultSet.getDate("TryTime").getYear());
                data.TryTime.setMonth(resultSet.getDate("TryTime").getMonth());
                data.TryTime.setDate(resultSet.getDate("TryTime").getDate());
                
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
                data.StartTime = new Date();
                data.StartTime.setTime(resultSet.getTime("StartTime").getTime());
                data.StartTime.setYear(resultSet.getDate("StartTime").getYear());
                data.StartTime.setMonth(resultSet.getDate("StartTime").getMonth());
                data.StartTime.setDate(resultSet.getDate("StartTime").getDate());

                data.EndTime = new Date();
                data.EndTime.setTime(resultSet.getTime("EndTime").getTime());
                data.EndTime.setYear(resultSet.getDate("EndTime").getYear());
                data.EndTime.setMonth(resultSet.getDate("EndTime").getMonth());
                data.EndTime.setDate(resultSet.getDate("EndTime").getDate());

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
        String sqlQuery = String.format("INSERT INTO [dbo].[Answers]"+
        "([UserId]"+
        ",[QuestionId]"+
        ",[Answer]"+
        ",[UserGrade]"+
        ",[FileAddress])"+
        "VALUES"+
        "('%s','%s','%s','%s', '%s')" , answersModel.UserId , answersModel.QuestionId , answersModel.Answer , 0 , answersModel.FileAddress);

        Boolean result = false;

        try {
            statement.execute(sqlQuery);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
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
                data.FileAddress = resultSet.getString("FileAddress");

                answersForGradings.add(data);
            }
            
            return answersForGradings;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    protected ArrayList<TriesModel> DB_GetTries(int QuizId )
    {
        // Create and execute a SELECT SQL statement.
        String sqlQuery = "SELECT * from Tries Where QuizId = " + QuizId ;
        ArrayList<TriesModel> triesModels = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                
                TriesModel data = new TriesModel();

                data.QuizId = resultSet.getInt("QuizId");
                data.TryTime = new Date();
                data.TryTime.setTime(resultSet.getTime("TryTime").getTime());
                data.TryTime.setYear(resultSet.getDate("TryTime").getYear());
                data.TryTime.setMonth(resultSet.getDate("TryTime").getMonth());
                data.TryTime.setDate(resultSet.getDate("TryTime").getDate());
                data.UserId = resultSet.getInt("UserId");
                

                triesModels.add(data);
            }
            
            return triesModels;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    protected ArrayList<UserModel> DB_GetUserInQuiz(int QuizId )
    {
        // Create and execute a SELECT SQL statement.
        String sqlQuery = "SELECT * from TriesView Where QuizId = " + QuizId ;
        ArrayList<UserModel> userModels = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                
                UserModel data = new UserModel();

                data.Id = resultSet.getInt("UserId");
                data.FirstName = resultSet.getString("FirstName");
                data.LastName= resultSet.getString("LastName");
                data.IdNumber = resultSet.getString("IdNumber");
                
                userModels.add(data);
            }
            
            return userModels;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    

    protected boolean DB_GradeAnswer(float grade , int answerId)
    {
        // Create and execute a Update SQL statement.
        String sqlQuery = String.format("UPDATE Answers SET [UserGrade] = '%s' , [Graded] = 'true' where Id = '%s'" , grade , answerId);

        Boolean result = false;

        try {
            statement.execute(sqlQuery);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        
        return result;
    }
    

    //#endregion

    //#region Reporting
    protected ArrayList<UserGradesInQuiz> DB_GetUserGradesInQuiz(int QuizId)
    {
        // Create and execute a SELECT SQL statement.
        String sqlQuery = "SELECT * from UserGradesInQuiz Where QuizId = " + QuizId;

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            ArrayList<UserGradesInQuiz> userGrades = new ArrayList<>();

            while (resultSet.next()) {
                UserGradesInQuiz data = new UserGradesInQuiz();

                data.UserGrade = resultSet.getFloat("UserGrade");
                data.IdNumber = resultSet.getString("IdNumber");
                data.QuizId = resultSet.getInt("QuizId");
                data.FirstName = resultSet.getString("FirstName");
                data.LastName = resultSet.getString("LastName");

                userGrades.add(data);
            }
            
            return userGrades;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    protected ArrayList<AvgQuizGrade> DB_AvgQuizGrade()
    {
        // Create and execute a SELECT SQL statement.
        String sqlQuery = "SELECT * from AvgQuizGrade";

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            ArrayList<AvgQuizGrade> avgQuizGrade = new ArrayList<>();

            while (resultSet.next()) {
                AvgQuizGrade data = new AvgQuizGrade();

                data.QuizName = resultSet.getString("QuizName");
                data.avgGrade = resultSet.getFloat("AvgGrade");
                data.quizId = resultSet.getInt("QuizId");

                avgQuizGrade.add(data);
            }
            
            return avgQuizGrade;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    //#endregion

    protected UserModel DB_GetUserInfo(String IdNumber)
    {
        // Create and execute a SELECT SQL statement.
        String sqlQuery = "SELECT * from Users Where IdNumber = '" + IdNumber + "'";

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

    protected int DB_AddUser(UserModel userModel) 
    {
        String sqlQuery = String.format("INSERT INTO Users "+
        "([FirstName]"+
        ",[LastName]"+
        ",[IdNumber]"+
        ",[UserName]"+
        ",[Password]"+
        ",[RoleId]) "+
        "VALUES "+
        "('%s','%s','%s','%s','%s','%s')" , userModel.FirstName , userModel.LastName , userModel.IdNumber , userModel.UserName ,
                                        userModel.Password , userModel.RoleId);

        Boolean result = false;

        try {
            statement.execute(sqlQuery);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
                                

        if(result)
        {
            //Get currently quiz id
            int UserId = -1;
            sqlQuery = "SELECT * from Users ORDER BY Id Asc";

            try {
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                while (resultSet.next()) {
                    UserId = resultSet.getInt("Id");
                }
                
                return UserId;
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return -1;
    }

    protected boolean DB_SubmitSurvey(SurveyModel surveyModel)
    {
        String sqlQuery = String.format("INSERT INTO [dbo].[Survey]"+
        "([UserId]"+
        ",[QuizId]"+
        ",[QuizLevel])"+
        "VALUES"+
        "('%s' ,' %s' , '%s')" , surveyModel.UserId , surveyModel.QuizId , surveyModel.QuizLevel );

        Boolean result = false;

        try {
            statement.execute(sqlQuery);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    protected ArrayList<QuizSurvey> DB_GetSurvey(int QuizId)
    {
        String sqlQuery = "Select * From QuizSurvey where QuizId ="+ QuizId;

        ArrayList<QuizSurvey> quizSurveys = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
           

            while (resultSet.next()) {
                QuizSurvey data = new QuizSurvey();

                data.QuizId = resultSet.getInt("QuizId");
                data.QuizName = resultSet.getString("QuizName");
                data.AvgQuizLvl = resultSet.getInt("AvgQuizLvl");

                quizSurveys.add(data);
            }
            
            return quizSurveys;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}