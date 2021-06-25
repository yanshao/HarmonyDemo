package com.yan.demo.provider;

import com.yan.demo.ResourceTable;
import com.yan.demo.bean.ListBean;
import com.yan.demo.slice.ListAbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.IntentParams;
import ohos.aafwk.content.Operation;
import ohos.agp.components.*;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.List;

public class ListItemProvider extends BaseItemProvider {
    private List<ListBean> mListBean;
    private ListAbilitySlice mSlice;
    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00202, "ListItemProvider");

    public ListItemProvider(ListAbilitySlice slice, List<ListBean> listBean) {
        this.mSlice = slice;
        this.mListBean = listBean;

    }

    @Override
    public int getCount() {
        return mListBean == null ? 0 : mListBean.size();
    }

    @Override
    public ListBean getItem(int position) {
        if (mListBean != null && position >= 0 && position < mListBean.size()) {
            return mListBean.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Component getComponent(int position, Component convertComponent, ComponentContainer componentContainer) {
        final Component cpt;
        if (convertComponent == null) {
            cpt = LayoutScatter.getInstance(mSlice).parse(ResourceTable.Layout_list_item, null, false);
        } else {
            cpt = convertComponent;
        }
        ListBean sampleItem = mListBean.get(position);

        Text name = (Text) cpt.findComponentById(ResourceTable.Id_name_text);
        name.setText(sampleItem.getName());
        Text age = (Text) cpt.findComponentById(ResourceTable.Id_age_text);
        age.setText(sampleItem.getAge() + "");
        DirectionalLayout item_layout = (DirectionalLayout) cpt.findComponentById(ResourceTable.Id_item_layout);
        item_layout.setClickedListener(component -> {
            Intent intent = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withAction("action.intent.INFO")
                    .build();
            IntentParams intentParams = new IntentParams();
            intentParams.setParam("key", sampleItem.getName());
            intent.setOperation(operation);
            intent.setParams(intentParams);
            mSlice.startAbilityForResult(intent, 2001);
        });
        return cpt;
    }
}
