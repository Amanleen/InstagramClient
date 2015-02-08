package com.lakshay.instagramclient;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by aman on 2/6/15.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {

    private static final String LIKES=" likes";

    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1,objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPhoto photo = getItem(position);
        //chk if v r re-cycling view
        if(convertView ==null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.items_photo, parent, false);
        }
        TextView tvCaption = (TextView)convertView.findViewById(R.id.tvCaption);
        ImageView ivPhoto = (ImageView)convertView.findViewById(R.id.ivPhoto);
        TextView tvUsername = (TextView)convertView.findViewById(R.id.tvUsername);
        com.makeramen.RoundedImageView riView = (com.makeramen.RoundedImageView)convertView.findViewById(R.id.imageView1);
        TextView tvUpdateTime = (TextView)convertView.findViewById(R.id.tvUpdateTime);
        TextView tvLikesCount = (TextView)convertView.findViewById(R.id.tvLikesCount);
        TextView tvComments = (TextView)convertView.findViewById(R.id.tvComments);
        TextView tvcommentUsername1 = (TextView)convertView.findViewById(R.id.tvcommentUsername1);
        TextView tvCommentText1 = (TextView)convertView.findViewById(R.id.tvCommentText1);
        TextView tvcommentUsername2 = (TextView)convertView.findViewById(R.id.tvcommentUsername2);
        TextView tvCommentText2 = (TextView)convertView.findViewById(R.id.tvCommentText2);
        ArrayList<InstagramPhotoComments> photocomments=photo.getPhotComments();
        //display comments
        for (int i=0;i<2;i++){
            InstagramPhotoComments photocommentsAaarayObject = photocomments.get(i);
            if(i==0){
                tvcommentUsername1.setText(photocommentsAaarayObject.getUsername());
                tvCommentText1.setText(photocommentsAaarayObject.getComment());
            }
            if(i==1){
                tvcommentUsername2.setText(photocommentsAaarayObject.getUsername());
                tvCommentText2.setText(photocommentsAaarayObject.getComment());
            }
        }
        tvComments.setText("view all "+String.valueOf(photo.getCommentsCount())+" comments");
        //display likes count
        tvLikesCount.setText(photo.getLikesCount()+LIKES);
        //display creation time
        CharSequence createdTime = DateUtils.getRelativeTimeSpanString(
        photo.getCreatedTime() * 1000,
        Calendar.getInstance().getTimeInMillis(),
        DateUtils.SECOND_IN_MILLIS,
        DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_ABBREV_MONTH  | DateUtils.FORMAT_ABBREV_TIME | DateUtils.FORMAT_ABBREV_RELATIVE);
        tvUpdateTime.setText(createdTime);
        //display user's profile picture
        Picasso.with(getContext()).load(photo.getProfilePictureUrl()).placeholder(R.drawable.ic_launcher).into(riView);
        //display user name
        tvUsername.setText(photo.getUsername());
        //display caption
        tvCaption.setText(photo.getCaption());
        //set instagram image
        ivPhoto.setImageResource(0);
        Picasso.with(getContext()).load(photo.getImageUrl()).placeholder(R.drawable.ic_launcher).into(ivPhoto);
        return convertView;
    }

}
