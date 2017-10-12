
package ng.shoppi.roamsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Town implements Parcelable {

    @SerializedName("code")
    private String mCode = "";

    @SerializedName("is_local_government")
    private boolean mIsLocalGovernment;

    @SerializedName("name")
    private String mName = "";

    @SerializedName("state")
    private State mState = new State();

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public boolean getIsLocalGovernment() {
        return mIsLocalGovernment;
    }

    public void setIsLocalGovernment(boolean isLocalGovernment) {
        mIsLocalGovernment = isLocalGovernment;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public State getState() {
        return mState;
    }

    public void setState(State state) {
        mState = state;
    }

    @Override
    public String toString() {
        return "Town{" +
                "mCode='" + mCode + '\'' +
                ", mIsLocalGovernment=" + mIsLocalGovernment +
                ", mName='" + mName + '\'' +
                ", mState=" + mState +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mCode);
        dest.writeByte(this.mIsLocalGovernment ? (byte) 1 : (byte) 0);
        dest.writeString(this.mName);
        dest.writeParcelable(this.mState, flags);
    }

    public Town() {
    }

    protected Town(Parcel in) {
        this.mCode = in.readString();
        this.mIsLocalGovernment = in.readByte() != 0;
        this.mName = in.readString();
        this.mState = in.readParcelable(State.class.getClassLoader());
    }

    public static final Parcelable.Creator<Town> CREATOR = new Parcelable.Creator<Town>() {
        @Override
        public Town createFromParcel(Parcel source) {
            return new Town(source);
        }

        @Override
        public Town[] newArray(int size) {
            return new Town[size];
        }
    };
}
