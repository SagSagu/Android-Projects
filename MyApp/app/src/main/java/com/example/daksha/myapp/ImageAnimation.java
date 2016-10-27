package com.example.daksha.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ImageAnimation extends AppCompatActivity {

    private ImageView ivGoku;

    public void animation(View view){
        switch (view.getId()){
            case R.id.btnRotate:
                final Animation animRotate=AnimationUtils.loadAnimation(this,R.anim.anim_rotate);
                ivGoku.startAnimation(animRotate);
                break;
            case R.id.btnScale:
                final Animation animScale=AnimationUtils.loadAnimation(this,R.anim.anim_scale);
                ivGoku.startAnimation(animScale);
                break;
            case R.id.btnAlpha:
                final Animation animAlpha=AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
                ivGoku.startAnimation(animAlpha);
                break;
            case R.id.btnTranslate:
                final Animation animTranslate=AnimationUtils.loadAnimation(this,R.anim.anim_translate);
                ivGoku.startAnimation(animTranslate);
                break;
            case R.id.btnShake:
                final Animation animShake=AnimationUtils.loadAnimation(this,R.anim.anim_shake);
                ivGoku.startAnimation(animShake);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_animation_layout);

        ivGoku=(ImageView)findViewById(R.id.ivGoku);
    }

}
