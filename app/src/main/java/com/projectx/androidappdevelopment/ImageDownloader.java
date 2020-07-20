package com.projectx.androidappdevelopment;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
//TODO save the file downloaded into the device!!!
public class ImageDownloader extends Fragment {
    String url = "https://wallpapersite.com/images/pages/pic_w/6408.jpg";
    ImageView image;
    Button button;
    EditText editText;
    ProgressDialog mProgressDialog;
    public ImageDownloader() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_downloader, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        image = (ImageView) getView().findViewById(R.id.imageToDl);
        button = (Button) getView().findViewById(R.id.downloadB);
        editText = (EditText) getView().findViewById(R.id.searchBox);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = editText.getText().toString();
                new DownloadImage().execute(url);
            }
        });
    }

    private class DownloadImage extends AsyncTask<String,Void,Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setTitle("Download Image Tutorial");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }
        @Override
        protected Bitmap doInBackground(String... URL) {
            String imageURL = URL[0];
            HttpURLConnection connection = null;
            try{
                connection = (HttpURLConnection) new URL(imageURL).openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                return BitmapFactory.decodeStream(bufferedInputStream);
            }catch(IOException e){
                e.printStackTrace();
            }
            return null;
        }
        // When all async task done
        protected void onPostExecute(Bitmap result){
            // Hide the progress dialog
            mProgressDialog.dismiss();
            if(result!=null){
                image.setImageBitmap(result);
            } else {
                // Notify user that an error occurred while downloading image
                Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}