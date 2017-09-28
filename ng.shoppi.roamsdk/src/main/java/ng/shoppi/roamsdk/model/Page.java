package ng.shoppi.roamsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 11/13/2016.
 */
public class Page implements Serializable {

    @SerializedName(value = "form_id", alternate = {"formId"})
    private int form_id;

    private int id;

    @SerializedName(value = "page_title", alternate = {"pageTitle"})
    private String pageTitle = "";

    @SerializedName(value = "page_layout", alternate = {"pageLayout"})
    private ArrayList<Field> pageLayout = new ArrayList<>();

    public Page() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Page(String pageTitle, ArrayList<Field> pageLayout) {
        this.pageTitle = pageTitle;
        this.pageLayout = pageLayout;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public ArrayList<Field> getPageLayout() {
        return pageLayout;
    }

    public void setPageLayout(ArrayList<Field> pageLayout) {
        this.pageLayout = pageLayout;
    }

    public int getForm_id() {
        return form_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    @Override
    public String toString() {
        return "Page{" +
                "form_id=" + form_id +
                ", id=" + id +
                ", pageTitle='" + pageTitle + '\'' +
                ", pageLayout=" + pageLayout +
                '}';
    }

    /*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.form_id);
        dest.writeInt(this.id);
        dest.writeString(this.pageTitle);
        dest.writeTypedList(this.pageLayout);
    }

    protected Page(Parcel in) {
        this.form_id = in.readInt();
        this.id = in.readInt();
        this.pageTitle = in.readString();
        this.pageLayout = in.createTypedArrayList(Field.CREATOR);
    }

    public static final Creator<Page> CREATOR = new Creator<Page>() {
        @Override
        public Page createFromParcel(Parcel source) {
            return new Page(source);
        }

        @Override
        public Page[] newArray(int size) {
            return new Page[size];
        }
    };*/
}
