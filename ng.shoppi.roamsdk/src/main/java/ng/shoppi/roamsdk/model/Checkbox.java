package ng.shoppi.roamsdk.model;

import android.os.Parcel;

/**
 * Created by User on 10/23/2016.
 */
public class Checkbox extends Radio {

    public Checkbox() {
    }

    @Override
    public void DoCheck(int pos, boolean b) {
        if (pos < getOptions().size()) {
            getOptions().get(pos).setChecked(b);
            setFieldValue(getFieldValue()+","+getOptions().get(pos).getName());
        }
    }

}
