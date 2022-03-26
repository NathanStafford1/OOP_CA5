package DTOs;

public class Game
{
    private int id;
    private String name;
    private Double price;
    private int quantity;


    public Game(int id, String name, Double price, int quantity)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Game( String name, Double price, int quantity)
    {
        this.id = 0;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Game()
        {
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }


}
