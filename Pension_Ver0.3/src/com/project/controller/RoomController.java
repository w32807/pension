package com.project.controller;

import com.project.ProjectMain;
import com.project.common.RoomStorage;
import com.project.dao.ProjectDao;
import com.project.service.RoomService;
import com.project.view.Viewer;

public class RoomController {
	//OOOOOO 관리가 있을 경우 이 컨트롤로로 시작
	
	RoomService rs = new RoomService();
	SearchController sc = new SearchController();
	
	public void roomMain() {
		//
		while(true) {
			
			Viewer.getInstance().printRoomMenu();
			int sel =  Viewer.getInstance(). input_Price();
			
			if(sel == 0) {
				Viewer.getInstance().print_mesg(2);
				break;
			}
			
			switch (sel) {
			case 1://전체 조회
				 sc.output_All();
				break;
			case 2://예약가능한 방 보기
				sc.possibleCheck(RoomStorage.getInstance().getRoomList());
				break;
			case 3://예약취소
				 sc.wanna_Room(RoomStorage.getInstance().getRoomList());
				break;
			
			//case 99:
				//rs.DBData();
				//Viewer.getInstance().printRoomData();
				//break;
				
			default:
				break;
			}
		}
	}
}
