package com.example.masterusingjson;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPConnectionClient {

    private static String BASE_URL = "https://itunes.apple.com/search?term=michael+jackson";

    public String ituneStuffData()
    {
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {

            httpURLConnection = (HttpURLConnection) (new URL(BASE_URL)).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            StringBuffer stringBuffer = new StringBuffer();

            inputStream = httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = null;

            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line +"\n");
            }

            inputStream.close();
            httpURLConnection.disconnect();

            return stringBuffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            try {
                inputStream.close();
                httpURLConnection.disconnect();


            }
           catch (Throwable t)
           {
               t.printStackTrace();
           }

        }
        return null;
    }


    public Bitmap getBitmap(String stringUrl)
    {
        Bitmap bitmap = null;

        try {
            URL url = new URL(stringUrl);
            InputStream inputStream = url.openStream();

            bitmap =BitmapFactory.decodeStream(inputStream);





        }
         catch (IOException e) {
            e.printStackTrace();

            return null;
        }

        return bitmap;
    }

}
