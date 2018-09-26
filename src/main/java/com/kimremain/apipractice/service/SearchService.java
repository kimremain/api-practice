package com.kimremain.apipractice.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kimremain.apipractice.module.CommonHttpClient;
import com.kimremain.apipractice.vo.NaverItemVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Service
public class SearchService extends BaseService {

    public ArrayList<NaverItemVO> getData(String location, String keyword) throws Exception {
        CommonHttpClient commonHttpClient = new CommonHttpClient();
        //String url = "https://store.naver.com/sogum/api/businesses?filterId=s11556055&filterOpening=true&query=" + location + " " + keyword + "&start=1&display=100&sortingOrder=precision";
        String query = location + " " + keyword;
        query = URLEncoder.encode(query, "utf-8");
        String url = "https://store.naver.com/sogum/api/businesses?filterOpening=true&query=" + query + "&start=1&display=1000&sortingOrder=precision";
        String result = commonHttpClient.requestHttpGet(url,"utf-8");
        int count = 0;

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(result);
        JsonObject obj = element.getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();

        ArrayList<NaverItemVO> vos = new ArrayList<>();
	    String urlBasic = "https://map.naver.com/local/siteview.nhn?code=";
	    String urlMap = "https://map.naver.com/?mapmode=0&pinType=site&pinId=";
        for (Map.Entry<String, JsonElement> entry: entries) {
            if(entry.getKey().equals("total")) {
                logger.debug("total:"+entry.getValue());
            }
            else if(entry.getKey().equals("items")) {
                JsonArray subArray = (JsonArray) entry.getValue();
                for(JsonElement dummy : subArray )
                {
                    count++;
                    logger.debug("id:"+count+"-"+dummy.getAsJsonObject().get("name"));
                    NaverItemVO vo = new NaverItemVO();
                    vo.setNo(count);
                    vo.setId(dummy.getAsJsonObject().get("id").getAsString());
                    vo.setName(dummy.getAsJsonObject().get("name").getAsString());
                    if(dummy.getAsJsonObject().get("phone") != null) {
                        vo.setPhone(dummy.getAsJsonObject().get("phone").getAsString());
                    }
                    if(dummy.getAsJsonObject().get("commonAddr") != null
                    && dummy.getAsJsonObject().get("addr") != null) {
                        vo.setAddress(dummy.getAsJsonObject().get("commonAddr").getAsString() + " " + dummy.getAsJsonObject().get("addr").getAsString());
                    }
	        		vo.setUrlBasic(urlBasic + vo.getId());
	        		vo.setUrlMap(urlMap + vo.getId());
                    vos.add(vo);
                }
            }
        }

        return vos;
    }
}
