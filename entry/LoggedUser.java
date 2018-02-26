package entry;

/**
 * Created by ISakuya on 2017/6/21.
 */
public class LoggedUser {
    private LoggedUser(){
        uid = "0";
    };
    private String uid;
    private static final LoggedUser user = new LoggedUser();
    public static LoggedUser getUser(){
        return user;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
