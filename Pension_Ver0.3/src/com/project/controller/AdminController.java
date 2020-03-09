package com.project.controller;

import java.util.ArrayList;

import com.project.dto.Customer;
import com.project.dto.Room;
import com.project.service.AdminService;
import com.project.view.Viewer;

public class AdminController {

	AdminService as = new AdminService();
	CustomerController cc = new CustomerController();
	Customer ct = new Customer();

	 public void AdminMain() {
	 boolean b = adminLogin();
	 
	      while(b) {
	         
	         Viewer.getInstance().printAdminMenu();
	         
	         int sel = Viewer.getInstance().input_Price();
	         
	         if(sel == 0) {
	            Viewer.getInstance().print_mesg(2);
	            break;
	         }
	         
	         switch (sel) {
	         case 1: //예약자 조회
	        	 ArrayList<Customer> list2 =new ArrayList<Customer>();
					list2 = as.totalCustomer();
					adminSearchCustomer(list2);
	            break;
	         case 2: //방정보 변경
	        	 adminRoomChange();
	            break;
	         default:
	            break;
	         }
	      }
	   }

	 public void adminRoomChange() {

			//룸에 대한 레코드를 한줄 삭제 후, 
			//새로 등록
		 while(true) {	
			 Viewer.getInstance().printRegistRoomMenu();
			 int sel = Viewer.getInstance().input_Price();

			 if (sel == 0) {
				 Viewer.getInstance().print_mesg(2);
				 break;
			 }
			 switch (sel) {
			 case 1:
				 String roomName = Viewer.getInstance().selectRoom();//삭제 할 룸에 대한 이름 입력
				 as.roomDelete(roomName);

				 break;
			 case 2:
				 String roomName_delete = Viewer.getInstance().input_Name();
				 Room r = new Room();

				 as.roomRegist(r);
				 break;
			 default:
				 break;

			 }
		 }
	 }
	
	 public boolean adminLogin() {
		 
		 int failCnt = 1;
		 Viewer.getInstance().skipenter();
		 
		 while(failCnt < 6) {
			 ArrayList<String> list = new ArrayList<String>();
			 list = Viewer.getInstance().printAdminLogin();

			 String id = list.get(0);
			 String pw = list.get(1);
			 boolean b = false;

			 b = as.adminLoginService(id, pw);

			 if(b) {
				 Viewer.getInstance().print_mesg(54);
				 return true;
			 }
			 else {
				 Viewer.getInstance().print_mesg(55);
				 Viewer.getInstance().printLoginFailCnt(failCnt);
				 failCnt++;
				 list.clear();
				 
				 //continue;
				 //return false;
			 }
		 }
		 return false;
	 }
	 
	 public void adminSearchCustomer(ArrayList<Customer> list) {
			for(Customer c : list) {
				Viewer.getInstance().print_AdminSearch(c);
			}
		}
}

