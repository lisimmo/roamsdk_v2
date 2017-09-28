package ng.shoppi.roamsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Akinola on 12/21/2016.
 */
@SuppressWarnings("unused")
public class Client implements Serializable {
    private long id;

    @SerializedName("company_name")
    private String companyName = "";

    @SerializedName("company_logo")
    private String companyLogo = "";

    @SerializedName("first_name")
    private String firstName = "";

    @SerializedName("last_name")
    private String lastName = "";

    @SerializedName("account_id")
    private String account_id = "";

    //override
    //private int clientLogoUrl;
    private ArrayList<Form> forms = new ArrayList<>();

    public Client() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<Form> getForms() {
        return forms;
    }

    public void setForms(ArrayList<Form> forms) {
        this.forms = forms;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "companyLogo='" + companyLogo + '\'' +
                ", id=" + id +
                ", companyName='" + companyName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", forms=" + forms +
                '}';
    }

    /*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.companyName);
        dest.writeString(this.companyLogo);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.account_id);
        //dest.writeTypedList(this.forms);
    }

    protected Client(Parcel in) {
        this.id = in.readLong();
        this.companyName = in.readString();
        this.companyLogo = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.account_id = in.readString();
        //this.forms = in.createTypedArrayList(Form.CREATOR);
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel source) {
            return new Client(source);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };*/
}
