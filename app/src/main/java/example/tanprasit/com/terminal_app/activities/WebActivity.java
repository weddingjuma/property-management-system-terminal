package example.tanprasit.com.terminal_app.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import example.tanprasit.com.terminal_app.R;
import example.tanprasit.com.terminal_app.fragments.FragmentList;
import example.tanprasit.com.terminal_app.fragments.FragmentSwitcher;
import example.tanprasit.com.terminal_app.fragments.WeatherFragment;
import example.tanprasit.com.terminal_app.fragments.NotificationFragment;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //No call for super(). Bug on API Level > 11.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment weatherFragment;
        Fragment webFragment;

        if (savedInstanceState == null) {
            // Generate Fragments for fragment switcher.
            weatherFragment = new WeatherFragment();
            webFragment = new NotificationFragment();

            FragmentList.list.add(weatherFragment);
            FragmentList.list.add(webFragment);

            for (Fragment fragment : FragmentList.list) {
                fragmentTransaction.add(fragment.getId(), fragment, String.valueOf(FragmentList.list.indexOf(fragment)));
            }

            fragmentTransaction.commit();
        }

        new FragmentSwitcher(20000, 1000, 1, this).start();
    }
}
