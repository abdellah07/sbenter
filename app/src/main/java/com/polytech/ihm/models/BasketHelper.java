package com.polytech.ihm.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.osmdroid.util.GeoPoint;

public class BasketHelper implements Parcelable {
    private int picture;
    private String title;
    private String description;
    private float weight;
    private String phoneNumber;

    protected BasketHelper(Parcel in) {
        picture = in.readInt();
        title = in.readString();
        description = in.readString();
        weight = in.readFloat();
        phoneNumber = in.readString();
        myGeoPoint = in.readParcelable(GeoPoint.class.getClassLoader());
    }

    public static final Creator<BasketHelper> CREATOR = new Creator<BasketHelper>() {
        @Override
        public BasketHelper createFromParcel(Parcel in) {
            return new BasketHelper(in);
        }

        @Override
        public BasketHelper[] newArray(int size) {
            return new BasketHelper[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(picture);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeFloat(weight);
        dest.writeString(phoneNumber);
        dest.writeParcelable(myGeoPoint, flags);
    }

    public enum Type { HONEYCOOB, CORRUGATED, PLAT, WOODEN };
    private Type type;
    private GeoPoint myGeoPoint;

    public BasketHelper(String title, String description, float weight, String phoneNumber, Type type,GeoPoint myGeoPoint,int picture) {
        this.title = title;
        this.myGeoPoint = myGeoPoint;
        this.description = description;
        this.weight = weight;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.picture=picture;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GeoPoint getMyGeoPoint() {
        return myGeoPoint;
    }

    public void setMyGeoPoint(GeoPoint myGeoPoint) {
        this.myGeoPoint = myGeoPoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BasketHelper{" +
                "picture=" + picture +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", type=" + type +
                ", myGeoPoint=" + myGeoPoint +
                '}';
    }
}
