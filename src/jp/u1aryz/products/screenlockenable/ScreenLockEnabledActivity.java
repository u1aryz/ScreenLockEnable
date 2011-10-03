package jp.u1aryz.products.screenlockenable;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ScreenLockEnabledActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lock);

        // Lock解除画面より手前に表示させる
        final Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        Button btnRelease = (Button) findViewById(R.id.btn_release);
        btnRelease.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // Activityを終了することでLock解除画面に移る
                finish();
            }
        });
    }
}
