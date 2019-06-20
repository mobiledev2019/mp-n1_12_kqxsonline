package com.example.ketquaxoso;

public class account {
    private String user;
    private String money;
    private int idAccount;
    private String date;
    public account(int IdAccount, String user, String date,String money){

        this.user = user;
        this.date = date;
        this.money = money;
        this.idAccount = IdAccount;
    }
    public account(String user, String date, String money){
        this.user = user;
        this.date = date;
        this.money = money;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
