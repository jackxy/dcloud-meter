package net.xdclass.service.common;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /**
     * 文件上传接口
     * @param file
     * @return
     */
    String upload(MultipartFile file);


    /**
     * 获取对象的临时访问url，有效期1分钟，也可以当参数传入
     * @param remoteFilePath
     * @return
     */
    String getTempAccessFileUrl(String remoteFilePath);


    /**
     * 读取远程文件下载到本地创建临时文件
     * @param remoteFilePath
     * @return
     */
    String copyRemoteFileToLocalTempFile(String remoteFilePath);

}
