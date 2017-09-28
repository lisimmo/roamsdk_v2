package ng.shoppi.roamsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Akinola on 10/20/2016.
 * Parent Class of a Field.
 */
public class Field implements Serializable {
    @SerializedName(value = "field_type", alternate = {"fieldType"})
    private String fieldType;

    @SerializedName(value = "field_name", alternate = {"fieldName"})
    private String fieldName = "";

    @SerializedName(value = "field_value", alternate = {"fieldValue"})
    private String fieldValue = "";

    @SerializedName(value = "field_id", alternate = {"fieldId"})
    private int fieldId;

    public Field() {
    }


    /*public static final Creator<Field> CREATOR = new Creator<Field>() {
        @Override
        public Field createFromParcel(Parcel in) {
            return new Field(in);
        }

        @Override
        public Field[] newArray(int size) {
            return new Field[size];
        }
    };*/

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldId=" + fieldId +
                ", fieldType='" + fieldType + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", fieldValue='" + fieldValue + '\'' +
                '}';
    }

    /*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fieldType);
        dest.writeString(this.fieldName);
        dest.writeString(this.fieldValue);
        dest.writeInt(this.fieldId);
    }

    protected Field(Parcel in) {
        this.fieldType = in.readString();
        this.fieldName = in.readString();
        this.fieldValue = in.readString();
        this.fieldId = in.readInt();
    }*/

}
