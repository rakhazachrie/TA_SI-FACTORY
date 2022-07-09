package apap.ta.sifactory.security;

import apap.ta.sifactory.model.PegawaiModel;
import apap.ta.sifactory.repository.PegawaiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PegawaiDb pegawaiDb;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PegawaiModel pegawai = pegawaiDb.findByUsername(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority(pegawai.getRole().getNamaRole()));
        return new User(pegawai.getUsername(), pegawai.getPassword(), grantedAuthorities);
    }
}
