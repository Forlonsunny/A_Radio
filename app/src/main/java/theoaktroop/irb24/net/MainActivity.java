package theoaktroop.irb24.net;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

//hjgjh

public class MainActivity extends ActionBarActivity {
    String TAG ="ORB";

//    private static String file_url = "http://103.19.255.242:8081/stream";
    boolean isServiceRunning;
    Intent intent;
    ImageView button;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (ImageView) findViewById(R.id.button);
        addVisibile();
        sharedPreferences = getSharedPreferences("IRB24Data", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(isInternetAvailable()){
            isServiceRunning = UtilFunctions.isServiceRunning(MyService.class.getName(), getApplicationContext());
            if (!isServiceRunning) {//Service is stop now, so Start it!
                intent = new Intent(getApplicationContext(), MyService.class);
                startService(intent);

                button.setImageResource(R.drawable.ic_stop);
                editor.putBoolean("flag", true);
                editor.commit();
//                Toast.makeText(getApplicationContext(),"mara kha...",Toast.LENGTH_LONG).show();

//              Toast.makeText(getApplicationContext(),"Start Service From MainActivity",Toast.LENGTH_SHORT).show();
            }
            else{//Service is running now
                button.setImageResource(R.drawable.ic_stop);
            }
        }
        else {
            button.setImageResource(R.drawable.ic_play);
//            editor.putBoolean("flag",false);
//            editor.commit();
            Toast.makeText(getApplicationContext(),"Check your Internet Connection!",Toast.LENGTH_LONG).show();
        }



    }

    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }

    public void buttonClick(View v) {

        if(isInternetAvailable()){

            isServiceRunning = UtilFunctions.isServiceRunning(MyService.class.getName(), getApplicationContext());
            if(!isServiceRunning){//If service is Stop (not running) then Start Service

                intent = new Intent(getApplicationContext(), MyService.class);
                startService(intent);
                //                button.setText("STOP");
                //                Toast.makeText(getApplicationContext(),"Service Start by Button",Toast.LENGTH_SHORT).show();
                button.setImageResource(R.drawable.ic_stop);
                editor.putBoolean("flag",true);
                editor.commit();
            }
            else {//Service is running, then Stop the Service
                intent = new Intent(getApplicationContext(), MyService.class);
                stopService(intent);
                button.setImageResource(R.drawable.ic_play);
                editor.putBoolean("flag", false);
                editor.commit();
            }

        }
        else{
            editor.putBoolean("flag", false);
            editor.commit();
            Toast.makeText(getApplicationContext(),"Check your Internet Connection!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
addVisibile();
        isServiceRunning = UtilFunctions.isServiceRunning(MyService.class.getName(), getApplicationContext());
        if (!isServiceRunning){
            editor.putBoolean("flag", false);
            editor.commit();
            finish();
        }

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
    private void addVisibile() {

        LinearLayout adLinearLayout=(LinearLayout)findViewById(R.id.adMainActivity);
        if(isNetworkAvailable()) {
            adLinearLayout.setVisibility(View.VISIBLE);
            AdView mAdView;
            mAdView = (AdView) findViewById(R.id.adView1);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }
        else {
            adLinearLayout.setVisibility(View.GONE);
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
