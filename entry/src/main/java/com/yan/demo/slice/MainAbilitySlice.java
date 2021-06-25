package com.yan.demo.slice;

import com.yan.demo.ResourceTable;
import com.yan.demo.bean.ListBean;
import com.yan.demo.provider.ListItemProvider;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.IntentParams;
import ohos.agp.components.*;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.CommonDialog;
import ohos.agp.window.dialog.ToastDialog;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.ArrayList;
import java.util.List;

public class MainAbilitySlice extends AbilitySlice {
    ListContainer mListContainer;
    List<ListBean> mData = new ArrayList<>();
    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00202, "MainAbilitySlice");

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        initList();
        initView();
    }

    private void initList() {
        for (int i = 0; i < 100; i++) {
            ListBean mListBean = new ListBean();
            mListBean.setAge(i);
            mListBean.setName("测试" + i);
            mData.add(mListBean);
        }
    }

    private void initView() {
        mListContainer = (ListContainer) findComponentById(ResourceTable.Id_mListContainer);
        ListItemProvider sampleItemProvider = new ListItemProvider(this, mData);
        mListContainer.setItemProvider(sampleItemProvider);
        findComponentById(ResourceTable.Id_camera_btn).setClickedListener(component -> {
            /*Intent intent=new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withAction("action.intent.Camera")
                    .build();
            intent.setOperation(operation);
            startAbility(intent);*/

            CommonDialog displayDialog = new CommonDialog(MainAbilitySlice.this);
            DirectionalLayout layout = (DirectionalLayout) LayoutScatter.getInstance(this)
                    .parse(ResourceTable.Layout_dialog_item, null, true);
            displayDialog.setSize(1000, DirectionalLayout.LayoutConfig.MATCH_CONTENT);
            displayDialog.setAlignment(LayoutAlignment.CENTER);
            displayDialog.setContentCustomComponent(layout);
            displayDialog.setTransparent(true);
            displayDialog.show();
            Button confirmBtn = (Button) layout.findComponentById(ResourceTable.Id_confirm_btn);
            confirmBtn.setClickedListener(component1 -> {
                displayDialog.destroy();
                new ToastDialog(MainAbilitySlice.this).setContentText("确定").show();
            });
            Button cancelBtn = (Button) layout.findComponentById(ResourceTable.Id_cancel_btn);
            cancelBtn.setClickedListener(new Component.ClickedListener() {
                @Override
                public void onClick(Component component) {
                    displayDialog.destroy();
                    new ToastDialog(MainAbilitySlice.this).setContentText("取消").show();
                }
            });
        });
    }


    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    @Override
    protected void onAbilityResult(int requestCode, int resultCode, Intent resultData) {
        super.onAbilityResult(requestCode, resultCode, resultData);
        HiLog.error(LABEL, "requestCode:%{public}s,resultCode:%{public}s", requestCode, resultCode);
        if (requestCode == 2001 && resultCode == 200) {
            IntentParams intentParams = resultData.getParams();
            String zz = (String) intentParams.getParam("key");
            HiLog.error(LABEL, "zz:%{public}s", zz);
        }
    }
}
