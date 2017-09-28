package ng.shoppi.roamsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import ng.shoppi.roamsdk.Roam;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 12/21/2016.
 */
public class Form implements Serializable {
    private long id;

    @SerializedName("title")
    private String title = "";

    @SerializedName(value = "formPrice", alternate = {"form_price"})
    private String formPrice = "";

    @SerializedName(value = "formCurrency", alternate = {"form_currency"})
    private String formCurrency = "₦";

    @SerializedName("description")
    private String description = "";

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

    public String getFormPrice() {
        return formPrice;
    }

    public void setFormPrice(String formPrice) {
        this.formPrice = formPrice;
    }

    public String getFormCurrency() {
        return formCurrency;
    }

    public void setFormCurrency(String formCurrency) {
        this.formCurrency = formCurrency;
    }

    /* public void getPagesFromUrl(final Roam.OnPagesResponseListener listener) {

        //Call<ArrayList<Form>> callGetForm = apiCalls.getForms(user_id);
        Call<ArrayList<Page>> callGetPages = Roam.apiCalls.getForms();
        callGetPages.enqueue(new Callback<ArrayList<Page>>() {
            @Override
            public void onResponse(Call<ArrayList<Page>> call, Response<ArrayList<Page>> response) {
                int code = response.code();
                if (code == 200) {
                    pages = response.body();

                    listener.onSuccess(code, pages);
                } else {
                    listener.onError(code);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Page>> call, Throwable t) {
                t.printStackTrace();
                listener.onTimeOut();
            }
        });

    }*/



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

    @Override
    public String toString() {
        return "Form{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", pages=" + pages +
                ", geoLocked=" + geoLocked +
                ", locations=" + locations +
                '}';
    }


}
