package uy.com.emptyloop.pilatesaas.tenant;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tenant create(@Valid @RequestBody TenantDTO dto) {
        return tenantService.create(dto);
    }

    @GetMapping
    public List<Tenant> findAll() {
        return tenantService.findAll();
    }

    @GetMapping("/{id}")
    public Tenant findById(@PathVariable Long id){
        return tenantService.findById(id);
    }
}