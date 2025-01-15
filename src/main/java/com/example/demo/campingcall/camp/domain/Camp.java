package com.example.demo.campingcall.camp.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Camp {
	
	// 이미지 리스트
	private List<CampImage> imageList;
	// primary key
	private String contentId;
	// 야영장명
	private String facltNm;
	// 한줄소개
	private String lineIntro;
	// 소개
	private String intro;
	// 전체 면적
	private String allar;
	// 사업 주체, 구분
	private String facltDivNm;
	// 휴장기간, 휴무기간 시작일
	private String hvofBgnde;
	// 휴장기간, 휴무기간 종료일
	private String hvofEnddle;
	// 특징 ex)물놀이시설 잘 갖춰짐
	private String featureNm;
	// 업종
	private String induty;
	// 입지구분
	private String lctCl;
	// 주소 ~~도 ex)경기도
	private String doNm;
	// 시군구 ~~시 ~~군 ~~구
	private String sigunguNm;
	// 주소
	private String addr1;
	// 경도
	private String mapX;
	// 위도
	private String mapY;
	// 오시는 길 컨텐츠
	private String direction;
	// 전화번호
	private String tel;
	// 홈페이지
	private String homepage;
	// 예약 구분
	private String resveCl;
	// 주요시설 일반 야영장
	private String gnrlSiteCo;
	// 주요시설 자동차야영장
	private String autoSiteCo;
	// 주요시설 글램핑
	private String glampSiteCo;
	// 주요시설 카라반
	private String caravSiteCo;
	// 주요시설 개인카라반
	private String indvdlCaravSiteCo;
	// 사이트간 거리
	private String sitedStnc;
	// 사이트 크기1 가로
	private String siteMg1Width;
	// 사이트 크기2 가로
	private String siteMg2Width;
	// 사이트 크기3 가로
	private String siteMg3Width;
	// 사이트 크기1 세로
	private String siteMg1Vrticl;
	// 사이트 크기2 세로
	private String siteMg2Vrticl;
	// 사이트 크기3 세로
	private String siteMg3Vrticl;
	// 사이트 크기1 수량
	private String siteMg1Co;
	// 사이트 크기2 수량
	private String siteMg2Co;
	// 사이트 크기3 수량
	private String siteMg3Co;
	// 잔디
	private String siteBottomCl1;
	// 파쇄석
	private String siteBottomCl2;
	// 테크
	private String siteBottomCl3;
	// 자갈
	private String siteBottomCl4;
	// 맨흙
	private String siteBottomCl5;
	// 툴팁
	private String tooltip;
	// 글램핑 - 내부시설
	private String glampInnerFclty;
	// 카라반 - 내부시설
	private String caravInnerFclty;
	// 운영기간
	private String operPdCl;
	// 운영일
	private String operDeCl;
	// 개인 트레일러 동반 여부
	private String trlerAcmpnyAt;
	// 개인 카라반 동반 여부
	private String caravAcmpnyAt;
	// 화장실 개수
	private String toiletCo;
	// 샤워실 개수
	private String swrmCo;
	// 개수대 개수
	private String wtrplCo;
	// 화로대
	private String brazierCl;
	// 부대시설
	private String sbrsCl;
	// 부대시설 기타
	private String sbrsEtc;
	// 주변이용가능시설
	private String posblFcltyCl;
	// 주변이용가능시설 기타
	private String posblFcltyEtc;
	// 체험프로그램 여부
	private String exprnProgrmAt;
	// 체험프로그램명
	private String exprnProgrm;
	// 소화기 개수
	private String extshrCo;
	// 방화수 개수
	private String frprvtWrppCo;
	// 방화사 개수
	private String frprvtSandCo;
	// 화재감지기 개수
	private String fireSensorCo;
	// 테마환경
	private String themaEnvrnCl;
	// 캠핑장비 대여
	private String eqpmnLendCl;
	// 애완동물 출입
	private String animalCmgCl;
	// 여행시기
	private String tourEraCl;
	// 대표이미지
	private String firstImageUrl;
	// 수정일
	private String modifiedtime;
}
