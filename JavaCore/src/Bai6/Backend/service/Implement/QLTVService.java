package Bai6.Backend.service.Implement;

import Bai6.Backend.reponsitory.Implement.QLTVResponsitory;
import Bai6.Backend.service.IQLTVService;
import Bai6.Backend.reponsitory.IQLTVResponsitory;

public class QLTVService implements IQLTVService {

    IQLTVResponsitory qlTVResponsitory = new QLTVResponsitory();

    @Override
    public void hienThi() {
        qlTVResponsitory.hienThi();
    }

    @Override
    public void them() {
        qlTVResponsitory.them();
    }

    @Override
    public void sua() {
        qlTVResponsitory.sua();
    }

    @Override
    public void xoa() {
        qlTVResponsitory.xoa();
    }

    @Override
    public void timKiem() {
        qlTVResponsitory.timKiem();
    }


}