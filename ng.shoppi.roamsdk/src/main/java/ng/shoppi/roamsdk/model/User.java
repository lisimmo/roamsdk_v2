package ng.shoppi.roamsdk.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

/**
 * Created by Akinola on 7/14/2016.
 * This houses all info about the user.
 */
public class User implements Parcelable {
    private String firstName = "", lastName = "", password = "", phoneNo = "", email = "";
    private boolean firstLaunch = true;
    private String accessToken = "";
    private static User userObject;
    public static double latitude;
    public static double longitude;

    private static Context context;

    private User(Context context) {

    }

    public static User getInstance(Context context) {
        User.context = context;
        if (userObject == null) {
            Gson gson = new Gson();
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

            String userJson = prefs.getString("USER", "");
            userObject = gson.fromJson(userJson, User.class);

            if (userObject == null) {
                userObject = new User(context);
            }
        }
        return userObject;
    }

    public static void saveInstance(Context context) {
        Gson gson = new Gson();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        String userJson = gson.toJson(userObject);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("USER", userJson);
        editor.apply();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public boolean isFirstLaunch() {
        return firstLaunch;
    }

    public void setFirstLaunch(boolean firstLaunch) {
        this.firstLaunch = firstLaunch;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastName) {
        this.lastName = lastName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public static void SetLocation(double latitude, double longitude) {
        if (latitude != 0.0 && longitude != 0.0) {
            User.latitude = latitude;
            User.longitude = longitude;
            saveInstance(context);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "accessToken='" + accessToken + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", email='" + email + '\'' +
                ", firstLaunch=" + firstLaunch +
                '}';
    }

    /*@Override
    public String toString() {
        return lastName + " " + firstName;
    }*/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.password);
        dest.writeString(this.phoneNo);
        dest.writeString(this.email);
        dest.writeByte(this.firstLaunch ? (byte) 1 : (byte) 0);
        dest.writeString(this.accessToken);
    }

    protected User(Parcel in) {
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.password = in.readString();
        this.phoneNo = in.readString();
        this.email = in.readString();
        this.firstLaunch = in.readByte() != 0;
        this.accessToken = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
