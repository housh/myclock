package hsh.com.my_gittest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView view = null;
    private TextView secondView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.clock);
        secondView = findViewById(R.id.clock_second);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Message message = myHandler.obtainMessage();
                    message.what = 1;
                    myHandler.sendMessage(message);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    private Handler myHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            try {
                if (msg.what == 1) {
                    view.setText(new SimpleDateFormat("HH:mm").format(new Date()));
                    Calendar calendar = Calendar.getInstance();
                    secondView.setText(String.format("%02d", calendar.get(Calendar.SECOND)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    });
}
