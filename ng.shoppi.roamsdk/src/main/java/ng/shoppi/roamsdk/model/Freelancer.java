
package ng.shoppi.roamsdk.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Freelancer {

    @SerializedName("account_name")
    private String mAccountName;
    @SerializedName("account_number")
    private String mAccountNumber;
    @SerializedName("bank_name")
    private String mBankName;
    @SerializedName("bank_verification_number")
    private String mBankVerificationNumber;
    @SerializedName("date_of_birth")
    private String mDateOfBirth;
    @SerializedName("educational_qualification")
    private String mEducationalQualification;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("first_name")
    private String mFirstName;
    @SerializedName("home_address")
    private String mHomeAddress;
    @SerializedName("last_name")
    private String mLastName;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("phone_number")
    private String mPhoneNumber;
    @SerializedName("state_of_origin")
    private String mStateOfOrigin;

    public String getAccountName() {
        return mAccountName;
    }

    public void setAccountName(String accountName) {
        mAccountName = accountName;
    }

    public String getAccountNumber() {
        return mAccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        mAccountNumber = accountNumber;
    }

    public String getBankName() {
        return mBankName;
    }

    public void setBankName(String bankName) {
        mBankName = bankName;
    }

    public String getBankVerificationNumber() {
        return mBankVerificationNumber;
    }

    public void setBankVerificationNumber(String bankVerificationNumber) {
        mBankVerificationNumber = bankVerificationNumber;
    }

    public String getDateOfBirth() {
        return mDateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        mDateOfBirth = dateOfBirth;
    }

    public String getEducationalQualification() {
        return mEducationalQualification;
    }

    public void setEducationalQualification(String educationalQualification) {
        mEducationalQualification = educationalQualification;
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

    public String getHomeAddress() {
        return mHomeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        mHomeAddress = homeAddress;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public String getStateOfOrigin() {
        return mStateOfOrigin;
    }

    public void setStateOfOrigin(String stateOfOrigin) {
        mStateOfOrigin = stateOfOrigin;
    }

}
