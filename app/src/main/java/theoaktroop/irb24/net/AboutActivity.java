package theoaktroop.irb24.net;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class AboutActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_acitivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_home:
//                Intent intentHome = new Intent(AboutActivity.this, MainActivity.class);
//                startActivity(intentHome);
            case R.id.action_contact:
                Intent intentContact = new Intent(AboutActivity.this, ContactActivity.class);
                startActivity(intentContact);
                return true;
            case R.id.action_developer:
                Intent intentDeveloper = new Intent(AboutActivity.this, DeveloperActivity.class);
                startActivity(intentDeveloper);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
