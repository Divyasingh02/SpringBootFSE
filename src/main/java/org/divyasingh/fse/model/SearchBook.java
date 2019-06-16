package org.divyasingh.fse.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class SearchBook implements Serializable {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
