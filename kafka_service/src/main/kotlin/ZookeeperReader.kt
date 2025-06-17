import org.apache.zookeeper.ZooKeeper
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets

@Service
class ZookeeperReader {

    private val zk = ZooKeeper("zookeeper:2181", 3000) { println("Connected to ZooKeeper for reading") }

    fun read(path: String): String? {
        val data = zk.getData(path, false, null)
        return data?.let { String(it, StandardCharsets.UTF_8) }
    }
}
