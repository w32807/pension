package com.project.service;

import com.project.ProjectMain;
import com.project.dto.Customer;
import com.project.dto.Room;

public class CustomerService {
		//-----------------------------------------------------------------------------------------
		//Ver0.2	
		//작성자 : 장원준
		//메소드 기능 : DB에서 예약여부를 변경하는 메소드를 전달하는 기능
		//-----------------------------------------------------------------------------------------

		public void changeReserve (Room r) {
			ProjectMain.db.ChangeReserve(r);
		}
		//-----------------------------------------------------------------------------------------
		//Ver0.2
		//작성자 : 장원준
		//메소드 기능 : 예약 변경 후, 손님 테이블의 예약한 방 이름을 업데이트 하는 기능

		//-----------------------------------------------------------------------------------------

		public void customerRoomUpdate(Customer cus) {
			ProjectMain.db.customerRoomUpdate(cus);
		}
		
		
}
