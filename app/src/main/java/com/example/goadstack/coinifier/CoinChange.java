package com.example.goadstack.coinifier;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by john on 5/9/16.
 */
public class CoinChange {


    private String Coin;
    private Context mainContext;
    private int Counter;

    public CoinChange(Context context, String coin) {
        mainContext = context;
        Coin = coin;
        Counter = 1;
    }

    public String GetChange() {
        HttpURLConnection connection = null;
        try {
            String ip = "52.29.207.160";
            URL url = new URL("http://"+ ip +":7001/change?coin="+Coin);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");



            InputStream is = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(is));



            return rd.readLine();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "false";
    }

}
