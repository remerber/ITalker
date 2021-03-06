package com.wzh.italker.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.wzh.common.app.BaseActivity;
import com.wzh.italker.R;
import com.wzh.italker.frags.user.UpdateInfoFragment;

import net.qiujuer.genius.ui.compat.UiCompat;

import butterknife.BindView;

/**
 * Created by HP on 2017/11/3.
 *
 * @author by wangWei
 */

public class UserActivity extends BaseActivity {
    private UpdateInfoFragment mCurFragment;
    @BindView(R.id.im_bg)
    ImageView mBg;

    /**
     * 显示界面的入口方法
     */
    public static void show(Context context) {
        context.startActivity(new Intent(context, UserActivity.class));
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_user;
    }


    @Override
    protected void initWidget() {
        super.initWidget();

        mCurFragment = new UpdateInfoFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.lay_container, mCurFragment)
                .commit();

        // 初始化背景
        Glide.with(this)
                .load(R.drawable.bg_src_tianjin)
                .centerCrop() //居中剪切
                .into(new ViewTarget<ImageView, GlideDrawable>(mBg) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        // 拿到glide的Drawable
                        Drawable drawable = resource.getCurrent();
                        // 使用适配类进行包装
                        drawable = DrawableCompat.wrap(drawable);
                        drawable.setColorFilter(UiCompat.getColor(getResources(), R.color.colorAccent),
                                PorterDuff.Mode.SCREEN); // 设置着色的效果和颜色，蒙板模式
                        // 设置给ImageView
                        this.view.setImageDrawable(drawable);
                    }
                });
    }

    // Activity中收到剪切图片成功的回调
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCurFragment.onActivityResult(requestCode, resultCode, data);
    }
}
