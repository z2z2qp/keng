put url: http://192.168.10.110:30672/api/queues/%2F/C
在vhost=/ 创建一个名为C的queues
其他参数 {"vhost":"/","name":"C","durable":"true","auto_delete":"false","arguments":{"x-max-length":50000,"x-queue-type":"classic"}}

post url: http://192.168.10.110:30672/api/bindings/%2F/e/amq.topic/q/C
在vhost=/ exchenge=amq.topic queues=C 建立绑定关系  绑定JZQ.data
其他参数 {"vhost":"/","destination":"C","destination_type":"q","source":"amq.topic","routing_key":"JZQ.data","arguments":{}}

注：%2F 为/ 的urlEncode
