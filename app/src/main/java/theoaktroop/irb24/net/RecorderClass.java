package theoaktroop.irb24.net;

import android.media.MediaRecorder;
import android.os.Environment;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Hasan Abdullah on 08-Jul-15.
 */
public class RecorderClass {

    MediaRecorder myAudioRecorder = new MediaRecorder();
    private String outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.mp3";;
    ;

    public RecorderClass() {
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);
    }

    public void startRecording(){
        try {
            myAudioRecorder.prepare();
            myAudioRecorder.start();
            System.out.println("Recording start!!!");
        }

        catch (IllegalStateException e) {
            e.printStackTrace();
            System.out.println("Recording is in Catch!");
        }

        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Recording is in I/O Exception!");
        }

    }


    public void stopRecording(){
        myAudioRecorder.stop();
        myAudioRecorder.release();
        myAudioRecorder  = null;
    }
}
