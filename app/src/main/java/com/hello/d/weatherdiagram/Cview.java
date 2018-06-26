package com.hello.d.weatherdiagram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 太平人d on 2018/5/11.
 */
public class Cview extends View {
    private Path path;
    public Cview(Context context,AttributeSet attrs)
    {
        super(context,attrs);
    }



    protected void onDraw(Canvas canvas){
        int j=100;
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(20);
        path=new Path();
        path.moveTo(30,600);
        path.lineTo(30,75);
        path.lineTo(10,95);
        path.moveTo(30,75);
        path.lineTo(50,95);//到这里画了y轴
        path.moveTo(30,600);
        path.lineTo(50,600);
        canvas.drawPath(path,paint);

        path.moveTo(70,600);
        while(j<=430)
            path.lineTo(j+=20,600);
        path.moveTo(440,600);
        path.lineTo(400,580);
        path.moveTo(440,600);
        path.lineTo(400,620);//到这里画了x轴

        PathEffect pathEffect=new DashPathEffect(new float[]{40,15},0);
        paint.setPathEffect(pathEffect);
        canvas.drawPath(path,paint);

        canvas.drawText("温度值",50,40,paint);
        canvas.drawText("星期",430,580,paint);

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

        paint.setColor(Color.rgb(255,0,255));
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

        paint.setColor(Color.YELLOW);
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

}
