package Utils;

public enum StatusCode {

    SUCCESS(200,"Request is successful"),
    BAD_REQUEST(400,   "There is something wrong in the method"),
    CREATED(201,        "Resource successfully created");

    //enums are by default static, that's why we don't need to create object to call it. for storing different values we will
    // created two instance variable and to initialize those variable we used constructor
    public final int code;
    public final String msg;

    StatusCode(int code, String msg) {
       this.code=code;
       this.msg=msg;
    }
}
