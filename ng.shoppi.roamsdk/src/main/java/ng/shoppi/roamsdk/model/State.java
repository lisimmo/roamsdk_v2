
package ng.shoppi.roamsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class State implements Parcelable {

    @SerializedName("capital")
    private String mCapital = "";

    @SerializedName("code")
    private String mCode = "";

    @SerializedName("name")
    private String mName = "";

    public String getCapital() {
        return mCapital;
    }

    public void setCapital(String capital) {
        mCapital = capital;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return "State{" +
                "mCapital='" + mCapital + '\'' +
                ", mCode='" + mCode + '\'' +
                ", mName='" + mName + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mCapital);
        dest.writeString(this.mCode);
        dest.writeString(this.mName);
    }

    public State() {
    }

    protected State(Parcel in) {
        this.mCapital = in.readString();
        this.mCode = in.readString();
        this.mName = in.readString();
    }

    public static final Parcelable.Creator<State> CREATOR = new Parcelable.Creator<State>() {
        @Override
        public State createFromParcel(Parcel source) {
            return new State(source);
        }

        @Override
        public State[] newArray(int size) {
            return new State[size];
        }
    };
}
