package theoaktroop.irb24.net;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.Toast;


public class ContactActivity extends ActionBarActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.002F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

    }

    public void emailAction(View view){
        view.startAnimation(buttonClick);

        Log.i("Send email", "");
//        String[] TO = {"hasan_cse91@yahoo.com","sunny_mhs@hotmail.com","chistyinfo@gmail.com","shakirahmed1996@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
//        System.out.println("dhuru ja   "+TO[0]);

        String[] TO = {"infoirb24.net@gmail.com"};
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);

        //emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "About App of Ramadan");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");

        try {
            startActivity(Intent.createChooser(emailIntent, "Email Sent!"));
            finish();
            Log.i("E-mail sent!", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ContactActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void contactAction(View view){
        view.startAnimation(buttonClick);

        try{
            String Url = null;
            switch (view.getId()) {
                case R.id.irb_fb:
                    Url = getString(R.string.irb_fb);
                    break;
                case R.id.irb_twitter:
                    Url = getString(R.string.irb_twitter);
                    break;
                case R.id.irb_youtube:
                    Url = getString(R.string.irb_youtube);
                    break;

            }
            Intent webIntent=new Intent(Intent.ACTION_VIEW);

            webIntent.setData(Uri.parse(Url));
            startActivity(webIntent);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Something wrong!\nReport to Developer!", Toast.LENGTH_SHORT).show();
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
//            case R.id.action_home:
//                Intent intentHome = new Intent(ContactActivity.this, MainActivity.class);
//                startActivity(intentHome);
            case R.id.action_about:
                Intent intentAbout = new Intent(ContactActivity.this, AboutActivity.class);
                startActivity(intentAbout);
                return true;
            case R.id.action_developer:
                Intent intentDeveloper = new Intent(ContactActivity.this, DeveloperActivity.class);
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
