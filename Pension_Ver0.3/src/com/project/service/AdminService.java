package com.project.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.project.ProjectMain;
import com.project.dto.Admin;
import com.project.dto.Customer;
import com.project.dto.Room;
import com.project.view.Viewer;


public class AdminService {

	public boolean adminLoginService(String id, String pw) {
		Admin admin = null;
		boolean b = false;
		admin = ProjectMain.db.adminLogin(id,pw);
		b = loginProcess(admin, id, pw);
		return b;
	}


	public boolean loginProcess(Admin admin, String id, String pw) {
		try {
			if(id.equals(admin.getAdmin_id())) {
				if(pw.equals(admin.getAdmin_pw())) {
					return true;
				}
			}
		} catch(Exception e){
			//System.out.println("process fail");
			return false;
		}

		return false;
	}

	//-----------------------------------------------------------------------------------------
	//Ver0.2
	//작성자 : 장원준
	//메소드 기능 :  방 이름을 매개변수로 받아, DB로 저장하는 메소드
	//-----------------------------------------------------------------------------------------

	public void roomDelete(String roomName) {

		ProjectMain.db.roomDelete(roomName);

	}
	//-----------------------------------------------------------------------------------------
	//Ver0.2
	//작성자 : 장원준
	//메소드 기능 :  방 인스턴스를 매개변수로 받아, DB로 전달하는 메소드
	//-----------------------------------------------------------------------------------------

	public void roomRegist(Room room) {
		Viewer.getInstance().print_mesg(34);
		try {
			Viewer.getInstance().input_room(room);
			ProjectMain.db.roomRegist(room);
		} catch (Exception e) {
			Viewer.getInstance().print_mesg(37);
		}


	}

	// 김종현
	public ArrayList<Customer> totalCustomer() {
		//예약자 조회, 손님 테이블 출력
		ArrayList<Customer> list1 = new ArrayList<Customer>();

		list1 = ProjectMain.db.adminGetCustomerData();

		return list1;
	}

}
