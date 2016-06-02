package com.github.xujianhua.xnet.bean;

/**
 * Created by xujianhua on 28/05/16.
 */
public enum MimeType {
    IMAGE_PNG(10,"image/png"),
    IMAGE_GIF(12,"image/gif"),
    IMAGE_JPEG(13,"image/jpeg"),
    TEXT(2,"text/plain"),
    TEXT_XML(21,"text/xml"),//.xsl
    TEXT_HTML(22,"text/html"),//.html
    AUDIO_MP3(30,"audio/mp3"),//.mp3文件
    AUDIO_WAV(31,"audio/wav"),//.wav
    BMP(4,"application/x-bmp"),//.bmp
    APK(5,"application/vnd.android.package-archive"),//.apk
    BINARY_STREAM(6,"application/octet-stream"),//.*未知文件
     ;
    private int key;
    private String value;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    MimeType(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
