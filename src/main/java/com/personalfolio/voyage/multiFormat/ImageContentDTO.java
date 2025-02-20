package com.personalfolio.voyage.multiFormat;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ImageContentDTO {
    byte[] content;
    String uniqueIdName;

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setUniqueIdName(String uniqueIdName) {
        this.uniqueIdName = uniqueIdName;
    }

    public byte[] getContent() {
        return content;
    }

    public String getUniqueIdName() {
        return uniqueIdName;
    }
}
