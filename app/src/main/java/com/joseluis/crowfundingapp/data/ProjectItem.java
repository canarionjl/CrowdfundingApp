package com.joseluis.crowfundingapp.data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity (tableName = "projects")
public class ProjectItem {

    @PrimaryKey
    public int id;

    public String title;
    public String author;
    public String category;
    public String description;
    public String picture;
    public String phoneContact;
    public long date;

    public String latitude;
    public String longitude;

}