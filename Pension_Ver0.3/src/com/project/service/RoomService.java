package com.project.service;

import java.util.ArrayList;

import com.project.ProjectMain;
import com.project.common.RoomStorage;
import com.project.controller.RoomController;
import com.project.dto.Room;

//import com.JdbcMain;

public class RoomService {
	
	public void loadDBData() {
		
		ProjectMain.db.getRoomData();
		//int i = RoomController.db.getColumnCnt();
		//System.out.println("컬럼갯수 : " + i);
		//i = RoomController.db.getRowCnt();
		//System.out.println("행갯수 : " + i);
	}
	
	public ArrayList<Room> DBData() {
		
		return RoomStorage.getInstance().getRoomList();
	}
}
