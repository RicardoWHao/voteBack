package com.lingling.controller;

import com.lingling.utils.FileUtils;
import com.lingling.utils.IdGenerator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * Created by WangHao on 2018/5/10.
 */
@RestController
public class UploadFileController {

    //处理文件上传
    @RequestMapping(value="/uploadimg", method = RequestMethod.POST)
    public @ResponseBody String uploadImg(@RequestParam("file") MultipartFile file,
                                          HttpSession httpSession) {

        String contentType = file.getContentType();
        //String fileName = file.getOriginalFilename();
        String fileName = IdGenerator.getId()+file.getOriginalFilename();
        /*System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);*/
        String filePath = httpSession.getServletContext().getRealPath("imgupload/");
        try {
            FileUtils.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        //返回json
        return fileName;
    }


}
