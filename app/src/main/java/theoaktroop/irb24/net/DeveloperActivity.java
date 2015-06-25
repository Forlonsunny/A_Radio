package theoaktroop.irb24.net;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;



public class DeveloperActivity extends ActionBarActivity {


    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.002F);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void callToDeveloper(View view){
        view.startAnimation(buttonClick);

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:01521208079"));
        startActivity(callIntent);

    }

    public void FacebookActionOak(View view){
        view.startAnimation(buttonClick);
        try{
            Intent webIntent=new Intent(Intent.ACTION_VIEW);
            String Url = null;
            Url = getString(R.string.title_team_fb);
            webIntent.setData(Uri.parse(Url));
            startActivity(webIntent);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Something wrong!", Toast.LENGTH_SHORT).show();

        }

    }

    public void playStoreShow(View view){

        try{
            String Url = "https://play.google.com/store/apps/developer?id=The+Oak+Troop";

            Intent webIntent=new Intent(Intent.ACTION_VIEW);

            webIntent.setData(Uri.parse(Url));
            startActivity(webIntent);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Something wrong. Report to Developer!", Toast.LENGTH_SHORT).show();
        }

    }

    public void FacebookAction(View view){
        view.startAnimation(buttonClick);
        try{

            String Url = null;
            switch (view.getId()){
                case R.id.hasan_fb:
                    Url=getString(R.string.title_hasan_fb);
                    break;
                case R.id.sunny_fb:
                    Url=getString(R.string.title_sunny_fb);
                    break;
                case R.id.chisty_fb:
                    Url=getString(R.string.title_chisty_fb);
                    break;
                case R.id.saimum_fb:
                    Url=getString(R.string.title_saimum_fb);
                    break;

            }
            Intent webIntent=new Intent(Intent.ACTION_VIEW);

            webIntent.setData(Uri.parse(Url));
            startActivity(webIntent);

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Something wrong!",Toast.LENGTH_SHORT).show();
        }


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

        if(view.getId()==R.id.emailHasan){
            String[] TO = {"hasan_cse91@yahoo.com"};
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        }
        else if(view.getId()==R.id.sunny_email){
            String[] TO = {"sunny_mhs@hotmail.com"};
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        }
        else if(view.getId()==R.id.chisty_email){
            String[] TO = {"chistyinfo@gmail.com"};
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        }
        else if(view.getId()==R.id.saimum_email){
            String[] TO = {"shakirahmed1996@gmail.com"};
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        }
        else if(view.getId()==R.id.emailOakTeam){
            String[] TO = {"oakteam2015@gmail.com"};
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        }


        //emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "About App of Ramadan");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("E-mail sent!", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(DeveloperActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
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
//                Intent intentHome = new Intent(DeveloperActivity.this, MainActivity.class);
//                startActivity(intentHome);
            case R.id.action_about:
                Intent intentAbout = new Intent(DeveloperActivity.this, AboutActivity.class);
                startActivity(intentAbout);
                return true;
            case R.id.action_contact:
                Intent intentContact = new Intent(DeveloperActivity.this, ContactActivity.class);
                startActivity(intentContact);
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
