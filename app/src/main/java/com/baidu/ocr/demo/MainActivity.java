package com.baidu.ocr.demo;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.google.gson.Gson;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CUSTOM = 132;
    private boolean hasGotToken = false;
    private AlertDialog.Builder alertDialog;
    private EditText etTitle;
    private EditText etName;
    private EditText etOrderNumber;
    private EditText etAddress;
    private EditText etZiMuDan;
    private EditText etDate;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alertDialog = new AlertDialog.Builder(this);

        initView();
        // 自定义模板
        findViewById(R.id.custom_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkTokenStatus()) {
                    return;
                }
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,
                        CameraActivity.CONTENT_TYPE_GENERAL);
                startActivityForResult(intent, REQUEST_CODE_CUSTOM);
            }
        });

        // 请选择您的初始化方式
        // initAccessToken();
        initAccessTokenWithAkSk();
    }

    private void initView() {
        etTitle = findViewById(R.id.etTitle);
        etName = findViewById(R.id.etName);
        etOrderNumber = findViewById(R.id.etOrderNumber);
        etAddress = findViewById(R.id.etAddress);
        etZiMuDan = findViewById(R.id.etZiMuDan);
        etDate = findViewById(R.id.etDate);
    }

    private boolean checkTokenStatus() {
        if (!hasGotToken) {
            Toast.makeText(getApplicationContext(), "token还未成功获取", Toast.LENGTH_LONG).show();
        }
        return hasGotToken;
    }

    /**
     * 以license文件方式初始化
     */
    private void initAccessToken() {
        OCR.getInstance(this).initAccessToken(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                String token = accessToken.getAccessToken();
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                alertText("licence方式获取token失败", error.getMessage());
            }
        }, getApplicationContext());
    }

    /**
     * 用明文ak，sk初始化
     */
    private void initAccessTokenWithAkSk() {
        OCR.getInstance(this).initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                String token = result.getAccessToken();
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                alertText("AK，SK方式获取token失败", error.getMessage());
            }
        }, getApplicationContext(), "MRyydlxokKW1sD5C3WnTZzj5", "MhAFYOvSgPCiUGHBvRE2DGvYeosFGoEP");
    }

    private void alertText(final String title, final String message) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.setTitle(title)
                        .setMessage(message)
                        .setPositiveButton("确定", null)
                        .show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initAccessToken();
        } else {
            Toast.makeText(getApplicationContext(), "需要android.permission.READ_PHONE_STATE", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dialog = ProgressDialog.show(MainActivity.this, "识别订单信息", "识别中，请稍后...");
        // 识别成功回调，自定义模板
        if (requestCode == REQUEST_CODE_CUSTOM && resultCode == Activity.RESULT_OK) {
            RecognizeService.recCustom(this, FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath(),
                    new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            displayUI(result);
                        }
                    });
        } else {
            dialog.dismiss();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    //展示内容UI
    private void displayUI(String result) {
        try {
            dialog.dismiss();
            Gson gson = new Gson();
            ResultModle resultModle = gson.fromJson(result, ResultModle.class);
            etTitle.setText(resultModle.getData().getRet().get(5).getWord());
            etName.setText(resultModle.getData().getRet().get(0).getWord());
            etOrderNumber.setText(resultModle.getData().getRet().get(3).getWord());
            etAddress.setText(resultModle.getData().getRet().get(4).getWord());
            etZiMuDan.setText(resultModle.getData().getRet().get(1).getWord());
            etDate.setText(resultModle.getData().getRet().get(2).getWord());
        } catch (Exception e) {
            Toast.makeText(this, "数据解析失败，请重试", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放内存资源
        OCR.getInstance(this).release();
    }
}
