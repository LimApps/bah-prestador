package com.general.files;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Jaison on 17/06/17.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        //Trigger the notification
        GeneralFunctions generalFunctions = MyApp.getInstance().getGeneralFun(context);

//        NotificationScheduler.showNotification(context, MainActivity.class, "",
//                generalFunctions.retrieveLangLBl("", "LBL_APP_INACTIVE_STATE_ALERT_NOTIFICATION"));

        LocalNotification.dispatchLocalNotification(context, generalFunctions.retrieveLangLBl("", "LBL_APP_INACTIVE_STATE_ALERT_NOTIFICATION"), true);

    }
}


