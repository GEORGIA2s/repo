package hotel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class HotelManagement extends Utils {
	Map<Integer, String> map;
//	Map<Integer, HotelVo> map;

	public static void main(String[] args) {
		new HotelManagement().manage();
	}
	
	public HotelManagement() {
		map = Utils.map;
//		map = new HashMap<>();
	}

	private void manage() {
		printOpen();
		while (true) {
			printMenu();
			int select = Integer.parseInt(sc.nextLine());
			switch (select) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				roomStatus();
				break;
			case 4:
				all();
				break;
			case 5:
				printExit();
				return;
			default:
				System.out.println("잘못된 메뉴 선택 입니다.");
			}
		}
	}

	public void checkIn() {
		int key = inputKey();
		// ↑ ↓ Scanner 의 결과값 return
		String value = inputValue();

		boolean b = containsKey(key);
		// 해당 키 값이 맵에 있는지 여부를 True/False로 반환
		// 있으면 TRUE 없으면 FALSE

		if (b)
			System.out.println(key + "방에는 이미 사람이 있습니다.");
		else {
			map.put(key, value);
//			map.put(key,new HotelVo(key,value)); -- Vo 사용시
			System.out.println("체크인 되었습니다.");
		}
	}

	public void checkOut() {
		int key = inputKey();
		boolean b = containsKey(key);

		if (b) {
			map.remove(key);
			System.out.println("체크아웃 되었습니다.");
		} else
			System.out.println(key + "방에는 체크인한 사람이 없습니다.");

	}

	public void roomStatus() {

		int key = inputKey();
		
		boolean b = containsKey(key);

		if (b)
			System.out.printf("방번호 : %s, 투숙객 : %s\n", key, map.get(key));
		//	System.out.printf("방번호 : %s, 투숙객 : %s\n", key, map.get(key).getName()); -- Vo 사용시
		else
			System.out.println("방에 사람이 없음");

	}

	public void all() {

		if (!map.isEmpty()) {
			// case 1
			
			// 정렬하지 않고 출력 (ForEach 활용)
			
			Map<Integer,String> map1 = new HashMap<>(map);
			// 맵 깊은 복사
			System.out.println("ForEach 활용");
			for (Entry<Integer, String> m : map1.entrySet()) {
				System.out.printf("방번호 : %d, 투숙객 : %s\n", m.getKey(), m.getValue());
			}
			System.out.println("====================================\n");
			
			// 정렬하지 않고 출력 (Iterator 활용)
			
			Map<Integer,String> map2 = new HashMap<>(map);
			Iterator<Entry<Integer, String>> it = map2.entrySet().iterator();
			System.out.println("Iterator 활용");
			while(it.hasNext()){
				Entry<Integer, String> e = it.next();
				System.out.printf("방번호 : %d, 투숙객 : %s\n", e.getKey(), e.getValue());
			}
			System.out.println("====================================\n");

			// case 2
			// 스트림과 람다 표현식을 이용해 역순 정렬
		
			// map.entrySet.stream()
			// => 맵의 엔트리셋을 스트림으로 변환
			
			// .sorted(Collections.reverseOrder(Map.Entry.comparingByKey())) 
			// => 맵에 들어있는 키를 기준으로 역순 정렬 comparingByKey() 실행 시 Comparator 생성
			// => 해당 Comparator는 Map.Entry의 제네릭 타입을 가진다.
			
			// .forEach(정렬된 값들을 반복(순회)하며 키값과 밸류값을 아웃풋
			
			System.out.println("스트림과 람다표현식 활용");
			Map<Integer,String> map3 = new HashMap<>(map);
			map3.entrySet().stream().sorted(Collections.reverseOrder(Entry.comparingByKey()))
					.forEach(e -> System.out.printf("방번호 : %d, 투숙객 : %s\n", e.getKey(), e.getValue()));
			
//			map3.entrySet().stream().sorted(Map.Entry.comparingByKey())
//			.forEach(e -> System.out.printf("방번호 : %d, 투숙객 : %s\n", e.getKey(), e.getValue()));
//			오름차순 정렬
			
			System.out.println("====================================\n");

			// case 3
			
			// Collections.sort() 는 List만 들어갈 수 있다.
			// 그렇기 때문에 List의 제네릭 타입에 맵에 EntrySet을 넣어줌
			// 그 후 Collections.sort()를 사용해 만들어둔 외부 정렬자를 활용
			// for 문으로 반복순회
			
			
			Map<Integer,String> map4 = new HashMap<>(map);
			List<Entry<Integer, String>> list = new ArrayList<>(map4.entrySet());
			Collections.sort(list, new Desc());
			System.out.println("List와 외부정렬자(Comparetor)활용");
			for (Entry<Integer, String> e : list) {
				System.out.printf("방번호 : %d, 투숙객 : %s\n", e.getKey(), e.getValue());
			}
			System.out.println("====================================\n");
			
			// case 4
			
			// 위와 동일 해쉬 맵은 기본적인 정렬 구조가 없고 순서가 랜덤하기 때문에
			// 리스트와 같이 순서가 보장된 컬렉션에 저장 후
			// Collections.sort를 해주는 코드이지만
			// 외부 정렬자 클래스를 따로 선언하지 않고 람다 표현식으로 Map.Entry 안에 있는 getKey 메서드를 실행해서
			// 키 값으로 정렬해주는 코드이다
			
			System.out.println("Comparator의 comparing 메서드 활용");
			Map<Integer,String> map5 = new HashMap<>(map);
			List<Entry<Integer, String>> list2 = new ArrayList<>(map5.entrySet());
			Collections.sort(list2, Comparator.comparing(Entry::getKey));
			// Map.Entry::getKey 이대로 적어주게 된다면 컴파일러가 타입을 추론할 수 없어서 thenComparing 메서드를 활용할 수 없다.
			// thenComparing 메서드는 이와 같다 (ex: 총점이 '같은 경우' 번호 순 정렬)
			
			Collections.sort(list2, Comparator.comparing(Entry<Integer,String>::getKey).thenComparing(Entry::getValue));
			// 현재는 키값이라 중복되지 않아서 문제 없지만 다른 값으로 정렬했을때 
			// 한번 더 정렬 기준을 정하고 싶으면 thenComparing 메서드를 활용하면 된다.
			
			for (Entry<Integer, String> e : list) {
				System.out.printf("방번호 : %d, 투숙객 : %s\n", e.getKey(), e.getValue());
			}
			System.out.println("====================================\n");
			
			// case 5
			// 위와 같은 방식들은 해쉬맵에 대한 정렬 방식임
			// 트리 맵으로 객체를 생성했을 경우 내부 정렬 알고리즘(레드-블랙 트리) 오름차순으로 정렬되면서 따로 정렬 코드들을 적어줄 필요가 없다.
			// 그러나 트리 맵에서 역순으로 정렬하고 싶을 땐?
			
			TreeMap<Integer,String> tm = new TreeMap<>(Comparator.reverseOrder());
			tm.putAll(map);
			// 맵에 대한 모든 정보를 tm에 put
			System.out.println("TreeMap 활용");
			
			for (Entry<Integer, String> e : tm.entrySet()) {
				System.out.printf("방번호 : %d, 투숙객 : %s\n", e.getKey(), e.getValue());
			}
			
			System.out.println("====================================\n");
			
			// 다만 이 방법의 단점이 있는데 키 값이 자바 내부 자료형(int,char,double) 또는 String 이면 문제가 없지만
			// 개발자가 새롭게 정의한 클래스의 값이 키값이 될 경우 ClassCastException이 발생할 수 있다.
			
			// 그렇기 때문에 위와 같은 값이 키 값이 될 경우 해당 클래스의 Comparable을 구현해주자
			// Comparable을 굳이 구현하고 싶지 않다면 외부 정렬자(Comparator)를 사용해서 정렬방식을 지정해주자

		} else
			System.out.println("투숙객이 없음");
	}
}

class Desc implements Comparator<Entry<Integer, String>> {
	
	@Override
	public int compare(Entry<Integer, String> m1, Entry<Integer, String> m2) {
		// return (x < y) ? -1 : ((x == y) ? 0 : 1);
		// 순서에 따라 오름차순, 내림차순 정렬 방식을 바꿀 수 있다.
		// m1(x) < m2(y) 오름차순 or m2(x) < m1(y) 내림차순 
		return m2.getKey().compareTo(m1.getKey());
	}

}

class HotelVo {
	int room;
	String name;
	
	public HotelVo(int room, String name) {
		super();
		this.room = room;
		this.name = name;
	}
	
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}