package jp.u1aryz.products.screenlockenable;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class ScreenStateService extends Service {

    private BroadcastReceiver mScreenOnListener = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // 画面の電源が入ったらActivityを起動
            if (action.equals(Intent.ACTION_SCREEN_ON)) {
                Intent i = new Intent(context, ScreenLockEnabledActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        }
    };

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        // ACTION_SCREEN_ONを受け取るBroadcastReceiverを登録
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(mScreenOnListener, filter);
    }

    @Override
    public void onDestroy() {
        // BroadcastReceiverを登録解除
        unregisterReceiver(mScreenOnListener);

        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
