package jp.u1aryz.products.screenlockenable;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class SetActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // チェックボックスの値を保存するために使用
        final SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        final Intent intent = new Intent(SetActivity.this, ScreenStateService.class);

        CheckBox chbEnable = (CheckBox) findViewById(R.id.chb_enable);
        chbEnable.setChecked(pref.getBoolean("is_lockEnable", false));
        chbEnable.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // チェックされたらサービスを起動
                if (isChecked) {
                    pref.edit().putBoolean("is_lockEnable", isChecked).commit();
                    startService(intent);
                // チェックが外されたらサービスを停止
                } else {
                    pref.edit().putBoolean("is_lockEnable", isChecked).commit();
                    stopService(intent);
                }
            }
        });
    }
}