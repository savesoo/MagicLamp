package com.app.magiclamp.service.book;

import com.app.magiclamp.entity.Book;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.json.simple.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@Service
@Log4j2
public class AladinOpenApi {

    String strurl;

    public ArrayList<Book> mainNewBook() {
        ArrayList<Book> arrayList = new ArrayList<>();
        strurl = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbmoonlovereve331034001&QueryType=ItemNewSpecial&MaxResults=4&start=1&SearchTarget=Book&output=js&Version=20131101";
        JSONArray jsonArray;
        jsonArray = JSONParsing(strurl);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject data = (JSONObject) jsonArray.get(i);
            Book book = new Book(
                    data.get("cover").toString(),
                    data.get("title").toString(),
                    data.get("description").toString(),
                    data.get("author").toString()
            );
            arrayList.add(book);
        }
        return arrayList;
    }

    public ArrayList<Book> bestSeller() {
        ArrayList<Book> arrayList = new ArrayList<>();
        strurl = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbmoonlovereve331034001&QueryType=Bestseller&MaxResults=10&start=1&SearchTarget=Book&output=js&Version=20131101";
        JSONArray jsonArray;
        jsonArray = JSONParsing(strurl);
        for (int i = 0; i < jsonArray.size(); i++){
            JSONObject data = (JSONObject) jsonArray.get(i);
            Book book = new Book (
                    data.get("title").toString(),
                    data.get("cover").toString()
            );
            arrayList.add(book);
        }
        return arrayList;
    }

    public JSONArray JSONParsing(String strurl) {
        JSONArray JsonArray = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            URL url = new URL(strurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
            String stringline;

            while ((stringline = bufferedReader.readLine()) != null) {
                stringBuffer.append(stringline + "\n");
            }

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(String.valueOf(stringBuffer));
            JsonArray = (JSONArray) jsonObject.get("item");
            httpURLConnection.disconnect();
        } catch (Exception e) {
            System.out.println("JSONParsing 오류" + e);
        }
        return JsonArray;
    }
}
