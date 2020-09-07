package cnabookstore;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Customer_table")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long customerId;
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @PostPersist
    public void onPostPersist(){
        Registered registered = new Registered();
        BeanUtils.copyProperties(this, registered);
        registered.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.
        
        
        //추후 시나리오 조회를 통해 수정요망(8/26)
//        cnabookstore.external.Book book = new cnabookstore.external.Book();
//        book.setBookName(this.getId());
//        book.setStock();

//        cnabookstore.external.Book book = new cnabookstore.external.Book();
//        // mappings goes here
//        CustomerApplication.applicationContext.getBean(cnabookstore.external.BookService.class)
//            .increaseInventory(book);


    }

    @PostUpdate
    public void onPostUpdate(){
        Changed changed = new Changed();
        BeanUtils.copyProperties(this, changed);
        changed.publishAfterCommit();


    }

    @PostRemove
    public void onPostRemove(){
        Deleted deleted = new Deleted();
        BeanUtils.copyProperties(this, deleted);
        deleted.publishAfterCommit();


    }


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }




}
