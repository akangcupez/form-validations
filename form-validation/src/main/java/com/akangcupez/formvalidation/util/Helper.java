package com.akangcupez.formvalidation.util;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.akangcupez.formvalidation.R;
import com.akangcupez.formvalidation.rule.RuleType;
import com.akangcupez.formvalidation.rule.RuleEntity;

/**
 * @author Aji Subastian (akangcupez@gmail.com)
 */
@SuppressWarnings({"BooleanMethodIsAlwaysInverted"})
public class Helper {

    private static String getResourceString(Context context, int resId) {
        return context.getResources().getString(resId);
    }

    public static String getErrorString(Context context, RuleEntity o, @Nullable CharSequence h) {

        String f = getResourceString(context, R.string.czfv_field);
        String n = (h != null && !TextUtils.isEmpty(h)) ? (String) h : f;

        RuleType rt = o.getRuleType();
        Object v1 = o.getRuleValue1();
        Object v2 = o.getRuleValue2();
        switch (rt) {
            case REQUIRED:
                return String.format(getResourceString(context, R.string.czfv_required), n);
            case EXACT_LENGTH:
                return String.format(getResourceString(context, R.string.czfv_exact_length), n,
                        String.valueOf(v1));
            case BETWEEN_LENGTH:
                return String.format(getResourceString(context, R.string.czfv_between_length), n,
                        String.valueOf(v1), String.valueOf(v2));
            case VALID_EMAIL:
                return String.format(getResourceString(context, R.string.czfv_valid_email), n);
            default:
                return "";
        }
    }

}
