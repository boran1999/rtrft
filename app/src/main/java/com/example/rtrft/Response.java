package com.example.rtrft;

public class Response {
    int total, totalHits;
    Hit[] hits;

    @Override
    public String toString() {

        return "totalHits = " + totalHits;
    }
}