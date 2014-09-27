package com.therahm.myzudemo.controllers;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;

public class RequestTask {

    private String response;

    protected String retrieveResponse (String uri) {
        String responseString = null;
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;

        try {
            response = httpclient.execute(new HttpGet(uri));
            StatusLine status = response.getStatusLine();

            if (status.getStatusCode() == HttpStatus.SC_OK) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                response.getEntity().writeTo(outputStream);
                outputStream.close();

                responseString = outputStream.toString();
            }
            else {
                response.getEntity().getContent().close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("doInBackground response string: ", responseString);

        this.response = responseString;

        return this.response;
    }

    public String getResponse() {
        return response;
    }
}
