package com.yan.demo;

import com.yan.demo.slice.InfoAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.IntentParams;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class InfoAbility extends Ability {
    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00202, "InfoAbility");
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(InfoAbilitySlice.class.getName());
        HiLog.error(LABEL, "onStart");
    }

    @Override
    protected void onActive() {
        super.onActive();
        HiLog.error(LABEL, "onActive");
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        HiLog.error(LABEL, "onInactive");

    }
}
