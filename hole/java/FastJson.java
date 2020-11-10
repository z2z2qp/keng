import java.util.Base64;

public class FastJson {

    public static void main(String[] args) {
        byte[] bytes = Base64.getDecoder().decode("eyJub3RpZnlUeXBlIjoiZGV2aWNlRGF0YXNDaGFuZ2VkIiwicmVxdWVzdElkIjpudWxsLCJkZXZpY2VJZCI6IjUxZTE5NTg0LWViYjctNDczNS1iMmUwLTJjOGZlNGE2NDRmNyIsImdhdGV3YXlJZCI6IjUxZTE5NTg0LWViYjctNDczNS1iMmUwLTJjOGZlNGE2NDRmNyIsInNlcnZpY2VzIjpbeyJzZXJ2aWNlSWQiOiJXYXRlck1ldGVyIiwic2VydmljZVR5cGUiOiJXYXRlck1ldGVyIiwiZGF0YSI6eyJkYXRhIjoiNjkwMGUxYjExMTcxMDAwMDAwMDAwMDAwMDAwMTY5MDEwMTY5MGRmZmEzMDAxOTAwNTU2YWU3MDQzODM2MzMzNzMxMzYzMDM0MzUzMzM5MzgzMjMzMzQzODM5MzgzNjMxMzEzMTM4MzIzNDM1MzAzMDM2MzAzMDMwMzYzMzMxMjAxMTA5MDAwMDAwMzAwMDFlMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwNjYxNyJ9LCJldmVudFRpbWUiOiIyMDIwMTExMFQwMzAxNDBaIn1dfQ==");
        
        JSONObject obj = new JSONObject();
        obj.put("sectty", 0);
        obj.put("encryty", 0);
        obj.put("jar", "dkm_gsnb_1.0-1.0.jar");
        obj.put("source", bytes);
        System.out.println(obj);//fastjson 如果是byte[] 数组可能输出的是base64加密后的字符串，而非数组
        bytes = obj.getBytes("source");//用getBytes 获取到的是解密后的byte[] 可以直接使用   此坑耗时3h
    }
    
}
