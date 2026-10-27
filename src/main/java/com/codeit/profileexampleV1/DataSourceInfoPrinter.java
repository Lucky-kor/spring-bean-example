package com.codeit.profileexampleV1;

/** 환경별 구현의 공통 계약입니다. 호출자는 현재 환경의 구체 구현을 몰라도 됩니다. */
public interface DataSourceInfoPrinter {
    void printDataSourceInfo();
}
