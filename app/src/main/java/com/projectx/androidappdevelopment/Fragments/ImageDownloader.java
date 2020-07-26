package com.projectx.androidappdevelopment.Fragments;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.os.Environment.DIRECTORY_DOWNLOADS;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.projectx.androidappdevelopment.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.ContentValues.TAG;

//TODO save the file downloaded into the device!!!
public class ImageDownloader extends Fragment {
    String url = "https://wallpapersite.com/images/pages/pic_w/6408.jpg";
    ImageView image;
    Button downloadButton;
    EditText editText;
    RelativeLayout relativeLayout;
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        image = (ImageView) getView().findViewById(R.id.imageToDl);
        downloadButton = (Button) getView().findViewById(R.id.downloadB);
        editText = (EditText) getView().findViewById(R.id.searchBox);
        relativeLayout = (RelativeLayout) getView().findViewById(R.id.relativeLayoutID);

        image.setDrawingCacheEnabled(true);
        String stringTab1 = "ImageDownloader in Android";
        getActivity().setTitle(stringTab1 + "");
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        downloadButton.setVisibility(View.INVISIBLE);

        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    try {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(relativeLayout.getWindowToken(), 0);
                    } catch (Exception e) {
                    }
                    url = editText.getText().toString();
                    new DownloadImage().execute(url);
                    return true;
                }
                return false;
            }
        });

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeImage(image.getDrawingCache());
            }
        });
    }

    private void storeImage(Bitmap bitmap) {
        File checkFile = new File(String.valueOf(getActivity().getApplicationContext().getExternalFilesDir("/" + "AAD Basics" + "/" + "ImageDownloader")));
        String timeStamp = new SimpleDateFormat("YYYYMMDD_HHmm").format(new Date());
        String imageName = timeStamp + ".jpg";
        File file = new File(checkFile, imageName);
        if (!file.exists()) {
            Log.d("path", file.toString());
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                Toast.makeText(getActivity().getApplicationContext(), imageName+" image saved at location: \n"+ checkFile, Toast.LENGTH_LONG).show();
                fos.flush();
                fos.close();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setTitle("Downloading the Image");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {
            String imageURL = URL[0];
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) new URL(imageURL).openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                return BitmapFactory.decodeStream(bufferedInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        // When all async task done
        protected void onPostExecute(Bitmap result) {
            // Hide the progress dialog
            mProgressDialog.dismiss();
            if (result != null) {
                image.setImageBitmap(result);
                downloadButton.setVisibility(View.VISIBLE);
            } else {
                // Notify user that an error occurred while downloading image
                Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}