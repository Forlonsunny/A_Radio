package theoaktroop.irb24.net;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
}


//        sharedPreferences = getSharedPreferences("RadioData",MODE_PRIVATE);
//        editor = sharedPreferences.edit();
//
//        editor.putBoolean("flag", true);
//        editor.commit();


//Telephony manager start
//    PhoneStateListener phoneStateListener = new PhoneStateListener() {
//
//        @Override
//        public void onCallStateChanged(int state, String incomingNumber) {
//            if (state == TelephonyManager.CALL_STATE_RINGING) {
//                //Incoming call: Pause music
//                //if(mediaPlayer.isPlaying())
//                    mediaPlayer.pause();
//            }
//            else if(state == TelephonyManager.CALL_STATE_IDLE) {
//                //Not in call: Play music
//
//                sharedPreferences = getSharedPreferences("RadioData",MODE_PRIVATE);
//                editor = sharedPreferences.edit();
//
//                editor.putBoolean("flag", true);
//                editor.commit();
//
//                try {
//                    mediaPlayer = new MediaPlayer();
//                    mediaPlayer.setDataSource(file_url);
//                    mediaPlayer.prepareAsync();
//                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//
//                        public void onPrepared(MediaPlayer mp) {
//                            mp.start();
//                        }
//                    });
//
//                    Toast.makeText(getApplicationContext(), "Playing...", Toast.LENGTH_SHORT).show();
//                }
//                catch (IOException e) {
//                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(),"Something wrong...",Toast.LENGTH_SHORT).show();
//                }
//            }
//            else if(state == TelephonyManager.CALL_STATE_OFFHOOK) {
//                //A call is dialing, active or on hold
//                mediaPlayer.pause();
//            }
//            super.onCallStateChanged(state, incomingNumber);
//        }
//    };

//    TelephonyManager mgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//    if(mgr != null) {
//        mgr.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
//    }
// Telephony manager End







//    http://as1.digitalsynapsebd.com:8081/stream
//    or
//    http://103.19.255.242:8081/stream
//    For Test:
//    http://www.as1.digitalsynapsebd.com:2199/start/irb24/
//    private static String file_url_foorti =   "http://121.200.62.53:1021";
// http://121.200.62.53:1021/;stream.mp3
// http://www.abcradiobd.fm/radio.html