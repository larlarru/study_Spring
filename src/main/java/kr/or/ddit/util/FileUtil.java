package kr.or.ddit.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	// contentDisposition ==> form-data; name="file"; filename="brown.png"
	public static String getFileName(String contentDisposition) {
		
//		String str = "hello World \"";	// 이렇게 \를 해줘야지 일부분?이라고 판단한다.
		String[] attrs = contentDisposition.split("; ");
		
		// 반복문 돌려서 filename=이 있으면 잘라내서 공백으로 만들고 파일이름 brown.png만 추출하기
		for(String attr : attrs) {
			
			if(attr.startsWith("filename=")) {
				
				// filename="brown.png"
				
				attr = attr.replace("filename=", "");
				
				// "brown.png"
				return attr.substring(1, attr.length() - 1);
				
				
			}
		}
		
		return "";
	}
	
	public static String getFileExtension(String filename) {
		
		// brown.png
		//	==>	arr[0]="brown", arr[1]="png";
		// return filename.split("\\")[1];
		
		// line.brown.png
//		filename.lastIndexOf(".");
//		return filename.split("\\.")[1];
		
		if(filename.indexOf(".") == -1) {
			return "";
		}
		
		logger.debug("getFileExtension에 return 전 : "+filename.substring(filename.lastIndexOf(".")+1));
		
		return "."+filename.substring(filename.lastIndexOf(".")+1);
	}
	
}
