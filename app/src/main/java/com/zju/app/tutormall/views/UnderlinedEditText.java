package com.zju.app.tutormall.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.zju.app.tutormall.R;

public class UnderlinedEditText extends EditText {
    private static final String TAG = "UnderlineEditText";
    private Paint mPaint;
    private Rect mRect;
    private float mult = 1.5f;
    private float add = 2.0f;

    public UnderlinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UnderlinedEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(getResources().getColor(R.color.mainblue));
        mPaint.setStrokeWidth(6);
        mPaint.setAntiAlias(true);
        this.setLineSpacing(add, mult);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Log.d(TAG, "func [onDraw]");
        int count = getLineCount();
        for (int i = 0; i < count; i++) {
            getLineBounds(i, mRect);
            int baseline = (i + 1) * getLineHeight();
            canvas.drawLine(mRect.left, baseline + 6, mRect.right, baseline + 2, mPaint);
        }
        super.onDraw(canvas);
    }

}

