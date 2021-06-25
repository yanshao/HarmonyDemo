package com.yan.demo.bean;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.rpc.IRemoteObject;

public class TestBean  extends Ability {
    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
    }

    @Override
    protected void onCommand(Intent intent, boolean restart, int startId) {
        super.onCommand(intent, restart, startId);
    }
    @Override
    public IRemoteObject onConnect(Intent intent) {
        return super.onConnect(intent);
    }

    @Override
    public void onDisconnect(Intent intent) {
        super.onDisconnect(intent);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
