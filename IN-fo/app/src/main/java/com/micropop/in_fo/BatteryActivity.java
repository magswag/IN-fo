package com.micropop.in_fo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class BatteryActivity extends AppCompatActivity {
    private TextView txtBattery;
    private TextView Temp;
    public ImageView icomm;

    private BroadcastReceiver batteryLevelReciver = new BroadcastReceiver() {
        private static final String EXTRA_IMAGE = "resourseInt";
        private static final String EXTRA_TITLE = "Titleee";

        @Override
        public void onReceive(Context context, Intent intent) {
            int temprature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
            float tempra = ((float) temprature) / 10;
            Bundle extras = getIntent().getExtras();
            if (extras == null)
            {
                return;
            }
            int res = extras.getInt("resourseInt");

            //int rawLevel = intent.getIntExtra("level", -1);
            int rawLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            //int scale = intent.getIntExtra("scale", -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int level = -1;
            if (rawLevel >= 0 && scale > 0) {
                level = (rawLevel * 100) / scale;
            }
            txtBattery.setText(level + "%");
            Temp.setText(tempra + " °C");
            Bundle extrass = getIntent().getExtras();

            int tittel = extrass.getInt("Titleee");
            if (tittel== R.string.phone) {
                String Buildd = android.os.Build.VERSION.RELEASE;
                Temp.setText(Buildd);

            }

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        txtBattery = (TextView) findViewById(R.id.per);
        Temp = (TextView) findViewById(R.id.temp);
        icomm = (ImageView) findViewById(R.id.iconn);
        batteryLevel();


        Bundle extras = getIntent().getExtras();
        if (extras == null)
        {
            return;
        }
        int tittel = extras.getInt("Titleee");
        setTitle(tittel);
        int res = extras.getInt("resourseInt");
        int imgbottom = extras.getInt("imgbutton");
        ImageView topimg = (ImageView) findViewById(R.id.top_img);

        ImageView bottomimg = (ImageView) findViewById(R.id.bottom_img);
        bottomimg.setImageResource(imgbottom);
        topimg.setImageResource(res);
        icomm.setImageResource(res);

    }


    private void batteryLevel() {
        IntentFilter batteryLevelFliter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryLevelReciver, batteryLevelFliter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
