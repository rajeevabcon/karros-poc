APP_NAME=acdm-admin

JVM_ARGS="$JVM_ARGS -Xms1024m -Xmx1024m"
JVM_ARGS="$JVM_ARGS -XX:+UseConcMarkSweepGC -XX:+PrintGCDateStamps -verbose:gc -Xloggc:${LOG_PATH}/${APP_NAME}-gc.log"
JVM_ARGS="$JVM_ARGS -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${LOG_PATH}"
JVM_ARGS="$JVM_ARGS -XX:SoftRefLRUPolicyMSPerMB=0"
