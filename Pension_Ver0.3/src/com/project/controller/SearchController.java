package com.project.controller;

import java.util.*;

import com.project.common.RoomStorage;
import com.project.dto.*;
import com.project.service.SearchService;
import com.project.view.Viewer;

public class SearchController {
	Viewer viewer = Viewer.getInstance();//인스턴스를 가져옴
	SearchService ss = new SearchService();


	//방 정보리스트를 받아와서 (매개변수로)
	//원하는 가격을 입력 후, 가격이 가장 비슷한 두개를 뽑아옴.

	//---------------------------------------------
	// v0.1
	// 작업자 : 장원준
	// 내  용 : 
	//---------------------------------------------
	public void wanna_Room (ArrayList<Room> room) {
		
		int [] temtarr= new int [room.size()];//가격을 저장할 임시 배열

		viewer.print_String("원하는 가격을 입력 하세요.");
		int want_Price = viewer.input_Price();//가격을 입력

		for(int i = 0 ; i < room.size(); i++) {
			if(!room.get(i).isRoom_Resv())
				temtarr[i] =  room.get(i).getRoom_Price();//i번째의 가격을 가져옴

		}

		int tempt1 =ss.nearlowerPrice(temtarr, want_Price);//원하는 가격보다 작은 값 중 가장 차이가 안 나는 가격 받아오기

		int tempt2 = ss.nearHigerPrice(temtarr, want_Price);//원하는 가격보다 큰 값 중 가장 차이가 안 나는 가격 받아오기


		for(Room r : room) {

			if( r.getRoom_Price()-want_Price  == tempt1) {
				Viewer.getInstance().print_mesg(31);
				Viewer.getInstance().printRoomData(r);
			}
			else if( r.getRoom_Price()-want_Price  == tempt2) {
				Viewer.getInstance().print_mesg(32);
				Viewer.getInstance().printRoomData(r);
			}
		}

	}//wanner_price 메소드의 끝


	//방전체보기 메소드

	//---------------------------------------------
	// v0.1
	// 작업자 : 장원준
	// 내  용 : 
	//---------------------------------------------
	public void output_All() {

		for (Room r : RoomStorage.getInstance().getRoomList()) {

			//System.out.println(r);
			Viewer.getInstance().printRoomData(r);
			// Viewer.getInstance().print_mesg(31);
		}

	}

	//---------------------------------------------------------
	//	v0.1
	//	작성자 : 김종현
	//	내용 : 어레이 리스트에 저장된 room 데이터를 반복문을 이용하여 예약가능한 방의
	//     수를 출력하고 없을때도 출력한다
	//---------------------------------------------------------
	
	public void possibleRoom(ArrayList<Room> room) {
		int cnt = 0;
		Viewer.getInstance().print_mesg(21);
		for(Room r : room) {
			if(!r.isRoom_Resv()) {
				cnt++;
				Viewer.getInstance().printRoomData(r);}
		}if( cnt == 0) {
			System.out.println("예약가능한 방이 없습니다.");
		}
	}
	//---------------------------------------------------------
	//	v0.1
	//	작성자 : 김종현
	//	내용 : 예약 불가능한 방메소드
	//---------------------------------------------------------
	public void impossibleRoom(ArrayList<Room> room) {

		for(Room r : room) {
			if(r.isRoom_Resv())

				Viewer.getInstance().printRoomData(r);
		}         

	}
	//---------------------------------------------------------
	//	v0.1
	//	작성자 : 김종현
	//	내용 : 예약가능 불가능 체크 메소드
	//---------------------------------------------------------
	public void possibleCheck(ArrayList<Room> room) {
		possibleRoom(room);
		impossibleRoom(room);

	}
}
