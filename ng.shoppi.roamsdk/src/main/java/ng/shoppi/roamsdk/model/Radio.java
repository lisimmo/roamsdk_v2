package ng.shoppi.roamsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 10/20/2016.
 */
public class Radio extends Field implements Serializable {

    private List<RadioOption> options;

    public Radio() {
        this.setFieldType("RADIO");
        options = new ArrayList<>();
    }

    public List<RadioOption> getOptions() {
        return options;
    }

    public void setOptions(List<RadioOption> options) {
        this.options = options;
    }

    public void DoCheck(int pos, boolean b) {
        if (pos < options.size() && pos >= 0) {
            for (RadioOption o : options) {
                o.setChecked(false);
            }
            options.get(pos).setChecked(true);
            setFieldValue(options.get(pos).getValue());
        }
    }


}
