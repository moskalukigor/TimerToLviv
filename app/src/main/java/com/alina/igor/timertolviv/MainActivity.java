package com.alina.igor.timertolviv;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.os.Handler;

import java.util.*;




public class MainActivity extends ActionBarActivity {

    private static final int MILLIS_PER_SECOND = 1000;
    private TextView lblTimer;
    Timer timer = new Timer();
    TimerTask timerTask;


    public int Day,Hours,Minutes,Seconds;
    public int nDay;
    public int wDay,wHours,wMinutes,wSeconds;

    final Handler handler = new Handler();





    public void startTimer() {
        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask, 0, 1000); //
    }


    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        if (wSeconds == 0) {
                        wSeconds = 60;
                        wMinutes--;
                            if (wMinutes == 0) {
                            wMinutes = 60;
                            wHours--;
                                if (wHours == 0) {
                                wHours = 24;
                                wDay--;
                                    if (wDay == 0) {
                                    timer.cancel();
                                    lblTimer.setText("Час вийшов, пора у Львів");
                                    }
                                }
                            }
                        }
                        lblTimer.setText("Залишилося до Львова:\n" + wDay + " Днів " + wHours
                        + " Годин " + wMinutes + " Хвилин " + wSeconds + " Секунд ");
                        wSeconds--;
                    }

                });

            }

        };
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);



        lblTimer = (TextView) findViewById(R.id.lblTimerId);
        Calendar cal = Calendar.getInstance();

        Day = cal.get(Calendar.DAY_OF_WEEK);
        Hours = cal.get(Calendar.HOUR_OF_DAY);
        Minutes = cal.get(Calendar.MINUTE);
        Seconds = cal.get(Calendar.SECOND);

        nDay = 15;

        wDay = 28 - Day + nDay;
        wHours = 24 - Hours;
        wMinutes = 60 - Minutes;
        wSeconds = 60 - Seconds;



        startTimer();





    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
