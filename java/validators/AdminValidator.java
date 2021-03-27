package validators;

import exception.InvalidDataException;
import messages.WrongMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminValidator extends Validator{

    public static void validateEmail(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if(matcher.matches() == false)
            throw new InvalidDataException(WrongMessage.INVALID_EMAIL);
    }

    public static void validatePassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,30}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        if(matcher.matches() == false)
            throw new InvalidDataException(WrongMessage.INVALID_PASSWORD);
    }
}
