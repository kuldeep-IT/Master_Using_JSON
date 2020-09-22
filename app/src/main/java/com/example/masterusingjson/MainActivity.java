package com.example.masterusingjson;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.masterusingjson.Model.ItuneStuff;

import org.json.JSONException;

import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {

    TextView type, artistName, collectionName, trackName, kind;
    Button button;
    ImageView imageView;

    String url;

    String imgUrl;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        type=findViewById(R.id.type);
        artistName=findViewById(R.id.artistName);
        collectionName=findViewById(R.id.collectionName);
        trackName=findViewById(R.id.trackName);
        kind=findViewById(R.id.kind);
        imageView=findViewById(R.id.imageView);

        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskJSONParser asyncTaskJSONParser = new AsyncTaskJSONParser(MainActivity.this);
                asyncTaskJSONParser.execute();
            }
        });

    }


    public class AsyncTaskJSONParser extends AsyncTask<String, Void, ItuneStuff> {

        Context context;
        ProgressDialog progressDialog;

        public AsyncTaskJSONParser(Context context) {
            this.context=context;
            progressDialog=new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("please wait ...");
            progressDialog.setTitle("Downloading Image");
            progressDialog.show();


        }

        @Override
        protected ItuneStuff doInBackground(String... strings) {

            ItuneStuff ituneStuff=new ItuneStuff();
            HTTPConnectionClient httpConnectionClient=new HTTPConnectionClient();

            String urlData = httpConnectionClient.ituneStuffData();

            try {
                ituneStuff = JsonItuneParser.getItuneStuff(urlData);

                imgUrl  = ituneStuff.getArtistViewUrl();
                bitmap = httpConnectionClient.getBitmap(imgUrl);

                Log.d("IMAGE","image");



            } catch (JSONException e) {
                e.printStackTrace();
            }
            return ituneStuff;
        }

        @Override
        protected void onPostExecute(ItuneStuff ituneStuff) {
            super.onPostExecute(ituneStuff);

            type.setText(ituneStuff.getType());
            artistName.setText(ituneStuff.getArtistName());
            collectionName.setText(ituneStuff.getCollectionName());
            trackName.setText(ituneStuff.getTrackName());
            kind.setText(ituneStuff.getKind());

            imageView.setImageBitmap(bitmap);

            if (progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }

        }
    }

}