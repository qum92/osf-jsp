package com.osf.test.exam;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import com.google.gson.Gson;
public class Translator {

    public static void main(String[] args) {
        String clientId = "CZwfMEudtjClJNttlEHq";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "cfQcFdK2kf";//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode("안녕하세요", "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            // post request
            String postParams = "source=ko&target=en&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            Gson gson = new Gson();
            Map<String,Object> result = gson.fromJson(response.toString(), Map.class);
            Map<String,Object> mMap = (Map<String, Object>) result.get("message");
            Map<String,Object> rMap = (Map<String, Object>) mMap.get("result");
            System.out.println(rMap.get("translatedText"));
//            Map<String,Map<String,Map<String,String>>> results = gson.fromJson(response.toString(), Map.class);
//            System.out.println(results.get("message").get("result").get("translatedText"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
