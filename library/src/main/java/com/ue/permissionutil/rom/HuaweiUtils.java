/*
 * Copyright (C) 2016 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.ue.permissionutil.rom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.ue.permissionutil.common.CommonUtils;
import com.ue.permissionutil.common.PermissionOps;

public class HuaweiUtils {

    public static void forwardPermSettingPage(Context context, int permOp) {
        if (permOp == PermissionOps.OP_SYSTEM_ALERT_WINDOW) {
            forwardPopupWinPermSettingPage(context);
        } else {
            CommonUtils.forwardAppDetailPage(context);
        }
    }

    private static void forwardPopupWinPermSettingPage(Context context) {
        Intent intent;
        ComponentName comp;

        intent = new Intent();
        comp = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity");//悬浮窗管理页面
        intent.setComponent(comp);
        if (CommonUtils.safelyStartActivity(context, intent, null)) {
            return;
        }

        intent = new Intent();
        comp = new ComponentName("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationManagmentActivity");//悬浮窗管理页面
        intent.setComponent(comp);
        if (CommonUtils.safelyStartActivity(context, intent, null)) {
            return;
        }

        forwardPermSettingsPage(context);
    }

    private static void forwardPermSettingsPage(Context context) {
        Intent intent;
        ComponentName comp;

        intent = new Intent();
        comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");//华为权限管理，跳转到本app的权限管理页面,这个需要华为接口权限，未解决
        intent.setComponent(comp);
        if (CommonUtils.safelyStartActivity(context, intent, null)) {
            return;
        }

        intent = new Intent();
        comp = new ComponentName("com.Android.settings", "com.android.settings.permission.TabItem");//权限管理页面 android4.4
        intent.setComponent(comp);
        if (CommonUtils.safelyStartActivity(context, intent, null)) {
            return;
        }

        CommonUtils.forwardAppDetailPage(context);
    }
}