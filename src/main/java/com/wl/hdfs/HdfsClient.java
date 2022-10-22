//package com.wl.hdfs;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FileStatus;
//import org.apache.hadoop.fs.LocatedFileStatus;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.fs.RemoteIterator;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.net.URI;
//import java.nio.file.FileSystem;
//
//public class HdfsClient {
//    private FileSystem fs;  // 定义HDFS文件系统对象
//
//    @Before
//    public void init() throws IOException, InterruptedException {
//
//        // HDFS的访问路径 hdfs://hadoop102:9820
//        URI uri = URI.create("hdfs://zhanghaojie101:9820");
//
//        // 配置对象
//        Configuration configuration = new Configuration();
//
//        // 使用Configuration重新设置副本数
//        configuration.set("dfs.replication", "6");
//
//        // 操作的用户(用哪个用户去操作HDFS)
//        String user = "zhj";
//
//        // 1.获取HDFS的客户端连接对象(文件系统对象)
//        fs = FileSystem.get(uri, configuration, user);
//
//        System.out.println(fs.getClass().getName());
//    }
//
//    /**
//     * 关闭资源
//     */
//    @After
//    public void close() throws IOException {
//        // 3.关闭资源
//        System.out.println("close");
//        fs.close();
//    }
//
//    /**
//     * 上传文件
//     */
//    @Test
//    public void testCopyFromLocal() throws IOException {
//        // 参数解读：
//        // 参数一：表示删除原数据，为true表示上传完文件后，原数据文件将会删除；为false表示上传完文件后，原数据文件将不会删除；
//        // 参数二：是否允许覆盖，为true表示不管目的地地址是否有同名的文件都会覆盖；
//        //      为false表示目的地地址有同名的文件则报错org.apache.hadoop.fs.PathExistsException: `/hdfs_client': Target /hdfs_client
//        //      already exists；
//        // 参数三：原数据路径Windows；
//        // 参数四：目的地路径HDFS
//        fs.copyFromLocalFile(false, false,
//                new Path("/Users/xunmi/Desktop/client_test.txt"),
//                new Path("/hdfs_client3/client_test.txt"));
//    }
//
//    /**
//     * 文件下载
//     */
//    @Test
//    public void testCopyToLocalFile() throws IOException {
//        // 执行下载操作
//        // boolean delSrc 指是否将原文件删除
//        // Path src 指要下载的文件路径
//        // Path dst 指将文件下载到的路径
//        // boolean useRawLocalFileSystem 是否开启文件校验 false表示开启校验和  true表示不生成检验和
//
//        // 如果 E://download 没有则新建
//        fs.copyToLocalFile(false, new Path("/hdfs_client3/client_test.txt"),
//                new Path("/Users/xunmi/Desktop/download"),
//                false);
//    }
//
//    /**
//     * 删除文件和目录
//     */
//    @Test
//    public void testDelete() throws IOException {
//        // boolean 是否递归删除
//        fs.delete(new Path("/hdfs_client3/client_test.txt"), true);
//    }
//
//    /**
//     * HDFS文件更名和移动
//     */
//    @Test
//    public void testRename() throws IOException {
//        // 修改文件名称(在不同的HDFS目录下就是移动)
//        fs.rename(new Path("/hdfs_client/WechatIMG263.jpeg"), new Path("/hdfs_client/test_pic.jpeg"));
//    }
//
//    /**
//     * HDFS文件详情查 -- 查看文件名称、权限、长度、块信息
//     */
//    @Test
//    public void testListFiles() throws IOException {
//        // 获取文件详情
//        // boolean 是否递归查询
//        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
//        while (listFiles.hasNext()) {
//            LocatedFileStatus status = listFiles.next();
//
//            // 文件输出详情
//            System.out.println("文件名:" + status.getPath().getName());
//
//            System.out.println("块大小:" + status.getBlockSize());
//
//            System.out.println("副本数:" + status.getReplication());
//
//            System.out.println("权限信息:" + status.getPermission());
//
//            System.out.println("长度:" + status.getLen());
//
//            System.out.println("分组:" + status.getGroup());
//
//            System.out.println("----------------------------------------");
//        }
//    }
//
//    /**
//     * 判断HDFS指定路径下 所有的东西  是文件还是文件夹
//     */
//    @Test
//    public void testListStatus() throws IOException {
//        // 判断是文件还是文件夹
//        FileStatus[]  fileStatusArr = fs.listStatus(new Path("/"));
////        FileStatus[] fileStatusArr = fs.listStatus(new Path("/hdfs_client"));
//        for (FileStatus fileStatus : fileStatusArr) {
//            // 如果是文件
//            if (fileStatus.isFile()) {
//                // 是文件
//                System.out.println("File:" + fileStatus.getPath().getName());   // File:xiaoqiao.txt
//            } else {
//                // 是目录
//                System.out.println("Dirtory:" + fileStatus.getPath().getName());
//            }
//        }
//    }
//
//}
//
//}
