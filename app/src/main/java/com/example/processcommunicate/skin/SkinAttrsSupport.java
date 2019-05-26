package com.example.processcommunicate.skin;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.example.processcommunicate.log.Log;

import java.util.ArrayList;
import java.util.List;

public class SkinAttrsSupport {
    private static final String TAG = "SkinAttrsSupport";

    public static List<SkinAttr> getAttrs(Context context, AttributeSet attrs) {
        List<SkinAttr> skinAttrs = new ArrayList<>();
        int attrNum = attrs.getAttributeCount();
        for (int index = 0; index < attrNum; ++index) {
            String attributeName = attrs.getAttributeName(index);
            String attributeValue = attrs.getAttributeValue(index);
            SkinType skinType = getSkinType(attributeName);
            if (skinType != null) {
                //加入到换肤的行列
                //首先获取换肤资源的名称，因为是通过名称获取资源的
                String resName = getResName(context, attributeValue);
                Log.e(TAG, "resName --> " + resName);
                if (TextUtils.isEmpty(resName))
                    continue;
                SkinAttr skinAttr = new SkinAttr(skinType, resName);
                skinAttrs.add(skinAttr);
            }
            Log.e(TAG, "attributeName --> " + attributeName +
                    ";attributeValue --> " + attributeValue);
        }

        return skinAttrs;
    }

    private static String getResName(Context context, String attributeValue) {

        if (attributeValue.startsWith("@")) {
            attributeValue = attributeValue.substring(1);
            int redId = Integer.parseInt(attributeValue);
            String fullName = context.getResources().getResourceName(redId);
            //通过上面的名称是全名，因此需要取简单名称
            String subName = fullName.substring(fullName.indexOf("/") + 1);
            Log.e(TAG, "subName --> " + subName);
            return subName;
        }
        return null;
    }

    private static SkinType getSkinType(String attributeName) {

        SkinType[] values = SkinType.values();
        for (SkinType value : values) {
            if (value.getResName().equals(attributeName)) {
                //需要换肤的属性
                return value;
            }

        }
        return null;
    }
}
