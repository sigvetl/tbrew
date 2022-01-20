package com.sigvetl.twoDog.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class BatchForm {
    private Integer batchId;
    private Float volume;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate brewDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate tapDate;
    private LocalDate finishDate;
    private Float abv;
    private Float og;
    private Float fg;
    private String quality;
    private String comments;
    private Integer batchBeerId;

    public Integer getBatchBeerId() {
        return batchBeerId;
    }

    public void setBatchBeerId(Integer batchBeerId) {
        this.batchBeerId = batchBeerId;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public LocalDate getBrewDate() {
        return brewDate;
    }

    public void setBrewDate(LocalDate brewDate) {
        this.brewDate = brewDate;
    }

    public LocalDate getTapDate() {
        return tapDate;
    }

    public void setTapDate(LocalDate tapDate) {
        this.tapDate = tapDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public Float getAbv() {
        return abv;
    }

    public void setAbv(Float abv) {
        this.abv = abv;
    }

    public Float getOg() {
        return og;
    }

    public void setOg(Float og) {
        this.og = og;
    }

    public Float getFg() {
        return fg;
    }

    public void setFg(Float fg) {
        this.fg = fg;
    }

}
