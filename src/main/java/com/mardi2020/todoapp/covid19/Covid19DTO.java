package com.mardi2020.todoapp.covid19;

import lombok.Data;

@Data
public class Covid19DTO {

    private String deathCnt; // 사망자 수

    private String defCnt; // 확진자 수

    private String gubun; // 지역

    private String incDec; // 전일대비 증감 수

    private String localOccCnt; // 지역 발생 수

    private String overFlowCnt; // 해외유입 수

    private String stdDay; // 기준일시
}
