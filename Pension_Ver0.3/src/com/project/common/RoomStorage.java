package com.project.common;

import java.util.ArrayList;

import com.project.dto.Room;

public class RoomStorage {
	private ArrayList<Room> roomList;

	private static RoomStorage instance;
	
	public static RoomStorage getInstance() {
		if(instance == null) {
			instance = new RoomStorage();
		}
		return instance;
	}
	
	private RoomStorage() {//생성자에서 필요한 상태값 초기화
		roomList = new ArrayList<>();
		
		//db.addStdData(stdList);
		//db.addProData(proList);
		//db.addSubData(subList);
	}
	
	public ArrayList<Room> getRoomList() {
		return roomList;
	}
	public void setRoomList(ArrayList<Room> roomList) {
		this.roomList = roomList;
	}
	
	
	
	
}
