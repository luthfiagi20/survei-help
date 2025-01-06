package id.co.app.application.repository;

import id.co.app.application.domain.table.MsMenu;
import id.co.app.application.domain.table.MsRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MsRoleMenuRepo extends JpaRepository<MsRoleMenu, Long> {
    @Query(value = RepoQuery.GET_MENU_ROLES)
    List<MsMenu> getMenusByRole(List<String> roles);
}



