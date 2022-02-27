package com.mardi2020.todoapp.covid19;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class Response {

    @XmlElement(name = "header")
    private Header header;

    @XmlElement(name = "body")
    private Body body;


    @Data
    @XmlRootElement(name = "header")
    public static class Header {

        private String resultCode;
        private String resultMsg;
    }

    @Data
    @XmlRootElement(name = "body")
    public static class Body {

        private Items items;
        private String numOfRows;
        private String pageNo;
        private String totalCount;

        @Data
        @XmlRootElement(name = "items")
        public static class Items {

            private List<Item> item;

            @Data
            @XmlRootElement(name = "item")
            public static class Item {

                private String createDt;
                private String deathCnt;
                private String defCnt;
                private String gubun;
                private String gubnCn;
                private String gubnEn;
                private String incDec;
                private String localOccCnt;
                private String overFlowCnt;
                private String qurRate;
                private String seq;
                private String stdDay;
                private String updateDt;

            }
        }
    }
}
