package javaTest;

public class Switch {
	public static void main(String[] args) {
	String category = "専門書";
	String path = null;
	
//	if(category == "学習参考書") {
//		path = "学習参考書";
//	} else if(category == "専門書") {
//		path = "専門書.png";
//	} else if(category == "ビジネス") {
//		path = "ビジネス.png";
//	} else if(category == "文学・文芸") {
//		path = "文芸.png";
//	} else if(category == "趣味") {
//		path = "趣味.png";
//	} else if(category == "マンガ") {
//		path = "マンガ.png";
//		
//	} else {
//		path = "その他.png";
//	}
	
	switch(category) {
	case "学習参考書":
		path = "学習参考書.png";
		break;
	case "専門書":
		path = "専門書.png";
		break;
	case "ビジネス":
		path = "ビジネス.png";
		break;
	case "文学・文芸":
		path = "文芸.png";
		break;
	case "趣味":
		path = "趣味.png";
		break;
	case "マンガ":
		path = "マンガ.png";
	break;
	default:
		path = "その他.png";
	}
	
	System.out.println(path);
	}
}
