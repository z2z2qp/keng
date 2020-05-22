package code.java;

public enum Snowflakes {
    INSTANCE;

    public long id(int machineId){
        long first = getNow();
        if(index >= 4096){
            first = nextMillis();
            index = 0;
        }
        
        return first << 22 | machineId << 10 | index++;
    }

    private long now = 0;

    private int index = 0;

    private synchronized long nextMillis(){
        long temp;
        do{
            temp = System.currentTimeMillis();
        }while(temp > now);
        now = temp;
        return now;
    }

    private long getNow(){
        now = System.currentTimeMillis();
        return now;
    }
}

class SnowflakesTest{
    public static void main(String[] args) {
        final Snowflakes snowflakes = Snowflakes.INSTANCE;
        for(int j = 0;j<10;j++){
            final int q = j;
            new Thread("t"+q){
                @Override
                public void run(){
                    for(int i = 0;i<10;i++){
                        System.out.println(this.getName() +":"+ snowflakes.id(q));
                    }
                }
            }.start();
        }
    }
}