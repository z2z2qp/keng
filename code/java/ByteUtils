public class ByteUtils{
    /**
     * 取bytes 数组中的 第i 位，二进制
     */
    public static byte get(byte[] bytes,int i){
        int index = i / 8;
        int tmp = i % 8;
        byte b = bytes[index];
        return (byte) ((b >> (7 - tmp)) & 0x1);
    } 
    
    /**
     * 取 bytes 数组中 第start位，到end位，二进制
     */
    public static String get(byte[] bytes,int start,int end){
        StringBuilder sb = new StringBuilder();
        for(int i = start;i < end;i++){
            sb.append(get(bytes,i));
        }
        return sb.toString();
    }

}
