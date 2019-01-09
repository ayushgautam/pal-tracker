package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {


    String port;
    String mem;
    String instance;
    String addr;


    public EnvController(@Value("${PORT:NOT SET}") String port, @Value("${MEMORY_LIMIT:NOT SET}") String mem ,@Value("${CF_INSTANCE_INDEX:NOT SET}") String instance, @Value("${CF_INSTANCE_ADDR:NOT SET}")String addr) {
        this.port = port;
        this.mem = mem;
        this.instance = instance;
        this.addr = addr;

    }

    @GetMapping("/env")
    public Map<String, String> getEnv(){
        Map<String, String> env = new HashMap<String, String>();
        env.put("PORT",port);
        env.put("MEMORY_LIMIT",mem);
        env.put("CF_INSTANCE_INDEX",instance);
        env.put("CF_INSTANCE_ADDR",addr);

        return env;
    }
}
