package Day_16and17;
//字节流：
//byte[] buffer = new byte[1024];
//int len = fis.read(buffer);
//String text =
//        new String(buffer, 0, len);
//缓冲流 BufferedInputStream / BufferedOutputStream
//BufferedInputStream bis = new BufferedInputStream(fis);
//BufferedInputStream 本身并不直接负责找到文件。
//真正连接文件的还是：FileInputStream
//而BufferedInputStream是在它外面包装了一层缓冲功能。
//包装流：意思就是我已经有一个普通输入流 fis 了，现在给它加一个缓冲功能。是 Java IO 很核心的一种设计。
//缓冲流能提高IO效率会帮我们减少底层频繁的文件读取操作。
//bis.close();通常也会关闭它包装的：fis所以一般关闭最外层流即可。
//字符串不能直接当成一个字节。所以写字符串应该：
/*String text = "Hello Java";

byte[] data = text.getBytes();

bos.write(data);*/
//今天新增一个重要方法：flush()
//bos.write(data);bos.flush();flush 可以理解成：把缓冲区里暂时存着的数据立即送出去。
//flush()把数据刷出去，但流仍然可以继续使用。close()处理剩余数据并关闭流，之后不能继续使用。
//name.getBytes()把字符串转换成字节内容，getBytes()是一个非常重要的方法
//英文字符串可以通过上述办法转换成功因为一个英文字符占一个UTF-8字节，但中文字符通常占多个
//所以中文字符需要byte[] data = text.getBytes(StandardCharsets.UTF_8);
//String text = new String(buffer, 0, len);用于在读取时将字节内容转换成字符串

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args){
        /*try{
            FileInputStream fis = new FileInputStream("src/Day_16and17/student.txt");
            BufferedInputStream bis = new BufferedInputStream(fis);
            int len;
            while ((len=bis.read())!=-1){
                System.out.print((char)len);
            }
            bis.close();
        }catch(IOException e){
            e.printStackTrace();
        }*/
        /*try{
            String txt="你好 java";
            FileOutputStream fos=new FileOutputStream("src/Day_16and17/student");//若是执行该条语句之后会默认会把这个文件打开用于写入，原有内容还可能被清空
            FileInputStream fis =new FileInputStream("src/Day_16and17/student");
            fos.write(txt.getBytes(StandardCharsets.UTF_8));
            byte[] buffer =new byte[1024];
            int len=fis.read(buffer);
            String test =new String(buffer,0,len);
            System.out.println(test);

            fos.close();
            fis.close();
        }catch(IOException e){
            e.printStackTrace();
        }*/
        try{
            FileOutputStream fos =new FileOutputStream("src/Day_16and17/copy.txt");
            BufferedOutputStream bos =new BufferedOutputStream(fos);
            FileInputStream fis =new FileInputStream("src/Day_16and17/student.txt");
            BufferedInputStream bis =new BufferedInputStream(fis);
            byte[] buffer=new byte[1024];
            int len;
            while((len=bis.read(buffer))!=-1){
                bos.write(buffer,0,len);
            }

            bis.close();
            bos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
