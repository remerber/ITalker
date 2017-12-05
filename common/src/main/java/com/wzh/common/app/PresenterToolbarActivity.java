package com.wzh.common.app;

import com.wzh.factory.presenter.BaseContract;

/**
 * Created by HP on 2017/11/28.
 *
 * @author by wangWei
 */

public abstract class PresenterToolbarActivity<Presenter extends BaseContract.Presenter> extends ToolbarActivity
        implements BaseContract.View<Presenter> {


    protected Presenter mPresenter;

    @Override
    protected void initBefore() {
        super.initBefore();
        initPresenter();
    }

    /**
     * 初始化Presenter
     *
     * @return
     */
    protected abstract Presenter initPresenter();


    @Override
    public void setPresenter(Presenter presenter) {
        // View中赋值Presenter
        mPresenter = presenter;
    }
    @Override
    public void showError(int str) {
        // 显示错误, 优先使用占位布局
        if (mPlaceHolderView != null) {
            mPlaceHolderView.triggerError(str);
        } else {
            BaseApplication.showToast(str);
        }
    }

    @Override
    public void showLoading() {
        if (mPlaceHolderView != null) {
            mPlaceHolderView.triggerLoading();
        }
    }

    protected void hideLoading() {
        if (mPlaceHolderView != null) {
            mPlaceHolderView.triggerOk();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //界面关闭时进行销毁
        if(mPresenter!=null){
            mPresenter.destroy();
        }
    }
}
