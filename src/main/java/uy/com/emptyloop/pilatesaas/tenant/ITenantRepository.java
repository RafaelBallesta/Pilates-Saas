package uy.com.emptyloop.pilatesaas.tenant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITenantRepository extends JpaRepository<Tenant, Long> {

    boolean existsBySubdomain(String subdomain);

}
