
package ng.shoppi.roamsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class JobCategory implements Parcelable {

    @SerializedName("description")
    private String mDescription = "";

    @SerializedName("name")
    private String mName = "";

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return "JobCategory{" +
                "mDescription='" + mDescription + '\'' +
                ", mName='" + mName + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mDescription);
        dest.writeString(this.mName);
    }

    public JobCategory() {
    }

    protected JobCategory(Parcel in) {
        this.mDescription = in.readString();
        this.mName = in.readString();
    }

    public static final Parcelable.Creator<JobCategory> CREATOR = new Parcelable.Creator<JobCategory>() {
        @Override
        public JobCategory createFromParcel(Parcel source) {
            return new JobCategory(source);
        }

        @Override
        public JobCategory[] newArray(int size) {
            return new JobCategory[size];
        }
    };
}
