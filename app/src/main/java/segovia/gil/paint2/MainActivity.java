package segovia.gil.paint2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.graphics.Path;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    public static Path path = new Path();
    public static Paint pincelPintar = new Paint();
    public static boolean color = true;
    public static boolean estrella = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pencil(View view) {
        color = true;
        pincelPintar.setColor(Color.BLACK);
        currentColor(pincelPintar.getColor());
    }

    public void eraser(View view) {
        Display.pathList.clear();
        Display.listaColores.clear();
        path.reset();
        Display.posicionesYo.clear();
        Display.posicionesEstrella.clear();
    }

    public void redColor(View view) {
        color = true;
        pincelPintar.setColor(Color.RED);
        currentColor(pincelPintar.getColor());

    }

    public void yellowColor(View view) {
        color = true;
        pincelPintar.setColor(Color.YELLOW);
        currentColor(pincelPintar.getColor());
    }

    public void greenColor(View view) {
        color = true;
        pincelPintar.setColor(Color.GREEN);
        currentColor(pincelPintar.getColor());
    }

    public void pinkColor(View view) {
        color = true;
        pincelPintar.setColor(Color.MAGENTA);
        currentColor(pincelPintar.getColor());

    }

    public void blueColor(View view) {
        color = true;
        pincelPintar.setColor(Color.BLUE);
        currentColor(pincelPintar.getColor());
    }

    public void pincelYo(View view) {
        color = false;
        estrella = false;
        //Display.mBitmapBrush = BitmapFactory.decodeResource(this.getResources(), R.drawable.me);
    }

    public void currentColor(int c) {
        Display.pincelActual = c;
        path = new Path();
    }

    public void pincelEstrella(View view) {
        color = false;
        estrella = true;
        //Display.mBitmapBrush = BitmapFactory.decodeResource(this.getResources(), R.drawable.estrella);
    }

    public void volver(View view) {
        Display.pathList.clear();
        Display.listaColores.clear();
        path.reset();
        Display.posicionesYo.clear();
        Display.posicionesEstrella.clear();
        Intent iniciarLienzo = new Intent(MainActivity.this, Principal.class);
        startActivity(iniciarLienzo);

    }
}