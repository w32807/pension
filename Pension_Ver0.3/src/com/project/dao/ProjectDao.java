package com.project.dao;

import java.sql.*;	
import java.util.ArrayList;

import com.project.common.CommonClass;
import com.project.common.RoomStorage;
import com.project.controller.RoomController;
import com.project.dto.Admin;
import com.project.dto.Customer;
import com.project.dto.Room;
import com.project.view.Viewer;



public class ProjectDao {
	//JDBC 관련 처리는 여기서

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.0.6:1521:xe";	
	private String dbuser = "pension";
	private String dbpass = "1234";

	private Connection conn;	
	private PreparedStatement pstmt;
	private ResultSet rs;


	public ProjectDao() {
		try {
			// 드라이버 매니져에게 드라이버를 넘겨 줌
			Class.forName(driver);		 
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			System.out.println("jdbc driver load fail!");
		}			
	}
	public void getConnection() {
		if(conn == null) {
			try {
				conn = DriverManager.getConnection(url, dbuser, dbpass);
			} catch (SQLException e) {
				//e.printStackTrace();
				System.out.println("Oracle connection fail!");
			}
		}
	}

	public void closeDB() {
		try {
			// DB 종료 시 진행 순서 결과종료 -> 쿼리실행기 종료 -> 접속 종료
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			//e.printStackTrace();
		}
	}

	//---------------------------------------------
	// v0.1
	// 작업자 : 정성규
	// 내  용 : 전체 방 정보를 DB에서 가져옴
	//---------------------------------------------
	public void getRoomData() {
		Room room = null;

		String query = "select * from ROOM";
		try {
			pstmt = conn.prepareStatement(query);
			//pstmt.setString(1,id);
			rs = pstmt.executeQuery();  // select는 executeQuery() 사용

			while(rs.next()) {	// DB에 데이터가 있으면 T / 없으면 F 
				room = new Room();
				room.setRoom_Num(rs.getInt("roomNumber"));
				room.setRoom_Price(rs.getInt("roomPrice"));
				room.setRoom_Name(rs.getString("roomName"));
				room.setRoom_Bed(rs.getInt("roomBed"));
				room.setRoom_Capacity(rs.getInt("roomCapacity"));
				room.setRoom_Floor(rs.getInt("roomFloor"));
				room.setRoom_Resv(Boolean.parseBoolean((rs.getString("roomRecv"))));

				RoomStorage.getInstance().getRoomList().add(room);
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("process fail");
		}
		//return room;
	}

	//---------------------------------------------
	// v0.1
	// 작업자 : 정성규
	// 내  용 : 손님 예약 정보를 DB 에서 가져옴
	//---------------------------------------------
	public Customer getCustomerData(Customer cus) {
		Customer cus1  = null;
		String query = "select * from customTbl where custom_name = ? and custom_Phone = ?";
		//System.out.println("aaaaaa");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,cus.getCustom_Name());
			pstmt.setString(2,cus.getCustom_Phone());
			rs = pstmt.executeQuery();  // select는 executeQuery() 사용

			if(rs.next()) {	// DB에 데이터가 있으면 T / 없으면 F 
				cus1 = new Customer();
				cus1.setCustom_Name(rs.getString("CUSTOM_NAME"));
				cus1.setCustom_TotalPeople(rs.getInt("CUSTOM_TOTALPEOPLE"));
				cus1.setCustom_Phone(rs.getString("CUSTOM_PHONE"));
				cus1.setCustom_RoomName(rs.getString("costum_RoomName"));
				cus1.setCustom_RoomNumber(rs.getInt("contum_RoomNumber"));
				//list.add(cus);
				//return cus;
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("process fail");
		}

		return cus1;
	}

	//---------------------------------------------
	// v0.1
	// 작업자 : 정성규
	// 내  용 : 예약자의 방 정보를 DB에서 가져옴
	//---------------------------------------------
	public Room getReseverRoom(int roomNum) {

		Room room  = null;
		String query = "select * from ROOM where roomNumber = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,roomNum);
			rs = pstmt.executeQuery();  // select는 executeQuery() 사용

			if(rs.next()) {	// DB에 데이터가 있으면 T / 없으면 F 
				room = new Room();

				room.setRoom_Num(rs.getInt("roomNumber"));
				room.setRoom_Price(rs.getInt("roomPrice"));
				room.setRoom_Name(rs.getString("roomName"));
				room.setRoom_Bed(rs.getInt("roomBed"));
				room.setRoom_Capacity(rs.getInt("roomCapacity"));
				room.setRoom_Floor(rs.getInt("roomFloor"));
				room.setRoom_Resv(Boolean.parseBoolean((rs.getString("roomRecv"))));

				//return cus;
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("process fail");
		}

		return room;
	}

	//-----------------------------------------------------------------------------------------
	//작성자 : 장원준
	//메소드 기능 : 손님 인스턴스를 매개변수로 받아, Oracle DB의 CustomTBL에 insert하기 위한 메소드 
	//-----------------------------------------------------------------------------------------
	public Customer insertCustomer(Customer c) {

		int result = 0 ;
		String query = "insert into customTbl values(?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, c.getCustom_Name());//손님 이름 입력 받아 DB에 전송
			pstmt.setString(2, c.getCustom_Phone());//손님 전화번호 입력 받아 DB에 전송
			pstmt.setInt(3, c.getCustom_TotalPeople());//손님 총 인원수 입력 받아 DB에 전송
			pstmt.setString(4, c.getReservedRoom().getRoom_Name());
			pstmt.setInt(5, c.getReservedRoom().getRoom_Num());
			result = pstmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("개인 정보 등록 실패...\n");
			return null;
		}	 
		if(result != 0) {
			System.out.println("개인 정보 등록 성공^^\n");

		}
		return c;
	}
	//-----------------------------------------------------------------------------------------
	//작성자 : 장원준
	//메소드 기능 :  방 이름(문자열)로 입력 받아서, 방에 대한 인스턴스를 반환 
	//-----------------------------------------------------------------------------------------
	public  Room reservedRoom(String room_name) {

		Room r = null;

		String query = "select * from ROOM where ROOMNAME = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, room_name);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				r = new Room();
				r.setRoom_Num((rs.getInt("ROOMNUMBER")));
				r.setRoom_Price(Integer.parseInt(rs.getString("ROOMPRICE")));
				r.setRoom_Name(rs.getString("ROOMNAME"));
				r.setRoom_Bed(Integer.parseInt(rs.getString("ROOMBED")));
				r.setRoom_Capacity(Integer.parseInt(rs.getString("ROOMCAPACITY")));
				r.setRoom_Floor(Integer.parseInt(rs.getString("ROOMFLOOR")));
				r.setRoom_Resv(Boolean.parseBoolean(rs.getString("ROOMRECV")));
			}

		} catch (SQLException e) {
			Viewer.getInstance().print_mesg(3);	
		}

		return r;
	}

	//-----------------------------------------------------------------------------------------
	//작성자 : 장원준
	//메소드 기능 :  사용자가 방을 예약 혹은 예약 취소를 했을 때, 예약 상태를 바꾸는 기능
	//-----------------------------------------------------------------------------------------
	public void ChangeReserve(Room r) {
	    
	    int result = 0 ;
	    String query = "update ROOM set ROOMRECV = ? where ROOMNAME = ?";
	   //실제 예약할 때 true로 안바꿈.
	    try {
	       pstmt = conn.prepareStatement(query);
	       
	       if(r.isRoom_Resv() == true) {
	       pstmt.setString(1, "false");//변경할 값 입력 true or false
	       pstmt.setString(2, r.getRoom_Name());//찾는 방의 이름
	       r.setRoom_Resv(false);
	       }
	       else if(r.isRoom_Resv() == false){
	       pstmt.setString(1, "true");//변경할 값 입력 true or false
	       pstmt.setString(2, r.getRoom_Name());//찾는 방의 이름
	       r.setRoom_Resv(true);
	       }
	      
	       result = pstmt.executeUpdate();
	   }
	    catch (SQLException e) {
	          System.out.println("예약 여부 변경 실패");
	    }    
//	    if(result != 0) {
//	         
//	         System.out.println("예약 여부 변경 성공!");
//	    }
	}

	//---------------------------------------------
	// v0.2
	// 작업자 : 정성규
	// 내  용 : 예약 취소 및 방 예약 여부를 false로 변경
	//---------------------------------------------
	public boolean deleteReserveData(Customer cus, Room room) {
		//Customer cus1  = null;
		int rs = 0;
		
		String roomQuery = "update ROOM set ROOMRECV = ? where roomNumber = ?";
		String query = "delete from customTbl where custom_name = ? and custom_Phone = ?";
		
		try {
			
			pstmt = conn.prepareStatement(roomQuery);
			pstmt.setString(1, "false");
			pstmt.setInt(2, cus.getCustom_RoomNumber());
			rs = pstmt.executeUpdate();
			room.setRoom_Resv(false);
			
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,cus.getCustom_Name());
			pstmt.setString(2,cus.getCustom_Phone());
			rs = pstmt.executeUpdate();  // select는 executeQuery() 사용

			//if(rs.next()) {	// DB에 데이터가 있으면 T / 없으면 F 
				
				return true;
			//}
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("process fail");
		}

		return false;
	}
	
	//---------------------------------------------
	// v0.2
	// 작업자 : 정성규
	// 내  용 : 예약 취소 및 방 예약 여부를 false로 변경
	//---------------------------------------------
	public Admin adminLogin(String id, String pw) {
		Admin ad = null;
		String query = "select * from AdminAcc where admin_name = ? and admin_pw = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,id);
			pstmt.setString(2,pw);
			rs = pstmt.executeQuery();  // select는 executeQuery() 사용

			if(rs.next()) {	// DB에 데이터가 있으면 T / 없으면 F 
				ad = new Admin();
				ad.setAdmin_id(rs.getString("admin_name"));
				ad.setAdmin_pw(rs.getString("admin_pw"));
				ad.setAdmin_duty(rs.getString("admin_duty"));
				return ad;
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("process fail");
		}

		return null;
	}

	//-----------------------------------------------------------------------------------------
		//Ver0.2
		//작성자 : 김종현
		//메소드 기능 :  손님테이블에서 손님이 예약한 방의 이름을 업데이트하는 기능
		//-----------------------------------------------------------------------------------------

		public void customerRoomUpdate(Customer cus) {

			String query = "update customTbl set COSTUM_ROOMNAME = ? where CUSTOM_NAME = ?";
			int result = 0 ;

			try {
				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, cus.getCustom_RoomName());//변경할 값 입력 true or false
				pstmt.setString(2, cus.getCustom_Name());//변경할 값 입력 true or false



				result = pstmt.executeUpdate();



			} catch (SQLException e) {

			}


		}

		//-----------------------------------------------------------------------------------------
		//Ver0.2
		//작성자 : 장원준
		//메소드 기능 :  방 이름을 입력 받은 후 방에 대한 레코드를 삭제
		//-----------------------------------------------------------------------------------------

		public boolean roomDelete(String roomName) {
		      
		      ArrayList<Room> List = RoomStorage.getInstance().getRoomList();
		      int result = 0 ;
		      boolean b = true;
		      String query = "delete from ROOM where ROOMNAME = ?";
	      
		         try {
		            searchRoomName(roomName);
		            pstmt = conn.prepareStatement(query);
		            pstmt.setString(1, roomName);
		            result = pstmt.executeUpdate();
		            Viewer.getInstance().print_mesg(4);
		            for(int i = 0 ; i < List.size() ; i++) {
		               
		               if(roomName.equals(List.get(i).getRoom_Name())){
		                  List.remove(i);
		               }
		            }
		         } 
		         catch (SQLException e) {
		            Viewer.getInstance().print_mesg(3);
		            b = false;
		         }
		         catch (Exception e) {
		            Viewer.getInstance().print_mesg(33);
		            b = false;
		         }
		         return b;
		   }
		//-----------------------------------------------------------------------------------------
		//Ver0.2
		//작성자 : 장원준
		//메소드 기능 :  방 인스턴스를 매개변수로 받아, DB에 저장하는 기능
		//-----------------------------------------------------------------------------------------
		public void roomRegist(Room room) {


			int result = 0 ;
			String query = "insert into ROOM values(?,?,?,?,?,?,?)";

			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, room.getRoom_Num());
				pstmt.setInt(2,  room.getRoom_Price());
				pstmt.setString(3,  room.getRoom_Name());
				pstmt.setInt(4,  room.getRoom_Bed());
				pstmt.setInt(5,  room.getRoom_Capacity());
				pstmt.setInt(6,  room.getRoom_Floor());
				pstmt.setString(7, ""+room.isRoom_Resv());
				result = pstmt.executeUpdate();
			}
			catch (SQLException e) {
				Viewer.getInstance().print_mesg(35);

			}	 
			if(result != 0) {
				Viewer.getInstance().print_mesg(36);
			}

		}
		
		//-----------------------------------------------------------------------------------------
		//Ver0.2
		//작성자 : 장원준
		//메소드 기능 :  방의 컬럼을 가져오고, 입력 받은 매개변수와 비교하는 메소드.
		//-----------------------------------------------------------------------------------------
		
		public void searchRoomName(String roomName) throws Exception{
			
			Room r = null;
			int cnt = 0;
			Exception e = new Exception();
			String query = "select ROOMNAME from ROOM ";
			
			try {
				pstmt = conn.prepareStatement(query);
				rs = pstmt.executeQuery();

				while(rs.next()) {
					if(roomName.equals(rs.getString("ROOMNAME"))) {
						cnt++;
					}
				}
					if(cnt == 0)
						throw e;
				

			} catch (SQLException ee) {
				
			}

			
			
		}
	
		// 김종현
				public ArrayList<Customer> adminGetCustomerData() {
					
					ArrayList<Customer> list = new ArrayList<Customer>();
					
					Customer cus1  = null;
					String query = "select * from customTbl";
					//System.out.println("aaaaaa");
					try {
						pstmt = conn.prepareStatement(query);
//						pstmt.setString(1,cus.getCustom_Name());
//						pstmt.setString(2,cus.getCustom_Phone());
						rs = pstmt.executeQuery();  // select는 executeQuery() 사용

						while(rs.next()) {	// DB에 데이터가 있으면 T / 없으면 F 
							cus1 = new Customer();
							cus1.setCustom_Name(rs.getString("CUSTOM_NAME"));
							cus1.setCustom_Phone(rs.getString("CUSTOM_PHONE"));
							cus1.setCustom_TotalPeople(rs.getInt("CUSTOM_TOTALPEOPLE"));
							cus1.setCustom_RoomName(rs.getString("costum_RoomName"));
							cus1.setCustom_RoomNumber(rs.getInt("contum_RoomNumber"));
							
							list.add(cus1);
							
						}
					} catch (SQLException e) {
						//e.printStackTrace();
						System.out.println("process fail");
					}
					
					return list;
				}
	
	// 컬럼 개수 구하는 메소드
	public int getColumnCnt() {

		ResultSetMetaData rsmd = null;
		int rowCnt = 0;

		try {
			rsmd=rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			rowCnt = rsmd.getColumnCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowCnt;
	}

	//	public int getRowCnt() {
	//		String query = "SELECT roomNumber FROM ROOM";
	//		try {
	//			pstmt = conn.prepareStatement(query);
	//		} catch (SQLException e1) {
	//			// TODO Auto-generated catch block
	//			e1.printStackTrace();
	//		}
	//		
	//		int rowCount = 0;
	//		
	//		try {
	//			rs = pstmt.executeQuery();
	//			rs.last();
	//			rowCount = rs.getRow();
	//		} catch (SQLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//
	//		return rowCount;
	//		
	//	}
}
