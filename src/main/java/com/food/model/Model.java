package com.food.model;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.food.mongo.ObjectIdAdaptor;

public class Model {
	
	@JsonProperty("_id")
    protected ObjectId id;

    @SuppressWarnings("restriction")
    @XmlJavaTypeAdapter(ObjectIdAdaptor.class)
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

}
