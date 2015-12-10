package com.example.accessibilitytest;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

/**
 * Created by JW on 15. 12. 1..
 */
public class AccessService extends AccessibilityService {
    String inputText = null;
    int prevEvent = 0;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // TODO Auto-generated method stub
        Log.e("TEST", "Catch Event : " + event.toString());
//        Log.e("TEST", "Catch Event Package Name : " + event.getEventTime());
//        Log.e("TEST", "Catch Event Package Name : " + event.getPackageName());
//        Log.e("TEST", "Catch Event Package Name : " + event.getEventType());

//        아래와 같은 이벤트, 값들을 수집 가능.
//        AccessibilityEvent.TYPE_VIEW_CLICKED

        if(event.getPackageName() == "com.kakao.talk") {
            inputText = event.getText().toString();

            Toast.makeText(this, inputText, Toast.LENGTH_SHORT).show();
            Log.e("TEST", "inputText : " + inputText);
        }
    }

    @Override
    public void onInterrupt() {
        // TODO Auto-generated method stub
        Log.e("TEST", "OnInterrupt");
    }

    public void onServiceConnected() {
        Log.e("TEST", "onServiceConnected!!");

        AccessibilityServiceInfo info = new AccessibilityServiceInfo();

        // We are interested in all types of accessibility events.
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;

        // We want to provide specific type of feedback.
        info.feedbackType = AccessibilityServiceInfo.DEFAULT | AccessibilityServiceInfo.FEEDBACK_HAPTIC;

        // We want to receive events in a certain interval.
        info.notificationTimeout = 100;

        // We want to receive accessibility events only from certain packages.
        // info.packageNames = PACKAGE_NAMES;
        setServiceInfo(info);
    }
}


