package com.personalfolio.voyage.multiFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MultiFormat {
    String time;
    String description;
    long id;
    byte[] data;

    public byte[] getData() {
        return data;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
