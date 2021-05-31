package com.yan.demo;

import com.yan.demo.slice.CameraAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class CameraAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(CameraAbilitySlice.class.getName());
    }

}
