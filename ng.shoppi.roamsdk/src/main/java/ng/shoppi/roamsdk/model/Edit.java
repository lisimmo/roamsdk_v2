package ng.shoppi.roamsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Akinola on 10/20/2016.
 * Edit field class
 */
public class Edit extends Field implements Serializable {

    @SerializedName(value = "field_hint", alternate = {"fieldHint"})
    private String fieldHint = "";

    @SerializedName(value = "field_format", alternate = {"fieldFormat"})
    private String fieldFormat = "";

    public Edit() {
        this.setFieldType("EDIT");
    }


    public String getFieldHint() {
        return fieldHint;
    }

    public void setFieldHint(String fieldHint) {
        this.fieldHint = fieldHint;
    }

    @Override
    public String toString() {
        return "Edit{" +
                "fieldFormat='" + fieldFormat + '\'' +
                ", fieldHint='" + fieldHint + '\'' +
                '}';
    }

    public String getFieldFormat() {
        return fieldFormat;
    }

    public void setFieldFormat(String fieldFormat) {
        this.fieldFormat = fieldFormat;
    }

    /*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.fieldHint);
        dest.writeString(this.fieldFormat);
    }

    protected Edit(Parcel in) {
        super(in);
        this.fieldHint = in.readString();
        this.fieldFormat = in.readString();
    }

    public static final Creator<Edit> CREATOR = new Creator<Edit>() {
        @Override
        public Edit createFromParcel(Parcel source) {
            return new Edit(source);
        }

        @Override
        public Edit[] newArray(int size) {
            return new Edit[size];
        }
    };*/
}
