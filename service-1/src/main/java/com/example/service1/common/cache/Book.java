package com.example.service1.common.cache;

import java.io.Serializable;

public class Book implements Serializable {

    private static final long serialVersionUID = 8714989111224630845L;

    private String ibns;
    private String title;

    public Book(String ibns, String title) {
        this.ibns = ibns;
        this.title = title;
    }

    public String getIbns() {
        return ibns;
    }

    public void setIbns(String ibns) {
        this.ibns = ibns;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ibns='" + ibns + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
