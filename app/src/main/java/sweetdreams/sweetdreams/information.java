package sweetdreams.sweetdreams;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class information extends Activity {
    private TextView informationText;
    private Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        informationText=(TextView)findViewById(R.id.information);
        back=(Button)findViewById(R.id.back_button);

        informationText.setText(R.string.info);

         back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i=new Intent(information.this,nightSleepActivity.class);
                 startActivity(i);
             }
         });




    }
}
