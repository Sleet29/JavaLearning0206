package exercise2.onmyown;

import java.util.ArrayList;
import java.util.Scanner;

public class CRUD {
	// CRUD:컴퓨터 소프트웨어가 가지는 기본적인 데이터 처리 기능
		// C(Create-insert), R(Read-select)
		// U(Update-update), D(Delete-delete)
		static String menus[] = { "사원번호", "사원이름", "직급", "매니저", "입사일자", "급여", "커미션", "부서번호", "종료" };

		/*
		public static void main(String[] args) {
			selectAll();
		}
		*/
		
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			int menu = menuselect(sc);
			System.out.println(menu);
			sc.close();
		}

		public static int menuselect(Scanner sc) {
			int menu;
			System.out.println("1.사원번호 \n2.사원이름\n"
					+ "3.직급\n4.매니저\n"
					+ "5.입사일자\n6.급여\n"
					+ "7.커미션\n8.부서번호\n"
					+ "9.종료");
			System.out.print("조회할 컬럼을 선택하세요>");
			menu = inputNumber(sc);
			return menu;
		}
		
		
		public static int inputNumber(Scanner sc) {
			while(true) {
				try {
					int Num = Integer.parseInt(sc.nextLine());
					if(Num>=1 && Num<=9) {
						return Num;
					} else {
						System.out.print("1~9 사이의 숫자를 입력하세요>");
					}
				} catch (NumberFormatException e) {
					System.out.print("숫자로 입력하세요>");
				}
			}
		}

				
			
		
		private static void selectAll() {
			DAO dao = new DAO();
			ArrayList<Emp> 	list = dao.selectAll();

			if (list.isEmpty()) {
				System.out.println("검색 결과가 없습니다.");
			} else {
				System.out.printf("%s\t%s\t%s\t\t%s\t%s\t\t%s\t%s\t%s\t\n", menus[0], menus[1], menus[2], menus[3],
						menus[4], menus[5], menus[6], menus[7]);
				for(int i=0;i<=80;i++)
				 System.out.print("=");
				
				System.out.println();
				
				for (Emp s : list) {
					System.out.println(s.toString());
			}
		}
	}
}

