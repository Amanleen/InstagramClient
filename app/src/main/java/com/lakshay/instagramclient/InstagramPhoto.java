package com.lakshay.instagramclient;

import java.util.ArrayList;

/**
 * Created by aman on 2/6/15.
 */
public class InstagramPhoto {
    private String username;
    private String caption;
    private String imageUrl;
    private int imageHeight;
    private int likesCount;
    private String profilePictureUrl;
    private long createdTime;
    private String comments;
    private int commentsCount;

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    private ArrayList<InstagramPhotoComments> photComments=new ArrayList<InstagramPhotoComments>();

    public ArrayList<InstagramPhotoComments> getPhotComments() {
        return photComments;
    }

    public void setPhotComments(ArrayList<InstagramPhotoComments> photComments) {
        this.photComments = photComments;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments=comments;
    }


    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }




}
