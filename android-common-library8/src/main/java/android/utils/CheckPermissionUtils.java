/**
 *
 */
package android.utils;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;

import java.lang.reflect.Method;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :Atar
 * @createTime:2015-9-23下午2:37:50
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
@TargetApi(VERSION_CODES.KITKAT)
public class CheckPermissionUtils {

    @SuppressWarnings("rawtypes")
    public static int checkPermiion(Context context, int op) {
        int returnOp = 0;
        if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
            try {
                AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
                Class[] params = new Class[3];
                params[0] = int.class;
                params[1] = int.class;
                params[2] = String.class;
                ApplicationInfo ai = null;
                Method m = null;
                PackageManager pm = context.getPackageManager();
                ai = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
                m = manager.getClass().getDeclaredMethod("checkOp", params);
                returnOp = (Integer) m.invoke(manager, new Object[]{op, ai.uid, context.getPackageName()});
            } catch (Exception e) {

            }
        }
        // if (returnOp != 0) {
        // Activity activity = ActivityManager.getActivityManager().currentActivity();
        // Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        // Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        // intent.setData(uri);
        // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // CommonApplication.getContext().startActivity(intent);
        // }
        return returnOp;
    }

    public interface OnResultListener {
        public void OnResult(int resultOp);
    }


//    private boolean verifyStoragePermissions(Activity activity) {
//        String[] PERMISSIONS_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
//        if (Build.VERSION.SDK_INT < 23) /*******below android 6.0*******/ {
//            return true;
//        }
//        // Check if we have write permission
//        int permission = ActivityCompat.checkSelfPermission(activity,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            // We don't have permission so prompt the user
//            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE);
//            return false;
//        } else {
//            return true;
//        }
//    }
}
