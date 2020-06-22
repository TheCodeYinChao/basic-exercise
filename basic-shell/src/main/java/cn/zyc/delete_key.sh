redis_comm=redis-cli
redis_server1=172.21.0.7
port1=16381

redis_server2=172.21.0.7
port2=16382

redis_server3=172.21.0.17
port3=16381


KEYS="access* auth* client_id_to_access* refresh* uname_to_access*"
for i in ${KEYS};do
    result1=`$redis_comm -c -h $redis_server1 -p $port1  keys $i`
    echo $result1
    $redis_comm -c -h $redis_server1 -p $port1  keys $i | xargs -i redis-cli -h $redis_server1 -p $port1 del {}
    
    result2=`$redis_comm -c -h $redis_server2 -p $port2  keys $i`
    echo $result2
    $redis_comm -c -h $redis_server2 -p $port2  keys $i | xargs -i redis-cli -h $redis_server2 -p $port2 del {}
    
    result3=`$redis_comm -c -h $redis_server3 -p $port3  keys $i`
    echo $result3
    $redis_comm -c -h $redis_server3 -p $port3  keys $i | xargs -i redis-cli -h $redis_server3 -p $port3 del {}
done
