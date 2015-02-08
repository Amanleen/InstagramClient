package com.lakshay.instagramclient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.jar.JarException;


public class Photos_Activity extends Activity {

    public static final String CLIENT_ID="c5cfa727744a481485efe24d98dbbb6b";
    public ArrayList<InstagramPhoto> photos;
    public InstagramPhotosAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        photos = new ArrayList<InstagramPhoto>();
        //create adapter and link it
        photoAdapter = new InstagramPhotosAdapter(this, photos);
        //find listview from the adapter
        ListView lvPhotos = (ListView)findViewById(R.id.lvPhotos);
        //set adapter binding the listview
        lvPhotos.setAdapter(photoAdapter);
        //SEND OUT API REQUEST TO POPULAR PHOTOS
        fetchPopularPhotos();

    }

    public void fetchPopularPhotos(){
       /*
        -CLIENT ID: c5cfa727744a481485efe24d98dbbb6b
        - Popular: https://api.instagram.com/v1/media/popular?access_token=ACCESS-TOKEN
        -Response

        */

        String url = "https://api.instagram.com/v1/media/popular?client_id="+CLIENT_ID;
        //Creating the network Client
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url,null, new JsonHttpResponseHandler(){
            //onSuccess(worked,200)

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
               //Expecting a JSONobject
                //- Type: {"data" => [x] => "type" } ("image" or "video")
                //Iterate the photo items and decode the items in the java object
                JSONArray photosJSON=null;
                try{

                    photosJSON = response.getJSONArray("data");//get array of posts
                    // iterate array of posts
                    for(int i=0; i<photosJSON.length(); i++){
                        JSONObject photoJSON = photosJSON.getJSONObject(i);
                        InstagramPhoto photo = new InstagramPhoto();
                        // Author Name: {"data" => [x] => "user" => "username"}
                        photo.setUsername(photoJSON.getJSONObject("user").getString("username"));
                        //Profile picture:{"data"=>[x]=>"user"=>"profile_picture""}
                        photo.setProfilePictureUrl(photoJSON.getJSONObject("user").getString("profile_picture"));
                        //Caption: {"data" => [x] => "caption" => "text"}
                        photo.setCaption(photoJSON.getJSONObject("caption").getString("text"));
                        //URL: {"data" => [x] => "images" => "standard_resolution" => "url"}
                        photo.setImageUrl(photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("url"));
                        //imageHeight: {"data" => [x] => "images" => "standard_resolution" => "height"}
                        photo.setImageHeight(photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getInt("height"));
                        //Type: {"data" => [x] => "likes" => "count"}
                        photo.setLikesCount(photoJSON.getJSONObject("likes").getInt("count"));
                        //Created time: {"data" => [x] => "created_time"}
                        photo.setCreatedTime(photoJSON.getLong("created_time"));
                        //comments:{"data"=>"comments"=>"count"}
                        photo.setCommentsCount(photoJSON.getJSONObject("comments").getInt("count"));
                        //comments:{"data"=>"comments"=>"data"=>"text"}
                        //comments:{"data"=>"comments"=>"from"=>"username"}
                        if(photoJSON.has("comments")){
                            ArrayList<InstagramPhotoComments> photComments=new ArrayList<InstagramPhotoComments>();
                            JSONArray photoComments = photoJSON.getJSONObject("comments").getJSONArray("data");
                            for(int j=0; j< photoComments.length();j++){
                                JSONObject photoComment = photoComments.getJSONObject(j);
                                InstagramPhotoComments comments = new InstagramPhotoComments();
                                comments.setComment(photoComment.getString("text"));
                                comments.setUsername(photoComment.getJSONObject("from").getString("username"));
                                comments.setCreationTime(photoComment.getLong("created_time"));
                                photComments.add(comments);
                            }
                            photo.setPhotComments(photComments);
                        }
                        photos.add(photo);
                    }
                }catch (JSONException e){
                    Log.e("PhotosActivity","Unable to parse: " + photosJSON.toString() ,e);
                }
                photoAdapter.notifyDataSetChanged();

            }

            //onFailure(fail)

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //Do something
//                display error msg
//                        check if comments exists
//                        add swipe to refresh
//                        create a separate page for comments
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photos_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
