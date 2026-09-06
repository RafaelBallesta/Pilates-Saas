package uy.com.emptyloop.pilatesaas.tenant;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {

    private final ITenantRepository tenantRepository;

    public TenantService(ITenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public Tenant create(TenantDTO dto) {
        if (tenantRepository.existsBySubdomain(dto.getSubdomain())) {
            throw new IllegalArgumentException(
                    "El subdominio ya está en uso"
            );
        }

        Tenant tenant = new Tenant();
        tenant.setName(dto.getName());
        tenant.setSubdomain(dto.getSubdomain());

        return tenantRepository.save(tenant);
    }

    public List<Tenant> findAll() {
        return tenantRepository.findAll();
    }

    public Tenant findById(Long id) {
        return tenantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Tenant no encontrado"
                ));
    }

}