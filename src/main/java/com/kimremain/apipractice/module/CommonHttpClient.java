package com.kimremain.apipractice.module;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.List;

public class CommonHttpClient  extends BaseModule{

    public String requestHttpGet(String sendUrl,String sendEncoding)  throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            sendUrl = new String(sendUrl.getBytes("UTF-8"),sendEncoding);
            logger.debug("requestHttpGet- start  : " + sendUrl);
            HttpGet httpget = new HttpGet(sendUrl);

            CloseableHttpResponse response = httpclient.execute(httpget);
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            if (statusLine.getStatusCode() >= 300) {
                throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
            }
            String responseBody = EntityUtils.toString(entity);
            response.close();

            return responseBody;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ClientProtocolException("request fail");
        } finally {
            httpclient.close();
        }
    }

    public String requestHttpPost(String sendUrl, List<NameValuePair> sendParam, String sendEncoding) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            logger.debug("requestHttpPost- start  : " + sendUrl);

            HttpPost httpost = new HttpPost(sendUrl);
            httpost.setHeader("Content-type", "application/x-www-form-urlencoded");

            UrlEncodedFormEntity parameter = new UrlEncodedFormEntity(sendParam,sendEncoding);
            httpost.setEntity(parameter);
            logger.debug("requestHttpPost- sendParameter  : " + parameter);

            CloseableHttpResponse response = httpclient.execute(httpost);
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();

            logger.debug("requestHttpPost- response.getStatusLine  : " + statusLine);

            if (statusLine.getStatusCode() >= 300) {
                throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
            }

            byte[] responseMsg = EntityUtils.toByteArray(entity);
            String responseBody = new String(responseMsg ,sendEncoding);
            logger.debug("responseBody:" + responseBody);

            response.close();
            return responseBody;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ClientProtocolException("request fail");

        } finally {
            httpclient.close();
        }
    }

    public  String responseHttpClient(CloseableHttpResponse response,int i) throws Exception {

        try {
            logger.debug("responseHttpClient start " );

            HttpEntity entity = response.getEntity();

            String res =  response.toString();
            logger.debug("responseHttpClient HEADER:" + res);


            String responseBody = EntityUtils.toString(entity);
            responseBody=   new String(responseBody.getBytes("ISO_8859_1"), "UTF-8");

            logger.debug("responseBody:" + responseBody);

            return responseBody;
        } catch (Exception e) {
            //throw new ClientProtocolException("request fail");

        }
        return null;
    }



}

