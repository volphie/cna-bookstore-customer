package cnabookstore;

import cnabookstore.config.kafka.KafkaProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@Transactional
public class MyPageViewHandler {


    @Autowired
    private MyPageRepository myPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrdered_then_CREATE_1 (@Payload Ordered ordered) {
        try {
            if (ordered.isMe()) {
                // view 객체 생성
                MyPage myPage = new MyPage();
                // view 객체에 이벤트의 Value 를 set 함
                myPage.setCustomerId(ordered.getCustomerId());
                myPage.setOrderId(ordered.getOrderId());
                myPage.setQuantity(ordered.getQuantity());
                myPage.setOrderStatus(ordered.getOrderStatus());
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryPrepared_then_UPDATE_1 (@Payload DeliveryPrepared deliveryPrepared) {
        try {
            if (deliveryPrepared.isMe()) {
                // view 객체 조회
                List<MyPage> myPageList = myPageRepository.findByOrderId(deliveryPrepared.getOrderId());
                for (MyPage myPage : myPageList) {
                    // view 객체에 이벤트의 Value 를 set 함
                    myPage.setDeliveryStatus(deliveryPrepared.getStatus());
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryStatusChanged_then_UPDATE_2 (@Payload DeliveryStatusChanged deliveryStatusChanged) {
        try {
            if (deliveryStatusChanged.isMe()) {
                // view 객체 조회
                List<MyPage> myPageList = myPageRepository.findByOrderId(deliveryStatusChanged.getOrderId());
                for (MyPage myPage : myPageList) {
                    // view 객체에 이벤트의 Value 를 set 함
                    myPage.setDeliveryStatus(deliveryStatusChanged.getDeliveryStatus());
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderCanceled_then_UPDATE_3 (@Payload OrderCanceled orderCanceled) {
        try {
            if (orderCanceled.isMe()) {
                // view 객체 조회
                List<MyPage> myPageList = myPageRepository.findByOrderId(orderCanceled.getOrderId());
                for (MyPage myPage : myPageList) {
                    // view 객체에 이벤트의 Value 를 set 함
                    myPage.setOrderStatus(orderCanceled.getStatus());
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();

//        try {
//            if (orderCanceled.isMe()) {
//                // view 레파지 토리에 삭제 쿼리
//                myPageRepository.deleteByOrderId(orderCanceled.getOrderId());
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        }
    }




}