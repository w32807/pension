package com.project.controller;


import java.util.ArrayList;	
import java.util.HashMap;

import com.project.ProjectMain;
import com.project.common.RoomStorage;
import com.project.dto.Customer;
import com.project.dto.Room;
import com.project.service.CustomerService;
import com.project.service.ReservationService;
import com.project.view.Viewer;

public class CustomerController {
	// 사용자 관리가 있을 경우 이 컨트롤로로 시작
	ReservationService rs = new ReservationService();
	SearchController sc = new SearchController();
	CustomerService cs = new CustomerService();

	//---------------------------------------------
	// v0.1
	// 작업자 : 정성규
	// 내  용 : 손님 모드 분기
	//---------------------------------------------
	public void customerMain() {

		while(true) {
			Viewer.getInstance().print_ReserveMenu();

			int sel =  Viewer.getInstance().input_Price();

			if(sel == 0) {
				Viewer.getInstance().print_mesg(2);
				break;
			}

			switch (sel) {
			case 1:	//예약하기
				resevation();
				break;

			case 2:	//예약 변경 / 취소
				reserveSubMenu();
				break;
			case 3:	// 예약 확인
				//Customer cus = null;
				// cus = rc.loadCustomerData();
				// Viewer.getInstance().print_ReserveSuccess(cus);

				searchReserverData();
				break;

			default:
				break;
			}
		}
	}

	//---------------------------------------------
	// v0.1
	// 작업자 : 정성규
	// 내  용 : 손님 모드 세부 항목 분기
	//---------------------------------------------
	public void reserveSubMenu() {
		while(true) {
			Viewer.getInstance().print_ReserveSubMenu();
			int sel =  Viewer.getInstance().input_Price();

			if(sel == 0) {
				Viewer.getInstance().print_mesg(2);
				break;
			}

			switch(sel) {
			case 1:	//변경하기
				reserveUpdate();
				break;
			case 2:	// 취소하기
				deleteResrvtion();
				break;

			default:
				break;
			}
		}
	}

	//-----------------------------------------------------------------------------------------
	//Ver0.2
	//작성자 : 김종현
	//메소드 기능 : 예약 변경하는 메소드
	//-----------------------------------------------------------------------------------------

	private void reserveUpdate() {
		ArrayList<Object> list = new ArrayList<>();
		Customer cus = null;

		list = searchReserverData();


		for(Object o : list) {
			if(o instanceof Customer) {
				cus = (Customer)o;
				//Viewer.getInstance().print_ReserveSuccess((Customer)o);
			}
		}//예약한 손님의 전화번호, 이름만 cus에 들어있음

		Room room = new Room();
		sc.possibleRoom(RoomStorage.getInstance().getRoomList());
		Viewer.getInstance().print_mesg(22);
		String searchName = Viewer.getInstance().input_Name();
		for(Room r : RoomStorage.getInstance().getRoomList()) {
			if(r.getRoom_Name().equals(searchName)) {
				RoomStorage.getInstance().getRoomList().remove(r);
			}
			
		}
		room.setRoom_Name(searchName);
		cs.changeReserve(room);
		cus = ProjectMain.db.getCustomerData(cus);
		room.setRoom_Name(cus.getCustom_RoomName());
		cs.changeReserve(room);
		cus.setCustom_RoomName(searchName);
		cs.customerRoomUpdate(cus);
		Viewer.getInstance().print_mesg(23);
	}

	//---------------------------------------------
	// v0.1
	// 작업자 : 정성규
	// 내  용 : 손님과 손님의 예약한 방정보를 가져옴
	//---------------------------------------------
	public ArrayList<Object> searchReserverData() {
		ArrayList<Object> list = new ArrayList<>();
		Customer cus = null;
		//Viewer.getInstance().skipenter();
		cus = inputCustomerData();//예약확인을 위한 손님의 이름과, 전화번호만 입력된 인스턴스


		list = rs.searchCustomerData(cus.getCustom_Name(), cus.getCustom_Phone());
		//손님과, 방 정보가 들어있는 object 리스트

		for(Object o : list) {
			if(o instanceof Customer) {
				Viewer.getInstance().print_ReserveSuccess((Customer)o);
			}
			else {
				Viewer.getInstance().printRoomData((Room)o);
			}
		}
		//Customer cus = ;

		return list;
	}


	//---------------------------------------------
	// v0.1
	// 작업자 : 장원준
	// 내  용 : 
	//---------------------------------------------
	public Customer inputCustomerData() {
		Customer cus = null;
		Viewer.getInstance().skipenter();
		cus = Viewer.getInstance().printInputName();
		return cus;
	}

	public void resevation() {

		//1. 종현

		sc.possibleRoom(RoomStorage.getInstance().getRoomList());
		//2. 원준

		Room r = rs.inputReservedRoom();
		Customer c = Viewer.getInstance().input_customer(r);
		
		rs.insertCustom_Db(c);//DB에 저장까지.
	}

	//---------------------------------------------
	// v0.2
	// 작업자 : 정성규
	// 내  용 : 예약 취소를 위한 손님 정보, 방정보를 가져옴
	//---------------------------------------------
	public void deleteResrvtion() {
		ArrayList<Object> list = new ArrayList<Object>();
		Customer cus = null;
		Room room = null;

		Viewer.getInstance().print_mesg(51);
		list = searchReserverData();	// 이름, 연락처로 예약 정보 검색
		Viewer.getInstance().printDeleteCheck();

		char ch = Viewer.getInstance().inputChar();
		boolean b = stringToBoolean(ch);

		for(Object o : list) {
			if(o instanceof Customer) {
				cus = (Customer)o;
			}
			else {
				room = (Room)o;
			}
		}

		if(b) {
			if(rs.deleteReserveData(cus, room)) {

				Viewer.getInstance().print_mesg(52);
			}
			else {
				Viewer.getInstance().print_mesg(53);
			}
		}
		else {
			Viewer.getInstance().print_mesg(53);
			return;
		}
	}

	//-------------------------------------------------------
	// v0.2
	// 작업자 : 정성규
	// 문자 Y, X 를 입력받아 분기 후 boolean 타입으로 리턴
	//-------------------------------------------------------
	private static boolean stringToBoolean(char s) {

		if(s == 'Y' || s == 'y') {
			return true;
		}
		else if(s == 'N' || s == 'n') {
			return false;
		}
		else {
			//MainView.printMsg(2);
		}
		return false;
	}

}