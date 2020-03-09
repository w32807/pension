package com.project.service;

public class SearchService {

	
	// 어떤 배열을 매개변수로 받아서, 내림차순으로

	public int nearlowerPrice(int[] rList, int want_Price) {// 가격이 저장된 배열을 가져옴

		int lt = -99999999;

		for (int co : rList) {

			int t = co - want_Price;// 배열의 값과 원하는 가격을 뺀다.

			if (t < 0) {//원하는 값이 더 클때.

				if (t > lt) {
					lt = t;
				}
			}
		}
		
		return lt;

	}

	public int nearHigerPrice(int[] rList, int want_Price) {// 가격이 저장된 배열을 가져옴
		int gt = 99999999;
		for (int co : rList) {
			int t = co - want_Price;

			if (t > 0) {
				if (t < gt) {
					gt = t;
				}
			}
			
		}
		return gt;
	}
}