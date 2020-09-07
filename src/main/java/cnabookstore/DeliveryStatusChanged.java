
package cnabookstore;

public class DeliveryStatusChanged extends AbstractEvent {

    private Long orderId;
    private String deliveryStatus;

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }


}
