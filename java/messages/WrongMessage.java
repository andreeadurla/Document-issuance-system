package messages;

public class WrongMessage {

    public static final String INVALID_NAME = "Invalid name, please try again!";

    public static final String INVALID_EMAIL = "Invalid email, please try again!";

    public static final String INVALID_PASSWORD = "Invalid password:\nMust contain at least 1 lowercase alphabetical character\n" +
                                                "Must contain at least 1 uppercase alphabetical character\n" +
                                                "Must contain at least 1 numeric character\n" +
                                                "Must contain at least one special character\n" +
                                                "Must be eight characters or longer";

    public static final String INVALID_PHONE_NUMBER = "Invalid phone number, please try again!";

    public static final String INVALID_CNP = "Invalid CNP, please try again!";

    public static final String WRONG_EMAIL_OR_PASSWORD = "Wrong email or password, please try again!";

    public static final String WRONG_PASSWORD = "Password and confirm password are different, please try again!";

    public static final String WRONG_EMAIL = "This email address already exists, please try again!";

    public static final String WRONG_CNP = "This CNP already exists, please try again!";

    public static final String WRONG_ADDRESS = "This address already exists, please try again!";

    public static final String WRONG_PURPOSE = "Purpouse field must contains the reason why you want the request, please try again!";

    public static final String EMPTY_FIELD = "Text field cannot be empty, please try again!";

    public static final String WRONG_ID = "Wrong id, please try again!";

    public static final String WRONG_DOCUMENT_TYPE = "This document type already exists, please try again!";

    public static final String WRONG_NO_REQUESTS = "You have made too many requests for this type of document, for this house, this year!";

    public static final String WRONG_ADMIN_DELETE_REQUEST = "You cannot delete a request IN_PROCESSING!";

    public static final String WRONG_USER_DELETE_REQUEST = "You cannot delete a request ACCEPTED or REJECTED!";

    public static final String WRONG_INPUT = "Input is null";

    public static final String INVALID_STATUS = "Request's status is invalid!";

    public static final String WRONG_UPDATE_REQUEST = "You can update a request only if its status is IN_PROCESSING!";

    public static final String WRONG_UPDATE_STATUS= "You cannot modify the status of an already processed request!";
}
