package by.kraskovski.pms.application.validation;

public final class Patterns {

    private Patterns() {
    }

    public static final String PASSWORD_PATTERN = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    public static final String PHONE_PATTERN = "^([+]+)*[0-9\\x20\\x28\\x29-]{5,20}$";
    public static final String MAIL_PATTERN = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
}
