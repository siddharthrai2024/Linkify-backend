package com.url.shortener.dtos;

public class AnalyticsResponseDTO {

    private String date;
    private long count;

    public AnalyticsResponseDTO(String date, long count) {
        this.date = date;
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public long getCount() {
        return count;
    }
}
