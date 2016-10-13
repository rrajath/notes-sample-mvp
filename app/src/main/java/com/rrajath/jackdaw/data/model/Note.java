package com.rrajath.jackdaw.data.model;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Note extends RealmObject {
    @PrimaryKey
    public String id;
    public String title;
    public String description;

    public Note() {
        id = UUID.randomUUID().toString();
    }

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
