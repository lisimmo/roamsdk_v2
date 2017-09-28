package ng.shoppi.roamsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 12/13/2016.
 */
public class Error implements Parcelable {
    @SerializedName("error_description")
    private String message = "";

    private String error = "";
    private int status;

    public Error(String error, String message, int status) {
        this.error = error;
        this.message = message;
        this.status = status;
    }

    public Error() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.message);
        dest.writeString(this.error);
        dest.writeInt(this.status);
    }

    protected Error(Parcel in) {
        this.message = in.readString();
        this.error = in.readString();
        this.status = in.readInt();
    }

    public static final Creator<Error> CREATOR = new Creator<Error>() {
        @Override
        public Error createFromParcel(Parcel source) {
            return new Error(source);
        }

        @Override
        public Error[] newArray(int size) {
            return new Error[size];
        }
    };
}
