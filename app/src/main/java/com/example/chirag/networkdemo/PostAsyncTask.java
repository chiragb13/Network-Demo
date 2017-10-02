package com.example.chirag.networkdemo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Chirag on 01-10-2017.
 */
public class PostAsyncTask extends android.os.AsyncTask<String,Void,ArrayList<Post>> {


    public PostDownloadListener postsDownloadListener;
    public PostAsyncTask(PostDownloadListener listener){
        postsDownloadListener = listener;
    }

    @Override
    protected ArrayList<Post> doInBackground(String... params) {

        String urlString = params[0];
        try{
            URL url = new URL(urlString);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("GET");
            InputStream inputStream = httpsURLConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            String response = "";
            while(scanner.hasNext()){
                response+=scanner.next();
            }

            ArrayList<Post> posts = parsePosts(response);
            return posts;
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Post> parsePosts(String response) throws JSONException{

        ArrayList<Post> posts = null;
        JSONArray rootArray = new JSONArray(response);
        if(rootArray!=null){
            posts = new ArrayList<>();
            for(int i=0;i<rootArray.length();i++){
                JSONObject postObject = rootArray.getJSONObject(i);
                int userId = postObject.getInt("userId");
                int id = postObject.getInt("id");
                String title = postObject.getString("title");
                String body = postObject.getString("body");
                Post post = new Post(userId,id,title,body);
                posts.add(post);


            }
        }
        return posts;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Post> posts) {
        postsDownloadListener.onDownload(posts);
    }

    public static interface PostDownloadListener{
        void onDownload(ArrayList<Post> posts);
    }
}
