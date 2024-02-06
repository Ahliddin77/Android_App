package uz.akhliddin.project4.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import uz.akhliddin.project4.feature.main.MainActivity;

public class Utils {

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public static void getNotificationPermission(Context context) {
        final Activity activityContext = (MainActivity) context;
        if (ContextCompat.checkSelfPermission(
                activityContext, android.Manifest.permission.POST_NOTIFICATIONS
        ) != PackageManager.PERMISSION_GRANTED) {

            // check if the user has previously denied the permission
            ActivityCompat.shouldShowRequestPermissionRationale(
                    activityContext, android.Manifest.permission.POST_NOTIFICATIONS
            );// Show an explanation to the user
// You can customize the message or UI based on your requirements

            // Request the permission
            ActivityCompat.requestPermissions(
                    activityContext,
                    new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                    1001
            );
        }
    }

}
