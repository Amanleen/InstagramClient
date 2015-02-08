package com.lakshay.instagramclient;

/**
 * Created by aman on 2/7/15.
 */
public class InstagramPhotoComments {
    private String username;
    private String comment;
    private Long creationTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }
}
