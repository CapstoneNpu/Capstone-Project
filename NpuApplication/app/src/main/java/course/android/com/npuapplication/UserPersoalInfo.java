package course.android.com.npuapplication;

/**
 * Created by pranali on 12/7/17.
 */

public class UserPersoalInfo {
    private String user_name,user_email,
            user_address,user_dob,user_phone,
            user_cursem,user_curgpa,user_spec;
    public UserPersoalInfo()
    {}
    public UserPersoalInfo(String user_name, String user_email) {
        this.user_name = user_name;
        this.user_email = user_email;
    }

    public UserPersoalInfo(String user_name, String user_email, String user_address, String user_dob, String user_phone, String user_cursem, String user_curgpa, String user_spec) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_address = user_address;
        this.user_dob = user_dob;
        this.user_phone = user_phone;
        this.user_cursem = user_cursem;
        this.user_curgpa = user_curgpa;
        this.user_spec = user_spec;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_dob() {
        return user_dob;
    }

    public void setUser_dob(String user_dob) {
        this.user_dob = user_dob;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_cursem() {
        return user_cursem;
    }

    public void setUser_cursem(String user_cursem) {
        this.user_cursem = user_cursem;
    }

    public String getUser_curgpa() {
        return user_curgpa;
    }

    public void setUser_curgpa(String user_curgpa) {
        this.user_curgpa = user_curgpa;
    }

    public String getUser_spec() {
        return user_spec;
    }

    public void setUser_spec(String user_spec) {
        this.user_spec = user_spec;
    }
}
