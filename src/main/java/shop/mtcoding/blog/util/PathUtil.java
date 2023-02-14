package shop.mtcoding.blog.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import shop.mtcoding.blog.handler.ex.CustomException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class PathUtil {

    private static String getStaticFolder(){
        return System.getProperty("user.dir") + "\\src\\main\\resources\\static\\";
    }

    public static String writeImageFile(MultipartFile profile){
        UUID uuid = UUID.randomUUID();
        String uuidImageRealName = "images\\"+uuid+"_"+profile.getOriginalFilename();

        String staticFolder = getStaticFolder();
        Path imageFilePath = Paths.get(staticFolder+"\\"+uuidImageRealName);
        try {
            Files.write(imageFilePath, profile.getBytes());
        }catch (Exception e){
            throw new CustomException("사진을 웹서버에 저장하지 못하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return "/images/"+uuid+"_"+profile.getOriginalFilename();
    }
}
