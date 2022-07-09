package apap.ta.sifactory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import apap.ta.sifactory.repository.RoleDb;
import apap.ta.sifactory.model.RoleModel;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDb roleDb;

    @Override
    public List<RoleModel> findAll() {
        return roleDb.findAll();
    }

    @Override
    public RoleModel findByNamaRole(String namaRole) { return roleDb.findByNamaRole(namaRole); }
}