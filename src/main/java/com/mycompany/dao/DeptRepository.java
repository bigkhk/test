/* SpringDataJap를 활용한 Dept table crud 
 * rule
 * 	- interface
 *  - <entity타입, pk타입>로 제한한 CrudRepository 상속  
 *  
 * 개발자는 rule에 맞게 개발
 * 	- interface는 절대 객체가 될수는 없으나 실행시 하위 구현체를 spring boot가 만들어서
 *     실제 실행시엔 하위 객체 생성 및 실행되는 원리 
 */

package com.mycompany.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.entity.DeptCopy;

@Repository   //Spring Bean 의미
public interface DeptRepository extends CrudRepository<DeptCopy, Integer> {

}
 