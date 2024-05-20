package com.wira_fkom.tugas_fingerprint_sensor.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashSet;
import java.util.Set;

public class Data implements Parcelable {
    private String name;
    private String description;
    private int photo;
    // Static set to track used photo IDs
    private static final Set<Integer> usedPhotoIds = new HashSet<>();

    public Data(String name, String description, int photo) throws IllegalArgumentException {
        if (usedPhotoIds.contains(photo)) {
            throw new IllegalArgumentException("Duplicate photo ID: " + photo);
        }
        this.name = name;
        this.description = description;
        this.photo = photo;
        usedPhotoIds.add(photo);
    }

    protected Data(Parcel in) {
        name = in.readString();
        description = in.readString();
        photo = in.readInt();
        usedPhotoIds.add(photo);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(photo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) throws IllegalArgumentException {
        if (usedPhotoIds.contains(photo)) {
            throw new IllegalArgumentException("Duplicate photo ID: " + photo);
        }
        usedPhotoIds.remove(this.photo);
        this.photo = photo;
        usedPhotoIds.add(photo);
    }

    // Method to clear used photo IDs (if needed)
    public static void clearUsedPhotoIds() {
        usedPhotoIds.clear();
    }
}
