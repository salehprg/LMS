package Model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OptionsModel implements Serializable {
    public int Id;
    public int QuestionId;
    public String OptionText;

    public boolean UserAnswer = false;
}