package com.controller;

import com.response.Response;
import com.util.FileConfig;
import com.util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/common")
public class CommonController {
    @Autowired
    private FileConfig fileConfig;

    /*
     * 上传图片
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/uploadFile")
    @CrossOrigin
    public Response<UploadFile> upload(HttpServletRequest req, HttpServletResponse res, @RequestParam("file") MultipartFile file) throws ServletException, IOException {
        String fileName = file.getOriginalFilename();
        fileName = fileName.substring(fileName.lastIndexOf("."));
        String fname = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + fileName;
        String basePath = fileConfig.getFileDir();
        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        System.out.println("basePath = " + basePath);
        //String furl = "upload/" + fname;
        String furl = fname;
        String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/" + furl;
        File dest = new File(basePath, fname);
        try {
            file.transferTo(dest);
            UploadFile uploadFile = new UploadFile();
            uploadFile.setFilePath(furl);
            uploadFile.setUrl(url);
            return Response.success(uploadFile);
        } catch (IOException e) {
            return Response.error();
        }
    }

    @RequestMapping("/downFile")
    @CrossOrigin
    public void downFile(HttpServletRequest req, HttpServletResponse res) {
        String filePath = req.getParameter("filePath");
        String basePath = fileConfig.getFileDir();
        String fileName = basePath + filePath;
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + filePath);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }


}
