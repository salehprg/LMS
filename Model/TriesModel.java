package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class TriesModel  implements Serializable {
    public int Id;
    public int UserId;
    public int QuizId;
    public Date TryTime;

}