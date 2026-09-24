package Bai6.Entity;

public class Account {
    private Integer idAccount;
    private String name;
    private String location;
    private String accountName;
    private String password;
    private Position position;
    private Department department;

    public Account() {}

    public Account(Integer idAccount, String name, String location, String accountName,
                   String password, Position position, Department department) {
        this.idAccount = idAccount;
        this.name = name;
        this.location = location;
        this.accountName = accountName;
        this.password = password;
        this.position = position;
        this.department = department;
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Account{" +
                "idAccount=" + idAccount +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", accountName='" + accountName + '\'' +
                ", password='" + password + '\'' +
                ", position=" + position +
                ", department=" + department +
                '}';
    }
}