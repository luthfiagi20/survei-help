package id.co.app.application.config;

import id.co.app.application.domain.model.Konstanta;
import id.co.app.application.domain.model.UserAccount;
import id.co.app.application.domain.table.MsMenu;
import id.co.app.application.domain.table.MsUser;
import id.co.app.application.repository.MsRoleMenuRepo;
import id.co.app.application.repository.MsUserRepo;
import id.co.app.application.repository.MsUserRoleRepo;
import id.co.app.application.service.CommonSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ripato on 21/02/2020.
 */
@Component
public class CustomAuthProvider implements AuthenticationProvider {

    public static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthProvider.class);

    @Autowired
    MsUserRepo msUserRepo;

    @Autowired
    MsUserRoleRepo msUserRoleRepo;

    @Autowired
    MsRoleMenuRepo msRoleMenuRepo;

    @Autowired
    CommonSvc commonSvc;

    @Value("${server.contextPath}")
    private String serverContext;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        MsUser msUser = msUserRepo.findByIdMsUser(username);
        UserAccount user = new UserAccount();
        user.setUsername(username);
        user.setDisplayName(commonSvc.getTampilanUserSidebar(msUser.getNama()));
        user.setNama(msUser.getNama());
        user.setFoto(serverContext + "assets/img/av-guru-l.jpg");
        user.setRolePengguna(msUser.getRole());

        List<String> roles = msUserRoleRepo.getRolesByIdUser(username);
        List<SimpleGrantedAuthority> grantList = new ArrayList<SimpleGrantedAuthority>();
        for(String r : roles){
            grantList.add(new SimpleGrantedAuthority(r));
        }
        user.setRoles(roles);
        List<MsMenu> menus = msRoleMenuRepo.getMenusByRole(roles);
        user.setMenus(menus);

        //write to log MsUser
        String ip = commonSvc.getClientIpAddressIfServletRequestExist();
        msUser.setLastLoginIp(ip);
        msUser.setLastLoginTime(new Date());
        msUserRepo.save(msUser);

        return new UsernamePasswordAuthenticationToken(user, password, grantList);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

//    private List<MsMenu> setAdminMenu(){
//        List<MsMenu> menus =  new ArrayList<>();
//        MsMenu menu1 = new MsMenu();
//        menu1.setIdMsMenu(new Long(0));
//        menu1.setIdParent(new Long(0));
//        menu1.setNama("Admin PIC");
//        menu1.setIcon("assignment_ind");
//        menu1.setLink("admin/user");
//        menu1.setFgAktif("1");
//        menu1.setHaveChild("0");
//        menu1.setUrutanPerLevel(new Long(1));
//        menus.add(menu1);
//        return menus;
//    }
}
