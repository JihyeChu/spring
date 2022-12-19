package com.tjoeun.springDI4_xml_interface;

public class PencilHBEraser implements Pencil {

	@Override
	public void use() {

		System.out.println("지우개가 달린 HB연필로 글씨를 씁니다.");
		
	}

}
