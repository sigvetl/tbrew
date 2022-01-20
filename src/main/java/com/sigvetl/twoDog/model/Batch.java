package com.sigvetl.twoDog.model;

import java.time.LocalDate;

public class Batch {
    private Integer batchId;
    private Integer beerId;
    private Integer userId;
    private Float volume;
    private LocalDate brewDate;
    private LocalDate tapDate;
    private LocalDate finishDate;
    private Float abv;
    private Float og;
    private Float fg;
    private String createdBy;
    private String lastUpdatedBy;
    private String quality;
    private String comments;

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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
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

    public Integer getBeerId() {
        return beerId;
    }

    public void setBeerId(Integer beerId) {
        this.beerId = beerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
