package Day_12;

import java.io.File;
import java.io.IOException;

//IO = Java 和外部数据之间的数据传输
//File file = new File("test.txt");建立一个File对象，并不是真的创建了文件“test.txt”而是创建了一个file对象指向它
//注意：
//File 对象并不等于文件里的内容。
//它更像是：
//Java 对某个文件/文件夹的一个“描述对象”。
//file.createNewFile();真正创建file对象指向的文件，该方法建立时使用了throws
//`createNewFile()` **不会自动创建父文件夹**，父目录不存在，直接抛出 `IOException`，进入 catch 块打印：`创建文件失败！`
//所以使用时必须配合try——catch使用，引起IOException的原因：一旦创建失败不是 “文件已存在” 的原因，就抛出 `IOException`
//"test.txt"：相对路径。File file = new File("test.txt");在程序当前工作目录下寻找 test.txt。
//还可以File file = new File("abc/test.txt");表示在abc目录下找test.txt
/*file常用方法：
             1.file.exists();判断文件是否存在；
             2.file.isFile();判断是不是文件；
             3.file.isDirectory();判断是不是文件夹；
             4.file.getName();获取文件名字；
             5.file.length();获取文件长度。返回的是字节数。
 */
//创建文件夹：File dir = new File("test");dir.mkdir();
//dir.mkdirs();创建多层目录；File dir = new File("a/b/c");dir.mkdirs;创建a下b，b下c；

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args){


       /* File file = new File("src/Day_12/text.txt");
        try{
            boolean result =file.createNewFile();
            System.out.println(result);
        }catch(IOException e){
            System.out.println("创建文件失败！");
        }
        System.out.println(file.exists());
        System.out.println(file.isFile());
        System.out.println(file.getName());
        System.out.println(file.length());

        */
        File dir = new File("test");
        dir.mkdir();
        System.out.println(dir.exists());
        System.out.println(dir.isDirectory());
    }
}
