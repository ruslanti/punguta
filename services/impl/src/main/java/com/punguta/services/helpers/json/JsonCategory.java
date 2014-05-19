package com.punguta.services.helpers.json;

import java.util.ArrayList;
import java.util.List;

/**
 * User: ruslan
 * Date: 5/19/14
 */
public class JsonCategory {
    String name;
    List<JsonCategory> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JsonCategory> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<JsonCategory> children) {
        this.children = children;
    }
}
