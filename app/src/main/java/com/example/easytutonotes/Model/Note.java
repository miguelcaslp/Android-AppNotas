package com.example.easytutonotes.Model;


import io.realm.RealmObject;

public class Note extends RealmObject {

    int id;
    String title;
    String description;
    String createdTime;
    String status;

    public Note(){
        this.id=0;
        this.title="";
        this.description="";
        this.createdTime="";
        this.status="";
    }

    public Note (int id,String title, String description, String createdTime, String status){
        this.id=id;
        this.title=title;
        this.description=description;
        this.createdTime=createdTime;
        this.status=status;
    }

    public Note (String title, String description, String createdTime, String status){
        this.title=title;
        this.description=description;
        this.createdTime=createdTime;
        this.status=status;
    }


    public int getId(){return id;}

    public void setId(int id){this.id=id;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
