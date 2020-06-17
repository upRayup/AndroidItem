package com.zqu.cs.mywebviewapp;
/*使用WebView 需要在AndroidManifest.xml文件中添加权限：
 *<uses-permission android:name="android.permission.INTERNET" />
 * 否则会出现Web page not available错误。
 * */

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView myWebImg;
    private WebView myWebText;
    private WebView myWebClient;
    private final String mimeType = "text/html"; //MIME (Multipurpose Internet Mail Extensions) 是描述消息内容类型的因特网标准，说白了也就是文件的媒体类型
    private final String encoding = "utf-8";//编码 简体中文码：GB2312;繁体中文码:BIG5;西欧字符:UTF-8.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myWebImg = (WebView) findViewById(R.id.webImg);
        myWebText = (WebView) findViewById(R.id.webText);
        myWebClient = (WebView) findViewById(R.id.webClient);
        loadWebPages();

           /*对webClient控件重写setWebViewClient代码取代系统浏览网页方法，
        以在webClient控件显示网页
        */
        myWebClient.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }

    void loadWebPages()
    {
        myWebImg.loadDataWithBaseURL(null, "<a href='http://www.zqu.edu.cn'><img src='file:///android_res/drawable/njnu.jpg'/></a>", mimeType, encoding, null);
        myWebText.loadDataWithBaseURL(null, "<a href='http://cs.zqu.edu.cn'>计算机科学与技术学院</a>", mimeType, encoding, null);
        //WebView的loadUrl()方法，设置WebView的网页
        myWebClient.loadUrl("http://www.zqu.edu.cn");
    }
    /*
     * 为了让WebView支持回退功能，覆盖系统Activity类的onKeyDown()方法,
     * 否则浏览器会调用finish()而结束自身，而不是回退到上一页面。
     * */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && myWebClient.canGoBack()) {
            myWebClient.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void onOkClick(View view) {

        myWebClient.loadDataWithBaseURL(null, "<a href='http://www.hxedu.com.cn/hxedu/fg/book/bookinfo.html?code=G0232700'><img src='file:///android_res/drawable/db0.jpg'/></a>", mimeType, encoding, null);

    }
}
