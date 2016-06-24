package com.example.commonlibrary.tools;

import android.os.AsyncTask;

import android.util.Xml;


import com.example.commonlibrary.tools.model.Version;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by xuqiwei-Office on 2016/4/11.
 * 拉取app更新信息
 */
public class XMLNetAsynTask extends AsyncTask<Object,Object, String> {
    private ArrayList<Version> verList = null;
    private String url;
    private GetVersionXMLListener getVersionXMLListener;
    public XMLNetAsynTask(String url, GetVersionXMLListener getVersionXMLListener)
    {
        this.url = url;
        this.getVersionXMLListener = getVersionXMLListener;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s.equals("success")){
            //abcLog.d("xmlsuccess==", "xmlsuccess");
            if (verList!=null)
            {
                getVersionXMLListener.getSuccess(verList.get(0));
            }else
            {
                //abcLog.d("verListsize==0","verListsize==0");
            }


        }else
        {
            //abcLog.d("xmlfailure==", "xmlfailure");
            getVersionXMLListener.getFailure();
        }
    }

    @Override
    protected String doInBackground(Object... params) {

//        String path = "http://192.168.1.230:8888/version.xml";
        String path = url;
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) new URL(path)
                    .openConnection();
            con.setConnectTimeout(8000);
            con.setRequestMethod("GET");
            int i=con.getResponseCode();
            if(i==200){
                InputStream in = con.getInputStream();
                XmlPullParser pullParser = Xml.newPullParser();
                pullParser.setInput(in, "UTF-8");
                int event = pullParser.getEventType();
                Version version = null;
                while (event != XmlPullParser.END_DOCUMENT) {
                    switch (event) {
                        case XmlPullParser.START_DOCUMENT:
                            verList = new ArrayList<>();
                            break;
                        case XmlPullParser.START_TAG:
                            if ("VERSION".equals(pullParser.getName()))
                            {
                                version = new Version();


                            }
                            if ("VERSIONCODE".equals(pullParser.getName())) {
                                version.setVersionCode(pullParser.nextText());
                            }
                            if ("FILENAME".equals(pullParser.getName())) {
                                version.setFileName(pullParser.nextText());
                            }
                            if ("LOADURL".equals(pullParser.getName())) {
                                version.setLoadURL(pullParser.nextText());
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if ("VERSION".equals(pullParser.getName())) {
                                verList.add(version);
                                version = null;
                            }
                            break;
                    }
                    event = pullParser.next();
                }

                //abcLog.d("verList",verList.get(0).getFileName() + "***" + verList.get(0).getLoadURL() + "***" +verList.get(0).getVersionCode());
                return "success";
//                这里先返回单个数ju以后要扩展在此行下扩展


            }

        } catch (IOException e) {
            e.printStackTrace();

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        return "failure";
    }
    public interface GetVersionXMLListener
    {
        public void getSuccess(Version version);
        public void getFailure();
    }



}
