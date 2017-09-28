package ng.shoppi.roamsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 10/23/2016.
 */
public class Spinner extends Radio{

    @Override
    public void DoCheck(int pos, boolean b) {
        if (pos < getOptions().size()) {
            setFieldValue(getOptions().get(pos).getValue());
            getOptions().get(pos).setChecked(b);
        }
    }

}
