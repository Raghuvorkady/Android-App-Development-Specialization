package com.projectx.androidappdevelopment.Fragments;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.media.app.NotificationCompat;

import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.projectx.androidappdevelopment.Activities.MainActivity;
import com.projectx.androidappdevelopment.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageDownloader extends Fragment {

    private String url = "";
    private ImageView image;
    private Button downloadButton;
    private EditText editText;
    private RelativeLayout relativeLayout;
    private ProgressDialog mProgressDialog;

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
    public void onViewCreated(View view, Bundle savedInstanceState) {

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
                        assert imm != null;
                        imm.hideSoftInputFromWindow(relativeLayout.getWindowToken(), 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    url = editText.getText().toString();
                    new DownloadImage().execute(url);
                    return true;
                }
                return false;
            }
        });

        relativeLayout.setOnClickListener(hideKeyboard);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                storeImage(image.getDrawingCache());
            }
        });
    }

    private View.OnClickListener hideKeyboard = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //used to remove the focus from the EditText View
            editText.clearFocus();
            //used to hide the keyboard
            try {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                assert imm != null;
                imm.hideSoftInputFromWindow(relativeLayout.getWindowToken(), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    //below method is used to save the image in the internal storage
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void storeImage(Bitmap bitmap) {
        File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "/" + "AAD Basics" + "/" + "ImageDownloader");
        File picPath = new File(getActivity().getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "/" + "AAD Basics" + "/" + "ImageDownloader");
        File checkFile = new File(String.valueOf(getActivity().getApplicationContext().getExternalFilesDir("/" + "AAD Basics" + "/" + "ImageDownloader")));
        File checkFile1 = new File(path + "/" + "AAD Basics" + "/" + "ImageDownloader");
        @SuppressLint("SimpleDateFormat")
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
        String imageName = timeStamp + ".jpg";
        File file = new File(checkFile, imageName);
        if (!file.exists()) {
            Log.d("path", file.toString());
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                createNotificationChannel();
                notifyImageSaved(imageName, checkFile, bitmap);
                Toast.makeText(getActivity().getApplicationContext(), imageName + " image saved at location: \n" + checkFile, Toast.LENGTH_LONG).show();
                fos.flush();
                fos.close();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name);
            String description = getString(R.string.app_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("404", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void notifyImageSaved(String imageName, File checkFile, Bitmap bitmap) {
        String text = imageName + " image saved at location: \n" + checkFile;
        int notificationId = 101;
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);

        File file = new File(checkFile + "/" + imageName); // set your image path

        Uri uri = FileProvider.getUriForFile(getContext(), getActivity().getApplicationContext().getPackageName() + ".fileProvider", file);
        intent.setDataAndType(uri, "image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, 0);

        Notification.Builder builder = new Notification.Builder(getContext(), "404")
                .setSmallIcon(R.mipmap.add_applogo)
                .setContentTitle("Image saved successfully")
                .setContentText(text)
                .setPriority(Notification.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setLargeIcon(bitmap)
                .setShowWhen(true)
                .setAutoCancel(true)
                .setStyle(new Notification.BigPictureStyle()
                        .bigPicture(bitmap)
                        .bigLargeIcon((Icon) null));
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, builder.build());
    }


    //used to retrieve the image in background
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