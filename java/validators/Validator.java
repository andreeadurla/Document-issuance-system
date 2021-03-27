package validators;

import exception.InvalidDataException;
import messages.WrongMessage;

public class Validator {

    public static void isNull(Object obj) {
        if(obj == null)
            throw new InvalidDataException(WrongMessage.EMPTY_FIELD);
    }

    public static void isEmptyOrNull(String text) {
        if(text == null || text.isEmpty())
            throw new InvalidDataException(WrongMessage.EMPTY_FIELD);
    }
}
