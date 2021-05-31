package com.yan.demo.slice;

import com.yan.demo.InfoAbility;
import com.yan.demo.ResourceTable;
import com.yan.demo.bean.ListBean;
import com.yan.demo.provider.ListItemProvider;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.IntentParams;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Component;
import ohos.agp.components.ListContainer;
import ohos.ai.cv.text.Text;
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

    private  void initList() {
        for (int i = 0; i < 100; i++) {
            ListBean mListBean = new ListBean();
            mListBean.setAge(i);
            mListBean.setName("测试" + i);
            mData.add(mListBean);
        }
    }

    private void initView() {
        mListContainer = (ListContainer) findComponentById(ResourceTable.Id_mListContainer);
        ListItemProvider sampleItemProvider = new ListItemProvider(this,mData);
        mListContainer.setItemProvider(sampleItemProvider);
        findComponentById(ResourceTable.Id_camera_btn).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                Intent intent=new Intent();
                Operation operation = new Intent.OperationBuilder()
                        .withAction("action.intent.Camera")
                        .build();
                intent.setOperation(operation);
                startAbility(intent);
            }
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
        HiLog.error(LABEL, "requestCode:%{public}s,resultCode:%{public}s", requestCode,resultCode);
    }
}
