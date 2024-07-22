package nnminh.playground.springbootplayground.role;

import lombok.RequiredArgsConstructor;
import nnminh.playground.springbootplayground.role.dto.CreateRoleDto;
import nnminh.playground.springbootplayground.role.dto.UpdateRoleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("create")
    public ResponseEntity<String> create(@RequestBody CreateRoleDto payload) {
        boolean isCreated = this.roleService.createRole(payload);
        if (!isCreated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Role created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> read(@PathVariable Integer id) {
        Optional<Role> role = this.roleService.findById(id);

        return role
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("update")
    public ResponseEntity<String> update(@RequestBody UpdateRoleDto payload) {
        boolean isUpdated = this.roleService.updateRole(payload);
        if (!isUpdated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Role updated");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        boolean isDeleted = this.roleService.deleteRole(id);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Role deleted");
    }
}
