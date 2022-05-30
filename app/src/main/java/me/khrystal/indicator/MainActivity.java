package me.khrystal.indicator;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import me.khrystal.widget.IndicatorView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SamplePagerAdapter vpa = new SamplePagerAdapter(new ArrayList<Integer>() {
            {
                this.add(Color.RED);
                this.add(Color.GREEN);
                this.add(Color.BLUE);
            }
        });

        IndicatorView indicatorView1 = findViewById(R.id.indicatorView1);
        IndicatorView indicatorView2 = findViewById(R.id.indicatorView2);
        IndicatorView indicatorView3 = findViewById(R.id.indicatorView3);
        IndicatorView indicatorView4 = findViewById(R.id.indicatorView4);
        IndicatorView indicatorView5 = findViewById(R.id.indicatorView5);
        indicatorView1.setIndicatorStyle(IndicatorView.IndicatorStyle.INDICATOR_CIRCLE);
        indicatorView2.setIndicatorStyle(IndicatorView.IndicatorStyle.INDICATOR_CIRCLE_RECT);
        indicatorView3.setIndicatorStyle(IndicatorView.IndicatorStyle.INDICATOR_BEZIER);
        indicatorView4.setIndicatorStyle(IndicatorView.IndicatorStyle.INDICATOR_DASH);
        indicatorView5.setIndicatorStyle(IndicatorView.IndicatorStyle.INDICATOR_BIG_CIRCLE);
        indicatorView1.setIndicatorSelectedRatio(3f);

        indicatorView1.initIndicatorCount(3, 0);
        indicatorView2.initIndicatorCount(3, 0);
        indicatorView3.initIndicatorCount(3, 0);
        indicatorView4.initIndicatorCount(3, 0);
        indicatorView5.initIndicatorCount(3, 0);


        ViewPager pager = findViewById(R.id.viewPager);
        pager.setAdapter(vpa);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicatorView1.onPagerScrolled(position, positionOffset, positionOffsetPixels);
                indicatorView2.onPagerScrolled(position, positionOffset, positionOffsetPixels);
                indicatorView3.onPagerScrolled(position, positionOffset, positionOffsetPixels);
                indicatorView4.onPagerScrolled(position, positionOffset, positionOffsetPixels);
                indicatorView5.onPagerScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                indicatorView1.onPageSelected(position);
                indicatorView2.onPageSelected(position);
                indicatorView3.onPageSelected(position);
                indicatorView4.onPageSelected(position);
                indicatorView5.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                indicatorView1.onPageScrollStateChanged(state);
                indicatorView2.onPageScrollStateChanged(state);
                indicatorView3.onPageScrollStateChanged(state);
                indicatorView4.onPageScrollStateChanged(state);
                indicatorView5.onPageScrollStateChanged(state);
            }
        });
    }

    class SamplePagerAdapter extends PagerAdapter {
        private List<Integer> colors;

        public SamplePagerAdapter(List<Integer> colors) {
            this.colors = colors;
        }

        @Override
        public int getCount() {
            return colors.size();
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            View view = (View) object;
            return (int) view.getTag();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            TextView textView = new TextView(MainActivity.this);
            textView.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
            textView.setText(String.valueOf(position));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(22);
            textView.setTextColor(Color.parseColor("#ffffff"));
            textView.setBackgroundColor(colors.get(position));
            textView.setTag(position);
            container.addView(textView);
            return textView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

}