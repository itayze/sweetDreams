package sweetdreams.sweetdreams;

import android.app.Activity;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class planSleepActivity extends Activity {

    private Button playPauseButton;
    private MediaPlayer planMedia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_sleep);

        planMedia=new MediaPlayer();
        planMedia=MediaPlayer.create(getApplicationContext(),R.raw.plan_sleep);
        planMedia.start();

        playPauseButton=(Button)findViewById(R.id.playPauseButton3);
        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(planMedia.isPlaying())
                {
                    pauseMusic();

                }
                else
                {
                    startMusic();
                }

            }
        });





    }

    @Override
    protected void onDestroy() {
        if(planMedia!=null && planMedia.isPlaying())
        {
            planMedia.stop();
            planMedia.release();
            planMedia=null;
        }
        super.onDestroy();
    }

    public void pauseMusic()
    {
        if(planMedia!=null)
        {
            planMedia.pause();
            playPauseButton.setBackgroundResource(R.drawable.play_button);
        }

    }

    public void startMusic()
    {
        if(planMedia!=null)
        {
            planMedia.start();
            playPauseButton.setBackgroundResource(R.drawable.pause_button);

        }


    }
}
