import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ClassName: Shop
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author AQiu
 * @Create 21/06/2023 21:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {
private Map<Integer,Info> map = new HashMap<>(); // 以count为key info为value
    public  List<String> findShopId(){
        Set<Integer> keySet = map.keySet();
        ArrayList<String> arrayList = new ArrayList<>();
        List<Integer> countList = keySet.stream().filter(k -> k > 10).collect(Collectors.toList()); // 大于10的count
        countList.forEach(count->{
              arrayList.add(map.get(count).shopId);
        });
        return arrayList;
    }
}
@Data
class Info{
    String shopId;
    List<ShopInfo> shopInfo;
}
@Data
class ShopInfo {
    String shopMessage;
    Integer count;
}
class review{
    public static void main(String[] args) {

    }
}