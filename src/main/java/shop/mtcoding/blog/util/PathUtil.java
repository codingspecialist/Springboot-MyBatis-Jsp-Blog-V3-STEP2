package shop.mtcoding.blog.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import shop.mtcoding.blog.handler.ex.CustomException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class PathUtil {

    // image 폴더는 외부에 잡고, 그 경로를 잡아주는 것이 좋음
    private static String getStaticFolder(){
        // build된 realpath로 변경 (인텔리J)
        return System.getProperty("user.dir") + "\\build\\resources\\main\\static\\";
        // vscode 용
        //return System.getProperty("user.dir") + "\\src\\main\\resources\\static\\";
    }

    public static String writeImageFile(MultipartFile profile){
        UUID uuid = UUID.randomUUID();
        String uuidImageRealName = "images\\"+uuid+"_"+profile.getOriginalFilename();

        String staticFolder = getStaticFolder();
        Path imageFilePath = Paths.get(staticFolder+uuidImageRealName);
        try {
            Files.write(imageFilePath, profile.getBytes());
        }catch (Exception e){
            throw new CustomException("사진을 웹서버에 저장하지 못하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return "/images/"+uuid+"_"+profile.getOriginalFilename();
    }
}
