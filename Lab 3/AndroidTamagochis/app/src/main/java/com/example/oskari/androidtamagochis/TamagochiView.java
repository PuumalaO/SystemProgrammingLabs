package com.example.oskari.androidtamagochis;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.Image;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.jar.Attributes;

public class TamagochiView extends LinearLayout {

    private Canvas canvas = null;

    public TamagochiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.tamagochi_view, this,true);

        String name;
        String food;
        TypedArray attributeArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TamagochiView,0,0);

        try {
            name = attributeArray.getString(R.styleable.TamagochiView_name);
            food = attributeArray.getString(R.styleable.TamagochiView_food);
        }finally {
            attributeArray.recycle();
        }

        if (name == null) {
            throw new RuntimeException("No title provided");
        }
        if (food == null) {
            throw new RuntimeException("No subtitle provided");
        }

        init(name, food);
    }

    void init(String name, String food){

        TextView nameView = findViewById(R.id.name);
        TextView foodView = findViewById(R.id.food);
        nameView.setText(name);
        foodView.setText(food);

    }

    void setFood(String food){
        TextView foodView = findViewById(R.id.food);
        foodView.setText(food);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.TRANSPARENT);
        Rect rect = new Rect();
        rect.left = 0;
        rect.right = getWidth();
        rect.top = 0;
        rect.bottom = getHeight();

        canvas.drawRect(rect, paint);
    }
}
