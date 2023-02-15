package com.app.magiclamp.service.book;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.repository.BookRepository;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.json.simple.JSONArray;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@Service
@Log4j2
public class AladinOpenApi {

    private final BookRepository bookRepository;

    String strurl;

    public AladinOpenApi(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public ArrayList<Book> mainNewBook() {
        ArrayList<Book> arrayList = new ArrayList<>();
        strurl = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbmoonlovereve331034001&QueryType=ItemNewSpecial&MaxResults=4&start=1&SearchTarget=Book&output=js&Version=20131101";
        JSONArray jsonArray;
        jsonArray = JSONParsing(strurl);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject data = (JSONObject) jsonArray.get(i);
            Book book = new Book(
                    data.get("isbn13").toString(),
                    data.get("cover").toString(),
                    data.get("title").toString(),
                    data.get("author").toString()
            );

            String[] array = data.get("categoryName").toString().split(">");
            String imgUrl = data.get("cover").toString().replace("coversum", "cover500");
            String imgName = data.get("isbn13").toString();
            String extension = imgUrl.substring(imgUrl.lastIndexOf('.') + 1);

            try {
                URL url = new URL(imgUrl);
                BufferedImage image = ImageIO.read(url);

                String path = "photo";
                File saveDir = new File(new File("").getAbsolutePath(), path);

                if(!saveDir.exists()){
                    saveDir.mkdirs();
                    log.info("make photo dir >>> " + saveDir);
                }

                String newFileName = imgName + "." + extension;
                File newfile = new File(saveDir, newFileName);
                
                ImageIO.write(image, extension, newfile); // 저장
            } catch (IOException e) {
                e.printStackTrace();
            }

            Book bookDB = new Book(
                    data.get("isbn13").toString(),
                    data.get("title").toString(),
                    data.get("author").toString(),
                    data.get("publisher").toString(),
                    data.get("pubDate").toString(),
                    "정상",
                    Integer.parseInt(data.get("priceStandard").toString()),
                    Integer.parseInt(data.get("priceSales").toString()),
                    5,
                    10,
                    "PB",
                    0, "12mm * 21mm", 500,
                    array[1],
                    data.get("description").toString(),
                    "", imgName + "." + extension, null, null
            );

            if(bookRepository.findById(bookDB.getIsbn()).orElse(null) == null){
                bookRepository.save(bookDB);
            }
            arrayList.add(book);
        }
        return arrayList;
    }

    public ArrayList<Book> bestSeller() {
        ArrayList<Book> arrayList = new ArrayList<>();
        strurl = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbmoonlovereve331034001&QueryType=Bestseller&MaxResults=10&start=1&SearchTarget=Book&output=js&Version=20131101";
        JSONArray jsonArray;
        jsonArray = JSONParsing(strurl);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject data = (JSONObject) jsonArray.get(i);
            Book book = new Book(
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
