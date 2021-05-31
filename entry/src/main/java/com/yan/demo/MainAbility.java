package com.yan.demo;

import com.yan.demo.slice.MainAbilitySlice;
import com.yan.demo.utils.LogUtil;
import com.yan.demo.utils.PermissionBridge;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.IntentParams;
import ohos.bundle.IBundleManager;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.security.SystemPermission;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.yan.demo.utils.PermissionBridge.EVENT_PERMISSION_DENIED;
import static com.yan.demo.utils.PermissionBridge.EVENT_PERMISSION_GRANTED;
import static ohos.bundle.IBundleManager.PERMISSION_GRANTED;

public class MainAbility extends Ability implements PermissionBridge.OnPermissionStateListener {
    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00202, "MainAbility");
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final String TAG = "MainAbility";

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
        requestCameraPermission();
        new PermissionBridge().setOnPermissionStateListener(this);
    }

    @Override
    protected void onAbilityResult(int requestCode, int resultCode, Intent resultData) {
        super.onAbilityResult(requestCode, resultCode, resultData);
        HiLog.error(LABEL, "requestCode:%{public}s,resultCode:%{public}s", requestCode,resultCode);
        /*IntentParams dataParam = resultData.getParams();
        String zz1 = (String) dataParam.getParam("key");
        HiLog.error(LABEL, "zz:%{public}s,s", zz1);
*/
    }
    private void requestCameraPermission() {
        List<String> permissions =
                new LinkedList<String>(
                        Arrays.asList(
                                SystemPermission.WRITE_USER_STORAGE,
                                SystemPermission.READ_USER_STORAGE,
                                SystemPermission.CAMERA,
                                SystemPermission.DISTRIBUTED_DATASYNC,
                                SystemPermission.MICROPHONE));
        permissions.removeIf(
                permission ->
                        verifySelfPermission(permission) == PERMISSION_GRANTED || !canRequestPermission(permission));

        if (!permissions.isEmpty()) {
            requestPermissionsFromUser(permissions.toArray(new String[permissions.size()]), PERMISSION_REQUEST_CODE);
        } else {
            PermissionBridge.getHandler().sendEvent(EVENT_PERMISSION_GRANTED);
        }
    }

    @Override
    public void onRequestPermissionsFromUserResult(int requestCode, String[] permissions, int[] grantResults) {
        LogUtil.error(TAG, "3333333="+requestCode);
        if (requestCode != PERMISSION_REQUEST_CODE) {
            return;
        }
        for (int grantResult : grantResults) {
            if (grantResult != PERMISSION_GRANTED) {
                PermissionBridge.getHandler().sendEvent(EVENT_PERMISSION_DENIED);
                terminateAbility();
                return;
            }
        }
        PermissionBridge.getHandler().sendEvent(EVENT_PERMISSION_GRANTED);
    }

    @Override
    public void onPermissionGranted() {

    }

    @Override
    public void onPermissionDenied() {
        terminateAbility();
    }
}
