package dto;

import com.ouc.mallmbg.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductIdsAndOtherInfo {
    List<Integer> indentIds;
    String address;

    String phoneNumber;

    String receiverName;
}
