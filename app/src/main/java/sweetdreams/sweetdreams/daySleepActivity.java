package sweetdreams.sweetdreams;

import android.app.Activity;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class daySleepActivity extends Activity {

    private MediaPlayer dayMedia;
    private Button pausePlayButton;
    private MediaPlayer nightMedia;
    private Button openTimerButton;
    private AlertDialog.Builder timePicker;
    private TextView timePickerResult;
    String[] differentTimes;
    private long milsecondsTimeToShutMusic;
    private static CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_sleep);





        dayMedia=new MediaPlayer();
        dayMedia=MediaPlayer.create(getApplicationContext(),R.raw.day_sleep);
        dayMedia.start();

        differentTimes=getResources().getStringArray(R.array.timer_picker_array);


        pausePlayButton=(Button)findViewById(R.id.playPauseButton);

        openTimerButton=(Button)findViewById(R.id.timerButton);
        timePickerResult=(TextView)findViewById(R.id.timePickerResult);

        pausePlayButton=(Button)findViewById(R.id.pausePlayButton2);

        pausePlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dayMedia.isPlaying())
                {
                    pauseMusic();

                }
                else
                {
                    startMusic();
                }
            }
        });



        openTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                timePicker=new AlertDialog.Builder(daySleepActivity.this);
                timePicker.setTitle(R.string.time_picker_title);
                timePicker.setSingleChoiceItems(differentTimes, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//when user clicks his choice
                        //here we can save user choice- index of "which"

                        openTimerButton.setBackgroundResource(R.drawable.empty_circle);





                        if(which==0)//10 minutes
                            milsecondsTimeToShutMusic=600000;
                        else if(which==1)//20 minutes
                            milsecondsTimeToShutMusic=1200000;
                        else if (which==2)//30 minutes
                            milsecondsTimeToShutMusic=1800000;
                        else if(which==3)//40 minutes
                            milsecondsTimeToShutMusic=2400000;
                        else if(which==4)//one hour
                            milsecondsTimeToShutMusic=3600000;
                        else if (which==5)//two hours
                            milsecondsTimeToShutMusic=7200000;
                        else if (which==6)//four hours
                            milsecondsTimeToShutMusic=14400000;
                        else if (which==7)//eight hours
                            milsecondsTimeToShutMusic=28800000;



                        if(countDownTimer!=null)//in case this is not the first timer USER picks
                        {
                            countDownTimer.cancel(); //cancel the previous timer and create a new one
                            countDownTimer=     new CountDownTimer(milsecondsTimeToShutMusic, 1000) {


                                public void onTick(long millisUntilFinished) {



                                    if (milsecondsTimeToShutMusic<=3600000) //USER pick less than one hour
                                    {

                                        timePickerResult.setText(""+String.format("%02d:%02d",
                                                TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -

                                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                                        timePickerResult.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);





                                    }
                                    else
                                    {

                                        timePickerResult.setText(""+String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished ),
                                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                                        timePickerResult.setTextSize(TypedValue.COMPLEX_UNIT_DIP,24);


                                    }


                                }

                                public void onFinish() { //countdown is finished

                                    dayMedia.pause();
                                }
                            }.start();


                        }
                        else //in case this is the first timer the USER picks
                            countDownTimer= new CountDownTimer(milsecondsTimeToShutMusic, 1000) {


                                public void onTick(long millisUntilFinished) {



                                    if (milsecondsTimeToShutMusic<=3600000)
                                    {
                                        timePickerResult.setText(""+String.format("%02d:%02d",
                                                TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -

                                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                                        timePickerResult.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);


                                    }
                                    else
                                    {
                                        timePickerResult.setText(""+String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished ),
                                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                                        timePickerResult.setTextSize(TypedValue.COMPLEX_UNIT_DIP,24);


                                    }


                                }

                                public void onFinish() {

                                    dayMedia.pause();
                                }
                            }.start();
                        //  timePickerResult.setText("יתכבה בעוד " + differentTimes[which]);


                        dialog.dismiss(); //close dialog

                    }
                });

                AlertDialog mDialog = timePicker.create();
                mDialog.show();



            }
        });

    }

    @Override
    protected void onDestroy() {
        if(dayMedia!=null && dayMedia.isPlaying())
        {
            dayMedia.stop();
            dayMedia.release();
            dayMedia=null;
        }
        super.onDestroy();
    }

    public void pauseMusic()
    {
        if(dayMedia!=null)
        {
            dayMedia.pause();
            pausePlayButton.setBackgroundResource(R.drawable.play_button);
        }

    }

    public void startMusic()
    {
        if(dayMedia!=null)
        {
            dayMedia.start();
            pausePlayButton.setBackgroundResource(R.drawable.pause_button);

        }


    }


}
