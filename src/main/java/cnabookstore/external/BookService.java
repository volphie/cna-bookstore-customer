
package cnabookstore.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="bookInventory", url="http://bookInventory:8080")
public interface BookService {

    @RequestMapping(method= RequestMethod.PATCH, path="/books")
    public void increaseInventory(@RequestBody Book book);

}