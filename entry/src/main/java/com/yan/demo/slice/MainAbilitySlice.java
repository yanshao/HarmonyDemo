package com.yan.demo.slice;

import com.yan.demo.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.CommonDialog;
import ohos.agp.window.dialog.ToastDialog;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.multimodalinput.event.TouchEvent;

public class MainAbilitySlice extends AbilitySlice implements Component.ClickedListener {

    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00202, "MainAbilitySlice");

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        initView();
    }


    private void initView() {

        Button cameraBtn = (Button) findComponentById(ResourceTable.Id_camera_btn);
        Button listBtn = (Button) findComponentById(ResourceTable.Id_list_btn);
        Button dialogBtn = (Button) findComponentById(ResourceTable.Id_dialog_btn);
        Button clickBtn=(Button) findComponentById(ResourceTable.Id_click_btn);
        cameraBtn.setClickedListener(this::onClick);
        listBtn.setClickedListener(this::onClick);
        dialogBtn.setClickedListener(this::onClick);


        clickBtn.setTouchEventListener(new Component.TouchEventListener() {
            @Override
            public boolean onTouchEvent(Component component, TouchEvent touchEvent) {
                HiLog.error(LABEL, "zz:%{public}s", touchEvent.getAction());
                switch (touchEvent.getAction()) {
                    case TouchEvent.PRIMARY_POINT_DOWN:
                        ShapeElement shapeElement = new ShapeElement(MainAbilitySlice.this, ResourceTable.Graphic_button_bg_gray);
                        clickBtn.setBackground(shapeElement);
                        break;
                    case TouchEvent.PRIMARY_POINT_UP:
                        ShapeElement shapeElement1 = new ShapeElement(MainAbilitySlice.this, ResourceTable.Graphic_button_bg_blue);
                        clickBtn.setBackground(shapeElement1);
                        break;
                }

                return true;
            }
        });
    }

    @Override
    public void onClick(Component component) {
        switch (component.getId()) {
            case ResourceTable.Id_camera_btn:
                Intent intent = new Intent();
                Operation operation = new Intent.OperationBuilder()
                        .withAction("action.intent.Camera")
                        .build();
                intent.setOperation(operation);
                startAbility(intent);
                break;
            case ResourceTable.Id_dialog_btn:
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

                break;
            case ResourceTable.Id_list_btn:
                Intent intent1 = new Intent();
                Operation operation1 = new Intent.OperationBuilder()
                        .withAction("action.intent.List")
                        .build();
                intent1.setOperation(operation1);
                startAbility(intent1);
                break;
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




}
