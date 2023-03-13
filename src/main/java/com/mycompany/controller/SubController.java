package com.mycompany.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.dao.DeptRepository;
import com.mycompany.entity.DeptCopy;

@RestController
public class SubController {
	
	@Autowired
	private DeptRepository dao;
	
	//http://localhost/step02/dept?deptno=10
	@GetMapping("/dept")
	public DeptCopy getDeptNo(Integer deptno) throws Exception{
		System.out.println(deptno);
		/* Optional 기능
		 * 	- 객체들만 보유 가능한 container
		 * 	- null 또는 객체
		 * 	- 포함된 객체 반환시 get() 
		 */
		
		/*DeptCopy 또는 null 만 가능한 Optional */
		Optional<DeptCopy> data = dao.findById(deptno);
		
		System.out.println(data);//Optional[DeptCopy(deptno=10, dname=ACCOUNTING, loc=NEW YORK)]
								  //Optional.empty
		
		//data.orElse("데이터가 없습니다");   //문자열 반영 불가를 입증한 코드 error
		//data.orElse(new DeptCopy()); //문법은 맞으나 논리적으로 없는 객체 생성해서 반환은 의미가 없음 
		
		/* ifPresentOrElse(Optional에 객체 존재시 실행, Optional에 null인경우 실행)
		 * 
		 * :: - 빠른 실행 연산자, 메소드 호출시 상용
		 * System.out.println("..") = (v) -> System.out::println  v변수값 출력
		 * 
		 * System.out.println(참조변수) = System.out.println(참조변수.toString())
		 * 
		 * Optional 내부에 DeptCopy가 존재할 경우 첫번째 parameter실행
		 * 	- 해당 데이터 출력
		 * 없을 경우 두번째 parameter가 실행 
		 * 
		 * ()->System.out.println("데이터가 없습니다")
		 * 	- 흡사한 코드라 가정
		 * void printData(){
		 * 	System.out.println("데이터가 없습니다")
		 * }
		 * 
		 * */
//		data.ifPresentOrElse(System.out::println, 
//							()->System.out.println("데이터가 없습니다"));
		
//		data.ifPresentOrElse( (v) ->System.out.println(v), 
//							  ()->System.out.println("데이터가 없습니다"));
		
//		if(data != null) {
//			System.out.println(data);
//		}else {
//			System.out.println("데이터가 없습니다");
//		}
		
		
		/* Optional<DeptCopy> 에 null이 있을 경우 Exception 생성
		 * 
		 */
		data.orElseThrow(Exception::new);  //new Exception()와 동일한 문법
		
		DeptCopy dept = data.get();  //존재하는 데이터 반환하는 메소드, 없는 데이터 반환시 예외발생
		
		System.out.println(dept);//DeptCopy(deptno=10, dname=ACCOUNTING, loc=NEW YORK)
		return dept;
	}
	
	@ExceptionHandler
	public String handler(Exception e) {
		return "해당 부서의 정보가 없습니다";
	}
}












