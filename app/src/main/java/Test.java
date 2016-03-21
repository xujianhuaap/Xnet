import com.github.xujianhua.xnet.annotation.Api;

/**
 * Created by xujianhua on 20/03/16.
 */
public interface Test {
    @Api("123")
    void httpPost(String url);
}
