package com.project.service;

import java.util.ArrayList;	
import java.util.HashMap;

import com.project.ProjectMain;
import com.project.common.RoomStorage;
import com.project.dao.ProjectDao;
import com.project.dto.Customer;
import com.project.dto.Room;
import com.project.view.Viewer;

public class ReservationService {

	//ProjectDao pd = new ProjectDao();

	//	 public Customer loadCustomerData() {
	//		 Customer cus = ProjectMain.db.getCustomerData();
	//		 
	//		 return cus;
	//		 
	//	   }

	public ArrayList<Object> searchCustomerData(String name, String phone) {

		//ArrayList<Customer> cusList = new ArrayList<>();
		Customer tempCus = new Customer();
		tempCus.setCustom_Name(name);
		tempCus.setCustom_Phone(phone);

		ArrayList<Object> list = new ArrayList<>();
		Room room = null;

		tempCus = ProjectMain.db.getCustomerData(tempCus);
		room = ProjectMain.db.getReseverRoom(tempCus.getCustom_RoomNumber());

		list.add(tempCus);
		list.add(room);

		return list;
	}

	//-----------------------------------------------------------------------------------------
	//작성자 : 장원준
	//메소드 기능 : DB에 손님 인스턴스의 정보를 입력하는 메소드  
	//-----------------------------------------------------------------------------------------

	public void insertCustom_Db(Customer c) {

		ProjectMain.db.insertCustomer(c);

	}

	//-----------------------------------------------------------------------------------------
	//작성자 : 장원준
	//메소드 기능 : 손님이 예약 하고자 하는 방 이름을 입력하면, 방 이름으로 DB에서 찾아서 손님 인스턴스에 입력
	//-----------------------------------------------------------------------------------------

	public Room inputReservedRoom() {

		String roomName = Viewer.getInstance().selectReserveRoom();//방 이름 반환 받아옴 
		
		Room r = ProjectMain.db.reservedRoom(roomName);//방 이름으로 DB에서 그 방에 대한 정보를 찾아옴
	//	RoomStorage.getInstance().getRoomList().remove(r);
		ProjectMain.db.ChangeReserve(r);//방의 예약 여부 변환
	//	RoomStorage.getInstance().getRoomList().add(r);
		return r;
	}

	public boolean deleteReserveData(Customer c, Room r) {
		boolean b = ProjectMain.db.deleteReserveData( c , r );
		return b;
	}
	//-----------------------------------------------------------------------------------------
	//ver0.3
	//작성자 : 장원준
	//메소드 기능 : 방 이름을 입력받아서 ArrayList에서 찾아, 이름이 똑같은 방을 반환.
	//-----------------------------------------------------------------------------------------
	public Room searchFromArrayList(String searchRoomName) {
		
		for(Room r : RoomStorage.getInstance().getRoomList()) {
			
			if(r.getRoom_Name().equals(searchRoomName)) {
				
				return r;
			}
		} 
		return null;
	}
}
