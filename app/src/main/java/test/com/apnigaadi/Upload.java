package com.example.bouncehackathon;

public class Upload {

     private String imageodometer;
     private String imageprice;
     private String imagelocation;
     private String imageUri;
     public Upload()
     {

     }

     public Upload(String imageodometer,String imageprice, String imagelocation, String imageUri)
     {
         this.imageodometer=imageodometer;
         this.imageprice=imageprice;
         this.imagelocation=imagelocation;
         this.imageUri=imageUri;
     }

    public String getImageodometer() {
        return imageodometer;
    }

    public void setImageodometer(String imageodometer) {
        this.imageodometer = imageodometer;
    }

    public String getImageprice() {
        return imageprice;
    }

    public void setImageprice(String imageprice) {
        this.imageprice = imageprice;
    }

    public String getImagelocation() {
        return imagelocation;
    }

    public void setImagelocation(String imagelocation) {
        this.imagelocation = imagelocation;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}

