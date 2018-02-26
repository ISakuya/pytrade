package entry;

/**
 * Created by isakuya on 17-6-10.
 */
public class Goods {
    private String name;
    private double price;
    private String pic;
    private  int id;
    public Goods(int _id,String _name, double _price, String _pic){
        id = _id;
        name = _name;
        price = _price;
        pic = _pic;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getPic() {
        return pic;
    }

    public int getId() {
        return id;
    }
}
