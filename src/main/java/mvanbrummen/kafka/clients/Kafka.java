package mvanbrummen.kafka.clients;

import org.apache.kafka.clients.admin.AdminClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Kafka {

    public static AdminClient adminClient() {
        var props = new Properties();
        try {
            props.load(new FileInputStream("/Users/mvanbrummen/workspace/kafka-tool-swing/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        var adminClient = AdminClient.create(props);

        return adminClient;
    }
}
