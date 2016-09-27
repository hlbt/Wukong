package com.jake.wukong.global;

import android.content.Context;
import android.content.Intent;

/**
 * 描述:
 *
 * @author jakechen
 * @since 2016/9/27 16:00
 */
public class Logic {
    public static void addShortcut(Context context, String name, int icon, Class<?> clazz) {
        Intent addShortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");

        // 不允许重复创建
        addShortcutIntent.putExtra("duplicate", false);// 经测试不是根据快捷方式的名字判断重复的
        // 应该是根据快链的Intent来判断是否重复的,即Intent.EXTRA_SHORTCUT_INTENT字段的value
        // 但是名称不同时，虽然有的手机系统会显示Toast提示重复，仍然会建立快链
        // 屏幕上没有空间时会提示
        // 注意：重复创建的行为MIUI和三星手机上不太一样，小米上似乎不能重复创建快捷方式

        // 名字
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

        // 图标
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(context, icon));

        // 设置关联程序
        Intent launcherIntent = new Intent(Intent.ACTION_MAIN);
        launcherIntent.setClass(context, clazz);
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        addShortcutIntent
                .putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcherIntent);

        // 发送广播
        context.sendBroadcast(addShortcutIntent);
    }
}
