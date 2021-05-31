package com.yan.demo.utils;

import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;
import ohos.eventhandler.InnerEvent;

public class PermissionBridge {
    /**
     * permission handler granted
     */
    public static final int EVENT_PERMISSION_GRANTED = 0x0000023;

    /**
     * permission handler denied
     */
    public static final int EVENT_PERMISSION_DENIED = 0x0000024;

    private static final String TAG = PermissionBridge.class.getSimpleName();

    private static OnPermissionStateListener onPermissionStateListener;

    private static EventHandler handler =
            new EventHandler(EventRunner.current()) {
                @Override
                protected void processEvent(InnerEvent event) {
                    switch (event.eventId) {
                        case EVENT_PERMISSION_GRANTED:
                            onPermissionStateListener.onPermissionGranted();
                            break;
                        case EVENT_PERMISSION_DENIED:
                            onPermissionStateListener.onPermissionDenied();
                            break;
                        default:
                            LogUtil.info(TAG, "EventHandler Undefined Event");
                            break;
                    }
                }
            };

    /**
     * setOnPermissionStateListener
     *
     * @param permissionStateListener OnPermissionStateListener
     */
    public void setOnPermissionStateListener(OnPermissionStateListener permissionStateListener) {
        onPermissionStateListener = permissionStateListener;
    }

    /**
     * OnPermissionStateListener
     *
     */
    public interface OnPermissionStateListener {
        void onPermissionGranted();

        void onPermissionDenied();
    }

    /**
     * getHandler
     *
     * @return EventHandler
     */
    public static EventHandler getHandler() {
        return handler;
    }
}
