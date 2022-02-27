package com.mardi2020.todoapp.covid19;

import com.mardi2020.todoapp.geoLocation.GeoLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class VirusInfoService {

    @Value("${covidKey}")
    private String key;

    private String getCovidInfo() throws IOException {
        StringBuilder sb = new StringBuilder();

        try {
            String urlBuilder = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson" + "?" +
                    URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + key + /*Service Key*/
                    "&" + URLEncoder.encode("pageNo", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8) + /*페이지번호*/
                    "&" + URLEncoder.encode("numOfRows", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("10", StandardCharsets.UTF_8) + /*한 페이지 결과 수*/
                    "&" + URLEncoder.encode("startCreateDt", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(getToday(), StandardCharsets.UTF_8) + /*검색할 생성일 범위의 시작*/
                    "&" + URLEncoder.encode("endCreateDt", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(getToday(), StandardCharsets.UTF_8);/*URL*//*검색할 생성일 범위의 종료*/
            URL url = new URL(urlBuilder);
            System.out.println("url = " + url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }


        } catch(Exception e){
            e.printStackTrace();
        }

        return sb.toString();
    }

    private String getToday(){
        SimpleDateFormat format1 = new SimpleDateFormat ("yyyyMMdd");
        Date time = new Date();
        return format1.format(time);
    }

    public Response xml2Object(){
        Response response = new Response();

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            String xml = getCovidInfo();

            response = (Response) unmarshaller.unmarshal(new StringReader(xml));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 도, 시 이름으로 원하는 정보만 가져옴
     * @return
     */
    public Covid19DTO filteringInfo(String distirct) {
        Response response = xml2Object();
        Covid19DTO covid19DTO = new Covid19DTO();

        for (Response.Body.Items.Item item : response.getBody().getItems().getItem()) {
            if(distirct.contains(item.getGubun())) {
                covid19DTO.setDeathCnt(item.getDeathCnt());
                covid19DTO.setGubun(item.getGubun());
                covid19DTO.setDefCnt(item.getDefCnt());
                covid19DTO.setIncDec(item.getIncDec());
                covid19DTO.setLocalOccCnt(item.getLocalOccCnt());
                covid19DTO.setOverFlowCnt(item.getOverFlowCnt());
                covid19DTO.setStdDay(item.getStdDay());
                break;
            }
        }
        if (covid19DTO.getGubun() == null) {
            for (Response.Body.Items.Item item : response.getBody().getItems().getItem()) {
                if(item.getGubun().equals("서울")) {
                    covid19DTO.setDeathCnt(item.getDeathCnt());
                    covid19DTO.setGubun(item.getGubun());
                    covid19DTO.setDefCnt(item.getDefCnt());
                    covid19DTO.setIncDec(item.getIncDec());
                    covid19DTO.setLocalOccCnt(item.getLocalOccCnt());
                    covid19DTO.setOverFlowCnt(item.getOverFlowCnt());
                    covid19DTO.setStdDay(item.getStdDay());
                    break;
                }
            }
        }
        return covid19DTO;
    }
}
