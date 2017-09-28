package ng.shoppi.roamsdk.model;

import android.os.Parcel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by User on 10/24/2016.
 */
public class MCamera extends Field implements Serializable {

    private ArrayList<String> imageList = new ArrayList<>();

    public MCamera() {
        imageList = new ArrayList<>();
    }

    public ArrayList<String> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<String> imageList) {
        this.imageList = imageList;
    }

}
