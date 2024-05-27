package com.board.pds.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.board.pds.domain.FilesVo;

public class PdsFile {

	// uploadPath 에 넘어온 파일들을 저장
	public static void save(
			HashMap<String, Object> map, 
			MultipartFile[] uploadFiles) {
		
		// 저장될 경로 가져온다
		String uploadPath  = String.valueOf( map.get("uploadPath") );
		   // String.valueOf( object ) -> String	 
		   // 제대로 작동안함 (String) map.get("uploadPath");
	    System.out.println("uploadPath:" + uploadPath 
	    		+ "uploadFiles length:" + uploadFiles.length
	    		);
	    
	    List<FilesVo>  filesVo  =  new ArrayList<>();
	    
	    for(MultipartFile uploadFile : uploadFiles ) {
	    	
	    	String  originalName =  uploadFile.getOriginalFilename();
	    	System.out.println( "originalName:" + originalName );
	    	String  fileName     =  originalName.substring( originalName.lastIndexOf("\\")+1);
	    	String fileExt = originalName.substring( originalName.lastIndexOf(".")+1);
	    	
	    	//d:\dev\date\2024\05\27
	    	//날짜 폴더를 생성 - 같은 폴더에는 파일 마지막 업로드 된 파일만 저장된다
	    	String folderPath = makeFolder(uploadPath);
	    	// 중복하지 않는 고유한 문자열 생성 : UUID
	    	String uuid = UUID.randomUUID().toString();
	    	// d:\dve\data \2024\05\27 \
	    	//uuid_data.abc.txt
	    	String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
	    	String saveName2 = folderPath + File.separator + uuid + "_" + fileName;
	    	Path savePath = Paths.get(saveName);
	    	// 파일저장 
	    	try {
				uploadFile.transferTo(savePath);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	System.out.println("저장됨");
	    	// 저장된 파일들의 정보를 map 에 List로 저장 
	    	FilesVo vo = new FilesVo(0, 0, fileName, fileExt, saveName2);
	    	fileList.add(vo);
	    } //end for
	    map.put("fileList", fileList);
	    
	} //save() end 
	private static String makeFolder(String uploadPath) {
		String dateStr = LocalDate.now().format(
				DateTimeFormatter.ofPattern("yyyy/mm/dd"));
		//String folderPath = dateStr.replace("/", "\\");
		String folderPath = dateStr.replace("/", File.separator);
		File uploadPathFolder = new File(uploadPath, folderPath);
		//System.out.println(uploadPathFolder.toPath().getFileSystem());
		if(uploadPathFolder.exists() == false) {
			uploadPathFolder.mkdir();
			//mkdir(); : 상위폴더가 없어도 폴더전체를 만들지 못한다
			//mkdirs(); : 상위폴더가 없어도 폴더전체를 만들어 준다.
		}
		return folderPath;
	}

}


