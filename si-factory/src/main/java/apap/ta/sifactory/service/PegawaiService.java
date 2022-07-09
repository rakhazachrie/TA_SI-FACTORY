package apap.ta.sifactory.service;

import apap.ta.sifactory.model.PegawaiModel;
import apap.ta.sifactory.model.RoleModel;

import java.util.List;

public interface PegawaiService {
    PegawaiModel addPegawai(PegawaiModel pegawai);

    PegawaiModel updatePegawai(PegawaiModel pegawai);

    public String encrypt(String password);

    PegawaiModel findByUsername(String username);

    List<PegawaiModel> getPegawaiListByRole(RoleModel role);

    List<PegawaiModel> getPegawaiList();
}
