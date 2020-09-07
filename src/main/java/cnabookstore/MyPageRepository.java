package cnabookstore;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyPageRepository extends CrudRepository<MyPage, Long> {

	List<MyPage> findByOrderId(Long orderId);

//    void deleteByDeliveryId(Long deliveryId);
    void deleteByOrderId(Long orderId);
}