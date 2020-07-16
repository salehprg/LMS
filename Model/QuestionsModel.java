package Model;

import java.time.LocalDateTime;

public class QuestionsModel {
    public int Id;
    public int QuizId;
    public String QuestionText;
    public QType QuestionType;
    public float Duration;
    public String Answer;
    public float Grade;

    public enum QType{
        Tashrihi(0) ,
        Testi(1) , 
        TrueFalse(2);

        private int Id;

        QType(int Id) {
            this.Id = Id;
        }

        public static QType get(int Id) {
            QType[] qTypes = QType.values();
    
            for(int i = 0; i < qTypes.length; i++) 
            {
                if(qTypes[i].Id == Id)
                    return qTypes[i];
            }
    
            return null;
        }
    }

}