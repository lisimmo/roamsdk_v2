package ng.shoppi.roamsdk.model;

import android.os.Parcel;

/**
 * Created by User on 10/24/2016.
 */
public class Button extends Field {
    private String submitUrl;

    public Button(String submitUrl) {
        this.submitUrl = submitUrl;
    }

    public String getSubmitUrl() {
        return submitUrl;
    }

    public void setSubmitUrl(String submitUrl) {
        this.submitUrl = submitUrl;
    }

}
