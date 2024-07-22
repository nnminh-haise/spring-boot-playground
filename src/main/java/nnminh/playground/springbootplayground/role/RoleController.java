package nnminh.playground.springbootplayground.role;

import lombok.RequiredArgsConstructor;
import nnminh.playground.springbootplayground.role.dto.CreateRoleDto;
import nnminh.playground.springbootplayground.role.dto.UpdateRoleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleRepository roleRepository;

    @PostMapping("create")
    public ResponseEntity<String> create(@RequestBody CreateRoleDto payload) {
        roleRepository.save(Role.builder()
                .name(payload.getName())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build()
        );
        return ResponseEntity.ok("Role created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> read(@PathVariable Integer id) {
        Optional<Role> role = this.roleRepository.findById(id);

        return role
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("update")
    public ResponseEntity<String> update(@RequestBody UpdateRoleDto payload) {
        Optional<Role> updatingRole = this.roleRepository.findById(payload.getId());
        if (updatingRole.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Role updatedRole = updatingRole.get();
        updatedRole.setName(payload.getName());
        updatedRole.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        this.roleRepository.save(updatedRole);
        return ResponseEntity.ok("Role updated");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        Optional<Role> role = this.roleRepository.findById(id);
        if (role.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Role deletedRole = role.get();
        deletedRole.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        deletedRole.setDeletedAt(Timestamp.valueOf(LocalDateTime.now()));
        this.roleRepository.save(deletedRole);
        return ResponseEntity.ok("Role deleted");
    }
}
