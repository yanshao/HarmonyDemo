package com.yan.demo.slice;

import com.yan.demo.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.IntentParams;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class InfoAbilitySlice extends AbilitySlice {
    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00202, "InfoAbilitySlice");
    IntentParams intentParams;
    Button back_btn;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_info);
        HiLog.error(LABEL, "onStart");
        intentParams = intent.getParams();
        String zz = (String) intentParams.getParam("key");
        HiLog.error(LABEL, "zz:%{public}s,s", zz);
        back_btn = (Button) findComponentById(ResourceTable.Id_back_btn);
        back_btn.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                HiLog.error(LABEL, "onClick");
                Intent resultIntent = new Intent();
                Operation operation = new Intent.OperationBuilder()
                        .withAction("action.system.home")
                        .build();
                resultIntent.setOperation(operation);
                resultIntent.setParams(intentParams);
                getAbility().setResult(200, resultIntent);
                terminate();
            }
        });
    }

    @Override
    public void onActive() {
        super.onActive();
        HiLog.error(LABEL, "onActive");

    }

    @Override
    protected void onInactive() {
        super.onInactive();
        HiLog.error(LABEL, "onInactive");

    }

    @Override
    protected void onBackground() {
        super.onBackground();
    }


}
