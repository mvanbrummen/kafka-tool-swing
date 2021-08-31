package mvanbrummen.kafka.clients;

import org.apache.kafka.clients.admin.AdminClient;

import java.util.Properties;

public class Kafka {

    public static AdminClient adminClient() {
        var props = new Properties();
        props.put("bootstrap.servers", "pkc-e09o6.australia-southeast1.gcp.confluent.cloud:9092");
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "PLAIN");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"OEOF4IYB3BS4R52C\" password=\"Ru4cuXzz1Z0P3Eh7c74mELl5PkJYOZCEwoP+XBcE7yvKmEf0gQaGAobDEFjVTrey\";");
        var adminClient = AdminClient.create(props);

        return adminClient;
    }
}
