package Day_19;

import java.io.*;

//DataInputStream / DataOutputStream
//在普通字节输出流的基础上，让我们能够直接写 Java 基本数据类型。
//dos.writeInt(34);
//dos.writeDouble(98.5);
//dos.writeBoolean(true);
//dos.writeChar('A');
//dos.writeUTF("David");
//String name = dis.readUTF();
//int age = dis.readInt();
//double score = dis.readDouble();
//boolean passed = dis.readBoolean();
//System.out.println(name);
//System.out.println(age);
//System.out.println(score);
//System.out.println(passed);
//今天最重要的规则：读取顺序必须和写入顺序一致
//writeInt(34) 和 write("34".getBytes()) 本质不同
//dos.writeInt(34);把整数 34 按 int 的二进制格式保存
//fos.write("34".getBytes());把字符 '3' 和字符 '4' 对应的字节保存
//处理任意文件BufferedInputStream BufferedOutputStream
//处理文本BufferedReader BufferedWriter
//处理基本数据类型DataInputStream DataOutputStream
//.dat 只是一个很常见的“数据文件”后缀，dat 可以理解成 data（数据）。它通常表示：这个文件主要是给程序保存数据用的，不一定是给人直接阅读的文本文件。
public class Main {
    public static void main(String[] args){
        /*try{
            FileOutputStream fos =new FileOutputStream("src/Day_19/student.dat");
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeUTF("David");
            dos.writeInt(20);
            dos.writeDouble(92.5);
            dos.writeBoolean(true);
            dos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        try{
            FileInputStream fis = new FileInputStream("src/Day_19/student.dat");
            DataInputStream dis = new DataInputStream(fis);
            String name= dis.readUTF();
            int age=dis.readInt();
            double score=dis.readDouble();
            boolean passed= dis.readBoolean();
            System.out.println("name"+name);
            System.out.println("age"+age);
            System.out.println("score"+score);
            System.out.println("passed"+passed);
            dis.close();
        }catch(IOException e){
            e.printStackTrace();
        }*/
        try{
            FileOutputStream fos =new FileOutputStream("src/Day_19/student2.dat");
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeInt(3);
            dos.writeUTF("David");
            dos.writeInt(20);
            dos.writeDouble(92.5);
            dos.writeUTF("Jack");
            dos.writeInt(21);
            dos.writeDouble(88.0);
            dos.writeUTF("Alice");
            dos.writeInt(19);
            dos.writeDouble(95.5);
            dos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        try {
            FileInputStream fis = new FileInputStream("src/Day_19/student2.dat");
            DataInputStream dis = new DataInputStream(fis);
            int key;
            key= dis.readInt();
            for(int i=0;i<key;i++){
                String name= dis.readUTF();
                int age=dis.readInt();
                double score=dis.readDouble();

                System.out.println("name"+name);
                System.out.println("age"+age);
                System.out.println("score"+score);

            }
            dis.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
