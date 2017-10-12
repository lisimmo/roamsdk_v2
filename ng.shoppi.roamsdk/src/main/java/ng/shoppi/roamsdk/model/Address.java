
package ng.shoppi.roamsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Address implements Parcelable {

    @SerializedName("checkableDistance")
    private double mCheckableDistance;

    @SerializedName("latitude")
    private double mLatitude;

    @SerializedName("longitude")
    private double mLongitude;

    @SerializedName("name")
    private String mName = "";

    @SerializedName("town")
    private Town mTown = new Town();

    public double getCheckableDistance() {
        return mCheckableDistance;
    }

    public void setCheckableDistance(Long checkableDistance) {
        mCheckableDistance = checkableDistance;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Town getTown() {
        return mTown;
    }

    public void setTown(Town town) {
        mTown = town;
    }

    public enum Sort {
        ASC, DESC
    }

    @Override
    public String toString() {
        return "Address{" +
                "mCheckableDistance=" + mCheckableDistance +
                ", mLatitude=" + mLatitude +
                ", mLongitude=" + mLongitude +
                ", mName='" + mName + '\'' +
                ", mTown=" + mTown +
                '}';
    }

    public double getDistanceFrom(double userLatitude, double userLongitude){
        char unit = 'K';
        double theta = userLongitude - getLongitude();
        double dist = Math.sin(deg2rad(userLatitude)) * Math.sin(deg2rad(getLatitude())) + Math.cos(deg2rad(userLatitude)) * Math.cos(deg2rad(getLatitude())) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        //Unit in Kilometers
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') { //unit in nautical miles
            dist = dist * 0.8684;
        }
        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


    public Address() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.mCheckableDistance);
        dest.writeDouble(this.mLatitude);
        dest.writeDouble(this.mLongitude);
        dest.writeString(this.mName);
        dest.writeParcelable(this.mTown, flags);
    }

    protected Address(Parcel in) {
        this.mCheckableDistance = in.readDouble();
        this.mLatitude = in.readDouble();
        this.mLongitude = in.readDouble();
        this.mName = in.readString();
        this.mTown = in.readParcelable(Town.class.getClassLoader());
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
