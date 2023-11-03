package custom.annotation.entity;


import custom.annotation.jackon.annotation.DecodeAddress;

public class User {
    
    @DecodeAddress
    private String name;
    
    private String password;
    @DecodeAddress
    private String address;
    private String idCard;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getIdCard() {
        return idCard;
    }
    
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    
    public static User getUser() {
        return new User() {{
            setAddress("mapper.writeValueAsString");
            setName("map");
            setIdCard("12434579823047");
            setPassword("dgdfsg898989");
        }};
    }
}
