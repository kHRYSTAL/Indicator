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

    private IndicatorView indicatorView;

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
        ViewPager pager = findViewById(R.id.viewPager);
        indicatorView = findViewById(R.id.indicatorView);
        indicatorView.initIndicatorCount(3, 0);
        pager.setAdapter(vpa);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicatorView.onPagerScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                indicatorView.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                indicatorView.onPageScrollStateChanged(state);
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