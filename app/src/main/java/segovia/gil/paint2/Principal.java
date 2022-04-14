package segovia.gil.paint2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class Principal extends AppCompatActivity {
    MediaPlayer mep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        mep = MediaPlayer.create(this, R.raw.song);
        mep.start();
    }

    public void crearLienzo(View view) {
        mep.stop();
        Intent iniciarLienzo = new Intent(Principal.this, MainActivity.class);
        startActivity(iniciarLienzo);

    }
}