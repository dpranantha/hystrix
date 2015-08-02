package myhystrix.simple;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    protected CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("HelloWorld"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "Hello " + name + "!";
    }

    @Override
    protected String getFallback() {
        return "Hello Failure " + name + "!";
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(name);
    }
}
