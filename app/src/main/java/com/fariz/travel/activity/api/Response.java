package com.fariz.travel.activity.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

//    private List<distributorListRequestData> results = new ArrayList<distributorListRequestData>();
//
//    public List<distributorListRequestData> getResults() {
//        return results;
//    }

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("img")
    @Expose
    String img;

    public Response(String name, String id, String img) {
        this.name = name;
        this.id = id;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
