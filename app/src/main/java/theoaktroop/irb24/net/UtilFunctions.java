package theoaktroop.irb24.net;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by Hasan Abdullah on 24-Jun-15.
 * 4
 */
public class UtilFunctions {

    public static boolean isServiceRunning(String serviceName, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if(serviceName.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
