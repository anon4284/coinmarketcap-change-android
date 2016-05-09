package com.example.goadstack.coinifier;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

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
        Counter = 0;

        Timer timer = new Timer();

        TimerTask hourlyTask = new TimerTask() {
            @Override
            public void run() {
                GetAndNotify();
            }
        };
        timer.schedule(hourlyTask, 01, 1000*60*60);

    }

    public void GetAndNotify() {

        HttpURLConnection connection = null;
        try {
            String ip = "";
            URL url = new URL("http://"+ ip +":5000/change?coin="+Coin);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");



            InputStream is = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            String response = rd.readLine();
            Notification not = new Notification(mainContext, Coin, response, Counter);
            Counter++;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
