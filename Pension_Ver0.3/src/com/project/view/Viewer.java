package com.project.view;

import java.util.ArrayList;	
import java.util.Scanner;

import com.project.common.RoomStorage;
import com.project.dto.Customer;
import com.project.dto.Room;

public class Viewer {
   private Scanner scan = new Scanner(System.in);

   // View도 싱글톤으로 만들어 봤습니다.
   private static Viewer instance;

   private Viewer() {

      // 생성자로서 아무것도 안함.
   }

   public static Viewer getInstance() {
      if (instance == null) {
         instance = new Viewer();
      }

      return instance;
   }

   // 입력과 출력은 여기에서 처리.
   // 모든 컨트롤러가 같은 View 클래스를 사용하도록 작성.(싱글톤으로서!)

   public void skipenter() {
      scan.nextLine();
   }

   public void print_mesg(int c) {

      switch (c) {
      case 1:
         System.out.println("프로그램 종료");

         break;
      case 2:
         System.out.println("이전으로 돌아갑니다.");

         break;
      case 3:
         System.out.println("처리 실패");
         break;
      case 4:
         System.out.println("처리 성공");
         break;
      case 21 : 
    	  System.out.println("\n\t*** 예약 가능한 방 ***");
    	  break;
      case 22 :
    	  System.out.println("\n*** 예약 변경할 방의 이름을 입력해 주세요. ***");
    	  break;
      case 23 :
    	  System.out.println("\n*** 예약 변경이 완료되었습니다.. ***");
    	  break;
      case 31 :   
    	  System.out.println("\n\t*** 원하는 가격대의 방 1 ***");
    	  break;
      case 32 :   
    	  System.out.println("\n\t*** 원하는 가격대의 방 2 ***");
    	  break;
      case 33 :   
			System.out.println("검색하신 이름의 방이 없습니다.");
			break;
		case 34 :   
			System.out.println("\n\t*** NEW 방 정보 등록 ***");
			break;
		case 35 :   
			System.out.println("\n 방 등록 실패");
			break;
		case 36 :   
			System.out.println("\n 방 등록 성공");
			break;
		case 37 :   
			System.out.println("적절한 값을 입력 해 주세요.");
			break;
      case 51:
    	  System.out.println("\n*** 예약 취소 ***");
    	  break;
      case 52:
    	  System.out.println("\n취소 되었습니다.");
    	  break;
      case 53:
    	  System.out.println("\n취소를 실패했습니다.");
    	  break;
      case 54:
    	  System.out.println("로그인에 성공했습니다.");
    	  break;
      case 55:
    	  System.out.println("로그인에 실패했습니다.");
    	  break;
      default:
         break;
      }
         //기본 : 1~20번
         //종현 : 21~30번
         //원준 : 31~40번
         //재웅 : 41~50번
         //성규 : 51~60번
      
   }

   public void printLoginFailCnt(int i) {
	   System.out.println(i+"회 틀렸습니다 ("+i+"/5)회 ");
   }
   
   public int input_Price() {

	   return scan.nextInt();
   }

   public void print_String(String string) {
      System.out.println(string);
   }

   public void printMainMenu() {
	   System.out.println("----종현이네 Ppension----");
	   System.out.println("1. 손님 ");
	   System.out.println("2. 관리자 ");
	   System.out.println("0. 종료");
	   System.out.print("입력 >> : ");
   }
   
   public void printCustomerMenu() {
	   System.out.println("----손놈 모드----");
	   System.out.println("1. 방 보기");
	   System.out.println("2. 예약하기");
	   System.out.println("0. 뒤로가기");
	   System.out.print("입력 >> : ");
   }
   
   public void printRoomMenu() {
	   System.out.println("----방 보기----");
	   System.out.println("1. 방 전체 보기");
	   System.out.println("2. 방 선택 보기");
	   System.out.println("3. 방 추천 받기");
	   System.out.println("0. 뒤로가기");
	   System.out.print("입력 >> : ");
   }
   
   /*--------------------------------------------
	 작업자 : 정성규
	 예약 메뉴 추가
	 ---------------------------------------------*/
   public void print_ReserveMenu() {
	   System.out.println("----예약하기----");
	   System.out.println("1. 예약 하기");
	   System.out.println("2. 예약 변경 / 취소");
	   System.out.println("3. 예약 확인");
	   System.out.println("0. 뒤로가기");
	   System.out.print("입력 >> : ");
   }
   
   /*--------------------------------------------
	 작업자 : 정성규
	 예약 메뉴 추가
	-----------------------------------------------*/
   public void print_ReserveSubMenu() {
	   System.out.println("----예약 변경 / 취소----");
	   System.out.println("1. 변경하기");
	   System.out.println("2. 취소하기");
	   System.out.println("0. 뒤로가기");
	   System.out.print("입력 >> : ");
   }
   
   public void printDeleteCheck() {
	  
		System.out.println("=================================");
		System.out.println("경고 : 예약을 취소하면 위약금이 발생합니다.?");
		System.out.println("정말 취소 하시겠습니까? (Y / N)");
		System.out.print("입력 >> : ");
		//System.out.println("================================="); 
   }
   
   public void printAdminMenu() {
	   System.out.println("\n----관리자 메뉴----");
	   System.out.println("1.예약자 조회");
	   System.out.println("2.방정보 변경");
	   System.out.println("0. 뒤로가기");
	   System.out.print("입력 >> : ");
   }

   //-------------------------------------------------------
   // 작업자 : 정성규
   // 문자 입력 후 반환 (문자열x)
   //-------------------------------------------------------
   public char inputChar() {
	   return scan.next().charAt(0);
   }
   
   public ArrayList<String> printAdminLogin() {
	   ArrayList<String> list = new ArrayList<String>();
	   System.out.println("=================================");
	   System.out.println("*** 관리자용 펜션 관리 ***");
	   System.out.print("ID : ");
	   
	   list.add(input_Name());
	   System.out.print("PW : ");
	   list.add(input_Name());
	   System.out.println("=================================");

	   return list;
   }
   
   public void printRoomData(Room room) {

	   System.out.println("=================================");
	   System.out.println("\t"+room.getRoom_Name()+" 룸");
	   System.out.print("\t"+room.getRoom_Floor()+" 층  ");
	   System.out.println("\t"+room.getRoom_Num()+"호");
	   System.out.println("=================================");
	   System.out.print("1박 비용 : ");
	   System.out.println(" "+room.getRoom_Price()+" 원");
	   System.out.print("침대 개수 : ");
	   System.out.println(" "+room.getRoom_Bed()+" 개");
	   System.out.print("수용 인원 : ");
	   System.out.println(" "+room.getRoom_Capacity()+" 명");
	   System.out.print("예약 여부 :");
	   if(!room.isRoom_Resv()) {
		   System.out.println(" 예약 가능\n");
	   }
	   else {
		   System.out.println(" 예약 완료\n");
	   }
   }

   	public void input_room(Room r) {
	   
	   System.out.print("방 이름 : ");
	   r.setRoom_Name(scan.nextLine());
	   System.out.print("방 호실 : ");
	   r.setRoom_Num(scan.nextInt());
	   System.out.print("방 가격 : ");
	   r.setRoom_Price(scan.nextInt());
	   System.out.print("침대 갯수 : ");
	   r.setRoom_Bed(scan.nextInt());
	   System.out.print("최대 수용 인원 : ");
	   r.setRoom_Capacity(scan.nextInt());
	   System.out.print("층 위치 : ");
	   r.setRoom_Floor(scan.nextInt());
	   System.out.print("예약 여부 : ");
	   r.setRoom_Resv(scan.nextBoolean());
	      
   }
   
   	
   	
   	/*--------------------------------------------
   	 작업자 : 정성규
   	 예약 완료된 데이터 출력
   	 ---------------------------------------------*/
   public void print_ReserveSuccess(Customer c) {
	  
		System.out.println("=================================");
		System.out.println("예약자   : " + c.getCustom_Name());
		System.out.println("예약인원 : " + c.getCustom_TotalPeople());
		System.out.println("전화번호 : " + c.getCustom_Phone());
		System.out.println("---------------------------------");
		System.out.println("*** 예약펜션 ***");
		//printRoomData(c.getReservedRoom());
   }
   
   public Customer printInputName() {
	   Customer c = new Customer();
	   System.out.println("*** 예약자 확인 ***");
	   System.out.print("이    름 : ");
	   c.setCustom_Name(input_Name());
	   System.out.print("전화번호 : ");
	   c.setCustom_Phone(input_Name());
	   return c;
   }
   
   public String input_Name() {
	   return scan.nextLine();
   }
   
	//-----------------------------------------------------------------------------------------
	// 
  	// 작성자 : 장원준
	//메소드 기능 : 손님이 예약가능한 방 목록을 보고, 원하는 방 번호를 선택하는 메소드
	//-----------------------------------------------------------------------------------------
	
   public String selectReserveRoom() {
	
	 System.out.println("\n예약 하실 방 이름을 입력 해 주세요. (ex. 1호실)");
	 System.out.print("입력 >> : ");
	 skipenter();
	 return scan.nextLine();//방 이름을 반환
	
}
   
	//-----------------------------------------------------------------------------------------
	// 
 	// 작성자 : 김종현
	//메소드 기능 :
	//-----------------------------------------------------------------------------------------
   public void print_AdminSearch(Customer c) {
		  
		System.out.println("=================================");
		System.out.println("예약자   : " + c.getCustom_Name());
		System.out.println("예약인원 : " + c.getCustom_TotalPeople());
		System.out.println("전화번호 : " + c.getCustom_Phone());
		System.out.println("방 이름   : " + c.getCustom_RoomName());
		System.out.println("방 번호   : " + c.getCustom_RoomNumber());
		System.out.println("=================================");
		
		//printRoomData(c.getReservedRoom());
 }
   
 //-----------------------------------------------------------------------------------------
 	//작성자 : 장원준
 	//메소드 기능 : 손님이 원하는 방을 선택 후 자신의 개인 정보를 입력하는 메소드
 	//-----------------------------------------------------------------------------------------

 public Customer input_customer(Room r) {
 		Customer c = new Customer();
 		System.out.println("\n*** 예약자 분의 정보를 입력 해 주세요 *** ");
 		System.out.print("예약자 이름 : ");
 		c.setCustom_Name(scan.nextLine());
 		System.out.print("예약 인원 수 : ");
 		c.setCustom_TotalPeople(scan.nextInt());
 		System.out.print("예약자 전화번호 : ");
 		skipenter();
 		c.setCustom_Phone(scan.nextLine());
 		c.setReservedRoom(r);
 		//System.out.println("예약한 방 번호 : ");
 		//c.setCustom_RoomNumber(scan.nextLine()); 
 		return c;
 }
  
//-----------------------------------------------------------------------------------------
	// 
	// 작성자 : 장원준
	//메소드 기능 : 원하는 방의 이름을 입력하는 메소드
	//-----------------------------------------------------------------------------------------

	public String selectRoom() {

		System.out.println("\n*** 삭제할 방 이름을 입력 해 주세요. ***");
		System.out.print("입력 >> : ");
		skipenter();
		return scan.nextLine();//방 이름을 반환

	}
 
//-----------------------------------------------------------------------------------------
	// 
	// 작성자 : 장원준
	//
	//-----------------------------------------------------------------------------------------
	public void printRegistRoomMenu() {
		
		System.out.println("---- 방 정보 메뉴 ----");
		System.out.println("1. 방 정보 삭제");
		System.out.println("2. 새로운 방 생성");
		System.out.println("0. 뒤로가기");
		System.out.print("입력 >> : ");
			
	}
   
}// view 클래스의 끝