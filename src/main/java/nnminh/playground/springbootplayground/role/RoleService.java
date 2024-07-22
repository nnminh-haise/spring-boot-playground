package nnminh.playground.springbootplayground.role;

import lombok.RequiredArgsConstructor;
import nnminh.playground.springbootplayground.role.dto.CreateRoleDto;
import nnminh.playground.springbootplayground.role.dto.UpdateRoleDto;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<Role> findById(Integer id) {
        return this.roleRepository.findById(id);
    }

    public Optional<Role> findByName(String name) {
        return this.roleRepository.findByName(name);
    }

    public Boolean createRole(CreateRoleDto payload) {
        Optional<Role> existingRole = this.findByName(payload.getName());
        if (existingRole.isPresent()) {
            return false;
        }

        this.roleRepository.save(Role.builder()
                .name(payload.getName())
                .build()
        );
        return true;
    }

    public Boolean updateRole(UpdateRoleDto payload) {
        Optional<Role> existingRole = this.findById(payload.getId());
        if (existingRole.isEmpty()) {
            return false;
        }

        Role updatedRole = existingRole.get();
        updatedRole.setName(payload.getName());
        this.roleRepository.save(updatedRole);
        return true;
    }

    public Boolean deleteRole(Integer id) {
        Optional<Role> existingRole = this.findById(id);
        if (existingRole.isEmpty()) {
            return false;
        }

        Role deletedRole = existingRole.get();
        deletedRole.setDeletedAt(Timestamp.valueOf(LocalDateTime.now()));
        this.roleRepository.save(deletedRole);
        return true;
    }
}
