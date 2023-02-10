package com.app.magiclamp.controller.api;

import com.app.magiclamp.service.order.KakaoPay;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
@Log4j2
@RequestMapping("/pay")
public class KakaoPayController {

    @Setter(onMethod_ = @Autowired)
    private KakaoPay kakaopay;


    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {

    }

    /*@PostMapping("/kakaoPay")
    public String kakaoPay() {
        log.info("kakaoPay post............................................");

        return "redirect:" + kakaopay.kakaoPayReady();

    }*/

    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        //return "view/api/kakaoPaySuccess";

    }

    @RequestMapping("/kakaopay")
    @ResponseBody
    public String kakaopay(){
        try{
            URL payurl = new URL("https://kapi.kakao.com/v1/payment/ready");
            HttpURLConnection conn = (HttpURLConnection) payurl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "KaKaoAK f31b396e197bb4a77e0cadabef844cfc");
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setDoOutput(true);
            String param = "cid=TC0ONETIME&partner_order_id=1&partner_user_id=test&item_name=갤럭시S9&quantity=1&total_amount=2100&tax_free_amount=100&approval_url=http://localhost:8080/kakaopay";

            OutputStream data = conn.getOutputStream(); // 데이터 받음
            DataOutputStream dataout = new DataOutputStream(data);
            dataout.writeBytes(param);
            dataout.close(); // 받은 데이터 보내고 닫아줌

            int result = conn.getResponseCode(); // 연결

            InputStream accept;

            if(result==200){
                accept = conn.getInputStream();
            } else {
                accept = conn.getErrorStream();
            }
            InputStreamReader reader = new InputStreamReader(accept);
            BufferedReader buff = new BufferedReader(reader);
            return buff.readLine();

        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "{\"result\":\"NO\"}";
    }
}
