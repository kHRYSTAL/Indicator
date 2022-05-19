package me.khrystal.widget;

import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Px;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 2022/5/19
 * update time:
 * email: 723526676@qq.com
 */

public interface Indicator {

    void initIndicatorCount(int count, int currentIndex);

    View getView();

    RelativeLayout.LayoutParams getParams();

    void onPagerScrolled(int position, float positionOffset, @Px int positionOffsetPixels);

    void onPageSelected(int position);

    void onPageScrollStateChanged(int state);
}
