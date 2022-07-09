package apap.ta.sifactory.service;

import apap.ta.sifactory.model.PegawaiModel;
import apap.ta.sifactory.model.RoleModel;
import apap.ta.sifactory.repository.PegawaiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {
    @Autowired
    private PegawaiDb pegawaiDb;

    @Override
    public PegawaiModel addPegawai(PegawaiModel pegawai) {
        if (pegawaiDb.findByUsername(pegawai.getUsername()) != null) {
            return null;
        }

        String pass = encrypt(pegawai.getPassword());
        pegawai.setPassword(pass);
        return pegawaiDb.save(pegawai);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public PegawaiModel findByUsername(String username) {
        return pegawaiDb.findByUsername(username);
    }

    @Override
    public PegawaiModel updatePegawai(PegawaiModel pegawai) {
        // TODO Auto-generated method stub
        return pegawaiDb.save(pegawai);
    }

    @Override
    public List<PegawaiModel> getPegawaiListByRole(RoleModel role){
        return pegawaiDb.findAllByRole(role);
    }

    @Override
    public List<PegawaiModel> getPegawaiList() {
        return pegawaiDb.findAll();
    }
}
