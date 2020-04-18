package sweetdreams.sweetdreams;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button nightSleepButton;
    private Button daySleepButton;
    private Button planSleepButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        nightSleepButton=(Button)findViewById(R.id.nightSleep);
        daySleepButton=(Button)findViewById(R.id.daySleep);
        planSleepButton=(Button)findViewById(R.id.planSleep);

        nightSleepButton.setOnClickListener(new View.OnClickListener() { //click on night sleep
            @Override
            public void onClick(View v) {

               Intent intentToNightSleep=new Intent(MainActivity.this,nightSleepActivity.class);
                intentToNightSleep.putExtra("choice",1);
                startActivity(intentToNightSleep);


            }
        });

        daySleepButton.setOnClickListener(new View.OnClickListener() { //click on day sleep
            @Override
            public void onClick(View v) {
                Intent intentToDaySleep=new Intent(MainActivity.this,nightSleepActivity.class);
                intentToDaySleep.putExtra("choice",2);
                startActivity(intentToDaySleep);
            }
        });

        planSleepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentToPlanSleep=new Intent(MainActivity.this,nightSleepActivity.class);
                intentToPlanSleep.putExtra("choice",3);
                startActivity(intentToPlanSleep);
            }
        });









    }
}
