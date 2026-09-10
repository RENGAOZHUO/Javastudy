package Day_15;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//一次读取一大块数据。byte[] buffer 就是干这个的
//byte[] buffer = new byte[1024];创建了一个：可以存放 1024 个字节的数组。可以理解成buffer是一个装了1024个·字节箱子
//fis.read(buffer);意思就是：从文件里读取一批数据，放进 buffer。同时 read(buffer) 会返回一个整数：本次实际读取了多少个字节，这个返回值非常重要；
//fos.write(buffer, 0, len);这里的三个参数可以理解为buffer->从第0个字节开始，一直写len个字节结束的读取。eg：len=1024，读取情况：buffer[0]~buffer[1024]
//byte[] buffer = new byte[1024]这里的1024并不是一个固定的值，它可以是2048，4096等等，为了学习方便设置为1024；
public class Main {
    public static void main(String[] args){
        /*try{
            FileInputStream fis =new FileInputStream("src/Day_15/test.txt");
            byte[] buffer =new byte[3];
            int len;
            while((len= fis.read(buffer))!=-1){
                System.out.println("本次读取"+len+"个字节");
                for(int i=0;i<len;i++){
                    System.out.print((char)buffer[i]);//要使用这种方法来一个一个打印buffer里的内容需使用buffer[i]的形式，我错误的认为是使用len，len其实是读取到的buffer所含字符长度
                }
                System.out.println();
            }
            fis.close();
        }catch(IOException e){
            e.printStackTrace();
        }*/
        try{
            FileOutputStream fos = new FileOutputStream("src/Day_15/picture.jpg");
            FileInputStream fis = new FileInputStream("C:/Users/21818/Desktop/lr magic/未命名导出/DSC_1420.jpg");
            byte[] buffer = new byte[1024];
            int len;
            while((len=fis.read(buffer))!=-1){
                fos.write(buffer,0,len);
            }//使用byte[]来读取时read()里需要填入对应byte[]类型变量名，负责读取内容为空，我就犯了这个错误；
            fos.close();
            fis.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
