package theoaktroop.irb24.net;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.IOException;

//hjgjh

public class MainActivity extends ActionBarActivity {
    String TAG ="ORB";

//    private static String file_url = "http://103.19.255.242:8081/stream";
    boolean isServiceRunning;
    Intent intent;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);

        isServiceRunning = UtilFunctions.isServiceRunning(MyService.class.getName(), getApplicationContext());
        if (!isServiceRunning) {
            intent = new Intent(getApplicationContext(), MyService.class);
            startService(intent);
            Toast.makeText(getApplicationContext(),"Start Service From MainActivity",Toast.LENGTH_SHORT).show();
        }

    }

    public void buttonClick(View v) {

        isServiceRunning = UtilFunctions.isServiceRunning(MyService.class.getName(), getApplicationContext());
        if (!isServiceRunning) {
            intent = new Intent(getApplicationContext(), MyService.class);
            startService(intent);
            button.setText("STOP");
            Toast.makeText(getApplicationContext(),"Service Start by Button",Toast.LENGTH_SHORT).show();
        }
        else{
            intent = new Intent(getApplicationContext(), MyService.class);
            stopService(intent);
            button.setText("PLAY");
            Toast.makeText(getApplicationContext(),"Service Stop by Button",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        isServiceRunning = UtilFunctions.isServiceRunning(MyService.class.getName(), getApplicationContext());
        if (!isServiceRunning)
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_acitivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                Intent intentAbout = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intentAbout);
                return true;
            case R.id.action_contact:
                Intent intentContact = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intentContact);
               return true;
            case R.id.action_developer:
                Intent intentDeveloper = new Intent(MainActivity.this, DeveloperActivity.class);
                startActivity(intentDeveloper);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
