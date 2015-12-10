package com.example.accessibilitytest;

import android.app.Service;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.btn);
        Intent intent = new Intent(this, AccessService.class);
        startService(intent);

        // 접근성 설정 해주는 화면으로 넘기는 부분
//        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
//        startActivityForResult(intent, 0);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Hello World", Toast.LENGTH_SHORT).show();
//                checkPermissions();
                setPermissions();
            }
        });
    }

    public void setPermissions() {
        Intent accessibilityServiceIntent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(accessibilityServiceIntent);
    }

    // 접근성 관련 서비스를 가진 패키지 리스트를 보여준다.
    private void checkPermissions() {
        AccessibilityManager accessibilityManager = (AccessibilityManager) getSystemService(Service.ACCESSIBILITY_SERVICE);
        List<ServiceInfo> installedServices = accessibilityManager.getAccessibilityServiceList();

        for (int i = 0, count = installedServices.size(); i < count; ++i) {
            ServiceInfo serviceInfo = installedServices.get(i);
            String key = serviceInfo.packageName + "/" + serviceInfo.name;

            Log.e("TEST", "access : " + key);
        }
    }

//    private void accessibleApp() {
//        final HashSet<String> enabled = new HashSet<String>();
//        String settingValue = Settings.Secure.getString(getContentResolver(),
//                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
//
//        if (settingValue != null) {
//            TextUtils.SimpleStringSplitter splitter = mStringColonSplitter;
//            splitter.setString(settingValue);
//            while (splitter.hasNext()) {
//                enabled.add(splitter.next());
//                Log.d("TEST", "splitter.next() : " + settingValue);
//            }
//        }
//
//        StringBuilder builder = new StringBuilder(256);
//        builder.append("com.example.accesstest");
//        builder.append(":");
//
//    }
}