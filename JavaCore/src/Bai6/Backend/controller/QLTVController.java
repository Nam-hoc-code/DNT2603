package Bai6.Backend.controller;
import Bai1.Models.Account;
import Bai6.Backend.service.IQLTVService;
import Bai6.Backend.service.Implement.QLTVService;

import java.util.List;

public class QLTVController {
    IQLTVService qlTVService = new QLTVService();
    public void getAccounts() {
        qlTVService.hienThi();
    }

    public void themAccount() { qlTVService.them();}

    public void suaAccount() {
        qlTVService.sua();
    }

    public void xoaAccount() {
        qlTVService.xoa();
    }
    public void timKiemAccount() {
        qlTVService.timKiem();
    }
}