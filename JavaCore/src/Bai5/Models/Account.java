package Bai5.Models;

public class Account {
    private Integer idAccount;
    private String name;
    private String location;
    private String accountName;
    private String password;
    private Integer idPosition;
    private Integer idDepartment;

    public Account() {}

    public Account(Integer idAccount, String name, String location, String accountName,
                   String password, Integer idPosition, Integer idDepartment) {
        this.idAccount = idAccount;
        this.name = name;
        this.location = location;
        this.accountName = accountName;
        this.password = password;
        this.idPosition = idPosition;
        this.idDepartment = idDepartment;
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

    public Integer getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(Integer idPosition) {
        this.idPosition = idPosition;
    }

    public Integer getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }

    @Override
    public String toString() {
        return "Account{" +
                "idAccount=" + idAccount +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", accountName='" + accountName + '\'' +
                ", password='" + password + '\'' +
                ", idPosition=" + idPosition +
                ", idDepartment=" + idDepartment +
                '}';
    }
}