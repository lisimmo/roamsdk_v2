
package ng.shoppi.roamsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Job implements Parcelable {

    @SerializedName("address")
    private ArrayList<Address> mAddress = new ArrayList<>();

    @SerializedName("description")
    private String mDescription;

    @SerializedName("duration")
    private long mDuration;

    @SerializedName("form")
    private Form mForm = new Form();

    @SerializedName("jobCategory")
    private JobCategory mJobCategory = new JobCategory();

    @SerializedName("status")
    private String mStatus;

    @SerializedName("wage")
    private long mWage;

    public ArrayList<Address> getAddress() {
        return mAddress;
    }

    public void setAddress(ArrayList<Address> address) {
        mAddress = address;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public long getDuration() {
        return mDuration;
    }

    public void setDuration(long duration) {
        mDuration = duration;
    }

    public Form getForm() {
        return mForm;
    }

    public void setForm(Form form) {
        mForm = form;
    }

    public JobCategory getJobCategory() {
        return mJobCategory;
    }

    public void setJobCategory(JobCategory jobCategory) {
        mJobCategory = jobCategory;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public long getWage() {
        return mWage;
    }

    public void setWage(long wage) {
        mWage = wage;
    }


    @Override
    public String toString() {
        return "Job{" +
                "mAddress=" + mAddress +
                ", mDescription='" + mDescription + '\'' +
                ", mDuration=" + mDuration +
                ", mForm=" + mForm +
                ", mJobCategory=" + mJobCategory +
                ", mStatus='" + mStatus + '\'' +
                ", mWage=" + mWage +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.mAddress);
        dest.writeString(this.mDescription);
        dest.writeLong(this.mDuration);
        dest.writeSerializable(this.mForm);
        dest.writeParcelable(this.mJobCategory, flags);
        dest.writeString(this.mStatus);
        dest.writeLong(this.mWage);
    }

    public Job() {
    }

    protected Job(Parcel in) {
        this.mAddress = new ArrayList<Address>();
        in.readList(this.mAddress, Address.class.getClassLoader());
        this.mDescription = in.readString();
        this.mDuration = in.readLong();
        this.mForm = (Form) in.readSerializable();
        this.mJobCategory = in.readParcelable(JobCategory.class.getClassLoader());
        this.mStatus = in.readString();
        this.mWage = in.readLong();
    }

    public static final Parcelable.Creator<Job> CREATOR = new Parcelable.Creator<Job>() {
        @Override
        public Job createFromParcel(Parcel source) {
            return new Job(source);
        }

        @Override
        public Job[] newArray(int size) {
            return new Job[size];
        }
    };
}
