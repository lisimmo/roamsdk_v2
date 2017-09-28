package ng.shoppi.roamsdk.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by User on 10/20/2016.
 */
public class RadioOption implements Serializable {
    private int id;

    @SerializedName(value = "option_name", alternate = {"optionName", "name"})
    private String name = "";

    @SerializedName(value = "option_value", alternate = {"optionValue", "value"})
    private String value = "";

    private boolean checked;

    public RadioOption() {
    }

    public RadioOption(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public RadioOption(int id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "RadioOption{" +
                "checked=" + checked +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

}
