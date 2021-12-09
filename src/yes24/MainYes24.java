package yes24;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainYes24 {
	// Jsoup 사용하여 웹 크롤링
	// Yes24에서 책 검색 하기
	// 책 이름, 저자, 가격, 출간일(가끔 Error 나옴)
	
	public static void main(String[] args) throws IOException {

		System.out.println("Yes24 책 검색하기");
		Scanner sc = new Scanner(System.in);
		System.out.print("검색어: ");
		String str = sc.next();
		String text = URLEncoder.encode(str, "UTF-8");
		String url = "http://www.yes24.com/Product/Search?domain=ALL&query=" + text;

		Document doc = Jsoup.connect(url).get(); // Document에는 페이지의 전체 소스가 저장

		// select를 이용하여 원하는 태그를 선택
		Elements element = doc.select("div.sGoodsSecArea");

		// Iterator을 사용하여 하나씩 값 가져오기
		Iterator<Element> name = element.select("a.gd_name").iterator();
		Iterator<Element> price = element.select("strong.txt_num").iterator();
		Iterator<Element> author = element.select("span.authPub.info_auth").iterator(); // 클래스 이름 space를 . 으로
		Iterator<Element> pDate = element.select("span.authPub.info_date").iterator(); 
		
//		Boolean next = name.hasNext() && author.hasNext() && pDate.hasNext() && price.hasNext(); // not working
		while (pDate.hasNext()) { 	// 출간일이 없는 경우가 더 많음
			System.out.println("책 이름: " + name.next().text());
			System.out.println("저자: " + author.next().text());
			System.out.println("출간일: " + pDate.next().text()); 
			System.out.println("가격: " + price.next().text());
			System.out.println();
		}


		// 다른 방법
//		System.out.println("Yes24 책 검색하기");
//		Scanner sc = new Scanner(System.in);
//		System.out.print("책 검색: ");
//		String str = sc.next();
//		String text = URLEncoder.encode(str, "UTF-8");
//		String URL = "http://www.yes24.com/Product/Search?domain=ALL&query=" + text;
//		Document doc = Jsoup.connect(URL).get();
//		Elements elem = doc.select("div[class=\"sGoodsSecArea\"]");
//		System.out.println("--");
//		for (Element e : elem.select("a")) {
//			if (e.className().equals("gd_name")) {
//				System.out.println(e.text());
//			}
//		}
	}
}
