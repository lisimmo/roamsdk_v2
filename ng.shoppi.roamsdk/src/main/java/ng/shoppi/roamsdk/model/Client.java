
package ng.shoppi.roamsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Client implements Serializable {

    @SerializedName("account_id")
    private String mAccountId = "";

    @SerializedName("company_logo")
    private String mCompanyLogo;

    @SerializedName("company_name")
    private String mCompanyName = "";

    @SerializedName("developer_account_id")
    private String mDeveloperAccountId = "";

    @SerializedName("email")
    private String mEmail = "";

    @SerializedName("first_name")
    private String mFirstName = "";

    @SerializedName("last_name")
    private String mLastName = "";

    @SerializedName("phone_number")
    private String mPhoneNumber = "";

    public String getAccountId() {
        return mAccountId;
    }

    public void setAccountId(String accountId) {
        mAccountId = accountId;
    }

    public String getCompanyLogo() {
        return mCompanyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        mCompanyLogo = companyLogo;
    }

    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String companyName) {
        mCompanyName = companyName;
    }

    public String getDeveloperAccountId() {
        return mDeveloperAccountId;
    }

    public void setDeveloperAccountId(String developerAccountId) {
        mDeveloperAccountId = developerAccountId;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "mAccountId='" + mAccountId + '\'' +
                ", mCompanyLogo='" + mCompanyLogo + '\'' +
                ", mCompanyName='" + mCompanyName + '\'' +
                ", mDeveloperAccountId='" + mDeveloperAccountId + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", mPhoneNumber='" + mPhoneNumber + '\'' +
                '}';
    }

}
