package com.yan.demo;

import com.yan.demo.slice.ListAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class ListAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(ListAbilitySlice.class.getName());
    }
}
