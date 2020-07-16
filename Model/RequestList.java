package Model;

public enum RequestList{
    User_Register(0),
    User_Login (1), 
    User_EnrolQuiz(2),
    User_GetQuizes(3),
    User_GetQuestions(4),
    User_Review_GetQuestions(5),
    User_Review_GetUserAnswer(6),
    User_SubmitAnswer(7),
    Admin_createNewQuiz(8),
    Admin_addQuestionToQuiz(9),
    Admin_addQuestionToQuizWithOption(10),
    Admin_AssignUserToQuiz(11),
    Admin_RemoveUserFromQuiz(12),
    Admin_BanUserFromQuiz(13),
    Admin_Grading_getUserAnswers(14),
    Admin_Grading_getQuestions(15);

    private int Id;

    RequestList(int Id) {
        this.Id = Id;
    }

    public static RequestList get(int Id) {
        RequestList[] requests = RequestList.values();

        for(int i = 0; i < requests.length; i++) 
        {
            if(requests[i].Id == Id)
                return requests[i];
        }

        return null;
    }

    public int getId() {
        return Id;
    }

}