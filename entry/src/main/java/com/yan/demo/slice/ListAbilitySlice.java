package com.yan.demo.slice;

import com.yan.demo.ResourceTable;
import com.yan.demo.bean.ListBean;
import com.yan.demo.provider.ListItemProvider;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.IntentParams;
import ohos.agp.components.ListContainer;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.ArrayList;
import java.util.List;

public class ListAbilitySlice extends AbilitySlice {
    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00202, "ListAbilitySlice");

    private ListContainer mListContainer;
    private List<ListBean> mData = new ArrayList<>();

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_list);
        initList();
        mListContainer = (ListContainer) findComponentById(ResourceTable.Id_mListContainer);
        ListItemProvider sampleItemProvider = new ListItemProvider(ListAbilitySlice.this, mData);
        mListContainer.setItemProvider(sampleItemProvider);
    }

    private void initList() {
        for (int i = 0; i < 100; i++) {
            ListBean mListBean = new ListBean();
            mListBean.setAge(i);
            mListBean.setName("测试" + i);
            mData.add(mListBean);
        }
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
