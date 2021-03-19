
import java.nio.ByteBuffer; 
import java.nio.channels.ReadableByteChannel; 
import java.nio.channels.WritableByteChannel; 
import java.nio.channels.Channels; 
import java.io.IOException;

public class Main
{
    public static void main(String[] args) {
        ReadableByteChannel readChannel = Channels.newChannel(System.in);
        WritableByteChannel writeChannel = Channels.newChannel(System.out);
         //ReadableByteChannel readChannel = Channels.newChannel(System.in);
         //WritableByteChannel writeChannel = Channels.newChannel(System.out);
        try{
         ByteBuffer buffer = ByteBuffer.allocateDirect(16*1024);
         while(readChannel.read(buffer) != -1){
             buffer.flip();
             writeChannel.write(buffer);
             buffer.compact();
         }
         buffer.flip();
         while(buffer.hasRemaining()){
             writeChannel.write(buffer);
         }
           readChannel.close();
           writeChannel.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
