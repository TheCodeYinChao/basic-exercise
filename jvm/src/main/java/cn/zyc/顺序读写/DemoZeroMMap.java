package cn.zyc.顺序读写;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * dsc: Demo
 * date: 2020/12/24 17:59
 * author: zyc 顺序读写
 *
 * 零拷贝 是
 * 与mmap映射是两种提升效率的技术手段
 * 零拷贝：是操作系统层面防止内核控件copy 到用户空间 而直接操作内核控件
 * mmap ：是内存映射技术，这玩意儿是对文件的内存读写直接可以映射到磁盘上，我们也可以将磁盘中的文件一次性映射到内存，
 *          我们对内存的操作会直接落盘到我们的硬盘上，而且是顺序读写
 *
 *https://www.cnblogs.com/binyue/p/3727511.html
 *
 */
public class DemoZeroMMap {

    @Test
    public  void testWrite(){
        String s = "C:\\Users\\RAYDATA\\Desktop\\aa.txt";
        Long l = fileWrite(s, "你好", 0);
        Long l1 = fileWrite(s, "你妹的", l.intValue());
        Long l2 = fileWrite(s, "meide ca ", l1.intValue());
        System.out.println(l2);

        String s1 = fileRead(s,3, l2);//读某一位置
        System.out.println(s1);

    }

    public static long fileWrite(String filePath, String content, int index) {
        File file = new File(filePath);
        RandomAccessFile randomAccessTargetFile;
        MappedByteBuffer map;//内存映射技术
        try {
            randomAccessTargetFile = new RandomAccessFile(file, "rw");
            FileChannel targetFileChannel = randomAccessTargetFile.getChannel();
            map = targetFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, (long) 10 * 1024 * 1024);//这里从磁盘扣一块内存出来 ，通过自己个维护的写入index来顺序的读写
            map.position(index);
            map.put(content.getBytes());
            return map.position();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return 0L;
    }


    public static String fileRead(String filePath, long startIndex,long index) {
        File file = new File(filePath);
        RandomAccessFile randomAccessTargetFile;
        MappedByteBuffer map;
        try {
            randomAccessTargetFile = new RandomAccessFile(file, "rw");
            FileChannel targetFileChannel = randomAccessTargetFile.getChannel();
            map = targetFileChannel.map(FileChannel.MapMode.READ_WRITE, startIndex, index);
            byte[] byteArr = new byte[10 * 1024];
            map.get(byteArr, 0, (int) index);
            return new String(byteArr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return "";
    }
}
