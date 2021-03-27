package validators;

import entity.Status_level;
import exception.InvalidDataException;
import messages.WrongMessage;

public class RequestValidator extends Validator{

    public static void validatePurpose(String purpose) {
        if(purpose == null || purpose.isEmpty())
            throw  new InvalidDataException(WrongMessage.WRONG_PURPOSE);
    }

    public static void isInProcessing(String status) {
        if(status.equals(Status_level.IN_PROCESSING.toString()) == false)
            throw new InvalidDataException(WrongMessage.INVALID_STATUS);
    }

    public static void validateStatus(String status) {

        if(status.equals(Status_level.IN_PROCESSING.toString()) == false &&
                status.equals(Status_level.ACCEPTED.toString()) == false &&
                status.equals(Status_level.REJECTED.toString()) == false)

            throw new InvalidDataException(WrongMessage.INVALID_STATUS);
    }
}
