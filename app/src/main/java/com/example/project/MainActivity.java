package com.example.project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected class MyView extends View {
        int j=0;
        int checkCnt=0;
        int[] x2=new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int[] y2=new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        public int cntCrt=0, cntWrg=0;
        public MyView(Context context) {
            super(context);
        }
        private Paint paint = new Paint();

        @Override
        protected void onDraw(Canvas canvas) {

            int width = canvas.getWidth();
            int height = canvas.getHeight();

            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.back);
            Bitmap resize_bitmap = Bitmap.createScaledBitmap(b, width, height, true);

            canvas.drawBitmap(resize_bitmap, 0, 0, paint);

            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setColor(Color.RED);

            for(int i=0;i<j;i++){
                canvas.drawCircle(x2[i], y2[i], 30, paint);
            }

            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
            paint.setStrokeWidth(1);

            canvas.drawText("맞은개수: "+cntCrt, 100, 50, paint);
            canvas.drawText("틀린개수: "+cntWrg, 450, 50, paint);
//            canvas2=canvas;
        }
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    int x = (int) event.getX();
                    int y = (int) event.getY();

                    //x 330~400 ,480~520
                    //y 150~230 ,210~250
                    if ((330 < x && x < 400) && (150 < y&& y < 230)&&checkCnt==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(MainActivity.this, "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        checkCnt = 1;
                        invalidate();
                    }
                    else if ((330 < x && x< 400) && (150 < y && y < 230)&&checkCnt==1){
                        Toast.makeText(MainActivity.this, "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "오답입니다", Toast.LENGTH_SHORT).show();
                        invalidate();
                        cntWrg++;
                    }
                    j++;
//                    String msg = "터치를 입력받음 : " +x+" / " +y;
//                    Toast. makeText(MainActivity. this, msg, Toast.LENGTH_SHORT ).show();
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
            }
            return true;
        }

    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            MyView w = new MyView(this);
            setContentView(w);

        }

}
