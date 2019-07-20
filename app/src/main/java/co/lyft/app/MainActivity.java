package co.lyft.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends Activity {

    private FrameLayout container;
    private View buttonBlack;
    private View buttonOrange;
    private View buttonBlue;
    private View buttonGreen;

    @ColorRes
    private int selectedColor;

    HashMap<Integer, Integer> hashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        container = findViewById(R.id.root);
        buttonBlack = findViewById(R.id.button_black);
        buttonBlack.setOnClickListener(createOnClick(R.color.black));

        buttonOrange = findViewById(R.id.button_orange);
        buttonOrange.setOnClickListener(createOnClick(R.color.orange));

        buttonBlue = findViewById(R.id.button_blue);
        buttonBlue.setOnClickListener(createOnClick(R.color.blue));

        buttonGreen = findViewById(R.id.button_green);
        buttonGreen.setOnClickListener(createOnClick(R.color.green));

        setButtonSelected(buttonBlack, R.color.black);

        handleCells();
    }

    private void setButtonSelected(View view, @ColorRes int color) {
        selectedColor = color;

        View[] buttons = new View[]{buttonBlack, buttonOrange, buttonBlue, buttonGreen};
        for (View button : buttons) {
            button.setAlpha(button.getId() == view.getId() ? 0.5F : 1.0F);
        }
    }

    private View.OnClickListener createOnClick(@ColorRes final int color) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonSelected(v, color);
            }
        };
    }

    private void handleCells() {

        container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                container.addView(getNewView(event));

                container.addView(getTextView(event));

                return true;
            }
        });
    }

    private View getTextView(@NonNull MotionEvent event) {
        TextView tv = new TextView(this);
        tv.setText("1");

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(50, 50);
        tv.setLayoutParams(params);
        tv.setX(event.getX());
        tv.setY(event.getY());

        return tv;
    }

    @SuppressLint("ResourceAsColor")
    private View getNewView(@NonNull MotionEvent event) {

        TextView tv = new TextView(this);
        tv.setText("1");

        View newCell = new View(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(50, 50);
        newCell.setLayoutParams(params);
        newCell.setX(event.getX());
        newCell.setY(event.getY());
        newCell.setBackgroundColor(ContextCompat.getColor(this, selectedColor));

        return newCell;
    }
}
