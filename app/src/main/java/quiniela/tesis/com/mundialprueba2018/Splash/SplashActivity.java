package quiniela.tesis.com.mundialprueba2018.Splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import quiniela.tesis.com.mundialprueba2018.Login.loginActivity;
import quiniela.tesis.com.mundialprueba2018.R;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, loginActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}
