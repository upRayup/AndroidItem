package com.example.websevice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String SERVER_URL="http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl";
    private static final String PACE="http://WebXml.com.cn/";
    private static final String W_NAME="getMobileCodeInfo";
    private EditText etPhone;
    private Button btnSearch;
    private TextView tvInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPhone=(EditText)findViewById(R.id.etphone);
        btnSearch=(Button)findViewById(R.id.btnsearch);
        tvInfo=(TextView)findViewById(R.id.tvinfo);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName=etPhone.getText().toString();
                if(cityName.length()>0){
                    getWeatherInfo(cityName);
                }
            }
        });
    }

    private void getWeatherInfo(String cityName) {
        new AsyncTask<String,Void,String>(){
            @Override
            protected String doInBackground(String... params){
                String local="";
                final HttpTransportSE httpSe=new HttpTransportSE(SERVER_URL);
                httpSe.debug=true;
                SoapObject soapObject=new SoapObject(PACE,W_NAME);
                soapObject.addProperty("mobileCode",params[0]);
                soapObject.addProperty("userID","");
                final SoapSerializationEnvelope serializa=new SoapSerializationEnvelope(
                        SoapEnvelope.VER10
                );
                serializa.setOutputSoapObject(soapObject);
                serializa.dotNet=true;
                try{
                    httpSe.call(PACE+W_NAME,serializa);
                    if(serializa.getResponse()!=null){
                        SoapObject result=(SoapObject)serializa.bodyIn;
                        local=result.getProperty("getMobileCodeInfoResult").toString();
                    }
                }catch (XmlPullParserException e){
                    e.printStackTrace();
                }catch (SoapFault soapFault){
                    soapFault.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
                return local;
            }
            @Override
            protected void onPostExecute(String result){
                tvInfo.setText(result);
            }
        }.execute(cityName);
    }
}
