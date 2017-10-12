package ng.shoppi.roamsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by User on 12/21/2016.
 */
public class Form implements Serializable{
    private long id;

    @SerializedName("title")
    private String title = "";

    @SerializedName("description")
    private String description = "";

    @SerializedName("isPublished")
    private boolean mIsPublished;

    @SerializedName("client")
    private Client mClient =  new Client();

    private ArrayList<Page> pages = new ArrayList<>();

    private boolean geoLocked = false;


    private ArrayList<Location> locations = new ArrayList<>();

    public Form() {
    }

    public boolean isGeoLocked() {
        return geoLocked;
    }

    public void setGeoLocked(boolean geoLocked) {
        this.geoLocked = geoLocked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public ArrayList<Page> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Page> pages) {
        this.pages = pages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public Client getClient() {
        return mClient;
    }

    public void setClient(Client client) {
        mClient = client;
    }
    public boolean getIsPublished() {
        return mIsPublished;
    }

    public void setIsPublished(boolean isPublished) {
        mIsPublished = isPublished;
    }

    @Override
    public String toString() {
        return "Form{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", mIsPublished=" + mIsPublished +
                ", mClient=" + mClient +
                ", pages=" + pages +
                ", geoLocked=" + geoLocked +
                ", locations=" + locations +
                '}';
    }
}
