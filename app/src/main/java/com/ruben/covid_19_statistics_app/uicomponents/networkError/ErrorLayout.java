package com.ruben.covid_19_statistics_app.uicomponents.networkError;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ruben.covid_19_statistics_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ErrorLayout extends ConstraintLayout {

    @BindView(R.id.error_layout_ui_component_retry_btn)
    TextView retryBtn;

    public ErrorLayout(Context context) {
        super(context);
        initView();
    }

    public ErrorLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ErrorLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public ErrorLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        View root = inflate(getContext(), R.layout.error_layout_ui_component, this);
        ButterKnife.bind(root);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        retryBtn.setOnClickListener(onClickListener);
    }

    public void hideRetryBtn() {
        retryBtn.setVisibility(View.GONE);
    }
}
