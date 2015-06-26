package theoaktroop.irb24.net;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class DonateActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        addVisibile();
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
                Intent intentAbout = new Intent(DonateActivity.this, AboutActivity.class);
                startActivity(intentAbout);
                return true;
            case R.id.action_contact:
                Intent intentContact = new Intent(DonateActivity.this, ContactActivity.class);
                startActivity(intentContact);
                return true;
            case R.id.action_developer:
                Intent intentDeveloper = new Intent(DonateActivity.this, DeveloperActivity.class);
                startActivity(intentDeveloper);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void addVisibile() {

        LinearLayout adLinearLayout=(LinearLayout)findViewById(R.id.adDonateActivity);
        if(isNetworkAvailable()) {
            adLinearLayout.setVisibility(View.VISIBLE);
            AdView mAdView;
            mAdView = (AdView) findViewById(R.id.addView4);
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

    @Override
    protected void onPause() {
        super.onPause();
        addVisibile();
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;

        sharedPreferences = getSharedPreferences("RadioAppData", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        int ot = getResources().getConfiguration().orientation;
        if(ot== Configuration.ORIENTATION_LANDSCAPE){
            editor.putInt("flagDo",1);
            editor.commit();

        }
        else if(ot==Configuration.ORIENTATION_PORTRAIT && sharedPreferences.getInt("flagDo",0)!=0) {
            editor.putInt("flagDo",0);
            editor.commit();
        }

        else if(ot==Configuration.ORIENTATION_PORTRAIT && sharedPreferences.getInt("flagDo",0)==0)
        {
            finish();

        }

    }
}
