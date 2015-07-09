package theoaktroop.irb24.net;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;


/**
 * Created by Hasan Abdullah on 24-Jun-15.
 *
 */
public class MyService extends Service implements AudioManager.OnAudioFocusChangeListener{

    private static String file_url;
    MediaPlayer mediaPlayer;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    RecorderClass myRecorderClass = new RecorderClass();

    @Override
    public void onCreate() {

        sharedPreferences = getSharedPreferences("IRB24Data", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        mediaPlayer = new MediaPlayer();
//        file_url = "http://103.19.255.242:8081/stream";
        file_url =  "http://programmerguru.com/android-tutorial/wp-content/uploads/2013/04/hosannatelugu.mp3";

//        System.out.println("Service in OnCreate()!");


        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
//      Request audio focus for playback
        int result = am.requestAudioFocus(focusChangeListener,
//      Use the music stream.
                AudioManager.STREAM_MUSIC,
//      Request permanent focus.
                AudioManager.AUDIOFOCUS_GAIN);


        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
// other app had stopped playing song now , so u can do u stuff now .
            try {
                //mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(file_url);
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
//                editor.putBoolean("flag", true);
//                editor.commit();
//                System.out.println("Service in Try!");
//                Toast.makeText(getApplicationContext(), "Playing IRB24.NET", Toast.LENGTH_SHORT).show();
            }
            catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Something wrong...",Toast.LENGTH_SHORT).show();
//                editor.putBoolean("flag", false);
//                editor.commit();
// System.out.println("Service in Catch!");
            }
        }
        super.onCreate();



    }

    public void startRec(){
        myRecorderClass.startRecording();
    }


        @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    private AudioManager.OnAudioFocusChangeListener focusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    AudioManager am =(AudioManager)getSystemService(Context.AUDIO_SERVICE);
                    switch (focusChange) {

                        case (AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) :
                            // Lower the volume while ducking.
                            if(mediaPlayer!=null) {
                                mediaPlayer.setVolume(0.2f, 0.2f);
                            }
                            break;
                        case (AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) :
                            if(mediaPlayer!=null) {
                                mediaPlayer.pause();
                            }
                            break;

                        case (AudioManager.AUDIOFOCUS_GAIN) :
                            // Return the volume to normal and resume if paused.
                            if(mediaPlayer!=null) {
                                mediaPlayer.setVolume(1f, 1f);
                                mediaPlayer.start();
                            }
                            break;
                        default: break;
                    }
                }
            };


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer=null;

//            editor.putBoolean("flag", false);
//            editor.commit();
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onAudioFocusChange(int focusChange) {

    }
}
