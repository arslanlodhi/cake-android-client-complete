package com.waracle.androidtest.network;

import android.os.AsyncTask;
import android.util.Log;

import com.waracle.androidtest.enums.NetworkCallType;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by arslanlodhi on 2/14/18.
 */

class NetworkCallProcess extends AsyncTask<String, Void, byte[]> {
    private static final String TAG = NetworkManager.class.getSimpleName();

    NetworkRequest request;

    public NetworkCallProcess(NetworkCallType networkCallType, ArrayList<NetworkCallBack> networkCallBacks) {
        request = new NetworkRequest(networkCallBacks, networkCallType);
    }

    public void addNetworkCallBack(NetworkCallBack callBack) {
        if (request != null)
            request.addNetworkCallBack(callBack);
    }

    public void clear(){
        cancel(true);
        request.clear();
        request=null;
    }



    @Override
    protected byte[] doInBackground(String... params) {
        request.setUrl(params[0]);
        byte[] response = null;
        try {
            response = processServerRequest(request.getUrl());

        } catch (IOException | JSONException e) {
            Log.e(TAG, e.getMessage());

            request.callBackOnFailure(e.getMessage());
        }
        return response;
    }


    @Override
    protected void onPostExecute(byte[] response) {

        try {
            request.callBackOnResponseReceived(response,request.charset);
        } catch (Exception e) {
            e.printStackTrace();
            request.callBackOnFailure("Response parsing issue.");
        }
        request.callBackOnStop();
    }

    @Override
    protected void onPreExecute() {
        request.callBackOnStart();
    }

    @Override
    protected void onProgressUpdate(Void... values) {

    }

    private byte[] processServerRequest(String urlStr) throws IOException, JSONException {
        URL url = new URL(urlStr);
        Log.e("URL", "" + urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = null;

        try {
            try {
                // Read data from workstation
                inputStream = connection.getInputStream();
            } catch (IOException e) {
                // Read the error from the workstation
                inputStream = connection.getErrorStream();
            }
            request.setCharset(parseCharset(connection.getRequestProperty("Content-Type")));

            // Can you think of a way to make the entire
            // HTTP more efficient using HTTP headers??
            byte[] bytes = readUnknownFully(inputStream);
            return bytes;
        } finally {
            // Close the input stream if it exists.
            close(inputStream);

            // Disconnect the connection
            connection.disconnect();
        }
    }

    /**
     * Returns the charset specified in the Content-Type of this header,
     * or the HTTP default (ISO-8859-1) if none can be found.
     */
    private static String parseCharset(String contentType) {
        if (contentType != null) {
            String[] params = contentType.split(",");
            for (int i = 1; i < params.length; i++) {
                String[] pair = params[i].trim().split("=");
                if (pair.length == 2) {
                    if (pair[0].equals("charset")) {
                        return pair[1];
                    }
                }
            }
        }
        return "UTF-8";
    }


    // Can you see what's wrong with this???
    //?? It can be done in a single Loop through ByteArrayOutputStream
    // And this method should't be in Utils as static method
    private byte[] readUnknownFully(InputStream stream) throws IOException {
        // Read in stream of bytes

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = stream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();

        return buffer.toByteArray();

    }

    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }


}