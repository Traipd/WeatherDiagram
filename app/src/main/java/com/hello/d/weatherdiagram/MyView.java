package com.hello.d.weatherdiagram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.jar.Attributes;

/**
 * Created by 太平人d on 2018/5/9.
 */
public class MyView extends View {

    public MyView(Context context,AttributeSet attrs)
    {
        super(context,attrs);

    }
    /*画板长宽标准：480*675   */
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        Paint paint=new Paint();
        paint.setAntiAlias(true);//抗锯齿

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2f);

        canvas.drawLine(20,40,20,620,paint);//纵轴
        canvas.drawLine(20,40,10,50,paint);
        canvas.drawLine(20,40,30,50,paint);

        canvas.drawLine(20,620,450,620,paint);//横轴
        canvas.drawLine(450,620,440,630,paint);
        canvas.drawLine(450,620,440,610,paint);

        paint.setTextSize(20);
        canvas.drawText("温度值",30,40,paint);
        canvas.drawText("星期",430,610,paint);

        String[] day={"周一","周二","周三","周四","周五","周六","周日"};//日期

        for(int i=0;i<day.length;i++)
        {
            canvas.drawText(day[i],30+i*60,660,paint);
        }

        float [] dh={42,53,49,38,40,35,43}; //温度数据
        float [] dl={18,35,28,24,15,14,20};

      //  float mid=midMath(dh,dl);//平均值
        float max,min;//最大值 最小值
        max=dh[0];min=dl[0];
        for(int i=1;i<dh.length;i++)
        {
            if(max<dh[i]){max=dh[i];}
            if(min>dl[i]){min=dl[i];}
        }
        float math=540/(max-min);//运算参数

        paint.setColor(Color.RED);
        canvas.drawText("最高温度", 400, 20, paint);
        canvas.drawLine(350, 20, 390, 20, paint);
        paint.setStyle(Paint.Style.STROKE);
        Path path=new Path();
        for(int i=0;i<dh.length;i++)//绘制温度折线
        {
            canvas.drawText(""+(int)dh[i],30+i*60,780-dh[i]*math,paint);
            if(i==0){path.moveTo(30+i*60,780-dh[i]*math);}
            else{path.lineTo(30+i*60,780-dh[i]*math);}
        }
        canvas.drawPath(path,paint);

        paint.setColor(Color.BLUE);
        canvas.drawText("最低温度",400,40,paint);
        canvas.drawLine(350,30,390,30,paint);
        Path path1=new Path();
        for(int i=0;i<dh.length;i++)//绘制温度折线
        {
            canvas.drawText(""+(int)dl[i],30+i*60,780-dl[i]*math,paint);
            if(i==0){path1.moveTo(30+i*60,780-dl[i]*math);}
            else{path1.lineTo(30+i*60,780-dl[i]*math);}
        }
        canvas.drawPath(path1,paint);
    }

    public float midMath( float [] dh,float [] dl)
    {
        float mid=0;
        for(int i=0;i<dh.length;i++)
        {
            mid=mid+dh[i]+dl[i];
        }
            mid=mid/(2*dh.length);
        return mid;
    }

}
