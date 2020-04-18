package sweetdreams.sweetdreams;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class splashScreen extends Activity {
    private ImageView sheep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sheep=(ImageView)findViewById(R.id.sheep);
        ObjectAnimator animation = ObjectAnimator.ofFloat(sheep, "translationX", 100f);
        animation.setDuration(2000);
        animation.start();

  Thread myThread= new Thread (){
      @Override
      public void run() {
          try {
              sleep(3000);

              Intent intent1=new Intent(splashScreen.this,MainActivity.class);
              startActivity(intent1);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
  };
myThread.start();
    }
}
