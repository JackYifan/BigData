package com.zju.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;


/**
 * @Author Yifan Wu
 * Date on 2022/1/12  21:29
 */
public class HdfsClient {

    private FileSystem fs;

    @Before
    public void init() throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        fs = FileSystem.get(new URI("hdfs://ecs-bigdatapro:8020"), configuration,"root");
    }

    @After
    public void close() throws IOException {
        // 3 关闭资源
        fs.close();
    }

    @Test
    public void upload() throws IOException, URISyntaxException, InterruptedException {

        Path path = new Path("/hello");
        fs.copyFromLocalFile(false,true,new Path("D:/hello.txt"),path);

    }

    @Test
    public void download() throws IOException {
        fs.copyToLocalFile(false,new Path("/hello/hello.txt"),new Path("D:/helloHDFS.txt"),true);
    }

    @Test
    public void getInfo() throws IOException {
        // 2 获取文件详情
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);

        while (listFiles.hasNext()) {
            LocatedFileStatus fileStatus = listFiles.next();
            System.out.println("========" + fileStatus.getPath() + "=========");
            System.out.println(fileStatus.getPermission());
            System.out.println(fileStatus.getOwner());
            System.out.println(fileStatus.getGroup());
            System.out.println(fileStatus.getLen());
            System.out.println(fileStatus.getModificationTime());
            System.out.println(fileStatus.getReplication());
            System.out.println(fileStatus.getBlockSize());
            System.out.println(fileStatus.getPath().getName());

            // 获取块信息
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            System.out.println(Arrays.toString(blockLocations));
        }

    }
}
