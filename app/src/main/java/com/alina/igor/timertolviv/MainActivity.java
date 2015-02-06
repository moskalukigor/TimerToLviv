package com.alina.igor.timertolviv;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

import java.util.*;




public class MainActivity extends ActionBarActivity {

    private static final int MILLIS_PER_SECOND = 1000;
    private TextView lblTimer;
    private Button btnRefresh;
    Timer timer = new Timer();
    TimerTask timerTask;


    public int Day,Hours,Minutes,Seconds;
    public int nDay;
    public int wDay,wHours,wMinutes,wSeconds;
    public int chk = 1;
    public String sSeconds = "";
    public String sMinutes = "";
    public String sHours = "";
    public String sDay = "";


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
                        wSeconds = 59;
                        wMinutes--;
                            if (wMinutes == 0) {
                            wMinutes = 59;
                            wHours--;
                                if (wHours == 0) {
                                wHours = 23;
                                wDay--;
                                    if (wDay == 0) {
                                    timer.cancel();
                                    lblTimer.setText("Час вийшов, пора у Львів");
                                    }
                                }
                            }
                        }
                        if(wSeconds == 1 || wSeconds == 21 || wSeconds == 31 || wSeconds == 41 || wSeconds == 51)
                            sSeconds = " Секунда ";
                        else if(wSeconds == 2 || wSeconds == 3 || wSeconds == 4 || wSeconds == 22 || wSeconds == 23 || wSeconds == 24 || wSeconds == 32 || wSeconds == 33 || wSeconds == 34 || wSeconds == 42 || wSeconds == 43 || wSeconds == 44 || wSeconds == 52 || wSeconds == 53 || wSeconds == 54)
                            sSeconds = " Секунди ";
                        else
                            sSeconds = " Секунд ";

                        if(wMinutes == 1 || wMinutes == 21 || wMinutes == 31 || wMinutes == 41 || wMinutes == 51)
                            sMinutes = " Хвилина ";
                        else if(wMinutes == 2 || wMinutes == 3 || wMinutes == 4 || wMinutes == 22 || wMinutes == 23 || wMinutes == 24 || wMinutes == 32 || wMinutes == 33 || wMinutes == 34 || wMinutes == 42 || wMinutes == 43 || wMinutes == 44 || wMinutes == 52 || wMinutes == 53 || wMinutes == 54)
                            sMinutes = " Хвилини ";
                        else
                            sMinutes = " Хвилин ";

                        if(wHours == 1 || wHours == 21)
                            sHours = " Година ";
                        else if(wHours == 2 || wHours == 3 || wHours == 4 || wHours == 22 || wHours == 23 || wHours == 24)
                            sHours = " Години ";
                        else
                            sHours = " Годин ";

                        if(wDay == 1 || wDay % 10 == 21 || wDay % 10 == 31 || wDay % 10 == 41 || wDay % 10 == 51 || wDay % 10 == 61 || wDay % 10 == 71 || wDay % 10 == 81 || wDay % 10 == 91)
                            sDay = " День ";
                        else if(wHours == 2 || wDay % 10 == 22 || wDay % 10 == 23 || wDay % 10 == 24 || wDay % 10 == 32 || wDay % 10 == 33 || wDay % 10 == 34 || wDay % 10 == 42 || wDay % 10 == 43 || wDay % 10 == 44
                                || wDay % 10 == 52 || wDay % 10 == 53 || wDay % 10 == 54|| wDay % 10 == 62 || wDay % 10 == 63 || wDay % 10 == 64|| wDay % 10 == 72 || wDay % 10 == 73 || wDay % 10 == 74
                                || wDay % 10 == 82 || wDay % 10 == 83 || wDay % 10 == 84|| wDay % 10 == 92 || wDay % 10 == 93 || wDay % 10 == 94)
                            sDay = " Дні ";
                        else
                            sDay = " Днів ";


                            lblTimer.setText("Залишилося до Львова:\n" + wDay + sDay + wHours
                            + sHours + wMinutes + sMinutes + wSeconds + sSeconds);
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
        btnRefresh = (Button) findViewById((R.id.btnRefresh));



        GetAllData();
        startTimer();

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetAllData();
            }
        });

    }


    public void GetAllData()
    {
        Calendar cal = Calendar.getInstance();
        Day = cal.get(Calendar.DAY_OF_WEEK);
        Hours = cal.get(Calendar.HOUR_OF_DAY);
        Minutes = cal.get(Calendar.MINUTE);
        Seconds = cal.get(Calendar.SECOND);
        nDay = 15;
        wDay = 28 - Day + nDay;
        wHours = 23 - Hours;
        wMinutes = 60 - Minutes;
        wSeconds = 60 - Seconds;
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
