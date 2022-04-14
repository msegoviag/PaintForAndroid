package segovia.gil.paint2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static segovia.gil.paint2.MainActivity.pincelPintar;
import static segovia.gil.paint2.MainActivity.path;

public class Display extends View {

    public static ArrayList<Path> pathList = new ArrayList<>();
    public static ArrayList<Integer> listaColores = new ArrayList<>();
    public ViewGroup.LayoutParams params;
    public static int pincelActual = Color.BLACK;
    public static Bitmap pincelImagenYo;
    private static Vector2 imagenYoDimensiones;
    public static Bitmap pincelEstrella;
    private static Vector2 pincelEstrellaDimensiones;

    public static List<Vector2> posicionesYo = new ArrayList<Vector2>();
    public static List<Vector2> posicionesEstrella = new ArrayList<Vector2>();

    public Display(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private static final class Vector2 {
        public Vector2(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public final float x;
        public final float y;
    }

    private void init(Context context) {
        pincelPintar.setAntiAlias(true);
        pincelPintar.setColor(Color.BLACK);
        pincelPintar.setStyle(Paint.Style.STROKE);
        pincelPintar.setStrokeCap(Paint.Cap.ROUND);
        pincelPintar.setStrokeJoin(Paint.Join.ROUND);
        pincelPintar.setStrokeWidth(30);
        //pincelImagenYo = BitmapFactory.decodeResource(context.getResources(), R.drawable.me);
        pincelImagenYo = BitmapFactory.decodeResource(context.getResources(), R.drawable.mepeq);
        imagenYoDimensiones = new Vector2(pincelImagenYo.getWidth(), pincelImagenYo.getHeight());
        pincelEstrella = BitmapFactory.decodeResource(context.getResources(), R.drawable.estrella);
        pincelEstrellaDimensiones = new Vector2(pincelEstrella.getWidth(), pincelEstrella.getHeight());

        setBackgroundColor(0xffffffff);
        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        invalidate();
        if (MainActivity.color) {
            //invalidate();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(x, y);
                    invalidate();
                    return true;

                case MotionEvent.ACTION_MOVE:
                    path.lineTo(x, y);
                    pathList.add(path);
                    listaColores.add(pincelActual);
                    invalidate();
                    return true;

                case MotionEvent.ACTION_UP:
                    path.lineTo(x, y);
                    invalidate();
                    return true;

                default:
                    return false;
            }

        } else {

            switch (event.getAction()) {


                case MotionEvent.ACTION_MOVE:
                    x = event.getX();
                    y = event.getY();
                    if (MainActivity.estrella) {
                        posicionesEstrella.add(new Vector2(x - pincelEstrellaDimensiones.x / 2, y - pincelEstrellaDimensiones.y / 2));
                    } else {
                        posicionesYo.add(new Vector2(x - imagenYoDimensiones.x / 2, y - imagenYoDimensiones.y / 2));
                    }


                    invalidate();
                    break;

                case MotionEvent.ACTION_UP:
                    x = event.getX();
                    y = event.getY();
                    if (MainActivity.estrella) {
                        posicionesEstrella.add(new Vector2(x - pincelEstrellaDimensiones.x / 2, y - pincelEstrellaDimensiones.y / 2));
                    } else {
                        posicionesYo.add(new Vector2(x - imagenYoDimensiones.x / 2, y - imagenYoDimensiones.y / 2));
                    }
                    invalidate();
                    return true;

            }
            //invalidate();

            return true;
        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (MainActivity.color) {
            for (Vector2 pos : posicionesEstrella) {
                canvas.drawBitmap(pincelEstrella, pos.x, pos.y, null);
            }
            for (Vector2 pos : posicionesYo) {
                canvas.drawBitmap(pincelImagenYo, pos.x, pos.y, null);
            }
            for (int i = 0; i < pathList.size(); i++) {
                pincelPintar.setColor(listaColores.get(i));
                canvas.drawPath(pathList.get(i), pincelPintar);

                //invalidate();
            }
        } else {

            for (int i = 0; i < pathList.size(); i++) {
                pincelPintar.setColor(listaColores.get(i));
                canvas.drawPath(pathList.get(i), pincelPintar);

                //invalidate();
            }
            for (Vector2 pos : posicionesYo) {
                canvas.drawBitmap(pincelImagenYo, pos.x, pos.y, null);

            }
            for (Vector2 pos : posicionesEstrella) {
                canvas.drawBitmap(pincelEstrella, pos.x, pos.y, null);
            }
        }

        invalidate();

    }
}

