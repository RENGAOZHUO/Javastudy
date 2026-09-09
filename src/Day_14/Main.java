package Day_14;

import javax.imageio.stream.FileImageInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//字节流FileInputStream对应FileReader，FileOutputStream对应FileWriter
//字节流 → 更底层，可以处理各种文件，例如mp3、jpg、png等等
//一个重要认知：Java IO 中很多类的使用方式非常相似，但它们处理数据的层次不同。
//字节流IO的一个重要用途：复制文件，合理使用input和output可以做到把一个文件里的内容复制到另外一个文件中去
//使用原始字节流处理数据只能一个字节一个字节处理，所以有byte[] buffer= new byte[1024];使用这样的方法去读取多个内容
// byte[] buffer = new byte[1024];建立大存储空间语句，可以让输入字节流/输出字节流一次不只读取一个字节的数据
//要读取buffer要这样：fis.read(buffer)
//写函数要：fos.write(buffer, 0, len);

public class Main {
    public static void main(String[] args) {
       /*try {
            FileOutputStream fos = new FileOutputStream("src/Day_14/test.txt");
            fos.write(65);
            fos.write(66);
            fos.write(67);
            fos.close();
        } catch (IOException e) {
            System.out.println("写入文件失败");
        }
        try{
            FileInputStream fis = new FileInputStream("src/Day_14/test.txt");
            int ch;
            while((ch=fis.read())!=-1){

                System.out.println((char)ch);
            }
            fis.close();
        }catch(IOException e){
             System.out.println("输出文件内容失败！");
        }*/
        /*try{
            byte[] data = {65, 66, 67, 68, 69};
            FileOutputStream fos =new FileOutputStream("src/Day_14/test2.txt");
            fos.write(data);
            fos.close();
        }catch(IOException e){
            System.out.println("写入文件失败！");
        }
        try{
            FileInputStream fis=new FileInputStream("src/Day_14/test2.txt");
            int ch;
            while((ch=fis.read())!=-1){
                System.out.println((char)ch);
                fis.close();
            }
        }catch(IOException e){
            System.out.println("读取字节流文件失败！");
        }*/
        try {
            FileOutputStream fos = new FileOutputStream("src/Day_14/picture2.jpg");
            FileInputStream fis = new FileInputStream("C:/Users/21818/Desktop/草东/微信图片_20240603214549.jpg");
            byte[] buffer = new byte[1024];
            int data;
            while ((data = fis.read(buffer)) != -1){
                fos.write(buffer,0,data);
            }//建立一个data变量复制给fis.read()函数来读取文件内容
            fos.close();
            fis.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
