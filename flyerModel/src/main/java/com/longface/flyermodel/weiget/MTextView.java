package com.longface.flyermodel.weiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.longface.flyermodel.R;

public class MTextView extends AppCompatTextView {

    private String ttc = "fonts/PC.ttf";

    public MTextView(@NonNull Context context) {
        this(context, null);
    }

    public MTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.mapp);
        String ttcName = typedArray.getString(R.styleable.mapp_ttf);

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + ttcName + ".ttf");
        setTypeface(typeface);
    }
}
