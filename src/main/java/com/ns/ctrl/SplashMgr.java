package com.ns.ctrl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ns.Splash.R;
import com.unity3d.player.UnityPlayer;

public class SplashMgr
{
    private  static SplashMgr instance = null;
    public  static  SplashMgr GetInstance()
    {
        if(instance == null)
        {
            instance = new SplashMgr();
        }
        return  instance;
    }

    private  SplashMgr(){}

    private UnityPlayer mUnityPlayer;
    private ImageView bgImg;
    private ProgressBar barShow;

    public void SetSplash(UnityPlayer unityPlayer, Activity mainActivity)
    {
        mUnityPlayer = unityPlayer;
        bgImg = new ImageView(unityPlayer.currentActivity);
        bgImg.setBackgroundResource(R.drawable.loadingsplash);
        bgImg.setScaleType(ImageView.ScaleType.FIT_XY);
        unityPlayer.addView(bgImg);
        SetProgressBar(unityPlayer,mainActivity);
    }

    public void SetProgressBar(UnityPlayer unityPlayer,Activity mainActivity)
    {
        barShow = new ProgressBar(unityPlayer.currentActivity, null, android.R.attr.progressBarStyleLarge);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2,-2);
        barShow.setLayoutParams(lp);
        WindowManager wm = (WindowManager)mainActivity.getSystemService(Context.WINDOW_SERVICE);
        int w = wm.getDefaultDisplay().getWidth()/2;
        int h = wm.getDefaultDisplay().getHeight()/2;
        barShow.setPadding(w-40,h-60,0,0);
        unityPlayer.addView(barShow,-1);
    }

    public void HideSplash(UnityPlayer unityPlayer)
    {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if(bgImg != null)
                {
                    mUnityPlayer.removeView(bgImg);
                    bgImg = null;
                }

                if(barShow != null)
                {
                    mUnityPlayer.removeView(barShow);
                    barShow = null;
                }
            }
        });
    }
}
